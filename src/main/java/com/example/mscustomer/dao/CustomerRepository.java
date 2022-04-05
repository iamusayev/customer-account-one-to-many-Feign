package com.example.mscustomer.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.function.Predicate;

public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {


}
