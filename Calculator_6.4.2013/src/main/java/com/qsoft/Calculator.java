package com.qsoft;

/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 6/4/13
 * Time: 1:46 PM
 */
public class Calculator {

    public int addFromArrayString(String[] numbers) {
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

    public int sum(String numbers) {
        if ("".equals(numbers)) {
            return 0;

        } else {
            numbers = numbers.replace("\n", ",");
            String delimiter = "";
            if (numbers.contains("//")) {

                    delimiter = numbers.substring(2, 3);
                    numbers = numbers.substring(3, numbers.length());


                numbers = numbers.replace(delimiter, ",");
            }
            String[] items = numbers.split(",");
            return addFromArrayString(items);

        }
    }
}
