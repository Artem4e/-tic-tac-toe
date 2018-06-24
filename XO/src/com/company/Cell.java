package com.company;

public class Cell{

    public static final int FIELDSIZE=3;

    //Положение клетки в поле
    int i;
    int j;

    //Конструктор
    public Cell(int i, int j){
        //Строка
        this.i=i;
        //Столбец
        this.j=j;
    }

    //Состояние кнопки(Пустая, Х, О). По умолчанию пустая
    private enum States{NO, PLAYER, COMPUTER};
    States state=States.NO;

    //Функция ставящая Х
    public void putComp(){
        state=States.PLAYER;
    }

    //Функция ставящая О
    public void putPlayer(){
        state=States.COMPUTER;
    }

    //Функция показывающая состояние клетки
    public int stateCell(){
        return state.ordinal();
    }

    //Функция рейтинга клетки
    public int rating(){


        //Если в клетке уже стоит знак, то рейтинг =-1
        if(state.ordinal()!=0) return -1;


        //Рейтинги строки и столбца
        int LineRating=rowRating(i,2);
        int ColumnRating=columnRating(j,2);

        //Рейтинг диагонали
        int DiagonalRating=diagonalRating(i,j,2);

        return LineRating+ColumnRating+DiagonalRating;

    }

    //
    //Функции, используемые методом rating
    //


    //Рейтинг строки
    private static int rowRating(final int ii, final int sign){
        //Кол-во моих
        int countMy=0;
        //Кол-во чужих
        int countStrangers=0;

        //Счетчик кол-ва своих и чужих знаков в ряду
        for (int k=0; k<FIELDSIZE; k++){
            if(Field.field[ii][k].stateCell()==sign) countMy++;
            if(Field.field[ii][k].stateCell()!=sign && Field.field[ii][k].stateCell()!=0) countStrangers++;
        }

        return calculateRating(countMy, countStrangers);
    }

    //Рейтинг столбца
    private static int columnRating(final int jj, final int sign){
        //Кол-во моих
        int countMy=0;
        //Кол-во чужих
        int countStrangers=0;

        //Счетчик кол-ва своих и чужих знаков в ряду
        for (int k=0; k<FIELDSIZE; k++){
            if(Field.field[k][jj].stateCell()==sign) countMy++;
            if(Field.field[k][jj].stateCell()!=sign && Field.field[k][jj].stateCell()!=0) countStrangers++;
        }

        return calculateRating(countMy, countStrangers);
    }

    //Рейтинг диагонали
    private static int diagonalRating(final int ii, final int jj, final int sign){

        int countMy=0;
        int countStrangers=0;



        //Если клетка не расположена на диагонали, то возвращается 0
        if(ii==jj){

            for(int i=0; i<FIELDSIZE; i++){
                if(Field.field[i][i].stateCell()==sign) countMy++;
                if(Field.field[i][i].stateCell()!=sign && Field.field[i][i].stateCell()!=0) countStrangers++;
            }


        }else if(ii+jj==FIELDSIZE-1){

            for (int i = 0; i < FIELDSIZE; i++) {
                if(Field.field[i][FIELDSIZE-1-i].stateCell()==sign) countMy++;
                if (Field.field[i][FIELDSIZE-1-i].stateCell()!=sign && Field.field[i][FIELDSIZE-1-i].stateCell()!=0) countStrangers++;
            }


        }else return 0;

        return calculateDiagonalRating(countMy, countStrangers);
    }

    //Вычисление рейтинга по кол-ву своих и чужих знаков
    private static int calculateRating(int countMy, int countStrangers){
        if(countMy==2) return 8;

        else if(countStrangers==2) return 7;

        else if (countMy==1) return 3;

        else if(countStrangers==1) return 2;

        else if(countMy==1 && countStrangers==1) return 0;

            //Если весь ряд пустой
        else return 1;
    }

    //Вычисление рейтинга для диагонали
    private static int calculateDiagonalRating(int countMy, int countStrangers){

        if(countMy==2) return 8;

        else if(countStrangers==2) return 7;

        else if(countMy==1 && countStrangers==1) return 4;

        else if(countMy==1) return 3;

        else if(countStrangers==1) return 2;

        else return 1;



    }
}
