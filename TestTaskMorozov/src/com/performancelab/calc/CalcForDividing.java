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
     * @return
     */
    private String roundResultValue(double resultValue) {
        String resultStr = String.valueOf(resultValue);
        if (resultStr.length() > 10) {
            if (resultStr.indexOf(".") < 3 && resultStr.contains(".")) {
                resultValue = new BigDecimal(resultValue).setScale(8, RoundingMode.DOWN).doubleValue();
                resultRoundCommentLabel.setText("Результат был округлен");
                ActionListener listener = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        resultRoundCommentLabel.setText("");
                    }
                };
                Timer timer = new Timer( 2000, listener );
                timer.start();

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
         * Слушатель событий кнопки "="
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
                        break;
                }
            }
        });

        /**
         *  Обработчик событий нажатия клавиш в divisibleTextField
         */
        divisibleTextField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                String regex="[0-9]{1,13}(\\.[0-9]*)?";
                char c = e.getKeyChar();
                if (divisibleTextField.getText().contains(".")){  //если есть точка
                        regex="\\\\d+";
                }
                if (!(divisibleTextField.getText().matches(regex)) && ((c < '0') || (c > '9')) &&
                            (c != KeyEvent.VK_BACK_SPACE) && (c != KeyEvent.VK_DELETE)){
                        e.consume();
                    }
                    if (c == KeyEvent.VK_SPACE){
                        e.consume();
                    }

//                if (divisibleTextField.getText().length()>10){
//                    e.consume();
//                    JOptionPane.showMessageDialog(null,
//                            "Максимальная длина введённого числа \n равна 10-ти символам");
//               }else if (c==KeyEvent.VK_ENTER){
//                    divisorTextField.requestFocus();
//                }
            }
        });

        /**
         * Обработчик событий нажатия клавиш в divisorTextField
         */
        divisorTextField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                formatterTextField(divisorTextField,e);

                if (e.getKeyChar()==KeyEvent.VK_ENTER){
                    equalSignButton.requestFocus();
                }
            }
        });
    }

    public void formatterTextField(JTextField jTextField,KeyEvent event){
        char c = event.getKeyChar();
        String regex="[0-9]{1,13}(\\.[0-9]*)?";

        boolean flag=false;
        if ((c < '0') || (c > '9')) {
            flag=true;
        }
        int h=0;
           for (int i=0;i<jTextField.getText().length();i++) {
               if (jTextField.getText().contains(".")) {
                   h++; //будет проверка если больше 1 то инвалид
               }
           }

           if (flag || h>1){
               JOptionPane.showMessageDialog(null,
                       "Ошибочка dsikf");
           }

        if (c==KeyEvent.VK_D) {
            JOptionPane.showMessageDialog(null,
                    "Максимальная длина введённого числа \n равна 10-ти символам");
        }
        if (jTextField.getText().indexOf('.')!=-1){  //если есть точка
            regex="\\\\d+";
        }
        if ( !jTextField.getText().matches(regex)&&((c < '0') || (c > '9')) &&
                (c != KeyEvent.VK_BACK_SPACE) && (c != KeyEvent.VK_DELETE)){
            event.consume();
        }
        if (c == KeyEvent.VK_SPACE){
            event.consume();
        }
        if (jTextField.getText().length()>10){
            event.consume();
            JOptionPane.showMessageDialog(null,
                    "Максимальная длина введённого числа \n равна 10-ти символам");
        }
    }
}
