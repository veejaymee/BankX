package com.bank.bankx.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="customer_profile")
@Data
public class CustomerProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String first_name;
    private String last_name;
    private String sa_id;
    private String passport;
    private Date dob;
    private Date created_date;


}
