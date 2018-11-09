package com.mycompany.services;

import com.mycompany.dto.SystemDTO;

import java.util.List;
import java.util.Optional;

/**
 * Interface for {@link com.mycompany.domain.System} Service
 */
public interface SystemService {

    /**
     * Save the @param in database and return {@link SystemDTO} with saved data.
     * @param systemDTO Object to save in database
     * @return Saved {@link SystemDTO}
     */
    SystemDTO save(SystemDTO systemDTO);

    /**
     * Return List of {@link SystemDTO} with all objects from database
     * @return List of {@link SystemDTO}
     */
    List<SystemDTO> getAll();

    /**
     * Return {@link SystemDTO} with object from database with id from @param
     * @param id id of searching {@link SystemDTO}
     * @return SystemDTO founded in database
     */
    Optional<SystemDTO> findById(Long id);

}
