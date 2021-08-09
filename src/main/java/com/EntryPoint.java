package com;

import com.nlc.NaturalLanguageCalculator;

import java.text.DecimalFormat;
import java.util.Scanner;

public class EntryPoint {

    public static void main(String args[]){
        System.out.println("Please enter a calculation:");
        NaturalLanguageCalculator nlc= new NaturalLanguageCalculator();
        String calculation = readCalculationFromStandardInput();
        double result =  nlc.performCalculation(calculation);
        System.out.println("result  " +result);
    }

    private static String readCalculationFromStandardInput() {
        try (Scanner scanner = new Scanner(System.in)) {
            return scanner.nextLine();
        }
    }

    private static String formatResult(double result) {
        return new DecimalFormat("0.##").format(result);
    }
}
