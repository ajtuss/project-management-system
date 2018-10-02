package com.mycompany.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "systems")
@Data
public class System {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(length = 25, unique = true)
    private String name;

    private String description;

    @Column(name = "tech_description")
    private String techDescription;

    @NotNull
    @Column(length = 25)
    private String owner;

    @OneToMany(mappedBy = "system")
    private List<Agreement> agreements;
}

