package com.example.Assignment2;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.UUID;

@Entity
public class Wallet {

    @Id
    public String phoneNo;
    private int Amount;

    //what with this error static
    public static ArrayList<UUID> transactions;


    public Wallet(String phoneNo, int amount) {
        this.phoneNo = phoneNo;
        Amount = amount;
        transactions=new ArrayList<UUID>();
    }

    public Wallet() {

    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public ArrayList<UUID> getTransactions() {
        return transactions;
    }
}
