package com.example.manytomany;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "corporate_group")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class Group {
    @Id
    private Long groupId;

    @Column
    private String name;

    @ManyToMany
    @JoinTable(name = "corporate_group_companies",
            inverseJoinColumns = @JoinColumn(name = "group_id"),
            joinColumns = @JoinColumn(name = "company_id"))
    private Set<Company> companies = new HashSet<>();
}
