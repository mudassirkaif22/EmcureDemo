package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Book;
import com.example.demo.repo.Bookrep;

@Service
public class BookService {

	@Autowired
	private Bookrep repo;
	
	public Book addBook(Book book) {
		return repo.save(book);
		
		
	}
}
