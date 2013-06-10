package com.qsoft;

import junit.framework.Assert;
import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 6/10/13
 * Time: 1:54 PM
 */
public class TestCalculator {
    Calculator calculator = new Calculator();

    @Test
    public void testAddEmpty() {
        int sum = calculator.sum("");
        Assert.assertEquals(0, sum);
    }

    @Test
    public void testAddOne() {
        int sum = calculator.sum("1");
        Assert.assertEquals(1, sum);
    }

    @Test
    public void testAddMoreOne() {
        int sum = calculator.sum("1,3");
        Assert.assertEquals(sum, 4);

        sum = calculator.sum("1,3,2");
        Assert.assertEquals(sum, 6);

    }

    @Test
    public void testAddNewLine() {
        int sum = calculator.sum("1\n3");
        Assert.assertEquals(sum, 4);

        sum = calculator.sum("1\n3,3");
        Assert.assertEquals(sum, 7);

    }

    @Test
    public void testWithDelimiters() {
        int sum = calculator.sum("//;\n1;2");
        Assert.assertEquals(3, sum);
    }

    @Test
    public void testWithOtherDelimiters() {
        int sum = calculator.sum("//@\n1@2,4,5");
        Assert.assertEquals(12, sum);
    }

    @Test(expected = NumberFormatException.class)
    public void testWithNegative() {
        try {
            int sum = calculator.sum("-1,-3");
            Assert.fail("No exception caught :(");
        } catch (NumberFormatException ex) {
            Assert.assertEquals("negatives not allowed:-1,-3,", ex.getMessage());
            throw new NumberFormatException();
        }
    }
    @Test
    public void testAddBigger1000() {
        int sum = calculator.sum("1001,2");
        Assert.assertEquals(2, sum);
    }
}
