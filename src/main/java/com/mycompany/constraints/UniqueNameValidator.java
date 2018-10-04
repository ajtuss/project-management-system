package com.mycompany.constraints;

import com.mycompany.domain.System;
import com.mycompany.dto.SystemDTO;
import com.mycompany.repositories.SystemRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueNameValidator implements ConstraintValidator<UniqueName, SystemDTO> {

    @Autowired
    private SystemRepository systemRepository;

    @Override
    public void initialize(UniqueName constraintAnnotation) {

    }

    @Override
    public boolean isValid(SystemDTO system, ConstraintValidatorContext context) {
        if (system == null) {
            return true;
        }
        Long id = system.getId();
        String name = system.getName();

        System found = systemRepository.findByNameIgnoreCase(name).orElse(null);
        if (found == null || found.getId().equals(id)) {
            return true;
        }
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
               .addPropertyNode("name").addConstraintViolation();
        return false;
    }
}
