package com.example.jee.projetjee.repositories;

import com.example.jee.projetjee.data.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    Optional<Category> findByName(String name);

    Optional<Category> findFirstByOrderByNameDesc();
}
