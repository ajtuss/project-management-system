package com.mycompany.services;

import com.mycompany.dto.AgreementDTO;

import java.util.List;

public interface AgreementService {

    /**
     * Return AgreementDTO found in database with id
     * @param id
     * @return AgreementDTO
     */
    AgreementDTO findById(Long id);

    /**
     * Return all AgreementDTOs found in in database
     * @return List of AgreementDTOs
     */
    List<AgreementDTO> getAll();

    /**
     * Return all AgreementsDTOs witch their param active is true
     * @return List of AgreementsDTOs
     */
    List<AgreementDTO> getAllActive();

    /**
     * Save param agreementDTO in database and return AgreementDTO with filled fields.
     * @param agreementDTO
     * @return
     */
    AgreementDTO save(AgreementDTO agreementDTO);

    /**
     * Save List of AgreementDTOs in database and return List of AgreementDTO with filled fields.
     * @param listAgreements
     * @return List of AgreementDTO with filled fields
     */
    List<AgreementDTO> save(List<AgreementDTO> listAgreements);

}
