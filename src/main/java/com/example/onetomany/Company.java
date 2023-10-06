package com.example.onetomany;

import jakarta.persistence.*;

@Entity
@Table(name = "corporate_company")
public class Company {
    @Id
    private Long id;

    @Column
    private String name;

    @Column(name = "group_id")
    private Long groupId;

    public Company(Long id) {
        this.id = id;
    }

    public Company() {
    }

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}