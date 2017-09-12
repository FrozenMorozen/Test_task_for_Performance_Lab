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
    public CalcForDividing() {

        /*
          Обработчик событий кнопки "="
         */
        equalSignButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                switch (divisorTextField.getText()){
//                    case "":
//                        clearResultFieldAndShowError(resultTextField,divisorTextField,
//                                                        divisorLabel.getText());
//                        break;
//                    case"0":
//                        clearResultFieldAndShowError(resultTextField,divisorTextField,"0");
//                        break;
//                    default:
//                        if (divisibleTextField.getText().equals("")){
//                            clearResultFieldAndShowError(resultTextField,divisibleTextField,
//                                                            divisibleLabel.getText());
//                        } else try {
//                            if (checkCurrentValue(divisibleTextField)
//                                        && checkCurrentValue(divisorTextField)) {
//                                /*
//                                Оперция деление
//                                 */
//                                double divisible=Double.parseDouble(divisibleTextField.getText());
//                                double divisor=Double.parseDouble(divisorTextField.getText());
//                                try {
//                                    resultTextField.setText(roundResultValue(divide(divisible,divisor)));
//                                } catch (Exception e1) {
//                                    e1.printStackTrace();
//                                }
//
//                                resultTextField.requestFocus();
//                                }
//                        } catch (Exception e1) {
//                            e1.printStackTrace();
//                        }
//                        break;
//                }
                try {
                    resultTextField.setText(roundResultValue(divide(divisibleTextField.getText(),
                            divisorTextField.getText())));
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        /*
           Обработчик событий нажатия клавиш в divisibleTextField
           При нажатии "Enter" фокус перемещается на следущее текстовое поле
         */
        divisibleTextField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar()==KeyEvent.VK_ENTER)
                    divisorTextField.requestFocus();
            }
        });

        /*
          Обработчик событий нажатия клавиш в divisorTextField
          При нажатии "Enter" фокус перемещается на кнопку "="
         */
        divisorTextField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar()==KeyEvent.VK_ENTER)
                    equalSignButton.requestFocus();
            }
        });
    }

    /**
     * Создание экземпляр JFrame с установленными параметрами,
     * установка видимости, размеров, иконки
     */
    void launchApp(){
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
    }

    /**
     * Операция деления.
     * Логически основной метод класса.
     * @param divisible - делимое
     * @param divisor - делитель
     * @return значение частного
     * @throws Exception исключение в случае получения невалидных данных
     */
    public double divide(String divisible, String divisor) throws Exception {
        switch (divisor) {
            case "":
                clearResultFieldAndShowError(resultTextField,divisorTextField,
                        divisorLabel.getText());
                throw new NumberFormatException("Пустое поле "+divisorLabel.getText());
            case "0":
                clearResultFieldAndShowError(resultTextField,divisorTextField,"0");
                throw new ArithmeticException("Деление на ноль невозможно");
            default:
                if (divisible.equals("")){
                    clearResultFieldAndShowError(resultTextField,divisibleTextField,
                            divisibleLabel.getText());
                    throw new Exception("Пустое поле "+divisibleLabel.getText());
                } else if (checkCurrentValue(divisibleTextField)
                        && checkCurrentValue(divisorTextField)) {
                    return Double.parseDouble(divisible) / Double.parseDouble(divisor);
                }
                break;
        }
        return 0;
    }

    /**
     * Проверяет валидность данных в текствовом поле.
     * Выводит сообщение об ошибке.
     * @param jTextField - текстовое поле с введёнными данными
     * @return true - если введены корректные данные
     *         false - если введеные не корректные данные
     */
    private boolean checkCurrentValue(JTextField jTextField) throws Exception {
            return checkTextFieldOnLetters(jTextField)
                    && checkTextFieldOnDots(jTextField)
                    && checkLengthTextField(jTextField);

    }

    private boolean checkTextFieldOnDots(JTextField jTextField){
        int dotsCount=0;
        for (char elementTextField : jTextField.getText().toCharArray()) {
            if (elementTextField=='.'){
                dotsCount++;
            }
        }
            /*
            Проверка если введены одни точки
            */
        if (dotsCount==jTextField.getText().length()){
            clearResultFieldAndShowError(resultTextField,jTextField,
                    "Text field has ONLY dots!");
            return false;
        }else if (dotsCount>1){
            clearResultFieldAndShowError(resultTextField,jTextField,
                    "Text field has a lot of dots!");
            return false;
        }
        return true;
    }

    /**
     * Проверяет наличие букв в текстовом поле
     * @param jTextField - текстовое поле для проверки
     * @return false - если буквы в текстовом поле были найдены
     *         true - в обратном случае
     */
    private boolean checkTextFieldOnLetters(JTextField jTextField) throws Exception {
        for (char elementTextField : jTextField.getText().toCharArray()) {
            if (!Character.isDigit(elementTextField) && elementTextField!='.') {
                clearResultFieldAndShowError(resultTextField,jTextField,
                                            "Text field has letters!");
                throw new NumberFormatException("Тесктовое поле содержит нечисленые данные");
            }
        }
        return true;
    }

    /**
     * Проверяет длину значения введённого в текстовое поле
     * @param jTextField - текстовое поле
     * @return true - если длина меньше или равна 10-ти символам,
     *         false - в обратном случае
     */
    private boolean checkLengthTextField(JTextField jTextField){
        if (jTextField.getText().length()>recomendLength){
            JOptionPane.showMessageDialog(null,
                    "Значение должно содержать не более "+ recomendLength +" символов");
            /*
            Выделение символов, находящихся за пределами рекомендованного диапозона
             */
            jTextField.requestFocus();
            jTextField.setSelectedTextColor(Color.RED);
            jTextField.setSelectionStart(recomendLength);
            jTextField.setSelectionEnd(jTextField.getText().length());
            return false;
        }
        return true;
    }

    /**
     * Округляет входящее значение double.
     * @param resultValue - частное в формате double
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
     * Выводит сообщение с именем некорректного текстового поля.
     * Очищает текстовое поле результата деления.
     * Перемещает фокус на некорретное текстовое поле.
     * @param resultField - поле результата деления
     * @param invalidField - текстовое поле с некорректными данными
     * @param errorMessage - имя текстового поля с некорретными данными
     */
    private void clearResultFieldAndShowError(JTextField resultField,JTextField invalidField,String errorMessage){
        String message="Введите значение в поле \""+errorMessage+"\"";
        switch (errorMessage) {
            case "0":
                message = "Деление на ноль невозможно";
                break;
            case "Text field has letters!":
                message = "Введено неверное значение \n'" + invalidField.getText() + "'";
                break;
            case "Text field has a lot of dots!":
                message = "Введено слишком много разделителей '.'";
                break;
            case "Text field has ONLY dots!":
                message = "Введите число вместо " + invalidField.getText();
                break;
        }
        JOptionPane.showMessageDialog(null,message);
        resultField.setText("");
        invalidField.requestFocus();
        invalidField.setSelectedTextColor(Color.RED);
        invalidField.selectAll();
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
