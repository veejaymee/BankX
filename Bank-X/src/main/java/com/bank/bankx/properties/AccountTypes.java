package com.bank.bankx.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class AccountTypes {
//    @Value("${account-types}")
//    private String[] array;


    @Value("#{'${account-types}'.split(',')}")
    private List<String> accountTypeList;

//    @PostConstruct
//    public List<String> accountTypeList(){
//        list.stream().forEach(s -> s.getBytes());
//        for (String a : array) {
//            System.out.println(a);
//        }
//    }
}
