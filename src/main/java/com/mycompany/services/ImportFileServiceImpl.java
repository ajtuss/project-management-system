package com.mycompany.services;

import com.mycompany.domain.AmountType;
import com.mycompany.domain.ImportMessage;
import com.mycompany.domain.Period;
import com.mycompany.dto.AgreementDTO;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Validator;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Implementation of {@link ImportFileService} interface.
 *<p>
 * Class needed import.properties file in resources with name of headers converted file.
 */
@Service
@PropertySource("classpath:import.properties")
public class ImportFileServiceImpl implements ImportFileService {

    private static final Logger logger = Logger.getLogger(ImportFileServiceImpl.class);
    private final Environment env;
    private final Validator validator;
    private final AgreementService agreementService;

    @Autowired
    public ImportFileServiceImpl(AgreementService agreementService, Validator validator, Environment env) {
        this.agreementService = agreementService;
        this.validator = validator;
        this.env = env;
    }

    /**
     * Import received file from @{@link org.springframework.stereotype.Controller} and write data to database.
     * @param multipartFile received from @{@link org.springframework.stereotype.Controller}
     * @return Object with stats about imported rows.
     */
    @Override
    public ImportMessage importSpreadsheet(MultipartFile multipartFile) {
        logger.info("Call importSpreadsheet()");

        ImportMessage result = new ImportMessage();
        try {
            Workbook workbook = null;
            workbook = new XSSFWorkbook(multipartFile.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);

            List<AgreementDTO> listAgreements = getListAgreementsFromSheet(sheet);

            List<AgreementDTO> filteredAgreements = listAgreements.stream()
                                                                  .filter(agreementDTO -> validator.validate(agreementDTO)
                                                                                                   .isEmpty())
                                                                  .collect(Collectors.toList());

            List<AgreementDTO> savedAgreements = agreementService.save(filteredAgreements);

            result = new ImportMessage(savedAgreements.size(),
                    listAgreements.size() - savedAgreements.size(), null);
        } catch (IOException | RuntimeException e) {
            result.setMessage("Błąd pliku. " + e.getMessage());
        }
        return result;
    }

    /**
     * Method getting first row as header, and convert other rows to List of {@link AgreementDTO}
     * @param sheet of .xml document
     * @return List of {@link AgreementDTO} with data from sheet
     * @throws InvalidPropertiesFormatException delegate exception from getHeaderMapFromRow()
     */
    private List<AgreementDTO> getListAgreementsFromSheet(Sheet sheet) throws InvalidPropertiesFormatException {
        ArrayList<AgreementDTO> result = new ArrayList<>();
        Map<String, Integer> headerMap = null;
        Iterator<Row> iterator = sheet.iterator();
        if (iterator.hasNext()) {
            Row row = iterator.next();
            headerMap = getHeaderMapFromRow(row);
        }
        while (iterator.hasNext()) {
            Row row = iterator.next();
            AgreementDTO agreementDTO = getAgreementFromRow(row, headerMap);
            result.add(agreementDTO);
        }
        return result;
    }

