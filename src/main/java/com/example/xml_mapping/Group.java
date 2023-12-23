package com.example.xml_mapping;

import java.util.List;

public class Group {
    private Long id;
    private List<Company> companies;

    public Group(Long id, List<Company> companies) {
        this.id = id;
        this.companies = companies;
    }

    protected Group() {
    }

    public Long getId() {
        return id;
    }

    public List<Company> getCompanies() {
        return companies;
    }
}
