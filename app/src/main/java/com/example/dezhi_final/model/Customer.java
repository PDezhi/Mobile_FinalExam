package com.example.dezhi_final.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Customer implements Serializable, Comparable {
    Account account;
    String firstName;
    String family;
    String phone;
    String SIN;

    public Customer() {
    }

    public Customer(Account account, String firstName, String family, String phone, String SIN) {
        this.account = account;
        this.firstName = firstName;
        this.family = family;
        this.phone = phone;
        this.SIN = SIN;
    }

    public Account getAccount() {
        return account;
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
        Customer otherObject = (Customer) o;
        return this.family.compareTo(otherObject.family);
    }

    public static List<Customer> customers = new ArrayList<>();

}
