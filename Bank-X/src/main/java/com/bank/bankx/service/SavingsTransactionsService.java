package com.bank.bankx.service;

import com.bank.bankx.entity.CurrentTransactions;
import com.bank.bankx.entity.SavingsTransactions;
import com.bank.bankx.repo.SavingsTransacrionsRepository;
import com.bank.bankx.utils.CurrentDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Comparator;
import java.util.List;

@Service
public class SavingsTransactionsService {

    @Autowired
    SavingsTransacrionsRepository savingsTransacrionsRepository;

    @Autowired
    CurrentDateTime currentDateTime;


    public void addOpeningBalance(SavingsTransactions savingsTransactions){
        savingsTransacrionsRepository.save(savingsTransactions);
    }
    public void addTransaction(SavingsTransactions savingsTransactions){
        savingsTransactions.setCreated_date(currentDateTime.getCurrentTime());
        if(savingsTransactions.getTransaction_type().equalsIgnoreCase("DEBIT")){
            savingsTransactions.setBalance(getLatestAccountBalance(savingsTransactions.getAccountId()) + savingsTransactions.getTransaction_amount());
        }else {
            savingsTransactions.setBalance( getLatestAccountBalance(savingsTransactions.getAccountId()) - savingsTransactions.getTransaction_amount());
        }
        savingsTransacrionsRepository.save(savingsTransactions);
    }

    public double getLatestAccountBalance(long accountId){
        List<SavingsTransactions> transactions = savingsTransacrionsRepository.findByAccountId(accountId);
        transactions.sort(Comparator.comparing(SavingsTransactions::getCreated_date).reversed());
        double balance = transactions.isEmpty() ? null : transactions.get(0).getBalance();
        return balance;
    }
}
