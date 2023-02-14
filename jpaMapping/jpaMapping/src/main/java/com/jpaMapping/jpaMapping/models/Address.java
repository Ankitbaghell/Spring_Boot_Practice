package com.jpaMapping.jpaMapping.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Address {
    @Id
    private int address_id;
    private String city;

    @ManyToOne
    private Student student;
}
