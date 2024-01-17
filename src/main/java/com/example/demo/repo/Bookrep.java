package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Book;

public interface Bookrep extends JpaRepository<Book,Integer > {

}
