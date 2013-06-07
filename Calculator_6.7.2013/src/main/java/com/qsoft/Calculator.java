package com.qsoft;

/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 6/6/13
 * Time: 1:39 PM
 */
public class Calculator {

    public int sum(String numberInput) {
        if ("".equals(numberInput)) {
            return 0;
        } else {
            numberInput = replaceString(numberInput);
            String[] numbers = numberInput.split(",");
            return addStringArray(numbers);
        }
    }

    private int addStringArray(String[] numbers) {
        boolean exist = false;
        String negatives = "";
        int sum = 0;
        for (String numberItem : numbers) {
            if ("".equals(numberItem) || Integer.parseInt(numberItem) > 1000) {
                numberItem = "0";
            }
            if (Integer.parseInt(numberItem) < 0) {
                exist = true;
                negatives = negatives + numberItem + ",";
            }
            sum += Integer.parseInt(numberItem);

        }
        if (exist) {
            throw new NumberFormatException("negatives not allowed:" + negatives);
        }
        return sum;
    }

    private String replaceString(String numbers) {
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

}
