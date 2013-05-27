/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 5/27/13
 * Time: 2:04 PM
 */
public class Calculator {
    public int add(String...  numbers)
    {
         if(numbers.length >= 2)
         {
             if(!"".equals(numbers[0]) && !"".equals(numbers[1]))
             {
                 return Integer.parseInt(numbers[0]) + Integer.parseInt(numbers[1]);
             }
             else
             {
                 return 0;
             }

         }
        else
         {
             if("".equals(numbers[0]))
             {
                 return 0;
             }
             else
             {
                 return Integer.parseInt(numbers[0]);
             }
         }

    }
}
