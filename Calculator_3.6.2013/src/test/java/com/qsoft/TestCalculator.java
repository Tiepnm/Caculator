package com.qsoft;

import junit.framework.Assert;
import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 6/3/13
 * Time: 1:50 PM
 */
public class TestCalculator {
    Calculator calculator = new Calculator();

    @Test
    public void testOneNumberCalculator() {

        int sum = calculator.sum("");
        Assert.assertEquals(0, sum);

        sum = calculator.sum("1");
        Assert.assertEquals(1, sum);
    }

    @Test
    public void testAddTwoNumbersCalculator() {
        int sum = calculator.sum("0,1");
        Assert.assertEquals(1, sum);
        sum = calculator.sum("1,1");
        Assert.assertEquals(2, sum);
    }

    @Test
    public void testAddMoreTwosCalculator() {
        int sum = calculator.sum("10,1,2");
        Assert.assertEquals(13, sum);

    }

    @Test
    public void testAddNewLines() {
        int sum = calculator.sum("10\n1,2");
        Assert.assertEquals(13, sum);
        sum = calculator.sum("\n10\n1,2");
        Assert.assertEquals(13, sum);
    }

    @Test
    public void testWithDelimiters() {
        int sum = calculator.sum("//;\n1;2");
        Assert.assertEquals(3, sum);
    }

    @Test(expected = NumberFormatException.class)
    public void testWithNegative()  {
        int sum = calculator.sum("-1,-3");

    }

    @Test
    public void testAddBigger1000() {
        int sum = calculator.sum("1000,2");
        Assert.assertEquals(1002, sum);
        sum = calculator.sum("1001,2");
        Assert.assertEquals(2, sum);
    }

    @Test
    public void testDelimitersCanBeOfLengths() {
        int sum = calculator.sum("//[***]\n4***5***6");
        Assert.assertEquals(15, sum);
    }


}
