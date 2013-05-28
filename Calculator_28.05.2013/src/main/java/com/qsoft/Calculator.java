package com.qsoft;

/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 5/28/13
 * Time: 1:45 PM
 */
public class Calculator {
    public int add(String numbers) {

        if ("".equals(numbers))
        {
            return 0;

        } else
        {
            if (numbers.contains(",")) {
                if (numbers.contains("\n")) {
                    numbers = numbers.replaceAll("\n", ",");
                }
                String[] str = numbers.split(",");
                int sum = 0;
                for (int i = 0; i < str.length; i++) {

                    sum += Integer.parseInt(str[i]);
                }
                return sum;
            } else {
                return 0;
            }

        }
    }
}
