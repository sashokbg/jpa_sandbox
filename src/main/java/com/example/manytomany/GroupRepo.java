package com.example.manytomany;

import org.springframework.data.repository.CrudRepository;

public interface GroupRepo extends CrudRepository<Group, Long> {
}
