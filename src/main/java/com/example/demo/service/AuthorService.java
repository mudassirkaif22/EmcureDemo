package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Author;
import com.example.demo.repo.AuthorRep;

@Service
public class AuthorService {
	
	@Autowired
	private AuthorRep repo;
	
	public Author addAuthor(Author author) {
		return repo.save(author);
	}

}
