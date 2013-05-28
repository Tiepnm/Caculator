package com.qsoft;

import junit.framework.Assert;
import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 5/28/13
 * Time: 1:42 PM
 */
public class TestCalculator {
    private Calculator calculator ;
    public TestCalculator()
    {
        calculator = new Calculator();
    }
    @Test
    public void testCalculatorEmpty()
    {

        Assert.assertEquals(0,calculator.add(""));
    }
    @Test
    public void testCalculatorWithTwoNumbers()
    {

        Assert.assertEquals(1,calculator.add("0,1"));
        Assert.assertEquals(24,calculator.add("1,23"));
        Assert.assertEquals(100,calculator.add("1,99"));
    }
    @Test
    public void testCalculatorWithThreeNumbers()
    {

        Assert.assertEquals(3,calculator.add("0,1,2"));
        Assert.assertEquals(24,calculator.add("2,20,2"));
        Assert.assertEquals(350,calculator.add("100,50,200"));
    }
    @Test
    public void testCalculatorWithNewLine()
    {

        Assert.assertEquals(6,calculator.add("1\n2,3"));
        Assert.assertEquals(15,calculator.add("1\n2\n3\n4,5"));
    }
//    @Test
//    public void testCalculatorWithDelimiters()
//    {
//        Assert.assertEquals(3,calculator.add("//;\n1;2"));
//    }
}
