package com.example.Assignment2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.UUID;

@Controller
public class WalletController {

    @Autowired
    private WalletInterface walletInterface;

    @Autowired
    private TransactionInterface transactionInterface;

    @PostMapping(path = "/wallet")
    public @ResponseBody
    String createWallet(@RequestParam String phoneNumber){
        if (walletInterface.findById(phoneNumber).isPresent())
            return "Wallet Already exists";

        int intialAmount=0;
        Wallet wallet=new Wallet(phoneNumber,intialAmount);
//        wallet.setPhoneNo(phoneNumber);
//        wallet.setAmount(0);

        walletInterface.save(wallet);

        return "Wallet Added for"+phoneNumber;
    }

    @PostMapping(path = "/transaction")
    public @ResponseBody
    String p2p(@RequestParam String payer_no,
                      @RequestParam String payee_no,
                      @RequestParam Integer amount){

        if(!walletInterface.findById(payer_no).isPresent() || !walletInterface.findById(payee_no).isPresent())
            return "Either payer or payee Doesn't exist";

        Wallet payer=walletInterface.findById(payer_no).get();
        Wallet payee=walletInterface.findById(payee_no).get();

        if(payer.getAmount()<amount)
            return "Insufficient Amount in payer";

        int payee_amount=payee.getAmount();
        payee.setAmount(payee_amount+amount);
        payer.setAmount(payee_amount-amount);

        Transaction transaction=new Transaction(payer_no,payee_no,amount);
        transactionInterface.save(transaction);

//        save new amounts to DB
//        walletInterface.save(payee);
//        walletInterface.save(payer);

        return "Transaction Completed";
    }

    @GetMapping(path = "/transaction")
    public ArrayList<Transaction> getTransaction(@RequestParam String userId){

        ArrayList<UUID> list=new ArrayList<>();
        if(!walletInterface.findById(userId).isPresent())
        {
            System.out.println("User Doesn`t Exists");
            return null;
        }
        Wallet user=walletInterface.findById(userId).get();
        list= user.getTransactions();

        ArrayList<Transaction> retList=new ArrayList<Transaction>();
        for (int i = 0; i < list.size() ; i++) {
            if(transactionInterface.findById(list.get(i)).isPresent()){
                retList.add(transactionInterface.findById(list.get(i)).get());
            }
        }
        //return all Transactions: get another function
//        return retListist;
        return  retList;
    }

    @GetMapping(path = "/transaction")
    public String checkStatus(@RequestParam UUID txnID){

        //we are only keeping
        if(!transactionInterface.findById(txnID).isPresent())
            return "transaction Passed";

        return "transaction Failed";
    }
}
/***
 * Wallet Management
 *
 *
 * 1. Create Wallet: API which will create wallet for a user
 * url:http://localhost:8080/wallet
 * METHOD : POST
 * input: phone number
 * Validations : phone number should exist , only one wallet for a user.
 *
 * 2.API to transfer money from one wallet to another wallet (p2p).
 * url:http://localhost:8080/transaction
 * METHOD : POST
 * input:{payer_phone_number,payee_phone_number,amount}
 * Validations : payer and payee both should exist, payer should have sufficient balance.
 *
 * 3.Transaction Summary API
 * url:http://localhost:8080/transaction?userId=<userId>
 * METHOD: GET
 * Validations: userId should exists
 * Note : this api should return in a pagination way.
 *
 * 4.Transaction Status
 * url:http://localhost:8080/transaction?txnId=<txnID>
 * Method :GET
 * Validation: TransactionId should exists
 *
 * Expectations:
 * =============
 * Flow Diagram in UML
 * Schema Design
 * Code with proper comment
 * Junit Test cases
 * ***/
