package qsoft.dto;

import java.util.Calendar;

/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 6/21/13
 * Time: 1:51 PM
 */
public class TransactionDTO {

    private String accountNumber;
    private Long timeStamp;
    private Double amount;
    private String description;
    private  Calendar calendar = Calendar.getInstance() ;

    public TransactionDTO() {

    }
    public TransactionDTO(String accountNumber, long timeStamp, String deposit) {
        this.accountNumber = accountNumber;
        this.timeStamp = timeStamp;

    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber =  accountNumber;
    }

    public void setTimeStamp(Long time) {
        this.timeStamp = time ;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public long getTimeStamp() {
        return timeStamp;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public Double getAmount() {
        return amount;
    }
}
