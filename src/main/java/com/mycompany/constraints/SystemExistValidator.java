package com.mycompany.constraints;

import com.mycompany.dto.AgreementDTO;
import com.mycompany.repositories.SystemRepository;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.InvocationTargetException;

public class SystemExistValidator implements ConstraintValidator<SystemExist, AgreementDTO> {

    @Autowired
    private SystemRepository systemRepository;

    private String systemId;
    private String systemName;

    @Override
    public void initialize(SystemExist systemExist) {
        systemId = systemExist.systemId();
        systemName = systemExist.systemName();
    }

    @Override
    public boolean isValid(AgreementDTO agreement, ConstraintValidatorContext context) {
        try {
            Long idValue = (Long) PropertyUtils.getProperty(agreement, systemId);
            String nameValue = (String) PropertyUtils.getProperty(agreement, systemName);
            if (idValue != null) {
                return systemRepository.existsById(idValue);
            }
            if (nameValue != null) {
                return systemRepository.existsByName(nameValue);
            }

        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | NullPointerException ignore) {
        }
        return false;
    }
}
