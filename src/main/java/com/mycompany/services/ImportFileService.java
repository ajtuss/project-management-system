package com.mycompany.services;

import com.mycompany.domain.ImportMessage;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImportFileService {

    ImportMessage importSpreadsheet(MultipartFile multipartFile) throws IOException;
}
