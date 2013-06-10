package com.qsoft;

/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 6/10/13
 * Time: 1:55 PM
 */
public class Calculator {
    private Integer LIMIT_INTEGER = 1000;
    private String SIGN_START = "[";
    private String SIGN_END = "]";
    private String START_BEGIN = "//";
    private String COMA = ",";
    public String[] afterReplaceString(String numbers)
    {
        String delimiter = COMA;
        if (numbers.contains(START_BEGIN)) {
            if (numbers.contains(SIGN_START) && numbers.contains(SIGN_END)) {
                int end = numbers.indexOf(SIGN_END);
                int start = numbers.indexOf(SIGN_START);
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
        return items;
    }
    public int sum(String numbers) {
        if ("".equals(numbers)) {
            return 0;
        } else {

            int sum = 0;
            boolean exist = false;
            String negatives = "";
            String[] items = afterReplaceString(numbers);
            for (String item : items) {
                if ("".equals(item) || Integer.parseInt(item) > LIMIT_INTEGER) {
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
