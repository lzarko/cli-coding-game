package com.example.handler;

import com.example.enums.Format;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;


public class InputHandler {

    private static final Logger LOGGER = Logger.getLogger(InputHandler.class.getName());
    private final Gson gson = new Gson();

    public List<Float> readInput(String source, Format inputFormat) throws IOException {
        List<Float> numbers = new ArrayList<>();

        try {
            if ("-".equals(source)) {
                LOGGER.info("Reading from stdin.");
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String line;
                while ((line = reader.readLine()) != null) {
                    numbers.addAll(parseLine(line, inputFormat));
                }
            } else {
                LOGGER.info("Reading from file: " + source);
                try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        numbers.addAll(parseLine(line, inputFormat));
                    }
                }
            }
        } catch (FileNotFoundException e) {
            LOGGER.severe("File not found: " + e.getMessage());
            throw e; // Rethrow to signal the caller
        } catch (IOException e) {
            LOGGER.severe("Error during I/O operation: " + e.getMessage());
            throw e; // Rethrow to signal the caller
        }

        return numbers;
    }

    private List<Float> parseLine(String line, Format inputFormat) {
        List<Float> numbers = new ArrayList<>();
        if (inputFormat == Format.CSV) {
            String[] parts = line.split(",");
            for (String part : parts) {
                try {
                    numbers.add(Float.parseFloat(part.trim()));
                } catch (NumberFormatException e) {
                    LOGGER.warning("Invalid float format: " + part);
                }
            }
        } else if (inputFormat == Format.JSON) {
            Float[] parsedNumbers = gson.fromJson(line, Float[].class);
            if (parsedNumbers != null) {
                numbers.addAll(Arrays.asList(parsedNumbers));
            }
        }
        return numbers;
    }
}
