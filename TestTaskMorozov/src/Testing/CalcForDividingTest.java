package Testing;


import com.performancelab.calc.CalcForDividing;
import javax.swing.JTextField;

import org.junit.Test;
import static org.junit.Assert.*;

public class CalcForDividingTest {

//    @Test
//    public void testDivideWholeNumbers() throws Exception {
//        double firstArg=54;
//        double secondArg=6;
//        CalcForDividing calcForDividing=new CalcForDividing();
//        assertEquals(firstArg/secondArg,calcForDividing.divide(firstArg,secondArg),DELTA);
//    }
//
    @Test(expected = ArithmeticException.class)
    public void testDivideOnZero() throws Exception {
        String firstArg="5";
        String secondArg="0";
        String result= String.valueOf((Double.parseDouble(firstArg)
                      /Double.parseDouble(secondArg)));
        CalcForDividing calcForDividing=new CalcForDividing();
        assertEquals(result,calcForDividing.divide(firstArg,secondArg));
    }

    @Test(expected = NumberFormatException.class)
    public void testInvalidValuesOfTextField() throws Exception {
        String firstArg="asdfsd";
        String secondArg="12";
        String result= String.valueOf((Double.parseDouble(firstArg)
                /Double.parseDouble(secondArg)));
        CalcForDividing calcForDividing=new CalcForDividing();
        assertEquals(result,calcForDividing.divide(firstArg,secondArg));
    }




}
