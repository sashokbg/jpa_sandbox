package com.example.manytomany;

import org.springframework.data.repository.CrudRepository;

interface GroupRepo extends CrudRepository<Group, Long> {
}
