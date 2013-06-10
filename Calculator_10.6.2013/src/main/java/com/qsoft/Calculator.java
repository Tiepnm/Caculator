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
            String delimiter = ",";
            if (numbers.contains("//")) {
                delimiter = numbers.substring(2, 3);
                numbers = numbers.substring(3, numbers.length());
            }
            numbers = numbers.replace(delimiter, ",");
            numbers = numbers.replace("\n", ",");
            String[] items = numbers.split(",");
            int sum = 0;
            for (String item : items) {
                if ("".equals(item)) {
                    item = "0";
                }
                sum += Integer.parseInt(item);
            }
            return sum;
        }

    }
}
