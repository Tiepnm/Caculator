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

    private final Integer LIMIT_INTEGER = 1000;
    private final String SIGN_START = "[";
    private final String SIGN_END = "]";
    private final String NEW_LINE = "\n";
    private final String COMMA = ",";
    private final String BEGIN ="//";
    private final String NEGATIVES = "negatives not allowed:";

    public int sum(String numberInput) {
        if ("".equals(numberInput))
        {
            return 0;
        }
        else {
            numberInput = replaceString(numberInput);
            String[] numbers = numberInput.split(",");
            return addStringArrays(numbers);
        }
    }

    private int addStringArrays(String[] numbers) {
        boolean exist = false;
        String negatives = "";
        int sum = 0;
        for (String numberItem : numbers) {
            if ("".equals(numberItem) || Integer.parseInt(numberItem) > LIMIT_INTEGER) {
                numberItem = "0";
            }
            if (Integer.parseInt(numberItem) < 0) {
                exist = true;
                negatives = negatives + numberItem + COMMA;
            }
            sum += Integer.parseInt(numberItem);

        }
        if (exist) {
            throw new NumberFormatException(NEGATIVES + negatives);
        }
        return sum;
    }

    private void getListDelimiter(String s, List<String> list) {
        int start = s.indexOf(SIGN_START);
        int end = s.indexOf(SIGN_END);
        if (s.contains(SIGN_START)) {
            list.add(s.substring(start + 1, end));
            s = s.substring(end + 1, s.length());
            getListDelimiter(s, list);
        }
    }

    private String replaceString(String numbers) {
        String delimiter = "";
        numbers = numbers.replace(NEW_LINE, COMMA);
        if (numbers.contains(BEGIN)) {

            if (numbers.contains(SIGN_START) && numbers.contains(SIGN_END)) {
                List<String> list = new ArrayList<String>();
                //get all delimiter list
                getListDelimiter(numbers, list);
                numbers = numbers.substring(numbers.lastIndexOf(SIGN_END) + 1, numbers.length());
                //replace all delimiter to Comma
                for (String delimiterItem : list) {
                    numbers = numbers.replace(delimiterItem, COMMA);
                }
            }
            else {
                delimiter = numbers.substring(2,3);
                numbers = numbers.substring(3, numbers.length());
            }
            numbers = numbers.replace(delimiter,COMMA);
        }
        return numbers;
    }

}
