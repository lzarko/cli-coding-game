package com.example.handler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class OutputHandler {
    public void writeOutput(String output, String destination) throws IOException {
        if ("-".equals(destination)) {
            System.out.println(output);
        } else {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(destination))) {
                writer.write(output);
                writer.newLine();
            }
        }
    }
}
