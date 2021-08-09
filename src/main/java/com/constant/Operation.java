package com.constant;

import java.util.*;

public enum Operation {

    ADD("+",1,"add","plus") {
        @Override
        public double apply(double operand1, double operand2){
            return  operand1 + operand2;
        }
    },

    SUBTRACT("-",2,"subtract", "minus"){
        @Override
        public double apply (double operand1, double operand2){
            return  operand1 - operand2;
        }
    },

    MULTIPLY("*",3,"multiply-by","times"){
        @Override
        public double apply (double operand1, double operand2){
            return  operand1 * operand2;
        }
    },

    DIVIDE("/",4,"divide-by","over"){
        @Override
        public double apply (double operand1, double operand2){
            return  operand1 / operand2;
        }
    };

    private final String symbol;

    private final int precedence;

    private final Set<String> aliases;

    private Operation(String symbol, int precedence, String ... aliases){
        this.symbol = symbol;
        this.precedence = precedence;
        this.aliases = new HashSet<String>();
        this.aliases.add(symbol);
        Collections.addAll(this.aliases, aliases);
    }

    private static final Map<String, Operation> aliasToOperator = new HashMap<>();
    static {
        Arrays.stream(Operation.values()).forEach(operator -> {
            operator.aliases.stream().forEach(alias -> {
                aliasToOperator.put(alias, operator);
            });
        });
    }

    public static boolean isOperator(String token) {
        return aliasToOperator.containsKey(token);
    }

    public static Operation fromAlias(String operatorAlias) {
        return Optional.ofNullable(aliasToOperator.get(operatorAlias)).orElseThrow(() -> new IllegalArgumentException("Unable to find operator from alias" + operatorAlias));
    }

    public boolean hasLessPriorityThan(Operation that) {
        return this.precedence < that.precedence;
    }

    public abstract double apply(double operand1, double operand2);

    @Override
    public String toString() {
        return symbol;
    }

}
