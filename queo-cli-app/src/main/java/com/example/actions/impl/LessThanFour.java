package com.example.actions.impl;

import com.example.actions.Action;
import com.example.enums.Format;

import java.util.List;

public class LessThanFour implements Action {

    @Override
    public String execute(List<Float> numbers, Format format) {
        List<Float> lessThanFour = numbers.stream()
                .filter(num -> num < 4)
                .toList();

        if(format == Format.CSV){
            return String.join(",", lessThanFour.stream()
                    .map(String::valueOf)
                    .toArray(String[]::new));
        } else {
            return lessThanFour.isEmpty() ? "[]" : lessThanFour.toString();
        }
    }
}
