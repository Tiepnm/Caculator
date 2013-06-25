package com.qsoft.dao;

import com.qsoft.dto.BankAccountDTO;
import com.qsoft.dto.TransactionDTO;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tiepnm
 * Date: 6/25/13
 * Time: 1:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class TransactionDAO {
    public void save(TransactionDTO transactionDTO) {

    }

    public List<TransactionDTO> getAllTransaction(String accountNumber) {
        return null;
    }
    public List<TransactionDTO> getAllTransactionsBetweenTime(String accountNumber,long timeStart,long timeEnd) {
        return null;
    }

    public List<TransactionDTO> getNTransactions(String accountNumber) {
        return null;
    }
}
