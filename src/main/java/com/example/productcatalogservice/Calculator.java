package com.example.productcatalogservice;

public class Calculator {

    public Calculator() {}

    public int add(int a, int b) {
        return a + b;
    }

    public int divide(int a, int b) {
        try{
            return a / b;
        }
        catch(ArithmeticException e){
            throw new ArithmeticException("Divide by zero");
        }
    }
}
