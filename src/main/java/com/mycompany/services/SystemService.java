package com.mycompany.services;

import com.mycompany.dto.SystemDTO;
import com.sun.istack.internal.NotNull;

import java.util.List;

public interface SystemService {

    SystemDTO save(SystemDTO systemDTO);

    List<SystemDTO> getAll();

    SystemDTO findById(@NotNull Long id);

}
