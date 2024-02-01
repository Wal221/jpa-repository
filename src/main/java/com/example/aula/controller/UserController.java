package com.example.aula.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.aula.entities.User;
import com.example.aula.repository.UserRepository;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private UserRepository repository;
	
	@GetMapping
	public ResponseEntity<List<User>> findAll() {
	    List<User> result = repository.findAll();
	    return ResponseEntity.ok(result);
	}

	/*
	 * Tenho um endpoint com dados paginados, pois imagine o seguinte
	 * se eu tenho um banco de dados com 5.000 usuarios, como vou devolver tudo isso
	 * em um tela, logo crio a paginação
	 */
	@GetMapping(value="/page")
	public ResponseEntity<Page<User>> findAll(Pageable page) {
	    Page<User> result = repository.findAll(page);
	    return ResponseEntity.ok(result);
	}
	
	                                        
	@GetMapping(value = "/search-salary")            //caso o valor minimo não seja informado o valor padrão e 0 o mesmo vale para o maxSalary
	public ResponseEntity<Page<User>> searchBySalary(@RequestParam(defaultValue = "0")
	 Double minSalary, @RequestParam(defaultValue = "1000000000000") 
	  Double maxSalary, Pageable pageable) {
	    Page<User> result = repository.findBySalaryBetween(minSalary, maxSalary, pageable);
	    return ResponseEntity.ok(result);
	}
	
	
	@GetMapping(value ="/search-name")
	public ResponseEntity<Page<User>> searchByName(@RequestParam(defaultValue = "")
	 String name, Pageable pageable) {
	    Page<User> result = repository.findByNameContainingIgnoreCase(name, pageable);
	    return ResponseEntity.ok(result);
	}



}
