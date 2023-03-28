package com.bank.bankx.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class DebitDTO {

    private String transaction_type;
    private String actions_from;
    private double transaction_amount;

}
