package com.mycompany.services;

import com.mycompany.domain.AmountType;
import com.mycompany.domain.ImportMessage;
import com.mycompany.domain.Period;
import com.mycompany.dto.AgreementDTO;
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

@Service
@PropertySource("classpath:import.properties")
public class ImportFileServiceImpl implements ImportFileService {

    private final Environment env;
    private final Validator validator;
    private final AgreementService agreementService;

    @Autowired
    public ImportFileServiceImpl(AgreementService agreementService, Validator validator, Environment env) {
        this.agreementService = agreementService;
        this.validator = validator;
        this.env = env;
    }

    @Override
    public ImportMessage importSpreadsheet(MultipartFile multipartFile) {
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
        } catch (IOException e) {
            result.setMessage("Błąd pliku. " + e.getMessage());
        }
        return result;
    }

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

    private Map<String, Integer> getHeaderMapFromRow(Row row) throws InvalidPropertiesFormatException {
        Map<String, Integer> result = new HashMap<>();
        List<String> headersList = Arrays.asList(env.getProperty("system"), env.getProperty("orderNumber"),
                env.getProperty("fromDate"), env.getProperty("toDate"), env.getProperty("amount"),
                env.getProperty("amountType"), env.getProperty("amountPeriod"), env.getProperty("active"));
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
                throw new InvalidPropertiesFormatException("Cant find column with name [" + s + "]");
            } else {
                result.put(s, columnIndex);
            }
        }
        return result;
    }

    private AgreementDTO getAgreementFromRow(Row row, Map<String, Integer> headerMap) {
        AgreementDTO agreementDTO = new AgreementDTO();

        try {
            String systemName = getStringValueFromCell(row.getCell(headerMap.get(env.getProperty("system"))));
            agreementDTO.setSystemName(systemName);

            agreementDTO.setOrderNumber(getStringValueFromCell(row.getCell(headerMap.get(env.getProperty("orderNumber")))));

            LocalDate startDate = row.getCell(headerMap.get(env.getProperty("fromDate")))
                                     .getDateCellValue()
                                     .toInstant()
                                     .atZone(ZoneId.systemDefault())
                                     .toLocalDate();
            agreementDTO.setStartDate(startDate);

            LocalDate endDate = row.getCell(headerMap.get(env.getProperty("toDate")))
                                   .getDateCellValue()
                                   .toInstant()
                                   .atZone(ZoneId.systemDefault())
                                   .toLocalDate();
            agreementDTO.setEndDate(endDate);

            BigDecimal amount = new BigDecimal(getStringValueFromCell(row.getCell(headerMap.get(env.getProperty("amount")))));
            agreementDTO.setAmount(amount);

            String amountTypeString = getStringValueFromCell(row.getCell(headerMap.get(env.getProperty("amountType"))));
            agreementDTO.setAmountType(getAmountFromString(amountTypeString));

            String amountPeriodString = getStringValueFromCell(row.getCell(headerMap.get(env.getProperty("amountPeriod"))));
            agreementDTO.setAmountPeriod(getAmountPeriodFromString(amountPeriodString));

            String activeString = getStringValueFromCell(row.getCell(headerMap.get(env.getProperty("active"))));
            agreementDTO.setActive(Boolean.valueOf(activeString));


        } catch (RuntimeException e) {
            return new AgreementDTO();
        }
        return agreementDTO;
    }

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

    private AmountType getAmountFromString(String amountString) {
        if (amountString.toLowerCase(Locale.ENGLISH).startsWith("net")) {
            return AmountType.NETTO;
        }
        if (amountString.toLowerCase(Locale.ENGLISH).startsWith("bru")) {
            return AmountType.BRUTTO;
        }
        return null;
    }


}
