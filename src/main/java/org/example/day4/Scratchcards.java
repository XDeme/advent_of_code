package org.example.day4;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;

public class Scratchcards {
    public static int matches(ArrayList<Integer> winning_numbers, ArrayList<Integer> owned_numbers) {
        int winning_count = 0;
        for (var win_num : winning_numbers) {
            if(owned_numbers.contains(win_num))
                ++winning_count;
        }
        return winning_count;
    }

    public static void solution(String[] input) {
        int result1 = 0;
        int[] copies = new int[input.length];
        for (int i = 0; i < input.length; ++i) {
            var numbers = input[i].split(":")[1].split("[|]");
            numbers = Arrays.stream(numbers).map(String::trim).toArray(String[]::new);
            var win_scan = new Scanner(numbers[0]);
            var own_scan = new Scanner(numbers[1]);
            var win_numbers = new ArrayList<Integer>();
            var own_numbers = new ArrayList<Integer>();
            while (win_scan.hasNextInt())
                win_numbers.add(win_scan.nextInt());
            while (own_scan.hasNextInt())
                own_numbers.add(own_scan.nextInt());
            int winning_count = matches(win_numbers, own_numbers);
            for (int j = 1; j <= winning_count; j++)
                copies[i + j] += copies[i] + 1;
            result1 += winning_count == 0 ? 0 : (1 << winning_count - 1);
        }
        int result2 = 0;
        for (var val : copies) {
            result2 += val + 1;
        }
        System.out.println("Solution 1: " + result1);
        System.out.println("Solution 2: " + result2);
    }
    public static void setup() {
        ArrayList<String> inputs = new ArrayList<>(100);
        try(var br = new BufferedReader(new FileReader("src/main/resources/4.txt"))) {
            String line;
            while((line = br.readLine()) != null) {
                inputs.add(line);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        String[] in = new String[inputs.size()];
        {
            try {
                Scratchcards.solution(inputs.toArray(in));
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
