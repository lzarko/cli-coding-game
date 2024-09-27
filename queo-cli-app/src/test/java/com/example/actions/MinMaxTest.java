package com.example.actions;

import com.example.actions.impl.MinMax;
import com.example.enums.Format;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MinMaxTest {

    private final MinMax minMax = new MinMax();

    @Test
    public void execute_FloatNumbersAndCSVFormat_outputCSVFormat() {
        // arrange, act
        List<Float> numbers = Arrays.asList(15.0f, 20.0f, 10.0f, 35.0f, 7.0f);
        String minMaxResult = minMax.execute(numbers, Format.CSV);

        // assert
        assertEquals(7.0f + ", " + 35.0f, minMaxResult);
    }

    @Test
    public void execute_FloatNumbersAndJSONFormat_outputJSONFormat() {
        // arrange, act
        List<Float> numbers = Arrays.asList(15.0f, 20.0f, 10.0f, 35.0f, 7.0f);
        String minMaxResult = minMax.execute(numbers, Format.JSON);

        // assert
        assertEquals("{\"min\":" + 7.0f + ",\"max\":" + 35.0f + "}", minMaxResult);
    }
}
