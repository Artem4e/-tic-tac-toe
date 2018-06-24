package com.company;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI extends JFrame{

    //Индикатор конца игры
    boolean EndGame=false;

    //Счет компьютера и игрока
    int CountComp=0, CountPlayer=0;

    //Число ходов
    static int I=0;

    //Кнопки поля
    private JButton[][] button=new JButton[Field.FIELDSIZE][Field.FIELDSIZE];

    //Пустое место, чтобы кнопки не сдвигались
    private JLabel Space=new JLabel(" ");

    //"Счет"
    private JLabel LableCount=new JLabel("Счет:");

    //Счет
    private JLabel Count = new JLabel("0 : 0");

    public GUI(){

        //Название формы
        super("Крестики нолики");

        this.setBounds(500,200,500,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container=this.getContentPane();
        container.setLayout(new GridLayout(4,3,2,2));

        container.add(Space);
        container.add(LableCount);
        container.add(Count);

        for (int i=0; i<Field.FIELDSIZE; i++){
            for(int j=0; j<Field.FIELDSIZE; j++){
                button[i][j]=new JButton("");
                button[i][j].addActionListener(new ButtonAction(i,j));
                container.add(button[i][j]);
            }
        }

    }

    //Функция ставящая знак в кнопку
   /*public void putSign(int i, int j, String sign){
        button[i][j].setText(sign);
    }*/

    class ButtonAction implements ActionListener{

        public int i;
        public int j;

        public ButtonAction(int i, int j){
            this.i=i;
            this.j=j;
        }

        public void actionPerformed(ActionEvent e){

            //Игрок сходил
            boolean PlayerSet=false;

            if(I <9 && Field.checkWin()==0) {

                if(button[i][j].getText()==""){
                    //Счетчик ходов
                    I++;

                    Field.addSign(i, j, true);
                    button[i][j].setText("X");
                    PlayerSet=true;
                } else JOptionPane.showMessageDialog(null,"Эта клетка занята","Ошибка",JOptionPane.PLAIN_MESSAGE);

            } else EndGame=true;

            if(I <9 && Field.checkWin()==0) {
                if(PlayerSet) {
                    //Счетчик ходов
                    I++;


                    PlayerComp playerComp = new PlayerComp();
                    playerComp.set();
                    button[playerComp.Iret][playerComp.Jret].setText("0");
                }
            } else EndGame=true;

            if(EndGame) {
                JOptionPane.showMessageDialog(null,"Ничья","Игра окончена",JOptionPane.PLAIN_MESSAGE);

                //Обнуление поля и клеток
                I=0;
                EndGame=false;
                for (int i = 0; i < Field.FIELDSIZE; i++) {
                    for(int j=0; j<Field.FIELDSIZE; j++){
                        button[i][j].setText("");
                    }
                }
                Field.fieldCreate();
            }
            if(Field.checkWin()==1) {
                JOptionPane.showMessageDialog(null,"Вы проиграли","Игра окончена",JOptionPane.PLAIN_MESSAGE);
                CountComp++;
                Count.setText(String.valueOf(CountComp)+" : "+String.valueOf(CountPlayer));

                //Обнуление поля и клеток
                I=0;
                EndGame=false;
                for (int i = 0; i < Field.FIELDSIZE; i++) {
                    for(int j=0; j<Field.FIELDSIZE; j++){
                        button[i][j].setText("");
                    }
                }
                Field.fieldCreate();

            }
            if(Field.checkWin()==2){
                JOptionPane.showMessageDialog(null,"Вы победили","Игра окончена",JOptionPane.PLAIN_MESSAGE);
                CountPlayer++;
                Count.setText(String.valueOf(CountComp)+" : "+String.valueOf(CountPlayer));

                //Обнуление поля и клеток
                I=0;
                EndGame=false;
                for (int i = 0; i < Field.FIELDSIZE; i++) {
                    for(int j=0; j<Field.FIELDSIZE; j++){
                        button[i][j].setText("");
                    }
                }
                Field.fieldCreate();
            }

        }




    }
}
