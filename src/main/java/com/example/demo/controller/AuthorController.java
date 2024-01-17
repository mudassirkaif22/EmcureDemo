package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Author;
import com.example.demo.repo.AuthorRep;
import com.example.demo.service.AuthorService;

@RestController
@RequestMapping("/author")
public class AuthorController {
	
	@Autowired
	private AuthorRep repo;
	
	@Autowired
	private AuthorService service;
	
	@PostMapping("/addauthor")
	Author addAuthor(@RequestBody Author author) {
		return service.addAuthor(author);
	}
	

}
