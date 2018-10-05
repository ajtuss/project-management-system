package com.mycompany.services;

import com.mycompany.domain.ImportMessage;
import org.springframework.web.multipart.MultipartFile;

public interface ImportFileService {

    /**
     * Method to import received .xml file to database.
     * @param multipartFile received from @{@link org.springframework.stereotype.Controller}
     * @return Object with stats about imported data.
     */
    ImportMessage importSpreadsheet(MultipartFile multipartFile);
}
