package com.qsoft;

import com.qsoft.dao.BankingAccountDAO;
import com.qsoft.dao.TransactionDAO;
import com.qsoft.dto.BankingAccountDTO;
import com.qsoft.dto.TransactionDTO;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 6/11/13
 * Time: 1:48 PM
 */
public class BankAccount {

    private BankingAccountDAO bankingAccountDAO;
    private Transaction transaction = new Transaction();
    private TransactionDTO transactionDTO = new TransactionDTO();
    public BankingAccountDTO openAccount(String accountNumber) {
        BankingAccountDTO bankingAccountDTO = new BankingAccountDTO();
        bankingAccountDTO.setAccountNumber(accountNumber);
        bankingAccountDAO.save(bankingAccountDTO);
        return bankingAccountDTO;
    }

    public void setBakingAccount(BankingAccountDAO bankingAccountDAO) {
        this.bankingAccountDAO = bankingAccountDAO;
    }

    public BankingAccountDTO getAccount(String accountNumber) {
        return bankingAccountDAO.getAccount(accountNumber);
    }

    public void setTransactionDao(TransactionDAO transactionDAO) {

        transaction.setTransactionDao(transactionDAO);
    }

    public Transaction getTransaction() {
        return transaction;
    }
    public TransactionDTO getTransactionDTO() {
        return transactionDTO;
    }
    public void deposit(BankingAccountDTO bankingAccountDTO, double amount) {
        bankingAccountDTO.setBalance(bankingAccountDTO.getBalance() + amount);
        bankingAccountDAO.save(bankingAccountDTO);

        transactionDTO.setAccountNumber(bankingAccountDTO.getAccountNumber());
        transactionDTO.setTimeStamp(transactionDTO.getCalendar().getTimeInMillis());
        transactionDTO.setAmount(amount);
        transactionDTO.setDescription("Deposit");
        transaction.save(transactionDTO);
    }

    public void withDraw(BankingAccountDTO bankingAccountDTO, double amount) {
        bankingAccountDTO.setBalance(bankingAccountDTO.getBalance() - amount);
        bankingAccountDAO.save(bankingAccountDTO);


        transactionDTO.setAccountNumber(bankingAccountDTO.getAccountNumber());
        transactionDTO.setTimeStamp(transactionDTO.getCalendar().getTimeInMillis());
        transactionDTO.setAmount(-amount);
        transactionDTO.setDescription("With Draw");
        transaction.save(transactionDTO);
    }

    public List<TransactionDTO> getAllTransactions(String accountNumber) {
        return transaction.getListTransaction(accountNumber);
    }

    public List<TransactionDTO> getAllTransactionsBetweenTime(String accountNumber, long timeStart, long timeEnd) {
        return transaction.getAllTransactionBetweenTime(accountNumber, timeStart, timeEnd);
    }

    public  List<TransactionDTO> getNTransactions(String accountNumber) {
        return transaction.getNTransactions(accountNumber);
    }
}
