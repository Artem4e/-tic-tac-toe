package com.company;

public class Main {

    //Размер поля
    public static final int FIELDSIZE=3;

    public static void main(String[] args) {

        GUI app =new GUI();
        app.setVisible(true);

        Field.fieldCreate();

    }

}
