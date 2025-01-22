package com.wesclic.freshlydropped.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "m_country")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "country_name")
    private String countryName;
}
