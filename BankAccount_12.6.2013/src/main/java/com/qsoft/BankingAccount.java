package com.qsoft;

import com.qsoft.dao.BankingAccountDAO;
import com.qsoft.dao.TransactionDAO;
import com.qsoft.dto.BankingAccountDTO;
import com.qsoft.dto.TransactionDTO;

/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 6/11/13
 * Time: 1:48 PM
 */
public class BankingAccount {

    private BankingAccountDAO bankingAccountDAO;
    private Transaction transaction = new Transaction();

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

    public void deposit(BankingAccountDTO bankingAccountDTO, double amount) {
        bankingAccountDTO.setBalance(bankingAccountDTO.getBalance() + amount);
        bankingAccountDAO.save(bankingAccountDTO);
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setAccountNumber(bankingAccountDTO.getAccountNumber());

        transactionDTO.setTimeStamp(1000L);
        transactionDTO.setAmount(amount);
        transactionDTO.setDescription("Deposit");
        transaction.save(transactionDTO);
    }

    public void withDraw(BankingAccountDTO bankingAccountDTO, double amount) {
        bankingAccountDTO.setBalance(bankingAccountDTO.getBalance() - amount);
        bankingAccountDAO.save(bankingAccountDTO);
    }

}
