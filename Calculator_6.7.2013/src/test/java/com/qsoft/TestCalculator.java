package com.qsoft;

import junit.framework.Assert;
import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 6/6/13
 * Time: 1:38 PM
 */
public class TestCalculator {
    Calculator calculator = new Calculator();

    @Test
    public void testEmptySum() {
        int sum = calculator.sum("");
        Assert.assertEquals(0, sum);

    }

    @Test
    public void testOneSum() {
        int sum = calculator.sum("3");
        Assert.assertEquals(3, sum);
    }

    @Test
    public void testMoreOneSum() {
        int sum = calculator.sum("1,5");
        Assert.assertEquals(6, sum);

        sum = calculator.sum("1,2,4");
        Assert.assertEquals(7, sum);
    }

    @Test
    public void testAddNewLine() {
        int sum = calculator.sum("2\n2");
        Assert.assertEquals(4, sum);

        sum = calculator.sum("2\n1\n7");
        Assert.assertEquals(10, sum);

        sum = calculator.sum("2\n1,3");
        Assert.assertEquals(6, sum);
    }

    @Test
    public void testWithDelimiters() {
        int sum = calculator.sum("//;\n1;2");
        Assert.assertEquals(3, sum);

        sum = calculator.sum("//'\n2'2");
        Assert.assertEquals(4, sum);
    }

    @Test
    public void testWithNegative() {
        try {
            int sum = calculator.sum("-5,-3");
            Assert.fail("No exception caught :(");
        } catch (NumberFormatException ex) {
            Assert.assertEquals("negatives not allowed:-5,-3,", ex.getMessage());
        }
    }

    @Test
    public void testAddBigger1000() {
        int sum = calculator.sum("1000,3");
        Assert.assertEquals(1003, sum);
        sum = calculator.sum("1002,2");
        Assert.assertEquals(2, sum);
    }

    @Test
    public void testDelimitersCanBeOfLengths() {
        int sum = calculator.sum("//[***]\n4***5***6");
        Assert.assertEquals(15, sum);

        sum = calculator.sum("//[%%%%]\n4%%%%5%%%%6");
        Assert.assertEquals(15, sum);


        sum = calculator.sum("//[?]\n4?5?6");
        Assert.assertEquals(15, sum);
    }

    @Test
    public void testMultiDelimiters() {
        int sum = calculator.sum("//[*][%]\n1*2%3");
        Assert.assertEquals(6, sum);
    }

}
