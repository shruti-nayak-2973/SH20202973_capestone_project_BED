package com.capstone.shruti.example.repository;

import java.util.*;

import com.capstone.shruti.example.entities.Customer;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepo extends CrudRepository<Customer, Integer>{
	@Query(value="select * from Customer s where s.name=?1")
	List<Customer> findCustomerByName(String name);
}