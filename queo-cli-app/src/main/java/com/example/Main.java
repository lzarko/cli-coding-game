package com.example;

import com.example.actions.Action;
import com.example.actions.impl.LessThanFour;
import com.example.actions.impl.MinMax;
import com.example.actions.impl.Sum;
import com.example.enums.Format;
import com.example.handler.InputHandler;
import com.example.handler.OutputHandler;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        String inputSource = "-";
        String outputDestination = "-";
        Format inputFormat = Format.CSV;
        Format outputFormat = Format.CSV;
        Action action = null;

        logger.info("CLI options: " + String.join(" ", args));

        try {
            for (int i = 0; i < args.length; i++) {
                switch (args[i]) {
                    case "-i":
                        inputSource = args[++i];
                        break;
                    case "-o":
                        outputDestination = args[++i];
                        break;
                    case "-f":
                        inputFormat = parseFormat(args[++i]);
                        break;
                    case "-F":
                        outputFormat = parseFormat(args[++i]);
                        break;
                    case "-a":
                        action = parseAction(args[++i]);
                        break;
                    default:
                        logger.severe("Unknown option: " + args[i]);
                        return;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            logger.severe("Missing value for an option: " + e.getMessage());
            return;
        }

        if (action == null) {
            logger.severe("Error: No action specified.");
            return;
        }

        InputHandler inputHandler = new InputHandler();
        List<Float> numbers;
        try {
            logger.info("Opening input source: " + inputSource);
            numbers = inputHandler.readInput(inputSource, inputFormat);
            logger.info("Closing input source: " + inputSource);

            if (numbers.isEmpty()) {
                logger.severe("Error: Input is empty.");
                return;
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error reading input: " + e.getMessage(), e);
            return;
        }

        String output;
        try {
            output = action.execute(numbers, outputFormat);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error executing action: " + e.getMessage(), e);
            return;
        }

        OutputHandler outputHandler = new OutputHandler();
        try {
            logger.info("Writing output to: " + outputDestination);
            outputHandler.writeOutput(output, outputDestination);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error writing output: " + e.getMessage(), e);
        }

        logger.info("Program completed successfully.");
    }

    private static Format parseFormat(String format) {
        return switch (format.toLowerCase()) {
            case "json" -> Format.JSON;
            case "csv" -> Format.CSV;
            default -> throw new IllegalArgumentException("Unsupported format: " + format);
        };
    }

    private static Action parseAction(String action) {
        return switch (action.toLowerCase()) {
            case "sum" -> new Sum();
            case "minmax" -> new MinMax();
            case "lt4" -> new LessThanFour();
            default -> throw new IllegalArgumentException("Unknown action: " + action);
        };
    }
    }
