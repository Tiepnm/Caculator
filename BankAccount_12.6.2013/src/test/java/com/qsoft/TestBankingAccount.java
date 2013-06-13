package com.qsoft;

import com.qsoft.dao.BankingAccountDAO;
import com.qsoft.dao.TransactionDAO;
import com.qsoft.dto.BankingAccountDTO;
import com.qsoft.dto.TransactionDTO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.Calendar;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 6/11/13
 * Time: 1:43 PM
 */
public class TestBankingAccount {
    private BankAccount bankingAccount = new BankAccount();

    private BankingAccountDAO mockAccountDao = mock(BankingAccountDAO.class);
    private TransactionDAO mockTransactionDao = mock(TransactionDAO.class);
    private Calendar mockCalendar = mock(Calendar.class);
    @Before
    public void setUp() {
        reset(mockAccountDao);
        bankingAccount.setBakingAccount(mockAccountDao);
        bankingAccount.setTransactionDao(mockTransactionDao);

    }

    @Test
    public void testOpenAccountWithZeroBalance() {
        bankingAccount.openAccount("012345678910");
        ArgumentCaptor<BankingAccountDTO> openAccount = ArgumentCaptor.forClass(BankingAccountDTO.class);
        verify(mockAccountDao).save(openAccount.capture());

        assertEquals(0.0, openAccount.getValue().getBalance(), 0.01);
        assertEquals("012345678910", openAccount.getValue().getAccountNumber());

    }

    @Test
    public void testCanGetAccount() {
        bankingAccount.openAccount("012345678910");
        bankingAccount.getAccount("012345678910");
        ArgumentCaptor<String> accountNumberArgument = ArgumentCaptor.forClass(String.class);
        verify(mockAccountDao).getAccount(accountNumberArgument.capture());

        assertEquals("012345678910", accountNumberArgument.getValue());

    }

    @Test
    public void testDeposit() {
        BankingAccountDTO bankingAccountDTO = bankingAccount.openAccount("012345678910");
        ArgumentCaptor<BankingAccountDTO> openAccount = ArgumentCaptor.forClass(BankingAccountDTO.class);
        //deposit first
        bankingAccount.deposit(bankingAccountDTO, 5000.0);
        verify(mockAccountDao,times(2)).save(openAccount.capture());

        bankingAccount.getAccount("012345678910");
        assertEquals(5000.0, openAccount.getValue().getBalance());

        //deposit second
        bankingAccount.deposit(bankingAccountDTO, 15000.0);
        verify(mockAccountDao, times(3)).save(openAccount.capture());

        assertEquals(20000.0, openAccount.getValue().getBalance());
    }
     //Test with transactions
    @Test
    public void testDepositWithTimeStamp() {
        BankingAccountDTO bankingAccountDTO = bankingAccount.openAccount("012345678910");

        bankingAccount.getTransactionDTO().setCalendar(mockCalendar);
        when(mockCalendar.getTimeInMillis()).thenReturn(1000L);
        //deposit first
        Double amount = 5000.0;
        bankingAccount.deposit(bankingAccountDTO, amount);
        ArgumentCaptor<TransactionDTO> transactionDTOArgumentCaptor = ArgumentCaptor.forClass(TransactionDTO.class);
        verify(mockTransactionDao,times(1)).save(transactionDTOArgumentCaptor.capture());

        assertEquals(1000L, transactionDTOArgumentCaptor.getAllValues().get(0).getTimeStamp());
        assertEquals(5000.0, transactionDTOArgumentCaptor.getAllValues().get(0).getAmount());

    }

    @Test
    public void testWithDraw() {
        BankingAccountDTO bankingAccountDTO = bankingAccount.openAccount("012345678910");
        ArgumentCaptor<BankingAccountDTO> openAccount = ArgumentCaptor.forClass(BankingAccountDTO.class);
        //deposit first
        bankingAccount.deposit(bankingAccountDTO, 5000.0);
        //withdraw
        bankingAccount.withDraw(bankingAccountDTO, 2000.0);
        verify(mockAccountDao, times(3)).save(openAccount.capture());
        assertEquals(3000.0, openAccount.getValue().getBalance());
    }
    @Test
    public void testDepositWithDrawTimeStamp() {
        BankingAccountDTO bankingAccountDTO = bankingAccount.openAccount("012345678910");

        Calendar mockCalendar = mock(Calendar.class);
        bankingAccount.getTransactionDTO().setCalendar(mockCalendar);
        when(mockCalendar.getTimeInMillis()).thenReturn(1000L).thenReturn(2000L);
        //deposit first
        Double amount = 5000.0;
        bankingAccount.deposit(bankingAccountDTO, amount);
        //withdraw
        bankingAccount.withDraw(bankingAccountDTO, 2000.0);
        ArgumentCaptor<TransactionDTO> transactionDTOArgumentCaptor = ArgumentCaptor.forClass(TransactionDTO.class);
        verify(mockTransactionDao,times(2)).save(transactionDTOArgumentCaptor.capture());

        assertEquals(-2000.0, transactionDTOArgumentCaptor.getAllValues().get(0).getAmount());
        assertEquals(2000L, transactionDTOArgumentCaptor.getAllValues().get(0).getTimeStamp());

    }


}
