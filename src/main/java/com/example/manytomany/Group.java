package com.example.manytomany;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "corporate_group")
class Group {
    @Id
    private Long groupId;

    @Column
    private String name;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "grpup_companies")
    @JoinColumn(name = "group_id")
    private Set<Company> companies = new HashSet<>();

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(Set<Company> companies) {
        this.companies = companies;
    }
}
