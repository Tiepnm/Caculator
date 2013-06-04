package com.qsoft;

import junit.framework.Assert;
import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 6/4/13
 * Time: 1:45 PM
 */
public class CalculatorTest {
    Calculator calculator = new Calculator();

    @Test
    public void testEmpty() {
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
        int sum = calculator.sum("2,1");
        Assert.assertEquals(3, sum);
    }

    @Test
    public void testAddNewLine() {
        int sum = calculator.sum("2\n1");
        Assert.assertEquals(3, sum);
    }

    @Test
    public void testWithDelimiters() {
        int sum = calculator.sum("//;\n1;2");
        Assert.assertEquals(3, sum);
    }

    @Test
    public void testWithNegative() {
        try {
            int sum = calculator.sum("-1,-3");
            Assert.fail("No exception caught :(");
        } catch (NumberFormatException ex) {
            Assert.assertEquals("negatives not allowed:-1,-3,", ex.getMessage());
        }
    }

    @Test
    public void testAddBigger1000() {
        int sum = calculator.sum("1000,3");
        Assert.assertEquals(1003, sum);
        sum = calculator.sum("1001,2");
        Assert.assertEquals(2, sum);
    }

    @Test
    public void testDelimitersCanBeOfLengths() {
        int sum = calculator.sum("//[***]\n4***5***6");
        Assert.assertEquals(15, sum);
    }

}
