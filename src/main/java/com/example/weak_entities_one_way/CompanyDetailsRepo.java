package com.example.weak_entities_one_way;


import org.springframework.data.repository.CrudRepository;

interface CompanyDetailsRepo extends CrudRepository<CompanyDetail, Long> {
}
