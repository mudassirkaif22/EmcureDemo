package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {

//	Optional<User> findByName(String name);
	
	
	@Query("SELECT u FROM user u where u.name=?1")
    User findByName(String name);
}
