package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Book;
import com.example.demo.repo.Bookrep;
import com.example.demo.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {
	@Autowired
	private Bookrep repo;
	
	@Autowired
	private BookService service;
	
	@PostMapping("/addbook")
	Book addNewBook(@RequestBody Book book) {
		return service.addBook(book);		
	}

}