    /**
     * Method scan row and searching column headers.
     * @param row with headers
     * @return Map with column name key and index of column.
     * @throws InvalidPropertiesFormatException if any column not found.
     */
    private Map<String, Integer> getHeaderMapFromRow(Row row) throws InvalidPropertiesFormatException {
        Map<String, Integer> result = new HashMap<>();
        List<String> headersList = Arrays.asList(env.getRequiredProperty("system"), env.getRequiredProperty("orderNumber"),
                env.getRequiredProperty("fromDate"), env.getRequiredProperty("toDate"), env.getRequiredProperty("amount"),
                env.getRequiredProperty("amountType"), env.getRequiredProperty("amountPeriod"), env.getRequiredProperty("active"));
        for (String s : headersList) {
            Integer columnIndex = null;
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                if (s.equals(cell.getStringCellValue())) {
                    columnIndex = cell.getColumnIndex();
                    cellIterator.remove();
                }
            }
            if (columnIndex == null) {
                throw new InvalidPropertiesFormatException("Nie można znaleźć kolumny o nazwie [" + s + "]");
            } else {
                result.put(s, columnIndex);
            }
        }
        return result;
    }

    /**
     * Method transfer @{@link Row} to @{@link AgreementDTO}.
     * @param row Data to convert
     * @param headerMap {@link Map} with column name and column number
     * @return AgreementDTO with converted data. If column contains incorrect data, return empty Object
     */
    private AgreementDTO getAgreementFromRow(Row row, Map<String, Integer> headerMap) {
        AgreementDTO agreementDTO = new AgreementDTO();

        try {
            String systemName = getStringValueFromCell(row.getCell(headerMap.get(env.getRequiredProperty("system"))));
            agreementDTO.setSystemName(systemName);

            agreementDTO.setOrderNumber(getStringValueFromCell(row.getCell(headerMap.get(env.getRequiredProperty("orderNumber")))));

            LocalDate startDate = row.getCell(headerMap.get(env.getRequiredProperty("fromDate")))
                                     .getDateCellValue()
                                     .toInstant()
                                     .atZone(ZoneId.systemDefault())
                                     .toLocalDate();
            agreementDTO.setStartDate(startDate);

            LocalDate endDate = row.getCell(headerMap.get(env.getRequiredProperty("toDate")))
                                   .getDateCellValue()
                                   .toInstant()
                                   .atZone(ZoneId.systemDefault())
                                   .toLocalDate();
            agreementDTO.setEndDate(endDate);

            BigDecimal amount = new BigDecimal(getStringValueFromCell(row.getCell(headerMap.get(env.getRequiredProperty("amount")))));
            agreementDTO.setAmount(amount);

            String amountTypeString = getStringValueFromCell(row.getCell(headerMap.get(env.getRequiredProperty("amountType"))));
            agreementDTO.setAmountType(getAmountTypeFromString(amountTypeString));

            String amountPeriodString = getStringValueFromCell(row.getCell(headerMap.get(env.getRequiredProperty("amountPeriod"))));
            agreementDTO.setAmountPeriod(getAmountPeriodFromString(amountPeriodString));

            String activeString = getStringValueFromCell(row.getCell(headerMap.get(env.getRequiredProperty("active"))));
            agreementDTO.setActive(Boolean.valueOf(activeString));


        } catch (RuntimeException e) {
            return new AgreementDTO();
        }
        return agreementDTO;
    }

    /**
     * Convert @{@link Cell} to @{@link String} value recognizing type of cell.
     * @param cell cell with data to convert.
     * @return String with value from cell
     */
    private String getStringValueFromCell(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            default:
                return "";
        }
    }

    /**
     * Transfer string with Amount Period to {@link Period} enum.
     * @param amountPeriodString {@link String} with amount period to convert
     * @return Period
     */
    private Period getAmountPeriodFromString(String amountPeriodString) {
        if ("month".equalsIgnoreCase(amountPeriodString)) {
            return Period.MONTH;
        }
        if ("quarter".equalsIgnoreCase(amountPeriodString)) {
            return Period.QUARTER;
        }
        if ("year".equalsIgnoreCase(amountPeriodString)) {
            return Period.YEAR;
        }
        return null;
    }

    /**
     * Transfer string with Amount Type to {@link AmountType} enum.
     * @param amountString {@link String} with amount type to convert.
     * @return AmountType
     */
    private AmountType getAmountTypeFromString(String amountString) {
        if (amountString.toLowerCase(Locale.ENGLISH).startsWith("net")) {
            return AmountType.NETTO;
        }
        if (amountString.toLowerCase(Locale.ENGLISH).startsWith("bru")) {
            return AmountType.BRUTTO;
        }
        return null;
    }


}
