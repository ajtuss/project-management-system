package com.mycompany.constraints;

import com.mycompany.domain.Agreement;
import com.mycompany.dto.AgreementDTO;
import com.mycompany.repositories.AgreementRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueOrderNumberValidator implements ConstraintValidator<UniqueOrderNumber, AgreementDTO> {

    @Autowired
    private AgreementRepository agreementRepository;


    @Override
    public void initialize(UniqueOrderNumber constraintAnnotation) {

    }

    @Override
    public boolean isValid(AgreementDTO value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        try {
            Long id = value.getId();
            String orderNumber = value.getOrderNumber();

            Agreement found = agreementRepository.findByOrderNumber(orderNumber);
            if (found == null || found.getId().equals(id)) {
                return true;
            }
        } catch (Exception ignore) {
        }
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
               .addPropertyNode("orderNumber").addConstraintViolation();
        return false;
    }

}
