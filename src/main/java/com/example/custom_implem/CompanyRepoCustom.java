package com.example.custom_implem;


import org.springframework.data.repository.CrudRepository;

public interface CompanyRepoCustom {
    Company custom(String toto);
}
