package com.whitelabellingSpringBoot.whitelabellingSpringBoot.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "whitelabel_styling")
public class WhitelabelStyling {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String css;

    @Lob
    @Column(columnDefinition = "BLOB")
    private byte[] logo;

    private String footer;

    private String header;

    @OneToOne
    private User user;
}
