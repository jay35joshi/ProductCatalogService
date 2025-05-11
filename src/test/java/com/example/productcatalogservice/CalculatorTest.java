package com.example.productcatalogservice;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    //one unit test for one method
    //naming convention: methodName_when_then

    @Test
    public void AdditionOn2Integers_RunsSuccessfully() {
        //Arrange
        Calculator calculator = new Calculator();

        //Act
        int res = calculator.add(3,5);

        //Assert
        assert(res == 8);
    }

    @Test
    public void DivideByZero_ThrowsException() {
        //Arrange
        Calculator calculator = new Calculator();

        //Act and assert
        //in case when you know it will throw exception
        assertThrows(ArithmeticException.class, () -> calculator.divide(5,0));
    }




}