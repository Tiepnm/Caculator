package com.qsoft;

import com.qsoft.dao.TransactionDAO;
import com.qsoft.dto.TransactionDTO;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 6/12/13
 * Time: 2:02 PM
 */
public class Transaction {
    private TransactionDAO transactionDao;

    public TransactionDAO getTransactionDao() {
        return transactionDao;
    }

    public void setTransactionDao(TransactionDAO transactionDao) {
        this.transactionDao = transactionDao;
    }

    public void save(TransactionDTO transactionDTO) {
        transactionDao.save(transactionDTO);
    }

    public List<TransactionDTO> getListTransaction(String accountNumber) {
        return transactionDao.getAllTransaction(accountNumber);
    }
}
