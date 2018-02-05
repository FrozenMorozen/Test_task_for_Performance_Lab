package Testing;


import com.performancelab.calc.CalcForDividing;

import org.junit.Test;
import static org.junit.Assert.*;

public class CalcForDividingTest {

    private static final double DELTA = 1e-15;

    @Test
    public void divideWholeNumbers() throws Exception {
        CalcForDividing calcForDividing=new CalcForDividing();

    assertEquals(2,calcForDividing.divide("4","2"),DELTA);
    assertEquals(2,calcForDividing.divide("66","33"),DELTA);
    assertEquals(2,calcForDividing.divide("368","184"),DELTA);
    assertEquals(55187,calcForDividing.divide("165561","3"),DELTA);
    assertEquals(20,calcForDividing.divide("1000000","50000"),DELTA);
    assertEquals(25000006,calcForDividing.divide("100000024","4"),DELTA);

        assertEquals(4,calcForDividing.divide("12","3"),DELTA);
}

    @Test
    public void divideRationalNumber() throws Exception {
        CalcForDividing calcForDividing=new CalcForDividing();

        assertEquals(2.13,calcForDividing.divide("4.26","2"),DELTA);
        assertEquals(1.987384155455904,calcForDividing.divide("66.478","33.45"),DELTA);
        assertEquals(2.0022491847826087,calcForDividing.divide("368.41385","184"),DELTA);
        assertEquals(53006.70935518985,calcForDividing.divide("165561.156","3.1234"),DELTA);
    }


    @Test(expected = NumberFormatException.class)
    public void onlyClickEqualButton() throws Exception {
        String divisible="";
        String divisor="";
        double result= Double.parseDouble(divisible)
                /Double.parseDouble(divisor);

        CalcForDividing calcForDividing=new CalcForDividing();
        assertEquals(result,calcForDividing.divide(divisible,divisor),DELTA);
    }

    @Test(expected = NumberFormatException.class)
    public void onlyDevideValue() throws Exception {
        String divisible="2";
        String divisor="";
        double result= Double.parseDouble(divisible)
                /Double.parseDouble(divisor);

        CalcForDividing calcForDividing=new CalcForDividing();
        assertEquals(result,calcForDividing.divide(divisible,divisor),DELTA);
    }

    @Test(expected = NumberFormatException.class)
    public void invalidValuesOfTextFieldDivisible() throws Exception {
        String divisible="jhg";
        String divisor="";
        double result= Double.parseDouble(divisible)
                /Double.parseDouble(divisor);

        CalcForDividing calcForDividing=new CalcForDividing();
        assertEquals(result,calcForDividing.divide(divisible,divisor),DELTA);
    }

    @Test(expected = ArithmeticException.class)
    public void divideOnZero() throws Exception {
        String divisible="5";
        String divisor="0";
        double result= Double.parseDouble(divisible)
                      /Double.parseDouble(divisor);

        CalcForDividing calcForDividing=new CalcForDividing();
        assertEquals(result,calcForDividing.divide(divisible,divisor),DELTA);
    }

    @Test(expected = NumberFormatException.class)
    public void invalidValuesOfTextFieldDivisor() throws Exception {
        String divisible="54";
        String divisor="jhg";
        double result= Double.parseDouble(divisible)
                      /Double.parseDouble(divisor);

        CalcForDividing calcForDividing=new CalcForDividing();
        assertEquals(result,calcForDividing.divide(divisible,divisor),DELTA);
    }

    @Test(expected = NumberFormatException.class)
    public void invalidValuesOfTextFieldDot() throws Exception {
        String divisible=".";
        String divisor="12";
        double result= Double.parseDouble(divisible)
                /Double.parseDouble(divisor);

        CalcForDividing calcForDividing=new CalcForDividing();
        assertEquals(result,calcForDividing.divide(divisible,divisor),DELTA);
    }

    @Test(expected = NumberFormatException.class)
    public void invalidValuesOfTextFieldDots() throws Exception {
        String divisible=".............";
        String divisor="12";
        double result= Double.parseDouble(divisible)
                /Double.parseDouble(divisor);

        CalcForDividing calcForDividing=new CalcForDividing();
        assertEquals(result,calcForDividing.divide(divisible,divisor),DELTA);
    }

}
