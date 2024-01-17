package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Author;

public interface AuthorRep extends JpaRepository<Author, Integer>{

}
