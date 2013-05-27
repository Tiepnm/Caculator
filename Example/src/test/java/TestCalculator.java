import org.junit.Assert;
import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 5/27/13
 * Time: 2:14 PM
 */
public class TestCalculator {
    private Calculator calculator = new Calculator();
    @Test
    public void testCalculatorTwoEmptyNumbers()
    {
        Assert.assertEquals(0 ,calculator.add(""));
        Assert.assertEquals(0 ,calculator.add("",""));


    }
    @Test
    public void testCalculatorTwoNumber()
    {
        Assert.assertEquals(1,calculator.add("0","1"));
        Assert.assertEquals(3,calculator.add("1","2"));
        Assert.assertEquals(11,calculator.add("10","1"));
    }
    @Test
    public void testCalculatorThreeNumber()
    {
        Assert.assertEquals(3,calculator.add("0","1","2"));

    }

}
