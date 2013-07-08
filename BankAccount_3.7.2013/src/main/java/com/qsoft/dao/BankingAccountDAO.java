package com.qsoft.dao;

import com.qsoft.dto.BankAccountDTO;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: tiepnm
 * Date: 1/7/13
 * Time: 1:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class BankingAccountDAO
{
    private Connection connection;

    public BankingAccountDAO(DataSource dataSource) throws SQLException
    {
        this.connection = dataSource.getConnection();
    }

    public BankAccountDTO save(BankAccountDTO bankAccountDTO) throws SQLException
    {
        PreparedStatement ps = null;
        String sql = "INSERT INTO SAVINGS_ACCOUNT(Account_Number,DESCRIPTION,BALANCE) values (?,?,?)";
        ps = connection.prepareStatement(sql);
        ps.setString(1, bankAccountDTO.getAccountNumber());
        ps.setString(2, bankAccountDTO.getDescription());
        ps.setDouble(3, bankAccountDTO.getBalance());
        ps.execute();
        bankAccountDTO = getAccount(bankAccountDTO.getAccountNumber());
        return bankAccountDTO;

    }

    public BankAccountDTO getAccount(String accountNumber) throws SQLException
    {
        String queryString = "SELECT * FROM SAVINGS_ACCOUNT WHERE ACCOUNT_NUMBER='" + accountNumber + "'";
        ResultSet resultSet = connection.createStatement().executeQuery(queryString);
        if (resultSet.next())
        {
            return new BankAccountDTO(accountNumber, resultSet.getDouble("balance"));
        }
        else
        {
            return null;
        }

    }
}
