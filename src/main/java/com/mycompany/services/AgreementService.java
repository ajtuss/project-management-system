package com.mycompany.services;

import com.mycompany.dto.AgreementDTO;

import java.util.List;

public interface AgreementService {

    AgreementDTO save(AgreementDTO agreementDTO);

    List<AgreementDTO> getAll();

    AgreementDTO findById(Long id);

    AgreementDTO update(AgreementDTO agreement);

    List<AgreementDTO> getAllActive();

    List<AgreementDTO> save(List<AgreementDTO> listAgreements);
}
