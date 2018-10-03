package com.mycompany.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ImportMessage {

    int done;
    int fail;
    String message;
}
