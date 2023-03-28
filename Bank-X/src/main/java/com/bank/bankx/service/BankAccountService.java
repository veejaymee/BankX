package com.bank.bankx.service;

import com.bank.bankx.entity.BankAccount;
import com.bank.bankx.entity.SavingsTransactions;
import com.bank.bankx.repo.BankAccountRepository;
import com.bank.bankx.utils.CurrentDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class BankAccountService {

    @Autowired
    BankAccountRepository bankAccountRepository;

    @Autowired
    SavingsTransactionsService savingsTransactionsService;

    @Autowired
    CurrentDateTime currentDateTime;

    @Value("#{'${account-types}'.split(',')}")
    private List<String> accountTypeList;

    public void createBankAccounts(long profileId) {
        Random random = new Random();
        SavingsTransactions savingsTransactions =  new SavingsTransactions();
        List<BankAccount> bankAccountList = new ArrayList<>();
        for (String accountType : accountTypeList) {
            BankAccount bankAccount = new BankAccount();
            bankAccount.setProfile_id(profileId);
            bankAccount.setAccount_number(generateRandomDigits());
            bankAccount.setAccount_type(accountType);
            bankAccountList.add(bankAccount);
        }
        List<BankAccount> bankAccountList1 = bankAccountRepository.saveAll(bankAccountList);
        bankAccountList1.stream().filter(b -> b.getAccount_type()
                .equalsIgnoreCase("SAVING"))
                        .forEach(val -> {
                            savingsTransactions.setId(val.getId());
                            savingsTransactions.setAccountId(val.getId());
                            savingsTransactions.setTransaction_type("CREDIT");
                            savingsTransactions.setActions_from("Account Opening Bonus");
                            savingsTransactions.setCreated_date(currentDateTime.getCurrentTime());
                            savingsTransactions.setTransaction_amount(500.00);
                            savingsTransactions.setBalance(500.00);
                            savingsTransactionsService.addOpeningBalance(savingsTransactions);
                        });

    }

    public long generateRandomDigits() {
        Random random = new Random();
        return 1000000000000L + (long) (random.nextFloat() * 9000000000000L);
    }
}
