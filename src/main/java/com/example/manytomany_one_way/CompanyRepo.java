package com.example.manytomany_one_way;

import org.springframework.data.repository.CrudRepository;

interface CompanyRepo extends CrudRepository<Company, Long> {

}
