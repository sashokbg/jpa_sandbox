package com.example.manytomany_one_way;

import org.springframework.data.repository.CrudRepository;

interface GroupRepo extends CrudRepository<Group, Long> {
}
