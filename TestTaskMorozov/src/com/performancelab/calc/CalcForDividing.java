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

    /**
     * Объявление UI элементов управления
     */
    private JPanel rootPanel;
    private JTextField divisibleTextField;
    private JTextField divisorTextField;
    private JButton equalSignButton;
    private JTextField resultTextField;
    private JLabel divisibleLabel;
    private JLabel divisorLabel;
    private JLabel resultRoundCommentLabel;

    private static int recomendLength=10;    //рекомендованная длина вводимых чисел

    /**
     * Конструктор класса
     */
    CalcForDividing() {

        /**
         * Обработчик событий кнопки "="
         */
        equalSignButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (divisorTextField.getText()){
                    case "":
                        clearResultFieldAndShowError(resultTextField,divisorTextField,
                                                        divisorLabel.getText());
                        break;
                    case"0":
                        clearResultFieldAndShowError(resultTextField,divisorTextField,"");
                        divisorTextField.selectAll();
                        break;
                    default:
                        if (divisibleTextField.getText().equals("")){
                            clearResultFieldAndShowError(resultTextField,divisibleTextField,
                                                            divisibleLabel.getText());
                        } else if (checkCurrentValue(divisibleTextField)
                                    && checkCurrentValue(divisorTextField)) {
                             double result=Double.parseDouble(divisibleTextField.getText()) /
                                        Double.parseDouble(divisorTextField.getText());
                             resultTextField.setText(roundResultValue(result));
                            resultTextField.requestFocus();
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
                if (e.getKeyChar()==KeyEvent.VK_ENTER)
                    divisorTextField.requestFocus();
            }
        });

        /**
         * Обработчик событий нажатия клавиш в divisorTextField
         * При нажатии "Enter" фокус перемещается на кнопку "="
         */
        divisorTextField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar()==KeyEvent.VK_ENTER)
                    equalSignButton.requestFocus();
            }
        });
    }

    /**
     * Создание главного фрейма приложения,
     * установка видимости, размеров, иконки
     * @return экземпляр JFrame с установленными параметрами
     */
    JFrame launchApp(){
        JFrame jFrame=new JFrame() {};
        jFrame.setContentPane(new CalcForDividing().rootPanel);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
     * Проверяет валидность данных в текствовом поле.
     * Выводит сообщение об ошибке.
     * @param jTextField - текстовое поле с введёнными данными
     * @return true - если введены корректные данные
     *         false - если введеные не корректные данные
     */
    private boolean checkCurrentValue(JTextField jTextField) {
        int dotCount = 0;
        char[] numbers=new char[]{'0','1','2','3','4','5','6','7','8','9','.'};
        boolean isNumber=true;

        for (char elementTextField : jTextField.getText().toCharArray()) {
            if (elementTextField == '.') {
                dotCount++;                 //счетчик точек в текстовом поле
                continue;
            }
            if (!isNumber || dotCount>1){  // если символ не число или кол-во точек > 1
                if (!isNumber) {
                    JOptionPane.showMessageDialog(null,
                            "Введено неверное значение");
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Введено слишком много разделителей ("+dotCount+")");
                }
                jTextField.requestFocus();
                jTextField.setSelectedTextColor(Color.RED);
                jTextField.selectAll();
                return false;
            }
            isNumber=false;
            for (char elementNumbers: numbers) {
                if (!isNumber){
                    if (elementTextField==elementNumbers) {  //соответствие символа строки с массивом чисел
                        isNumber=true;
                    }
                } else break;
            }
        }

        if (jTextField.getText().length()>recomendLength){
            JOptionPane.showMessageDialog(null,
                    "Значение должно содержать не более "+ recomendLength +" символов");
            jTextField.requestFocus();
            /*
            Выделение символов, находящихся за пределами рекомендованного диапозона
             */
            jTextField.setSelectedTextColor(Color.RED);
            jTextField.setSelectionStart(recomendLength);
            jTextField.setSelectionEnd(jTextField.getText().length());
            return false;
        }
        return true;
    }

    /**
     * Округляет входящее значение double.
     * @param resultValue- частное в формате double
     * @return округлённое значение resultValue
     */
    private String roundResultValue(double resultValue) {
        String resultStr = String.valueOf(resultValue);
        if (resultStr.length() > 10) {
            if (resultStr.indexOf(".") < 3 && resultStr.contains(".")) {
                resultValue = new BigDecimal(resultValue).setScale(8, RoundingMode.DOWN).doubleValue();
                resultStr=String.valueOf(String.format("%.8f%n",resultValue));
                showRoundCommentLabel(resultRoundCommentLabel);
            } else {
                resultValue = Math.round(resultValue);   //округляем целую часть
                resultStr=String.valueOf(resultValue);
                showRoundCommentLabel(resultRoundCommentLabel);
            }
        }
        return resultStr;
    }

    /**
     * Очищает текстовое поле результата деления.
     * Перемещает фокус на некорретное текстовое поле.
     * Выводит сообщение с именем некорректного текстового поля.
     * @param resultField - поле результата деления
     * @param invalidField - текстовое поле с некорректными данными
     * @param invalidFieldName - имя текстового поля с некорретными данными
     */
    private void clearResultFieldAndShowError(JTextField resultField,JTextField invalidField,String invalidFieldName){
        String message="Введите значение в поле \""+invalidFieldName+"\"";
        if (invalidFieldName.equals("")){
            message="Деление на ноль невозможно";
        }
        JOptionPane.showMessageDialog(null,message);
        resultField.setText("");
        invalidField.requestFocus();
    }

    /**
     * Отображает заметку под значением результата деления с информацией о том,
     *                                      что конечный результат был округлен.
     * @param label - текстовое поле с информацие об округлении результата
     */
    private void showRoundCommentLabel(JLabel label) {
        label.setText("Результат был округлен");
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultRoundCommentLabel.setText("");
            }
        };
        Timer timer = new Timer( 2000, listener );
        timer.start();
    }
}
