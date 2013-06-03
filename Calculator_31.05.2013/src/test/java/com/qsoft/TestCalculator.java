package com.qsoft;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 5/31/13
 * Time: 1:45 PM
 */
public class TestCalculator {
    Calculator calculator;

    @Before
    public void init() {
        calculator = new Calculator();
    }

    @Test
    public void testAdd() {

        int sum = calculator.add("");
        Assert.assertEquals(0, sum);
        sum = calculator.add("1");
        Assert.assertEquals(1, sum);
    }

    @Test
    public void testAddTwos() {

        int sum = calculator.add("1,2");
        Assert.assertEquals(3, sum);
    }

    @Test
    public void testAddMoreTwos() {

        int sum = calculator.add("1,2,3");
        Assert.assertEquals(6, sum);
    }

    @Test
    public void testAddHandleNewLine() {

        int sum = calculator.add("1,2\n3");
        Assert.assertEquals(6, sum);

        sum = calculator.add("1,2\n3,3\n4");
        Assert.assertEquals(13, sum);

        sum = calculator.add("1\n2\n3\n3\n4");
        Assert.assertEquals(13, sum);
    }

    @Test
    public void testWithDelimiter() {
        int sum = calculator.add("//;1;2");
        Assert.assertEquals(3, sum);
        sum = calculator.add("//;\n1;2");
        Assert.assertEquals(3, sum);
    }

    @Test(expected = NegativeException.class)
    public void testWithNegative() throws NegativeException {
        int sum = calculator.add("-1,-3");
        if (sum < 0) {
            throw new NegativeException("");
        }

    }

    @Test
    public void testAddBigger1000() {
        int sum = calculator.add("1000,2");
        Assert.assertEquals(1002, sum);

        sum = calculator.add("1001,2");
        Assert.assertEquals(2, sum);
    }

    @Test
    public void testDelimitersCanBeOfLengths() {
        int sum = calculator.add("//[***]\n1***2***3");
        Assert.assertEquals(6, sum);

        sum = calculator.add("//[++]\n1++2,3");
        Assert.assertEquals(6, sum);

    }


}
