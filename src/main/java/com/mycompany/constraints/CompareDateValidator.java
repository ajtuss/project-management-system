package com.mycompany.constraints;

import org.apache.commons.beanutils.PropertyUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;

public class CompareDateValidator implements ConstraintValidator<CompareDate, Object> {

    private String startDateString;
    private String endDateString;

    @Override
    public void initialize(CompareDate pastThen) {
        startDateString = pastThen.startDate();
        endDateString = pastThen.endDate();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
               .addPropertyNode(endDateString).addConstraintViolation();

        try {
            LocalDate startDate = (LocalDate) PropertyUtils.getProperty(value, startDateString);
            LocalDate endDate = (LocalDate) PropertyUtils.getProperty(value, endDateString);
            return startDate.compareTo(endDate) <= 0;
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException  | NullPointerException e) {
            e.printStackTrace();
        }

        return false;
    }
}
