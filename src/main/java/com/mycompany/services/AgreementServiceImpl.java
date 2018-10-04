package com.mycompany.services;

import com.mycompany.domain.Agreement;
import com.mycompany.domain.System;
import com.mycompany.dto.AgreementDTO;
import com.mycompany.repositories.AgreementRepository;
import com.mycompany.repositories.SystemRepository;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class AgreementServiceImpl implements AgreementService {

    private static final Logger logger = Logger.getLogger(AgreementServiceImpl.class);
    private final AgreementRepository agreementRepository;
    private final SystemRepository systemRepository;
    private final ModelMapper mapper;

    @Autowired
    public AgreementServiceImpl(AgreementRepository agreementRepository, ModelMapper mapper, SystemRepository systemRepository) {
        this.agreementRepository = agreementRepository;
        this.mapper = mapper;
        this.systemRepository = systemRepository;
    }


    @Override
    public AgreementDTO save(AgreementDTO agreementDTO) {
        logger.info("Call save()");
        Agreement agreement = mapper.map(agreementDTO, Agreement.class);
        Optional<System> system;
        if (agreementDTO.getSystemName() != null) {
            system = systemRepository.findByNameIgnoreCase(agreementDTO.getSystemName());
        } else {
            system = systemRepository.findById(agreementDTO.getSystemId());
        }
        agreement.setSystem(system.orElse(null));
        Agreement save = agreementRepository.save(agreement);
        return mapper.map(save, AgreementDTO.class);
    }

    @Override
    public List<AgreementDTO> save(List<AgreementDTO> listAgreements) {
        logger.info("Call save()");
        return listAgreements.stream().map(this::save)
                             .collect(Collectors.toList());
    }

    @Override
    public List<AgreementDTO> getAll() {
        logger.info("Call getAll()");
        List<Agreement> agreements = agreementRepository.findAll();
        return agreements.stream()
                         .map(agreement -> mapper.map(agreement, AgreementDTO.class))
                         .collect(Collectors.toList());
    }

    @Override
    public List<AgreementDTO> getAllActive() {
        logger.info("Call getAllActive()");

        List<Agreement> agreements = agreementRepository.findAllActive();
        return agreements.stream()
                         .map(agreement -> mapper.map(agreement, AgreementDTO.class))
                         .collect(Collectors.toList());
    }

    @Override
    public AgreementDTO findById(Long id) {
        logger.info("Call findById()");

        Agreement agreement = agreementRepository.findById(id).orElse(new Agreement());
        return mapper.map(agreement, AgreementDTO.class);
    }

    @Override
    public AgreementDTO update(AgreementDTO agreement) {
        logger.info("Call update()");

        return save(agreement);
    }

}
