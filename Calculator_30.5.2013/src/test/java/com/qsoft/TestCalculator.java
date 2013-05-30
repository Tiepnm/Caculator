package com.qsoft;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 5/30/13
 * Time: 1:42 PM
 */
public class TestCalculator {
    Calculator calculator;

    @Before
    public void TestCalculator() {
        calculator = new Calculator();
    }

    @Test
    public void testEmptyAdd() throws NegativeException {

        int sum = calculator.add("");
        Assert.assertEquals(0, sum);
    }

    @Test
    public void testAddTwoNumbers() throws NegativeException {

        int sum = calculator.add("0,1");
        Assert.assertEquals(1, sum);
    }

    @Test
    public void testAddMoreNumbers() throws NegativeException {

        int sum = calculator.add("0,1,2");
        Assert.assertEquals(3, sum);
    }

    @Test
    public void testAllowNewLine() throws NegativeException {

        int sum = calculator.add("1\n2");
        Assert.assertEquals(3, sum);
        sum = calculator.add("1\n2,3");
        Assert.assertEquals(6, sum);
        sum = calculator.add("1\n2\n3");
        Assert.assertEquals(6, sum);
        sum = calculator.add("1\n2\n3,4");
        Assert.assertEquals(10, sum);
    }

    @Test
    public void testWithDelimiter() throws NegativeException {
        int sum = calculator.add("//;1;2");
        Assert.assertEquals(3, sum);
        sum = calculator.add("//=3=2");
        Assert.assertEquals(5, sum);
        sum = calculator.add("//=3=2,6\n3");
        Assert.assertEquals(14, sum);
    }

    @Test(expected = NegativeException.class)
    public void testWithNegative() throws NegativeException {

        int sum = calculator.add("1,-3");
          if(sum == -1)
          {
              throw new NegativeException("negatives not allowed:");
          }

    }


}
