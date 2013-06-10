package com.qsoft;

/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 6/10/13
 * Time: 1:55 PM
 */
public class Calculator {
    public int sum(String numbers) {
        if ("".equals(numbers)) {
            return 0;
        } else {
            numbers = numbers.replace("\n", ",");
            String[] items = numbers.split(",");
            int sum = 0;
            for (String item : items) {
                sum += Integer.parseInt(item);
            }
            return sum;
        }

    }
}
