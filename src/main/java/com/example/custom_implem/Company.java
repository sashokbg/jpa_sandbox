package com.example.custom_implem;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

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

}
