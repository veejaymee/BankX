package com.bank.bankx.service;

import com.bank.bankx.entity.CustomerProfile;
import com.bank.bankx.repo.CustomerProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerProfileService {

    @Autowired
    CustomerProfileRepository customerProfileRepository;

    @Autowired
    BankAccountService bankAccountService;

    public CustomerProfile createProfile(CustomerProfile customerProfile){
        CustomerProfile saveCustomer = customerProfileRepository.save(customerProfile);
        bankAccountService.createBankAccounts(saveCustomer.getId());
        return saveCustomer;
    }
}
