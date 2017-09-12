package Testing;


import com.performancelab.calc.CalcForDividing;
import javax.swing.JTextField;

import org.junit.Test;
import static org.junit.Assert.*;

public class CalcForDividingTest {

    private static final double DELTA = 1e-15;

    @Test
    public void testDivideWholeNumbers() throws Exception {
        double firstArg=54;
        double secondArg=6;
        CalcForDividing calcForDividing=new CalcForDividing();
        assertEquals(firstArg/secondArg,calcForDividing.divide(firstArg,secondArg),DELTA);
    }

    @Test(expected = Exception.class)
    public void testDivideOnZero() throws Exception {
        double firstArg=5;
        double secondArg=0;
        CalcForDividing calcForDividing=new CalcForDividing();
        assertEquals(firstArg/secondArg,calcForDividing.divide(firstArg,secondArg),DELTA);
    }

    @Test
    public void testDivideHardNumber() throws Exception {
        double firstArg=5.456168318;
        double secondArg=12.416816;
        CalcForDividing calcForDividing=new CalcForDividing();
        assertEquals(firstArg/secondArg,calcForDividing.divide(firstArg,secondArg),DELTA);
    }




}
