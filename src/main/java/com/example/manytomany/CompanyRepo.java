package com.example.manytomany;

import org.springframework.data.repository.CrudRepository;

interface CompanyRepo extends CrudRepository<Company, Long> {

}
