package com.qsoft;

import java.util.ArrayList;
import java.util.List;

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

    private void getListDelimiter(String s, List<String> list) {
        int start = s.indexOf("[");
        int end = s.indexOf("]");
        if (s.contains("[")) {
            list.add(s.substring(start + 1, end));
            s = s.substring(end + 1, s.length());
            getListDelimiter(s, list);
        }
    }

    private String replaceString(String numbers) {
        String delimiter = "";
        numbers = numbers.replace("\n", ",");
        if (numbers.contains("//")) {

            if (numbers.contains("[") && numbers.contains("]")) {
                List<String> list = new ArrayList<String>();

                getListDelimiter(numbers, list);
                numbers = numbers.substring(numbers.lastIndexOf("]") + 1, numbers.length());
                for (String delimiterItem : list) {
                    numbers = numbers.replace(delimiterItem, ",");
                }

            } else {
                delimiter = numbers.substring(2, 3);
                numbers = numbers.substring(3, numbers.length());
            }
            numbers = numbers.replace(delimiter, ",");
        }
        return numbers;
    }

}
