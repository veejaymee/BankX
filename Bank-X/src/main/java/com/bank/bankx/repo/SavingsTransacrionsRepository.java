package com.bank.bankx.repo;

import com.bank.bankx.entity.CurrentTransactions;
import com.bank.bankx.entity.SavingsTransactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SavingsTransacrionsRepository extends JpaRepository<SavingsTransactions, Long> {
    List<SavingsTransactions> findByAccountId(Long accountId);
}
