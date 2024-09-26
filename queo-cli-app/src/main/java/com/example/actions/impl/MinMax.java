package com.example.actions.impl;

import com.example.actions.Action;
import com.example.enums.Format;

import java.util.Collections;
import java.util.List;

public class MinMax implements Action {
    @Override
    public String execute(List<Float> numbers, Format format) {
        if(numbers.isEmpty()) {
            return "";
        }

        float max = Collections.max(numbers);
        float min = Collections.min(numbers);

        return outputFormat(max, min, format);
    }

    private String outputFormat(Float max, Float min, Format format) {
        return switch (format) {
            case CSV -> min + ", " + max;
            case JSON -> "{\"min\":" + min + ",\"max\":" + max + "}";
        };
    }
}
