package qsoft.dao;

import qsoft.dto.TransactionDTO;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 6/24/13
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
