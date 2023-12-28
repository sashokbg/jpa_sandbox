package com.example.manytomany;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "corporate_company")
class Company {
    @Id
    private Long id;

    @Column
    private String name;

    @ManyToMany
    @JoinTable(name = "corporate_group_companies",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "company_id"))
    List<Group> groups;
}
