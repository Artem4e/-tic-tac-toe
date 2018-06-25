package com.company;

public class Field {

    final static int FIELDSIZE=3;
    public static Cell[][] field=new Cell[FIELDSIZE][FIELDSIZE];


    //Создание поля
    public static void fieldCreate(){
        //Поле
        for (int i=0; i<FIELDSIZE; i++){
            for (int j=0; j<FIELDSIZE; j++){
                field[i][j]= new Cell(i, j);
            }
        }
    }

    //Добавление знака
    public static void addSign(int i, int j, boolean Player){
        if(Player) field[i][j].putPlayer();
        else field[i][j].putComp();
    }

    //Проверка выигрыша
    public static int checkWin(){

        //Проверка рядов на выигрыш
        for (int i = 0; i < FIELDSIZE; i++) {

            if(rowCheckWin(1)) return 1;
            if(rowCheckWin(2)) return 2;

        }

        //Проверка столбцов на выигрыш
        for (int i = 0; i < FIELDSIZE; i++) {

            if(columnCheckWin(1)) return 1;
            if(columnCheckWin(2)) return 2;

        }

        //Проверка диагонали на выигрыш
        if(diagonalCheckWin(1)) return 1;
        if (diagonalCheckWin(1)) return 1;

        if(diagonalCheckWin(2)) return 2;
        if(diagonalCheckWin(2)) return 2;
        return 0;
    }


    //Функции, используемые в checkWin

    //Проверка на выигрыш в строках
    private static boolean rowCheckWin(final int sign){
        //Кол-во моих
        int count=0;

        //Счетчик кол-ва своих и чужих знаков в ряду
        for (int k=0; k<FIELDSIZE; k++){
            for (int i = 0; i < FIELDSIZE; i++) {
                if(field[k][i].stateCell()==sign) count++;
                else if(count==3) return true;
                else count=0;
            }

            if(count==3) return true;
            count=0;
        }

        return false;
    }


    //Проверка на выигрыш в столбцах
    private static boolean columnCheckWin(final int sign){

        //Кол-во моих
        int count=0;

        //Счетчик кол-ва своих и чужих знаков в ряду
        for (int k=0; k<FIELDSIZE; k++){
            for (int i = 0; i < Field.FIELDSIZE; i++) {
                if(Field.field[i][k].stateCell()==sign) count++;
                else if(count==3) return true;
                else count=0;
            }
            if (count==3) return true;
            count=0;

        }

        return false;
    }


    //Проверка на выигрыш по диагонали
    private static boolean diagonalCheckWin(final int sign){

        int count=0;

        //Проверяем поле по диагонали справа на лево
        for(int i=0; i<FIELDSIZE; i++){
            if(Field.field[i][i].stateCell()==sign) count++;
            else if (count==3) return true;
            else count=0;
        }

        if(count==3) return true;
        count=0;

        //Проверяем по второй диагонали
        for (int i = 0; i < FIELDSIZE; i++) {
            if(Field.field[i][FIELDSIZE-1-i].stateCell()==sign) count++;
            else if(count==3) return true;
            else count=0;
        }

        if(count==3) return true;

        return false;
    }
}
