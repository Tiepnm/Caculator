package com.qsoft;

import com.qsoft.dao.BankingAccountDAO;
import com.qsoft.dao.TransactionDAO;
import com.qsoft.dto.BankAccountDTO;
import com.qsoft.dto.TransactionDTO;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.sql.SQLException;
import java.util.Calendar;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

/**
 * Created with IntelliJ IDEA.
 * User: tiepnm
 * Date: 1/7/13
 * Time: 1:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestBankAccount {

    private BankAccount bankAccount = new BankAccount();
    private Transaction transaction = new Transaction();
    private String accountNumber = "123456789";
    private BankingAccountDAO mockAccountDAO = mock(BankingAccountDAO.class);
    private TransactionDAO mockTransactionDAO = mock(TransactionDAO.class);
    private Calendar mockCalendar = mock(Calendar.class);
    private BankAccountDTO bankingAccountDTO;

    @Before
    public void setUp() {
        bankAccount.setBankingAccountDAO(mockAccountDAO);
        bankAccount.setTransaction(transaction);
        transaction.setTransactionDao(mockTransactionDAO);
        bankingAccountDTO = bankAccount.openAccount(accountNumber);
    }

    @Test
    public void testOpenAccount() {
        ArgumentCaptor<BankAccountDTO> openAccount = ArgumentCaptor.forClass(BankAccountDTO.class);
        try
        {
            verify(mockAccountDAO).save(openAccount.capture());
        }
        catch (SQLException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        ArgumentCaptor<TransactionDTO> transactionArgument = ArgumentCaptor.forClass(TransactionDTO.class);
        verify(mockTransactionDAO).save(transactionArgument.capture());

        Assert.assertEquals(0.0, openAccount.getValue().getBalance());
    }

    @Test
    public void testGetAccountFromDB() {
        bankAccount.getAccount(accountNumber);
        ArgumentCaptor<String> accountNumberArgument = ArgumentCaptor.forClass(String.class);
        try
        {
            verify(mockAccountDAO).getAccount(accountNumberArgument.capture());
        }
        catch (SQLException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        assertEquals(accountNumber, accountNumberArgument.getValue());
    }

    @Test
    public void testDeposit() {
        ArgumentCaptor<BankAccountDTO> openAccount = ArgumentCaptor.forClass(BankAccountDTO.class);
        //deposit first
        bankAccount.deposit(bankingAccountDTO, 1000.0);
        try
        {
            verify(mockAccountDAO, times(2)).save(openAccount.capture());
        }
        catch (SQLException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        bankAccount.getAccount(accountNumber);
        assertEquals(1000.0, openAccount.getValue().getBalance());
    }

    @Test
    public void testDepositWithTimeStamp() {

        bankAccount.getTransactionDTO().setCalendar(mockCalendar);
        when(mockCalendar.getTimeInMillis()).thenReturn(1L);
        //deposit first
        Double amount = 2000.0;
        bankAccount.deposit(bankingAccountDTO, amount);
        ArgumentCaptor<TransactionDTO> transactionDTOArgumentCaptor = ArgumentCaptor.forClass(TransactionDTO.class);
        verify(mockTransactionDAO, times(2)).save(transactionDTOArgumentCaptor.capture());

        assertEquals(1L, transactionDTOArgumentCaptor.getAllValues().get(0).getTimeStamp());
        assertEquals(2000.0, transactionDTOArgumentCaptor.getAllValues().get(0).getAmount());

    }

    @Test
    public void testWithDraw() {

        ArgumentCaptor<BankAccountDTO> openAccount = ArgumentCaptor.forClass(BankAccountDTO.class);
        //deposit first
        bankAccount.deposit(bankingAccountDTO, 5000.0);
        //withdraw
        bankAccount.withDraw(bankingAccountDTO, 3000.0);
        try
        {
            verify(mockAccountDAO, times(3)).save(openAccount.capture());
        }
        catch (SQLException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        assertEquals(2000.0, openAccount.getValue().getBalance());
    }


    @Test
    public void testWithDrawTimeStamp() {

        Calendar mockCalendar = mock(Calendar.class);
        bankAccount.getTransactionDTO().setCalendar(mockCalendar);
        when(mockCalendar.getTimeInMillis()).thenReturn(1000L).thenReturn(2000L);
        //deposit first
        Double amount = 5000.0;
        bankAccount.deposit(bankingAccountDTO, amount);

        //withdraw
        bankAccount.withDraw(bankingAccountDTO, 2000.0);
        ArgumentCaptor<TransactionDTO> transactionDTOArgumentCaptor = ArgumentCaptor.forClass(TransactionDTO.class);
        verify(mockTransactionDAO, times(3)).save(transactionDTOArgumentCaptor.capture());

        assertEquals(-2000.0, transactionDTOArgumentCaptor.getAllValues().get(0).getAmount());
        assertEquals(2000L, transactionDTOArgumentCaptor.getAllValues().get(0).getTimeStamp());

    }

    @Test()
    public void testExceptionWithDraw() {
        try {
            //deposit first
            bankAccount.deposit(bankingAccountDTO, 2000.0);
            //withdraw
            bankAccount.withDraw(bankingAccountDTO, 20000.0);
        } catch (RuntimeException e) {
            Assert.assertEquals("Exception With Draw", e.getMessage());
        }

    }

    @Test
    public void testGetAllTransactions() {
        bankAccount.getAllTransactions(accountNumber);
        verify(mockTransactionDAO).getAllTransaction(accountNumber);
    }

    @Test
    public void testGetAllTransactionsBetweenTime() {
        long timeStart = 1000L;
        long timeEnd = 2000L;
        bankAccount.getAllTransactionsBetweenTime(accountNumber, timeStart, timeEnd);
        verify(mockTransactionDAO).getAllTransactionsBetweenTime(accountNumber, timeStart, timeEnd);
    }

    @Test
    public void testGetNTransactions() {
        bankAccount.getNTransactions(accountNumber);
        verify(mockTransactionDAO).getNTransactions(accountNumber);
    }

}
