package com.jpaMapping.jpaMapping.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "laptop_table")
public class Laptop {
    @Id
    private int laptop_id;
    private String laptop_name;

    @OneToOne
    private Student student;
}
