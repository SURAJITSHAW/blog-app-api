package com.shaw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.shaw.entity.User;

public interface UserRepo extends JpaRepository<User, Integer>{

}
