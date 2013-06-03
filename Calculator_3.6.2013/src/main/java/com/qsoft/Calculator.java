package com.qsoft;

/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 6/3/13
 * Time: 1:51 PM
 */
public class Calculator {
    public int sum(String numbers) {
        String delimiter = ",";
        int sum = 0;
        if ("".equals(numbers)) {
            return sum;
        } else {
            boolean exits = false;
            String negatives = "";
            if (numbers.length() > 1) {
                numbers = numbers.replace("\n", ",");
                if (numbers.contains("//")) {
                    if (numbers.contains("[") && numbers.contains("]")) {
                        int end = numbers.indexOf("]");
                        int start = numbers.indexOf("[");
                        delimiter = numbers.substring(start + 1, end);
                        numbers = numbers.substring(end + 1, numbers.length());
                    } else {
                        delimiter = numbers.substring(2, 3);
                        numbers = numbers.substring(3, numbers.length());
                    }
                    numbers = numbers.replace(delimiter, ",");
                }
                String[] items = numbers.split(",");
                for (String item : items) {
                    if ("".equals(item) || Integer.parseInt(item) > 1000) {
                        item = "0";
                    }
                    if (Integer.parseInt(item) < 0) {
                        exits = true;
                        negatives = negatives + item;
                    }
                    sum += Integer.parseInt(item);
                }
                if (exits) {
                   System.out.println("negatives not allowed:" + negatives);
                   throw new NumberFormatException(negatives);
                }
                return sum;
            } else {
                return Integer.parseInt(numbers);
            }
        }

    }
}
