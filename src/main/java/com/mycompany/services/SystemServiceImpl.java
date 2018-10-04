package com.mycompany.services;

import com.mycompany.domain.System;
import com.mycompany.dto.SystemDTO;
import com.mycompany.repositories.SystemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SystemServiceImpl implements SystemService {

    private final SystemRepository systemRepository;
    private final ModelMapper mapper;

    @Autowired
    public SystemServiceImpl(SystemRepository systemRepository, ModelMapper mapper) {
        this.systemRepository = systemRepository;
        this.mapper = mapper;
    }

    @Override
    public SystemDTO save(SystemDTO systemDTO) {
        System system = mapper.map(systemDTO, System.class);
        System save = systemRepository.save(system);
        return mapper.map(save, SystemDTO.class);
    }

    @Override
    public List<SystemDTO> getAll() {
        List<System> systems = systemRepository.findAll();
        List<SystemDTO> result = systems.stream()
                                        .map(system -> mapper.map(system, SystemDTO.class))
                                        .collect(Collectors.toList());
        return result;
    }

    @Override
    public SystemDTO findById(Long id) {
        System system = systemRepository.findById(id).orElse(new System());
        return mapper.map(system, SystemDTO.class);
    }

    @Override
    public SystemDTO update(SystemDTO systemDTO) {
        System system = mapper.map(systemDTO, System.class);
        System save = systemRepository.save(system);
        return mapper.map(save, SystemDTO.class);
    }

}
