package com.qsoft;

import junit.framework.Assert;
import qsoft.BankAccount;
import qsoft.dao.BankingAccountDAO;
import qsoft.dao.TransactionDAO;
import qsoft.dto.BankingAccountDTO;
import qsoft.dto.TransactionDTO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.Calendar;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 6/24/13
 */
public class TestBankingAccount {

    private BankAccount bankingAccount = new BankAccount();
    private BankingAccountDAO mockAccountDao = mock(BankingAccountDAO.class);
    private TransactionDAO mockTransactionDao = mock(TransactionDAO.class);
    private Calendar mockCalendar = mock(Calendar.class);
    private String accountNumber = "123456789";
    private BankingAccountDTO bankingAccountDTO;

    @Before
    public void setUp() {

        reset(mockAccountDao);
        bankingAccount.setBakingAccount(mockAccountDao);
        bankingAccount.setTransactionDao(mockTransactionDao);

        bankingAccountDTO = bankingAccount.openAccount(accountNumber);
    }

    @Test
    public void testOpenAccountWithZeroBalance() {
        ArgumentCaptor<BankingAccountDTO> openAccount = ArgumentCaptor.forClass(BankingAccountDTO.class);
        verify(mockAccountDao).save(openAccount.capture());

        assertEquals(0.0, openAccount.getValue().getBalance(), 0.01);
    }

    @Test
    public void testCanGetAccountFromDB() {
        bankingAccount.getAccount(accountNumber);
        ArgumentCaptor<String> accountNumberArgument = ArgumentCaptor.forClass(String.class);
        verify(mockAccountDao).getAccount(accountNumberArgument.capture());

        assertEquals(accountNumber, accountNumberArgument.getValue());
    }

    @Test
    public void testDeposit() {
        ArgumentCaptor<BankingAccountDTO> openAccount = ArgumentCaptor.forClass(BankingAccountDTO.class);
        //deposit first
        bankingAccount.deposit(bankingAccountDTO, 1000.0);
        verify(mockAccountDao, times(2)).save(openAccount.capture());

        bankingAccount.getAccount(accountNumber);
        assertEquals(1000.0, openAccount.getValue().getBalance());
    }
    //Test with transactions
    @Test
    public void testDepositWithTimeStamp() {

        bankingAccount.getTransactionDTO().setCalendar(mockCalendar);
        when(mockCalendar.getTimeInMillis()).thenReturn(1L);
        //deposit first
        Double amount = 5000.0;
        bankingAccount.deposit(bankingAccountDTO, amount);
        ArgumentCaptor<TransactionDTO> transactionDTOArgumentCaptor = ArgumentCaptor.forClass(TransactionDTO.class);
        verify(mockTransactionDao, times(1)).save(transactionDTOArgumentCaptor.capture());

        assertEquals(1L, transactionDTOArgumentCaptor.getAllValues().get(0).getTimeStamp());
        assertEquals(5000.0, transactionDTOArgumentCaptor.getAllValues().get(0).getAmount());

    }
    @Test
    public void testWithDraw() {

        ArgumentCaptor<BankingAccountDTO> openAccount = ArgumentCaptor.forClass(BankingAccountDTO.class);
        //deposit first
        bankingAccount.deposit(bankingAccountDTO, 5000.0);
        //withdraw
        bankingAccount.withDraw(bankingAccountDTO, 3000.0);
        verify(mockAccountDao, times(3)).save(openAccount.capture());
        assertEquals(2000.0, openAccount.getValue().getBalance());
    }
    @Test()
    public void testExceptionWithDraw()
    {
        try{
            //deposit first
            bankingAccount.deposit(bankingAccountDTO, 5000.0);
            //withdraw
            bankingAccount.withDraw(bankingAccountDTO, 10000.0);
        }
        catch (RuntimeException e)
        {
            Assert.assertEquals("Exception",e.getMessage());
        }

    }
    @Test
    public void testWithDrawTimeStamp() {

        Calendar mockCalendar = mock(Calendar.class);
        bankingAccount.getTransactionDTO().setCalendar(mockCalendar);
        when(mockCalendar.getTimeInMillis()).thenReturn(1000L).thenReturn(2000L);
        //deposit first
        Double amount = 5000.0;
        bankingAccount.deposit(bankingAccountDTO, amount);

        //withdraw
        bankingAccount.withDraw(bankingAccountDTO, 2000.0);
        ArgumentCaptor<TransactionDTO> transactionDTOArgumentCaptor = ArgumentCaptor.forClass(TransactionDTO.class);
        verify(mockTransactionDao, times(2)).save(transactionDTOArgumentCaptor.capture());

        assertEquals(-2000.0, transactionDTOArgumentCaptor.getAllValues().get(0).getAmount());
        assertEquals(2000L, transactionDTOArgumentCaptor.getAllValues().get(0).getTimeStamp());

    }

    @Test
    public void testGetAllTransactions() {
        bankingAccount.getAllTransactions(accountNumber);
        verify(mockTransactionDao).getAllTransaction(accountNumber);
    }

    @Test
    public void testGetAllTransactionsBetweenTime() {
        long timeStart = 1000L;
        long timeEnd = 2000L;
        bankingAccount.getAllTransactionsBetweenTime(accountNumber, timeStart, timeEnd);
        verify(mockTransactionDao).getAllTransactionsBetweenTime(accountNumber, timeStart, timeEnd);
    }
    @Test
    public void testGetNTransactions() {
        bankingAccount.getNTransactions(accountNumber);
        verify(mockTransactionDao).getNTransactions(accountNumber);
    }
}
