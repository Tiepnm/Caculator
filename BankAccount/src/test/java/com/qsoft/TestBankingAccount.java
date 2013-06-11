package com.qsoft;

import com.qsoft.dao.BankingAccountDAO;
import com.qsoft.dto.BankingAccountDTO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 6/11/13
 * Time: 1:43 PM
 */
public class TestBankingAccount {
    private BankingAccount bankingAccount = new BankingAccount();
    BankingAccountDAO mockAccountDao = mock(BankingAccountDAO.class);

    @Before
    public void setUp() {
        reset(mockAccountDao);
        bankingAccount.setBakingAccount(mockAccountDao);
    }

    @Test
    public void testOpenAccountWithZeroBalance() {
        BankingAccountDTO bankingAccountDTO = bankingAccount.openAccount("012345678910");
        ArgumentCaptor<BankingAccountDTO> openAccount = ArgumentCaptor.forClass(BankingAccountDTO.class);

        verify(mockAccountDao).save(openAccount.capture());

        assertEquals(0.0, openAccount.getValue().getBalance(), 0.01);
        assertEquals("012345678910", openAccount.getValue().getAccountNumber());
        assertEquals("012345678910", bankingAccountDTO.getAccountNumber());
    }

    @Test
    public void testCanGetAccount() {

        bankingAccount.openAccount("012345678910");
        ArgumentCaptor<BankingAccountDTO> openAccount = ArgumentCaptor.forClass(BankingAccountDTO.class);
        verify(mockAccountDao).save(openAccount.capture());

        bankingAccount.getAccount("012345678910");
        ArgumentCaptor<String> accountNumberArgument = ArgumentCaptor.forClass(String.class);
        verify(mockAccountDao).getAccount(accountNumberArgument.capture());

        assertEquals("012345678910", accountNumberArgument.getValue());

    }

    @Test
    public void testDeposit() {
        BankingAccountDTO bankingAccountDTO = bankingAccount.openAccount("012345678910");
        ArgumentCaptor<BankingAccountDTO> openAccount = ArgumentCaptor.forClass(BankingAccountDTO.class);
        verify(mockAccountDao).save(openAccount.capture());
        //deposit first
        bankingAccount.deposit(bankingAccountDTO, 5000.0);
        verify(mockAccountDao, times(2)).save(openAccount.capture());

        bankingAccount.getAccount("012345678910");
        ArgumentCaptor<String> accountNumberArgument = ArgumentCaptor.forClass(String.class);
        verify(mockAccountDao).getAccount(accountNumberArgument.capture());
        assertEquals(5000.0, openAccount.getValue().getBalance());

        //deposit second
        bankingAccount.deposit(bankingAccountDTO, 15000.0);
        verify(mockAccountDao, times(3)).save(openAccount.capture());
        assertEquals(20000.0, openAccount.getValue().getBalance());
    }


}
