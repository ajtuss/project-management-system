package com.mycompany.dto;

import com.mycompany.constraints.UniqueName;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
@UniqueName
public class SystemDTO {

    private Long id;

    @Size(min = 3, max = 25)
    private String name;

    private String description;

    private String techDescription;

    @Size(min = 3, max = 25)
    private String owner;

    public void setName(String name) {
        this.name = name.trim().toUpperCase();
    }


    public void setDescription(String description) {
        this.description = description.trim();
    }

    public void setTechDescription(String techDescription) {
        this.techDescription = techDescription.trim();
    }

    public void setOwner(String owner) {
        this.owner = owner.trim();
    }
}
