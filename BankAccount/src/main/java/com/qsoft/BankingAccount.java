package com.qsoft;

import com.qsoft.dao.BankingAccountDAO;
import com.qsoft.dto.BankingAccountDTO;

/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 6/11/13
 * Time: 1:48 PM
 */
public class BankingAccount {
    private BankingAccountDAO bankingAccountDAO;

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

    public void deposit(BankingAccountDTO bankingAccountDTO, double amount) {
        bankingAccountDTO.setBalance(bankingAccountDTO.getBalance()+amount);
        bankingAccountDAO.save(bankingAccountDTO);

    }
}
