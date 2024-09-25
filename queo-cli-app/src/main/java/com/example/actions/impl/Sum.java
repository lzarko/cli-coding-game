package com.example.actions.impl;

import com.example.actions.Action;
import com.example.enums.Format;

import java.util.List;

public class Sum implements Action {
    @Override
    public String execute(List<Float> numbers, Format format) {
        float sum = 0;
        for(float num : numbers) {
            sum += num;
        }

        return format == Format.CSV ? String.valueOf(sum) : "{\"sum\":" + sum + "}";
    }
}
