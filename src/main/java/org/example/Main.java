package org.example;

import org.example.day1.Trebuchet;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void day1() {
        Trebuchet part1 = new Trebuchet();
        ArrayList<String> input = new ArrayList<>(1000);
        try(var br = new BufferedReader(new FileReader("src/main/resources/inputDay1.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                input.add(line);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        String[] in = new String[input.size()];

        {
            // Part1
            var result = part1.solution1(input.toArray(in));
            System.out.println(result);
        }
        {
            // Part2
            var result = part1.solution2(input.toArray(in));
            System.out.println(result);
        }
    }
    public static void main(String[] args) {
        System.out.print("Selected input day: ");
        int selected = 0;

        try(var br = new BufferedReader(new InputStreamReader(System.in))) {
            String s = br.readLine();
            selected = Integer.valueOf(s);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        switch (selected) {
            case 1: day1();
            break;
            default:
                System.out.println("Nothing selected");
        }
    }
}