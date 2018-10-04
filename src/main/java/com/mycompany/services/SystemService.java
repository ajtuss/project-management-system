package com.mycompany.services;

import com.mycompany.dto.SystemDTO;

import java.util.List;

public interface SystemService {

    SystemDTO save(SystemDTO systemDTO);

    List<SystemDTO> getAll();

    SystemDTO findById(Long id);

    SystemDTO update(SystemDTO systemDTO);
}
