package com.company;

public class PlayerComp {

    public static final int FIELDSIZE=3;

    public int Iret=0;
    public int Jret=0;

    public void set(){

        int max=0;

        int ii=0;
        int jj=0;

        for(int i=0; i<FIELDSIZE; i++){
            for (int j=0; j<FIELDSIZE; j++){
                int m=Field.field[i][j].rating();
                if(m>max){
                    max=m;
                    ii=i;
                    jj=j;
                }
                System.out.println(i+" "+j+" "+m);
            }
        }

        //Ход компьютера
        Field.addSign(ii,jj,false);

        Iret=ii;
        Jret=jj;

        System.out.println(ii+" "+jj);

    }

}
