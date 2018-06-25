package com.company;

public class PlayerComp {


    public int ii;
    public int jj;

    public void set(){

        byte max=0;

        ii=0;
        jj=0;

        for(int i=0; i<Field.FIELDSIZE; i++){
            for (int j=0; j<Field.FIELDSIZE; j++){
                byte m=Field.field[i][j].rating();
                if(m>max){
                    max=m;
                    ii=i;
                    jj=j;
                }
            }
        }

        //Ход компьютера
        Field.addSign(ii,jj,false);

    }

}
