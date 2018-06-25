package com.company;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI extends JFrame{

    public static String PlayerSign="X";
    public static String CompSign="O";

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

    //Пустое место в конец
    private JLabel Place[]=new JLabel[Field.FIELDSIZE-3];

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
        container.setLayout(new GridLayout(Field.FIELDSIZE+1,Field.FIELDSIZE,2,2));

        container.add(Space);
        container.add(LableCount);
        container.add(Count);

        //Добавляем пустое место в конец
        for (int i=0; i<Field.FIELDSIZE-3; i++){
            Place[i]=new JLabel(" ");
            container.add(Place[i]);
        }

        for (int i=0; i<Field.FIELDSIZE; i++){
            for(int j=0; j<Field.FIELDSIZE; j++){
                button[i][j]=new JButton("");
                button[i][j].addActionListener(new ButtonAction(i,j));
                container.add(button[i][j]);
            }
        }

    }

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

            if(I <Field.FIELDSIZE*Field.FIELDSIZE && Field.checkWin()==0) {

                if(button[i][j].getText()==""){
                    //Счетчик ходов
                    I++;

                    Field.addSign(i, j, true);
                    button[i][j].setText(PlayerSign);
                    PlayerSet=true;
                } else JOptionPane.showMessageDialog(null,"Эта клетка занята","Ошибка",JOptionPane.PLAIN_MESSAGE);

            } else EndGame=true;

            if(I <Field.FIELDSIZE*Field.FIELDSIZE && Field.checkWin()==0) {
                if(PlayerSet) {
                    //Счетчик ходов
                    I++;

                    PlayerComp playerComp = new PlayerComp();
                    playerComp.set();
                    button[playerComp.ii][playerComp.jj].setText(CompSign);

                    if(I>=Field.FIELDSIZE*Field.FIELDSIZE) EndGame=true;
                }
            } else EndGame=true;

            if(EndGame && Field.checkWin()==0) {
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

                //Ходим по очереди
                if(Main.FirstComp) Main.FirstComp=false;
                else {
                    //Меняем знаки
                    String A=PlayerSign;
                    PlayerSign=CompSign;
                    CompSign=A;

                    PlayerComp playerComp = new PlayerComp();
                    playerComp.set();
                    button[playerComp.ii][playerComp.jj].setText(CompSign);
                    I++;
                    Main.FirstComp=true;
                }
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

                //Ходим по очереди
                if(Main.FirstComp) Main.FirstComp=false;
                else {
                    //Меняем знаки
                    String A=PlayerSign;
                    PlayerSign=CompSign;
                    CompSign=A;

                    PlayerComp playerComp = new PlayerComp();
                    playerComp.set();
                    button[playerComp.ii][playerComp.jj].setText(CompSign);
                    I++;
                    Main.FirstComp=true;
                }

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

                //Ходим по очереди
                if(Main.FirstComp) Main.FirstComp=false;
                else {
                    //Меняем знаки
                    String A=PlayerSign;
                    PlayerSign=CompSign;
                    CompSign=A;

                    PlayerComp playerComp=new PlayerComp();
                    playerComp.set();
                    button[playerComp.ii][playerComp.jj].setText(CompSign);
                    I++;
                    Main.FirstComp=true;
                }

            }

        }

    }
}
