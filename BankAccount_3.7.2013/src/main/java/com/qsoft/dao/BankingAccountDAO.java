package com.qsoft.dao;

import com.qsoft.dto.BankAccountDTO;

import javax.sql.DataSource;
import java.sql.Connection;
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

    public BankAccountDTO save(BankAccountDTO accountNumber)
    {
        return null;
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
