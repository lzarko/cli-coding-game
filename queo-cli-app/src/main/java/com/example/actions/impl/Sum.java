package com.example.actions.impl;

import com.example.actions.Action;
import com.example.enums.Format;

import java.util.List;

public class Sum implements Action {
    @Override
    public String execute(List<Float> numbers, Format outputFormat) {
        float sum = 0;
        for(float num : numbers) {
            sum += num;
        }

        return outputFormat(sum, outputFormat);
    }

    private String outputFormat(Float sum, Format format) {
        return switch (format) {
            case CSV -> sum + "\n";
            case JSON -> "{\"sum\":" + sum + "}";
        };
    }
}
