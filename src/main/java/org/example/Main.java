package org.example;

import org.example.day1.Trebuchet;
import org.example.day2.CubeConumdrum;
import org.example.day3.GearRatios;
import org.example.day4.Scratchcards;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

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
            case 1:
                Trebuchet.setup();
                break;
            case 2:
                CubeConumdrum.setup();
                break;
            case 3:
                GearRatios.setup();
            case 4:
                Scratchcards.setup();
            default:
                System.out.println("Nothing selected");
        }
    }
}