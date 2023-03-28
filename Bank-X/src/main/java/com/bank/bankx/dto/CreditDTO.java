package com.bank.bankx.dto;

import lombok.Data;

@Data
public class CreditDTO {

    private long account_id;
    private String transaction_type;
    private String actions_from;
    private double transaction_amount;
}
