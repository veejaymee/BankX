package com.bank.bankx.entity;


import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="savings_transactions")
@Data
public class SavingsTransactions {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private long accountId;
    private String transaction_type;
    private Date created_date;
    private String actions_from;
    private double transaction_amount;
    private double balance;

    public SavingsTransactions() {
    }
}
