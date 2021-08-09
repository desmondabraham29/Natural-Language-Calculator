package com.nlc;

import com.constant.Operation;
import com.constant.Number;

import java.util.*;
import java.util.stream.Collectors;

public class NaturalLanguageCalculator {

    public NaturalLanguageCalculator(){

    }

    public double performCalculation(String calculation){
        List<String> infixRepresentation = getInfixRepresentationFromString(calculation);
        List<String> postfixRepresentation = getPostfixRepresentationFromInfixRepresentation(infixRepresentation);
        return evaluatePostfixRepresentation(postfixRepresentation);
    }

    private List<String> getInfixRepresentationFromString(String calculation) {
        return Arrays.stream(calculation.split("\\s+")).map(token -> {
            if (Operation.isOperator(token)) {
                return Operation.fromAlias(token).toString();
            } else if (Number.isNumber(token)) {
                return Number.fromName(token).toString();
            } else {
                throw new IllegalArgumentException("Unable to parse token " + token);
            }
        }).collect(Collectors.toList());
    }

    private static List<String> getPostfixRepresentationFromInfixRepresentation(List<String> infixRepresentation) {

        List<String> output = new ArrayList<>();
        Deque<String> stack  = new LinkedList<>();

        for (String token : infixRepresentation) {

            if (Operation.isOperator(token)) {
                Operation operator = Operation.fromAlias(token);
                while (!stack.isEmpty() && operator.hasLessPriorityThan(Operation.fromAlias(stack.peek()))) {
                    output.add(stack.pop());
                }
                stack.push(token);
            } else {
                output.add(token);
            }

        }

        while (!stack.isEmpty()) {
            output.add(stack.pop());
        }

        return output;
    }

    private static double evaluatePostfixRepresentation(List<String> postfixRepresentation) {

        Deque<String> stack  = new LinkedList<>();

        for (String token : postfixRepresentation) {

            if (Operation.isOperator(token)) {
                Operation operator = Operation.fromAlias(token);
                double operand2 = Double.parseDouble(stack.pop());
                double operand1 = Double.parseDouble(stack.pop());
                double result = operator.apply(operand1, operand2);
                stack.push(String.valueOf(result));
            } else {
                stack.push(token);
            }

        }

        return Double.parseDouble(stack.pop());
    }

}
