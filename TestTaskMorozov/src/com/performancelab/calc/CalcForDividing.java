package com.performancelab.calc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * class  CalcForDividing
 * Основной класс инициализации рабочей области приложения
 *
 * @version 1.0.0
 * @author Morozov Denis
 */
public class  CalcForDividing {

    private JPanel rootPanel;
    private JTextField divisibleTextField;
    private JTextField divisorTextField;
    private JButton equalSignButton;
    private JTextField resultTextField;
    private JLabel divisibleLabel;
    private JLabel divisorLabel;
    private JLabel resultRoundCommentLabel;
    private char cop;


    /**
     * Создание главного фрейма приложения,
     * установка видимости, размеров, иконки
     * @return экземпляр JFrame с установленными параметрами
     */

    JFrame initJFrame(){
        JFrame jFrame=new JFrame() {};
        jFrame.setContentPane(new CalcForDividing().rootPanel);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Toolkit toolkit=Toolkit.getDefaultToolkit();
            Dimension dimension=toolkit.getScreenSize();
            jFrame.setBounds(dimension.width/2-250,dimension.height/2-150,600,120);
        jFrame.setTitle("Calculator for dividing");
            ImageIcon icon = new ImageIcon("src/resources/division.png");
            jFrame.setIconImage(icon.getImage());
        divisibleTextField.setFocusable(true);
        return jFrame;
    }

    /**
     * Округляет входящее значение double
     * @param resultValue- частное в формате double
     * @return округлённое значение resultValue
     */
    private String roundResultValue(double resultValue) {
        String resultStr = String.valueOf(resultValue);
        if (resultStr.length() > 10) {
            if (resultStr.indexOf(".") < 3 && resultStr.contains(".")) {
                resultValue = new BigDecimal(resultValue).setScale(8, RoundingMode.DOWN).doubleValue();
                resultRoundCommentLabel.setText("Результат был округлен");
                resultStr=String.valueOf(String.format("%.8f%n",resultValue));
            } else {
                //округляем целую часть
                resultValue = Math.round(resultValue);
                resultStr=String.valueOf(resultValue);
            }
        }
        return resultStr;
    }



    public CalcForDividing() {

        /**
         * Обработчик событий кнопки "="
         */
        equalSignButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //resultTextField.size(resultTextField.getText().length() * 7);
                switch (divisorTextField.getText()){
                    case "":
                        JOptionPane.showMessageDialog(null,
                                    "Введите значение в поле \""+divisorLabel.getText()+"\"");
                        resultTextField.setText("");
                        divisorTextField.requestFocus();
                        break;
                    case"0":
                        JOptionPane.showMessageDialog(null,
                                    "Деление на ноль невозможно");
                        resultTextField.setText("");
                        divisorTextField.requestFocus();
                        divisorTextField.selectAll();
                        break;
                    default:
                        if (checkCurrentValue(divisibleTextField)){
                            if (divisibleTextField.getText().equals("")){
                                JOptionPane.showMessageDialog(null,
                                        "Введите значение в поле \""+divisibleLabel.getText()+"\"");
                                resultTextField.setText("");
                                divisibleTextField.requestFocus();
                            } else {
                                double result=Double.parseDouble(divisibleTextField.getText()) /
                                        Double.parseDouble(divisorTextField.getText());
                                resultTextField.setText(roundResultValue(result));
                            }
                        }

                        break;
                }
            }
        });

        /**
         *  Обработчик событий нажатия клавиш в divisibleTextField
         *  При нажатии "Enter" фокус перемещается на следущее текстовое поле
         */
        divisibleTextField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar()==KeyEvent.VK_ENTER){
                    divisorTextField.requestFocus();
                }
            }
        });

        /**
         * Обработчик событий нажатия клавиш в divisorTextField
         * При нажатии "Enter" фокус перемещается на кнопку "="
         */
        divisorTextField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar()==KeyEvent.VK_ENTER){
                    equalSignButton.requestFocus();
                }
            }
        });
    }

    public boolean checkCurrentValue(JTextField jTextField) {
        int dotCount = 0;
        char[] numbers=new char[]{'0','1','2','3','4','5','6','7','8','9','.'};
        boolean isNumber=true;

        for (char elementTextField : jTextField.getText().toCharArray()) {
            if (elementTextField == '.') {
                dotCount++;
                continue;
            }
            if (!isNumber || dotCount>1){
                if (!isNumber) {
                    JOptionPane.showMessageDialog(null,
                            "Введено неверное значение");
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Введено слишком много разделителей ("+dotCount+")");
                }
                jTextField.requestFocus();
                jTextField.selectAll();
                return false;
            }
            isNumber=false;
            for (char elementNumbers: numbers) {
                if (!isNumber){
                    if (elementTextField==elementNumbers) {
                        isNumber=true;
                    }
                } else break;
            }
        }
        return true;
    }
}
