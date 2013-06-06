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

    private Integer LIMIT_INTEGER = 1000;
    private String SIGN_START = "[";
    private String SIGN_END = "]";

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
            if ("".equals(numberItem) || Integer.parseInt(numberItem) > LIMIT_INTEGER) {
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

    private void getAllDelimiter(String s, List<String> list) {
        int start = s.indexOf("[");
        int end = s.indexOf("]");
        if (s.contains("[")) {
            list.add(s.substring(start + 1, end));
            s = s.substring(end + 1, s.length());
            getAllDelimiter(s, list);
        }
    }

    private String replaceString(String numberInput) {
        String delimiter = "";
        numberInput = numberInput.replace("\n", ",");
        if (numberInput.contains("//")) {

            if (numberInput.contains(SIGN_START) && numberInput.contains(SIGN_END)) {
                List<String> list = new ArrayList<String>();

                getAllDelimiter(numberInput, list);
                numberInput = numberInput.substring(numberInput.lastIndexOf("]") + 1, numberInput.length());
                for (String delimiterItem : list) {
                    numberInput = numberInput.replace(delimiterItem, ",");
                }

            } else {
                delimiter = numberInput.substring(2, 3);
                numberInput = numberInput.substring(3, numberInput.length());
            }
            numberInput = numberInput.replace(delimiter, ",");
        }
        return numberInput;
    }


}
