package com.example.dezhi_final.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Customer implements Serializable,Comparable {
    String account;
    String openDate;
    float balance;

    String firstName;
    String family;
    String phone;
    String SIN;

    public Customer() {
    }

    public Customer(String account, String openDate, float balance, String firstName, String family, String phone, String SIN) {
        this.account = account;
        this.openDate = openDate;
        this.balance = balance;
        this.firstName = firstName;
        this.family = family;
        this.phone = phone;
        this.SIN = SIN;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getOpenDate() {
        return openDate;
    }

    public void setOpenDate(String openDate) {
        this.openDate = openDate;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSIN() {
        return SIN;
    }

    public void setSIN(String SIN) {
        this.SIN = SIN;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", family='" + family + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return SIN.equals(customer.SIN);
    }

    @Override
    public int hashCode() {
        return Objects.hash(SIN);
    }

    @Override
    public int compareTo(Object o) {
        Customer otherObject = (Customer)o;
        return this.family.compareTo(otherObject.family);
    }


    public static ArrayList<Customer> customers = new ArrayList<>();



}
