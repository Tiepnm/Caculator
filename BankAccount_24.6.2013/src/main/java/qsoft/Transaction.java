package qsoft;

import qsoft.dao.TransactionDAO;
import qsoft.dto.TransactionDTO;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 6/24/13
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
    public List<TransactionDTO> getAllTransactionBetweenTime(String accountNumber,long timeStart,long timeEnd) {
        return transactionDao.getAllTransactionsBetweenTime(accountNumber, timeStart, timeEnd);
    }


    public List<TransactionDTO> getNTransactions(String accountNumber) {
        return transactionDao.getNTransactions(accountNumber);
    }
}
