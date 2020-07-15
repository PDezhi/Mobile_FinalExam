package com.example.dezhi_final.model;

import java.io.Serializable;

public class Account implements Serializable {

    String accountNo;
    String openDate;
    float balance;

    public Account(String account, String openDate, float balance) {
        this.accountNo = account;
        this.openDate = openDate;
        this.balance = balance;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public String getOpenDate() {
        return openDate;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }
}
