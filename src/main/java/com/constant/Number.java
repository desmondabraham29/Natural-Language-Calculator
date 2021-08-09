package com.constant;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum Number {
    ZERO("zero",0), ONE("one",1), TWO("two",2), THREE("three",3),
    FOUR("four",4), FIVE("five",5), SIX("six",6), SEVEN("seven",7),
    EIGHT("eight",8), NINE("nine",9);

    private final String name;
    private final int value;
    private Number(String name, int value){
        this.name=name;
        this.value=value;
    }

    private static final Map<String,Number> nameToNumber = new HashMap<>();
    static{
        Arrays.stream(Number.values()).forEach(number ->{
            nameToNumber.put(number.name,number);
        });
    }


    public static boolean isNumber(String token) {
        return nameToNumber.containsKey(token);
    }

    public static Number fromName(String numberName) {
        return Optional.ofNullable(nameToNumber.get(numberName)).orElseThrow(() -> new IllegalArgumentException("Unable to find number from name" + numberName));
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

}
