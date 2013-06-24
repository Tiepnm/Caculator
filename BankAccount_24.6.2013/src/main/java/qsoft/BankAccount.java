package qsoft;

import qsoft.dao.BankingAccountDAO;
import qsoft.dao.TransactionDAO;
import qsoft.dto.BankingAccountDTO;
import qsoft.dto.TransactionDTO;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 6/24/13
 */
public class BankAccount {

    private BankingAccountDAO bankingAccountDAO;
    private Transaction transaction = new Transaction();
    private TransactionDTO transactionDTO = new TransactionDTO();

    public void setBakingAccount(BankingAccountDAO bankingAccountDAO) {
        this.bankingAccountDAO = bankingAccountDAO;
    }

    public BankingAccountDTO getAccount(String accountNumber) {
        return bankingAccountDAO.getAccount(accountNumber);
    }

    public void setTransactionDao(TransactionDAO transactionDAO) {

        transaction.setTransactionDao(transactionDAO);
    }

    public TransactionDTO getTransactionDTO() {
        return transactionDTO;
    }

    public BankingAccountDTO openAccount(String accountNumber) {
        BankingAccountDTO bankingAccountDTO = new BankingAccountDTO();
        bankingAccountDTO.setAccountNumber(accountNumber);
        bankingAccountDAO.save(bankingAccountDTO);

        transactionDTO.setAccountNumber(bankingAccountDTO.getAccountNumber());
        transactionDTO.setTimeStamp(transactionDTO.getCalendar().getTimeInMillis());
        transactionDTO.setAmount(0.0);
        transactionDTO.setDescription("Open Account");
        return bankingAccountDTO;
    }

    public void deposit(BankingAccountDTO bankingAccountDTO, double amount) {
        bankingAccountDTO.setBalance(bankingAccountDTO.getBalance() + amount);
        bankingAccountDAO.save(bankingAccountDTO);
        doTransaction(bankingAccountDTO.getAccountNumber(),amount,"Deposit");

    }

    public void withDraw(BankingAccountDTO bankingAccountDTO, double amount)  {
        if (bankingAccountDTO.getBalance() < amount) {
            throw new RuntimeException("Exception");
        }
        bankingAccountDTO.setBalance(bankingAccountDTO.getBalance() - amount);
        bankingAccountDAO.save(bankingAccountDTO);
        doTransaction(bankingAccountDTO.getAccountNumber(),-amount,"With Draw");

    }

    private void doTransaction(String accountNumber ,Double amount ,String description)
    {
        transactionDTO.setAccountNumber(accountNumber);
        transactionDTO.setTimeStamp(transactionDTO.getCalendar().getTimeInMillis());
        transactionDTO.setAmount(amount);
        transactionDTO.setDescription(description);
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
