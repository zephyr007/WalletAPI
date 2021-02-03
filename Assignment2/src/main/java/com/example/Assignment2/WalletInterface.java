package com.example.Assignment2;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public interface WalletInterface extends CrudRepository<Wallet,String> {

}
