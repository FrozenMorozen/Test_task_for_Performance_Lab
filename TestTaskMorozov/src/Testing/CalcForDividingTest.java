package Testing;


import com.performancelab.calc.CalcForDividing;
import javax.swing.JTextField;

import org.junit.Test;
import static org.junit.Assert.*;

public class CalcForDividingTest {

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
    void testDivide(){
        double minForRnd=5;
        double maxForRnd=1;
        CalcForDividing calcForDividing=new CalcForDividing();
        assertEquals(5,calcForDividing.divide(minForRnd,maxForRnd));
    }

    private static double rnd(double min, double max)
    {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }




}
