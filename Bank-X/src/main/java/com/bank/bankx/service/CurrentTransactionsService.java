package com.bank.bankx.service;

import com.bank.bankx.entity.CurrentTransactions;
import com.bank.bankx.repo.CurrentTransactionsRepository;
import com.bank.bankx.utils.CurrentDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Comparator;
import java.util.List;

@Service
public class CurrentTransactionsService {

    @Autowired
    CurrentTransactionsRepository currentTransactionsRepository;

    @Autowired
    CurrentDateTime currentDateTime;

    public void addTransaction(CurrentTransactions currentTransactions){
        currentTransactions.setCreated_date(currentDateTime.getCurrentTime());
        if(currentTransactions.getTransaction_type().equalsIgnoreCase("DEBIT")){
            currentTransactions.setBalance(getLatestAccountBalance(currentTransactions.getAccountId()) + currentTransactions.getTransaction_amount());
        }else {
            currentTransactions.setBalance( getLatestAccountBalance(currentTransactions.getAccountId()) - currentTransactions.getTransaction_amount());
        }
        currentTransactionsRepository.save(currentTransactions);
    }

    public double getLatestAccountBalance(long accountId){
        List<CurrentTransactions> transactions = currentTransactionsRepository.findByAccountId(accountId);
        transactions.sort(Comparator.comparing(CurrentTransactions::getCreated_date).reversed());
        double balance = transactions.isEmpty() ? 0.00 : transactions.get(0).getBalance();
        return balance;
    }
}
