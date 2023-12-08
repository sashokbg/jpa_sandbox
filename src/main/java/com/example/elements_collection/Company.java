package com.example.elements_collection;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "corporate_company")
@Data
@NoArgsConstructor
public class Company {

    @EmbeddedId
    private CompanyPk id;

    @Column
    private String name;

    @Column(name = "group_id")
    private Long groupId;

    public Company(Long id, String name) {
        this.id = new CompanyPk(id);
        this.name = name;
    }

    @ElementCollection
    List<CompanyDetail> companyDetails = new ArrayList<>();

}
