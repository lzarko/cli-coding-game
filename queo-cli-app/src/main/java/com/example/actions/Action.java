package com.example.actions;

import com.example.enums.Format;

import java.util.List;

public interface Action{

    String execute(List<Float> numbers, Format outputFormat);
}

