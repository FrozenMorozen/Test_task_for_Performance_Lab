package Testing;


import com.performancelab.calc.CalcForDividing;
import javax.swing.JTextField;

import org.junit.Test;
import static org.junit.Assert.*;

public class CalcForDividingTest {

    private static final double DELTA = 1e-15;

    @Test
    public void testActionPerformed(){
        double minForRnd=0.265;
        double maxForRnd=1000.6632;
        double[] number=new double[10];

        CalcForDividing calcForDividing=new CalcForDividing();
        JTextField textField=new JTextField();

        for (int i=0;i<number.length;i++){
            number[i]=rnd(minForRnd, maxForRnd);
            textField.setText(String.valueOf(number[i]));
            boolean testCurrentValue=calcForDividing.checkCurrentValue(textField);
            assertTrue("Значение верно",testCurrentValue);
            //assertFalse("Значение неверно",testCurrentValue);
        }
    }

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

    private static double rnd(double min, double max)
    {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }




}
