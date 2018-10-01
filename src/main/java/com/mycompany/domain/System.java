package com.mycompany.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "systems")
@Data
public class System {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 25)
    private String name;

    private String description;

    @Column(name = "tech_description")
    private String techDescription;

    @Column(length = 25)
    private String owner;

    @OneToMany(mappedBy = "system")
    private List<Agreement> agreements;
}

