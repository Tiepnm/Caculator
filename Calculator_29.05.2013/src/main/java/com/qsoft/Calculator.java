package com.qsoft;

/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 5/29/13
 * Time: 1:46 PM
 */
public class Calculator {
    public int add(String numbers) {
        String delimiter = ",";
        if ("" == numbers) {
            return 0;
        } else {
            int sum = 0;
            if (numbers.contains("\n")) {
                numbers = numbers.replace("\n", ",");
            }
            if (numbers.contains("//")) {
                delimiter = numbers.substring(2, 3);
                numbers = numbers.substring(3, numbers.length());
                numbers = numbers.replace(delimiter, ",");
            }

            String str[] = numbers.split(",");
            for (String s : str) {
                if ("".equals(s)) {
                    s = "0";
                }
                sum += Integer.parseInt(s);
            }
            return sum;
        }
    }
}
