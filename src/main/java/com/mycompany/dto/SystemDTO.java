package com.mycompany.dto;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class SystemDTO {

    private Long id;

    @Size(min = 3, max = 25)
    private String name;

    private String description;

    private String techDescription;

    @Size(min = 3, max = 25)
    private String owner;

}
