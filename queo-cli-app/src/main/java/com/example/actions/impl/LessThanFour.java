package com.example.actions.impl;

import com.example.actions.Action;
import com.example.enums.Format;

import java.util.List;
import java.util.stream.Collectors;

public class LessThanFour implements Action {

    @Override
    public String execute(List<Float> numbers, Format outputFormat) {
        List<Float> lessThanFour = numbers.stream()
                .filter(num -> num < 4)
                .toList();

        return outputFormat(lessThanFour, outputFormat);
    }

    private String outputFormat(List<Float> numbers, Format format) {
        return switch (format) {
            case CSV -> numbers.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(",")) + "\n";
            case JSON -> numbers.isEmpty() ? "[]\n" : numbers.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(",", "[", "]")) + "\n";
        };
    }

}
