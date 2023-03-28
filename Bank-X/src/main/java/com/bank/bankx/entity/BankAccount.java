package com.bank.bankx.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="bank_account")
@Data
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private long profile_id;
    private long account_number;
    private String account_type;

    public BankAccount() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccount that = (BankAccount) o;
        return id == that.id && account_number == that.account_number && account_type.equals(that.account_type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, account_number, account_type);
    }
}
