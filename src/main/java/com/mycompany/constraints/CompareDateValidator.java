package com.mycompany.constraints;

import org.apache.commons.beanutils.PropertyUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class CompareDateValidator implements ConstraintValidator<CompareDate, Object> {

    String startDateString;
    String endDateString;
    @Override
    public void initialize(CompareDate pastThen) {
        startDateString = pastThen.startDate();
        endDateString = pastThen.endDate();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        try {
            LocalDate startDate = (LocalDate) PropertyUtils.getProperty(value, startDateString);
            LocalDate endDate = (LocalDate) PropertyUtils.getProperty(value, endDateString);
            return endDate.isAfter(startDate);
        } catch (Exception ignore) {
        }
        return false;
    }
}
