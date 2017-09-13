package Testing;


import com.performancelab.calc.CalcForDividing;
import javax.swing.JTextField;

import org.junit.Test;
import static org.junit.Assert.*;

public class CalcForDividingTest {

    private static final double DELTA = 1e-15;

    @Test
    public void divideWholeNumbers() throws Exception {
        String firstArg="54";
        String secondArg="6";
    double result= Double.parseDouble(firstArg)
                  /Double.parseDouble(secondArg);

        CalcForDividing calcForDividing=new CalcForDividing();
    assertEquals(result,calcForDividing.divide(firstArg,secondArg),DELTA);
    assertEquals(8,calcForDividing.divide("64","8"),DELTA);
    assertEquals(7,calcForDividing.divide("49","7"),DELTA);

}

    @Test
    public void divideRationalNumber() throws Exception {
        String firstArg="153638.212";
        String secondArg="1384.478";
        double result= Double.parseDouble(firstArg)
                      /Double.parseDouble(secondArg);

        CalcForDividing calcForDividing=new CalcForDividing();
        assertEquals(result,calcForDividing.divide(firstArg,secondArg),DELTA);
    }

    @Test(expected = ArithmeticException.class)
    public void divideOnZero() throws Exception {
        String firstArg="5";
        String secondArg="0";
        double result= Double.parseDouble(firstArg)
                      /Double.parseDouble(secondArg);

        CalcForDividing calcForDividing=new CalcForDividing();
        assertEquals(result,calcForDividing.divide(firstArg,secondArg),DELTA);
    }

    @Test(expected = NumberFormatException.class)
    public void divideEmptyValue() throws Exception {
        String firstArg="5";
        String secondArg="";
        double result= Double.parseDouble(firstArg)
                /Double.parseDouble(secondArg);

        CalcForDividing calcForDividing=new CalcForDividing();
        assertEquals(result,calcForDividing.divide(firstArg,secondArg),DELTA);
    }

    @Test(expected = NumberFormatException.class)
    public void invalidValuesOfTextFieldDivisible() throws Exception {
        String firstArg="jhg";
        String secondArg="12";
        double result= Double.parseDouble(firstArg)
                      /Double.parseDouble(secondArg);

        CalcForDividing calcForDividing=new CalcForDividing();
        assertEquals(result,calcForDividing.divide(firstArg,secondArg),DELTA);
    }

    @Test(expected = NumberFormatException.class)
    public void invalidValuesOfTextFieldDivisor() throws Exception {
        String firstArg="54";
        String secondArg="jhg";
        double result= Double.parseDouble(firstArg)
                      /Double.parseDouble(secondArg);

        CalcForDividing calcForDividing=new CalcForDividing();
        assertEquals(result,calcForDividing.divide(firstArg,secondArg),DELTA);
    }

    @Test(expected = NumberFormatException.class)
    public void invalidValuesOfTextFieldDot() throws Exception {
        String firstArg=".";
        String secondArg="12";
        double result= Double.parseDouble(firstArg)
                /Double.parseDouble(secondArg);

        CalcForDividing calcForDividing=new CalcForDividing();
        assertEquals(result,calcForDividing.divide(firstArg,secondArg),DELTA);
    }

    @Test(expected = NumberFormatException.class)
    public void invalidValuesOfTextFieldDots() throws Exception {
        String firstArg=".............";
        String secondArg="12";
        double result= Double.parseDouble(firstArg)
                /Double.parseDouble(secondArg);

        CalcForDividing calcForDividing=new CalcForDividing();
        assertEquals(result,calcForDividing.divide(firstArg,secondArg),DELTA);
    }

}
