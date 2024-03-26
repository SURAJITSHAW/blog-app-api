package com.shaw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.shaw.entity.User;
import java.util.List;


public interface UserRepo extends JpaRepository<User, Integer>{
	User findByEmail(String email);
}
