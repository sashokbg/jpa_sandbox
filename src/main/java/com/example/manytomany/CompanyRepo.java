package com.example.manytomany;

import org.springframework.data.repository.CrudRepository;

public interface CompanyRepo extends CrudRepository<Company, Long> {

}
