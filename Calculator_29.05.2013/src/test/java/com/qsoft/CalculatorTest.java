package com.qsoft;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 5/29/13
 * Time: 1:45 PM
 */
public class CalculatorTest {
    @Test
    public void testAddOneNumber() {
        Calculator calculator = new Calculator();
        Assert.assertEquals(0, calculator.add(""));
        Assert.assertEquals(1, calculator.add("1"));
        Assert.assertEquals(2, calculator.add("2"));
    }

    @Test
    public void testAddTwoNumbers() {
        Calculator calculator = new Calculator();
        Assert.assertEquals(3, calculator.add("1,2"));
        Assert.assertEquals(22, calculator.add("20,2"));

    }

    @Test
    public void testAddMoreTwoNumbers() {
        Calculator calculator = new Calculator();
        Assert.assertEquals(6, calculator.add("1,2,3"));
        Assert.assertEquals(10, calculator.add("1,2,3,4"));
    }

    @Test
    public void testAddNewLine() {
        Calculator calculator = new Calculator();
        Assert.assertEquals(6, calculator.add("1\n2,3"));
        Assert.assertEquals(6, calculator.add("1\n2\n3"));
        Assert.assertEquals(10, calculator.add("1\n2\n3,4"));
        Assert.assertEquals(12, calculator.add("1\n2,4\n5"));
        Assert.assertEquals(6, calculator.add("1,\n5"));
    }

    @Test
    public void testAnotherDelimiter() {

        Calculator calculator = new Calculator();
        Assert.assertEquals(3, calculator.add("//;\n1;2"));
        Assert.assertEquals(17, calculator.add("//;\n1;2\n3\n6,5"));

        //option delimiter
        Assert.assertEquals(17, calculator.add("//.\n1.2\n3\n6.5"));
        Assert.assertEquals(17, calculator.add("//=\n1=2\n3\n6=5"));

    }
    @Test
    public void testWithNegatives()
    {
        Calculator calculator = new Calculator();
        Assert.assertEquals(-4,calculator.add("-1,-3"));
    }
}

