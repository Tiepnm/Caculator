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
                if (numbers.contains("[") && numbers.contains("]")) {
                    int end = numbers.indexOf("]");
                    int start = numbers.indexOf("[");
                    delimiter = numbers.substring(start + 1, end);
                    numbers = numbers.substring(end + 1, numbers.length());
                }
                else
                {
                    delimiter = numbers.substring(2, 3);
                    numbers = numbers.substring(3, numbers.length());
                }

            }
            numbers = numbers.replace(delimiter, ",");
            numbers = numbers.replace("\n", ",");
            String[] items = numbers.split(",");
            int sum = 0;
            boolean exist = false;
            String negatives = "";
            for (String item : items) {
                if ("".equals(item) || Integer.parseInt(item) > 1000) {
                    item = "0";
                }
                if (Integer.parseInt(item) < 0) {
                    exist = true;
                    negatives = negatives + item + ",";
                }
                sum += Integer.parseInt(item);
            }
            if (exist) {
                throw new NumberFormatException("negatives not allowed:" + negatives);
            }
            return sum;
        }

    }
}
