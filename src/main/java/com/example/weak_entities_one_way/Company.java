package com.example.weak_entities_one_way;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "corporate_company")
@Getter
@Setter
@NoArgsConstructor
class Company {
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
