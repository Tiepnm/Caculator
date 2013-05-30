package com.qsoft;

/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 5/30/13
 * Time: 1:43 PM
 */
public class Calculator {

    public int add(String numbers) throws NegativeException {
        if ("".equals(numbers)) {
            return 0;
        } else {
            int sum = 0;
            String delimiter = ",";
            numbers = numbers.replace("\n", ",");
            if (numbers.contains("//")) {
                delimiter = numbers.substring(2, 3);
                numbers = numbers.substring(3, numbers.length());

            }
            numbers = numbers.replace(delimiter, ",");
            String[] numberTemp = numbers.split(",");
            for (String number : numberTemp) {
                if (Integer.parseInt(number) < 0) {
                    return -1;

                }
                sum += Integer.parseInt(number);
            }
            return sum;
        }

    }
}
