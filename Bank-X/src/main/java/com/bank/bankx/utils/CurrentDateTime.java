package com.bank.bankx.utils;

import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class CurrentDateTime {

    public Date getCurrentTime() {
//        java.time.LocalDate localDate = java.time.LocalDate.now();
//        Date sqlDate = java.sql.Date.valueOf(localDate);
        long currentTimeMillis = System.currentTimeMillis();
        Date currentDate = new Date(currentTimeMillis);
        return currentDate;
    }
}
