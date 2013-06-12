package com.qsoft.dto;

/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 6/11/13
 * Time: 1:50 PM
 */
public class BankingAccountDTO {
    private Double balance = 0.0;
    private String accountNumber;

    public Double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
