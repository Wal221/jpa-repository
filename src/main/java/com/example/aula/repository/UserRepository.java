package com.example.aula.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.aula.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	@Query("SELECT obj FROM User obj WHERE obj.salary >= :minSalary AND obj.salary <= :maxSalary")
	Page<User> searchBySalary(Double minSalary, Double maxSalary, Pageable pageable);

	
	@Query("SELECT obj FROM User obj WHERE obj.name LIKE :name%")
	Page<User> searchByName(String name, Pageable pageable);
    
	
	/*
	 *Todos os atributos mapeados na entidade podem ser usados na consulta
	 *A algumas consultas que talvez não precise do @Query ja que dependendo do 
	 *nome do seu metodo o Spring JPA vai analisar o nome do seu metodo e gera a sua consulta 
	 */
	Page<User> findBySalaryBetween(Double minSalary, Double maxSalary, Pageable pageable);
	
	//As essa consulta e a mesma acima searchByName() oque muda e que em uma a implementação foi realizada a mão
	//Ja essa segunda o propio Spring data Jpa forneceu algumas consultas de acordo com o nome do metodo
	Page<User> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
