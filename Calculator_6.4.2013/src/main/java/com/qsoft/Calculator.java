package com.qsoft;

/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 6/4/13
 * Time: 1:46 PM
 */
public class Calculator {

    private int addFromArrayString(String[] numbers) {
        int sum = 0;
        boolean exist = false;
        String negatives = "";
        for (String item : numbers) {
            if ("".equals(item) || Integer.parseInt(item) > 1000) {
                item = "0";
            }
            if (Integer.parseInt(item) < 0) {
                exist = true;
                negatives = negatives  + item+",";
            }
            sum += Integer.parseInt(item);
        }
        if (exist) {

            throw new NumberFormatException("negatives not allowed:"+negatives);
        }
        return sum;
    }
    private String afterReplaceString(String numbers)
    {
        numbers = numbers.replace("\n", ",");
        String delimiter = "";
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
        return numbers;
    }
    public int sum(String numbers) {
        if ("".equals(numbers)) {
            return 0;

        } else {
            numbers = afterReplaceString(numbers);
            String[] items = numbers.split(",");
            return addFromArrayString(items);
        }
    }
}
