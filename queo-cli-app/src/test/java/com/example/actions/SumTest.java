package com.example.actions;

import com.example.actions.impl.Sum;
import com.example.enums.Format;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SumTest {

    private final Sum sum = new Sum();

    @Test
    public void execute_FloatNumbersAndCSVFormat_outputCSVFormat() {
        // arrange, act
        List<Float> numbers = Arrays.asList(5.5f, 10.0f, 12.5f);
        String sumResult = sum.execute(numbers, Format.CSV);

        // assert
        assertEquals("28.0\n", sumResult);
    }

    @Test
    public void execute_FloatNumbersAndJSONFormat_outputJSONFormat() {
        // arrange, act
        List<Float> numbers = Arrays.asList(5.5f, 10.0f, 12.5f);
        String sumResult = sum.execute(numbers, Format.JSON);

        // assert
        assertEquals("{\"sum\":" + 28.0f + "}", sumResult);
    }

    @Test
    public void execute_emptyList_outputJSONFormat() {
        // arrange, act
        List<Float> numbers = new ArrayList<>();
        String sumResult = sum.execute(numbers, Format.JSON);

        // assert
        assertEquals("{\"sum\":" + 0.0f + "}", sumResult);
    }
}
