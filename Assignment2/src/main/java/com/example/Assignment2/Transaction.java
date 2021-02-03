package com.example.Assignment2;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID TransactionID;
    public String from;
    public String to;
    public Integer amount;

    public Transaction(String from, String to, Integer amount) {
        TransactionID=UUID.randomUUID();
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    public Transaction() {
        
    }

    public UUID getTransactionID() {
        return TransactionID;
    }

//    public void setTransactionID(UUID transactionID) {
//        TransactionID = transactionID;
//    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
