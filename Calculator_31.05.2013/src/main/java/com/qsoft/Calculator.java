package com.qsoft;

/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 5/31/13
 * Time: 1:47 PM
 */
public class Calculator {
    public int add(String numbers) {
        String delimiter = ",";
        int sum = 0;

        if ("".equals(numbers)) {
            return sum;
        } else {
            if (numbers.length() > 1) {

                numbers = numbers.replace("\n", ",");
                if (numbers.contains("//")) {
                    if (numbers.contains("[") && numbers.contains("]")) {
                        int start = numbers.indexOf("[");
                        int end = numbers.indexOf("]");
                        delimiter = numbers.substring(start + 1, end);
                        numbers = numbers.substring(end + 1, numbers.length());

                    } else {
                        delimiter = numbers.substring(2, 3);
                        numbers = numbers.substring(3, numbers.length());

                    }
                    numbers = numbers.replace(delimiter, ",");
                }

                String[] items = numbers.split(",");
                boolean exitsNegative = false;
                String negatives = "";
                for (String item : items) {
                    if ("".equals(item) || Integer.parseInt(item) > 1000) {
                        item = "0";
                    }
                    if (Integer.parseInt(item) < 0) {
                        exitsNegative = true;
                        negatives += item + ",";
                    }
                    sum += Integer.parseInt(item);
                }
                if (exitsNegative) {
                    try {
                        throw new NegativeException("negatives not allowed:" + negatives);
                    } catch (NegativeException e) {

                    }

                }
                return sum;
            } else {
                return Integer.parseInt(numbers);
            }

        }
    }
}
