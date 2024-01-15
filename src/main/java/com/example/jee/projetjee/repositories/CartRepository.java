package com.example.jee.projetjee.repositories;

import com.example.jee.projetjee.data.Cart;
import com.example.jee.projetjee.data.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface CartRepository extends CrudRepository<Cart, Long> {
    Set<Cart> findByUser(User user);
}
