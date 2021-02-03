package com.example.Assignment2;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface TransactionInterface extends CrudRepository<Transaction, UUID> {

    //returns Transaction with UUID

}
