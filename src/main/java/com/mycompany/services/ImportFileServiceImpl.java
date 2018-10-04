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
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Validator;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImportFileServiceImpl implements ImportFileService {

    private final Validator validator;
    private final AgreementService agreementService;

    @Autowired
    public ImportFileServiceImpl(AgreementService agreementService, Validator validator) {
        this.agreementService = agreementService;
        this.validator = validator;
    }

    @Override
    public ImportMessage importSpreadsheet(@NotNull MultipartFile multipartFile) throws Exception {

        Workbook workbook = new XSSFWorkbook(multipartFile.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);

        List<AgreementDTO> listAgreements = getListAgreementsFromSheet(sheet);

        List<AgreementDTO> filteredAgreements = listAgreements.stream()
                                                              .filter(agreementDTO -> validator.validate(agreementDTO)
                                                                                               .isEmpty())
                                                              .collect(Collectors.toList());

        List<AgreementDTO> savedAgreements = agreementService.save(filteredAgreements);

        return new ImportMessage(savedAgreements.size(),
                listAgreements.size() - savedAgreements.size(), null);
    }

    private List<AgreementDTO> getListAgreementsFromSheet(Sheet sheet) {
        ArrayList<AgreementDTO> result = new ArrayList<>();
        Iterator<Row> iterator = sheet.iterator();
        if (iterator.hasNext()) {
            iterator.next();
        }
        while (iterator.hasNext()) {
            Row row = iterator.next();
            AgreementDTO agreementDTO = getAgreementFromRow(row);
            result.add(agreementDTO);
        }
        return result;
    }

    private AgreementDTO getAgreementFromRow(Row row) {
        AgreementDTO agreementDTO = new AgreementDTO();

        try {
            Long systemId;

            String systemName = getStringValueFromCell(row.getCell(0));
            agreementDTO.setSystemName(systemName);

            agreementDTO.setOrderNumber(getStringValueFromCell(row.getCell(2)));

            LocalDate startDate = row.getCell(3)
                                     .getDateCellValue()
                                     .toInstant()
                                     .atZone(ZoneId.systemDefault())
                                     .toLocalDate();
            agreementDTO.setStartDate(startDate);

            LocalDate endDate = row.getCell(4)
                                   .getDateCellValue()
                                   .toInstant()
                                   .atZone(ZoneId.systemDefault())
                                   .toLocalDate();
            agreementDTO.setEndDate(endDate);

            BigDecimal amount = new BigDecimal(getStringValueFromCell(row.getCell(5)));
            agreementDTO.setAmount(amount);

            String amountTypeString = getStringValueFromCell(row.getCell(6));
            agreementDTO.setAmountType(getAmountFromString(amountTypeString));

            String amountPeriodString = getStringValueFromCell(row.getCell(7));
            agreementDTO.setAmountPeriod(getAmountPeriodFromString(amountPeriodString));

            String activeString = getStringValueFromCell(row.getCell(9));
            agreementDTO.setActive(new Boolean(activeString));


        } catch (Exception ignore) {
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
        if (amountString.toLowerCase().startsWith("net")) {
            return AmountType.NETTO;
        }
        if (amountString.toLowerCase().startsWith("bru")) {
            return AmountType.BRUTTO;
        }
        return null;
    }


}
