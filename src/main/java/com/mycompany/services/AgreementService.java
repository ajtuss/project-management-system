package com.mycompany.services;

import com.mycompany.dto.AgreementDTO;
import com.sun.istack.internal.NotNull;

import java.util.List;

public interface AgreementService {

    AgreementDTO save(AgreementDTO agreementDTO);

    List<AgreementDTO> getAll();

    AgreementDTO findById(@NotNull Long id);

}
