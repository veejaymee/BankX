package com.bank.bankx.repo;

import com.bank.bankx.entity.CurrentTransactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrentTransactionsRepository extends JpaRepository<CurrentTransactions, Long> {
    List<CurrentTransactions> findByAccountId(Long accountId);

}
