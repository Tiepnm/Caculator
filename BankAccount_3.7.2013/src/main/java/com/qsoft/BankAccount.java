package com.qsoft;

import com.qsoft.dao.BankingAccountDAO;
import com.qsoft.dao.TransactionDAO;
import com.qsoft.dto.BankAccountDTO;
import com.qsoft.dto.TransactionDTO;

import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tiepnm
 * Date: 1/7/13
 * Time: 1:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class BankAccount {
    private BankingAccountDAO bankingAccountDAO;
    private TransactionDTO transactionDTO = new TransactionDTO();
    private Transaction transaction;

    public void setBankingAccountDAO(BankingAccountDAO bankingAccountDAO) {
        this.bankingAccountDAO = bankingAccountDAO;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public TransactionDTO getTransactionDTO() {
        return transactionDTO;
    }

    private void doTransaction(String accountNumber, Double amount, String description) {
        transactionDTO.setAccountNumber(accountNumber);
        transactionDTO.setTimeStamp(transactionDTO.getCalendar().getTimeInMillis());
        transactionDTO.setAmount(amount);
        transactionDTO.setDescription(description);
        transaction.save(transactionDTO);
    }

    public BankAccountDTO openAccount(String accountNumber) {
        BankAccountDTO bankingAccountDTO = new BankAccountDTO();
        bankingAccountDTO.setAccountNumber(accountNumber);
        try
        {
            bankingAccountDAO.save(bankingAccountDTO);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        doTransaction(accountNumber, 0.0, "");
        return bankingAccountDTO;
    }

    public BankAccountDTO getAccount(String accountNumber) {
        try{
            return bankingAccountDAO.getAccount(accountNumber);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }

    }

    public void deposit(BankAccountDTO bankingAccountDTO, double amount) {
        bankingAccountDTO.setBalance(bankingAccountDTO.getBalance() + amount);
        try
        {
            bankingAccountDAO.save(bankingAccountDTO);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        doTransaction(bankingAccountDTO.getAccountNumber(), amount, "Deposit");

    }

    public void withDraw(BankAccountDTO bankAccountDTO, double amount) {
        if (bankAccountDTO.getBalance() < amount) {
            throw new RuntimeException("Exception With Draw");
        }
        bankAccountDTO.setBalance(bankAccountDTO.getBalance() - amount);
        try
        {
            bankingAccountDAO.save(bankAccountDTO);
        }
        catch (SQLException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        doTransaction(bankAccountDTO.getAccountNumber(), -amount, "With Draw");

    }

    public List<TransactionDTO> getAllTransactions(String accountNumber) {
        return transaction.getListTransaction(accountNumber);
    }

    public List<TransactionDTO> getAllTransactionsBetweenTime(String accountNumber, long timeStart, long timeEnd) {
        return transaction.getAllTransactionBetweenTime(accountNumber, timeStart, timeEnd);
    }

    public List<TransactionDTO> getNTransactions(String accountNumber) {
        return transaction.getNTransactions(accountNumber);
    }
}
