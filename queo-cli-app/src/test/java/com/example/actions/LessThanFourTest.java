package com.example.actions;

import com.example.actions.impl.LessThanFour;
import com.example.enums.Format;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LessThanFourTest {

    private final LessThanFour lessThanFour = new LessThanFour();

    @Test
    public void execute_FloatNumbersAndCSVFormat_outputCSVFormat() {
        // arrange, act
        List<Float> numbers = Arrays.asList(4.0f, 2.5f, 4.1f, 1.7f);
        String LT4Result = lessThanFour.execute(numbers, Format.CSV);

        assertEquals("2.5,1.7\n", LT4Result);
    }

    @Test
    public void execute_FloatNumbersAndJSONFormat_outputJSONFormat() {
        // arrange, act
        List<Float> numbers = Arrays.asList(4.0f, 2.5f, 4.1f, 1.7f);
        String LT4Result = lessThanFour.execute(numbers, Format.JSON);

        // assert
        assertEquals("[2.5,1.7]\n", LT4Result);
    }

    @Test
    public void execute_emptyList_outputJSONFormat() {
        // arrange, act
        List<Float> numbers = new ArrayList<>();
        String LT4Result = lessThanFour.execute(numbers, Format.JSON);

        // assert
        assertEquals("[]\n", LT4Result);
    }
}
