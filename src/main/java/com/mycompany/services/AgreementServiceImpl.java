package com.mycompany.services;

import com.mycompany.domain.Agreement;
import com.mycompany.dto.AgreementDTO;
import com.mycompany.repositories.AgreementRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AgreementServiceImpl implements AgreementService {

    private final AgreementRepository agreementRepository;

    private final ModelMapper mapper;

    @Autowired
    public AgreementServiceImpl(AgreementRepository agreementRepository, ModelMapper mapper) {
        this.agreementRepository = agreementRepository;
        this.mapper = mapper;
    }


    @Override
    public AgreementDTO save(AgreementDTO agreementDTO) {
        Agreement agreement = mapper.map(agreementDTO, Agreement.class);
        Agreement save = agreementRepository.save(agreement);
        return mapper.map(save, AgreementDTO.class);
    }

    @Override
    public List<AgreementDTO> getAll() {
        List<Agreement> agreements = agreementRepository.findAll();
        List<AgreementDTO> result = agreements.stream()
                                                     .map(agreement -> mapper.map(agreement, AgreementDTO.class))
                                                     .collect(Collectors.toList());
        return result;
    }

    @Override
    public AgreementDTO findById(Long id) {
        Agreement agreement = agreementRepository.findById(id).orElse(new Agreement());
        return mapper.map(agreement, AgreementDTO.class);
    }
}
