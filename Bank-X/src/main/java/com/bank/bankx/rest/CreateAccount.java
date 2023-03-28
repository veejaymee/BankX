package com.bank.bankx.rest;

import com.bank.bankx.entity.CurrentTransactions;
import com.bank.bankx.entity.CustomerProfile;
import com.bank.bankx.entity.SavingsTransactions;
import com.bank.bankx.service.CurrentTransactionsService;
import com.bank.bankx.service.CustomerProfileService;
import com.bank.bankx.service.SavingsTransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class CreateAccount {

    @Autowired
    CustomerProfileService customerProfileService;

    @Autowired
    CurrentTransactionsService currentTransactionsService;

    @Autowired
    SavingsTransactionsService savingsTransactionsService;

    @PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
    public CustomerProfile createAccount(@RequestBody CustomerProfile customerProfile, HttpServletResponse response){
        return customerProfileService.createProfile(customerProfile);
    }

    @PostMapping(value = "/deposit", consumes = "application/json", produces = "application/json")
    public void deposit(@RequestBody CurrentTransactions currentTransactions){

        if(currentTransactions.getTransaction_type().equalsIgnoreCase("CURRENT")){
            currentTransactions.setTransaction_type("CREDIT");
            currentTransactionsService.addTransaction(currentTransactions);
        }else{
            SavingsTransactions savingsTransactions =  new SavingsTransactions();
            savingsTransactions.setCreated_date(currentTransactions.getCreated_date());
            savingsTransactions.setAccountId(currentTransactions.getAccountId());
            savingsTransactions.setTransaction_type("CREDIT");
            savingsTransactions.setTransaction_amount(currentTransactions.getTransaction_amount());
            savingsTransactions.setActions_from(currentTransactions.getActions_from());
            savingsTransactionsService.addTransaction(savingsTransactions);
        }
    }

    @PostMapping(value = "/withDraw", consumes = "application/json", produces = "application/json")
    public void withdrawFromCurrrentAccount(@RequestBody CurrentTransactions currentTransactions){
        if(currentTransactions.getTransaction_type().equalsIgnoreCase("CURRENT")){
            currentTransactions.setTransaction_type("DEBIT");
            currentTransactionsService.addTransaction(currentTransactions);
        }else{
            SavingsTransactions savingsTransactions =  new SavingsTransactions();
            savingsTransactions.setCreated_date(currentTransactions.getCreated_date());
            savingsTransactions.setAccountId(currentTransactions.getAccountId());
            savingsTransactions.setTransaction_type("DEBIT");
            savingsTransactions.setTransaction_amount(currentTransactions.getTransaction_amount());
            savingsTransactions.setActions_from(currentTransactions.getActions_from());
            savingsTransactionsService.addTransaction(savingsTransactions);
        }

    }



}
