package com.company;

import java.util.Scanner;

public class Player {

    public void playerSet(){

        Scanner scan=new Scanner(System.in);
        int i=scan.nextInt();
        int j=scan.nextInt();
        Field.addSign(i,j,true);

    }

}
