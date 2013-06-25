package com.qsoft.dto;

/**
 * Created with IntelliJ IDEA.
 * User: tiepnm
 * Date: 6/25/13
 * Time: 1:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class BankAccountDTO {
    private String accountNumber;
    private double balance;

    public void setAccountNumber(String accountNumber) {
         this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
