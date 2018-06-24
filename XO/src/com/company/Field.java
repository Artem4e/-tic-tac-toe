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

            if(rowCheckWin(i,1)==3) return 1;
            if(rowCheckWin(i,2)==3) return 2;

        }

        //Проверка столбцов на выигрыш
        for (int i = 0; i < FIELDSIZE; i++) {

            if(columnCheckWin(i,1)==3) return 1;
            if(columnCheckWin(i,2)==3) return 2;

        }

        //Проверка диагонали на выигрыш
        if(diagonalCheckWin(0,0,1) ==3) return 1;
        if (diagonalCheckWin(0,2,1)==3) return 1;

        if(diagonalCheckWin(0,0,2)==3) return 2;
        if(diagonalCheckWin(0,2,2)==3) return 2;
        return 0;
    }


    //Функции, используемые в checkWin

    //Проверка на выигрыш в строках
    private static int rowCheckWin(final int ii, final int sign){
        //Кол-во моих
        int count=0;

        //Счетчик кол-ва своих и чужих знаков в ряду
        for (int k=0; k<FIELDSIZE; k++){
            if(Field.field[ii][k].stateCell()==sign) count++;
        }

        return count;
    }


    //Проверка на выигрыш в столбцах
    private static int columnCheckWin(final int jj, final int sign){

        //Кол-во моих
        int count=0;

        //Счетчик кол-ва своих и чужих знаков в ряду
        for (int k=0; k<FIELDSIZE; k++){
            if(Field.field[k][jj].stateCell()==sign) count++;
        }

        return count;
    }


    //Проверка на выигрыш по диагонали
    private static int diagonalCheckWin(final int ii, final int jj, final int sign){

        int count=0;

        //Если клетка не расположена на диагонали, то возвращается 0
        if(ii==jj){

            for(int i=0; i<FIELDSIZE; i++){
                if(Field.field[i][i].stateCell()==sign) count++;
            }


        }else if(ii+jj==FIELDSIZE-1){

            for (int i = 0; i < FIELDSIZE; i++) {
                if(Field.field[i][FIELDSIZE-1-i].stateCell()==sign) count++;
            }


        }else return 0;

        return count;
    }
}
