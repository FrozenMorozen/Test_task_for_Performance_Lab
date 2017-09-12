package Testing;


import com.performancelab.calc.CalcForDividing;
import javax.swing.JTextField;

import org.junit.Test;
import static org.junit.Assert.*;

public class CalcForDividingTest {

    private static final double DELTA = 1e-15;

    @Test
    public void testDivideWholeNumbers() throws Exception {
        String firstArg="54.0";
        String secondArg="6.0";
    double result= Double.parseDouble(firstArg)
                  /Double.parseDouble(secondArg);
        CalcForDividing calcForDividing=new CalcForDividing();
    double h=calcForDividing.divide(firstArg,secondArg);

    assertEquals(result,calcForDividing.divide(firstArg,secondArg),1);
}

    @Test(expected = ArithmeticException.class)
    public void testDivideOnZero() throws Exception {
        String firstArg="5";
        String secondArg="0";
        double result= Double.parseDouble(firstArg)
                      /Double.parseDouble(secondArg);

        CalcForDividing calcForDividing=new CalcForDividing();
        assertEquals(result,calcForDividing.divide(firstArg,secondArg),DELTA);
    }

    @Test(expected = NumberFormatException.class)
    public void testInvalidValuesOfTextField() throws Exception {
        String firstArg="jhg";
        String secondArg="12";
        double result= Double.parseDouble(firstArg)
                      /Double.parseDouble(secondArg);

        CalcForDividing calcForDividing=new CalcForDividing();
        assertEquals(result,calcForDividing.divide(firstArg,secondArg),DELTA);
    }

}
