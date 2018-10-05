package com.mycompany.services;

import com.mycompany.domain.System;
import com.mycompany.dto.SystemDTO;
import com.mycompany.repositories.SystemRepository;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of {@link SystemService}
 */
@Service
@Transactional
public class SystemServiceImpl implements SystemService {

    private static final Logger logger = Logger.getLogger(SystemServiceImpl.class);
    private final SystemRepository systemRepository;
    private final ModelMapper mapper;

    @Autowired
    public SystemServiceImpl(SystemRepository systemRepository, ModelMapper mapper) {
        this.systemRepository = systemRepository;
        this.mapper = mapper;
    }

    /**
     * Save the @param in database and return {@link SystemDTO} with saved data.
      * @param systemDTO {@link SystemDTO} to save in database. Must be validated before save.
     * @return SystemDTO saved in database.
     */
    @Override
    public SystemDTO save(SystemDTO systemDTO) {
        logger.info("Call save()");

        System system = mapper.map(systemDTO, System.class);
        System save = systemRepository.save(system);
        return mapper.map(save, SystemDTO.class);
    }

    /**
     * Method return List of {@link SystemDTO} with all entities from database
     * @return List of {@link SystemDTO} with all entities from database
     */
    @Override
    public List<SystemDTO> getAll() {
        logger.info("Call getAll()");

        List<System> systems = systemRepository.findAll();
        return systems.stream()
                      .map(system -> mapper.map(system, SystemDTO.class))
                      .collect(Collectors.toList());
    }

    /**
     * Method return {@link SystemDTO} with founded entity in database
     * @param id id of searching {@link SystemDTO}
     * @return SystemDTO with founded entity in database
     */
    @Override
    public SystemDTO findById(Long id) {
        logger.info("Call findById()");

        System system = systemRepository.findById(id).orElse(new System());
        return mapper.map(system, SystemDTO.class);
    }

}
