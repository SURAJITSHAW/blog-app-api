package com.shaw.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shaw.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
