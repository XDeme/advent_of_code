package org.example.day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Trebuchet {
    private static char is_named_char(String str) {
        if(str.startsWith("one"))
            return '1';
        else if(str.startsWith("two"))
            return '2';
        else if(str.startsWith("three"))
            return '3';
        else if (str.startsWith("four"))
            return '4';
        else if(str.startsWith("five"))
            return '5';
        else if(str.startsWith("six"))
            return '6';
        else if(str.startsWith("seven"))
            return '7';
        else if(str.startsWith("eight"))
            return '8';
        else if(str.startsWith("nine"))
            return '9';
        return 0;
    }
    public static int solution1(String[] input) {
        int result = 0;
        for (var line : input) {
            char first = 0;
            char last = 0;
            for (int i = 0; i < line.length(); i++) {
                char curr = line.charAt(i);
                if(!Character.isDigit(curr)) {
                    continue;
                }
                else if(last == 0) {
                    first = curr;
                    last = curr;
                }
                else {
                    last = curr;
                }
            }
            result += ((first - '0') * 10) + (last - '0');
        }
        return result;
    }
    public static int solution2(String[] input) {
        int result = 0;
        for (var line : input) {
            char first = 0;
            char last = 0;
            for (int i = 0; i < line.length(); i++) {
                char curr = line.charAt(i);
                var named_num = is_named_char(line.substring(i));
                if(!Character.isDigit(curr) && named_num == 0) {
                    continue;
                }
                else if(last == 0) {
                    first = named_num == 0 ? curr : named_num;
                    last = named_num == 0 ? curr : named_num;
                }
                else {
                    last = named_num == 0 ? curr : named_num;
                }
            }
            result += ((first - '0') * 10) + (last - '0');
        }
        return result;
    }
    public static void setup() {
        ArrayList<String> input = new ArrayList<>(1000);
        try(var br = new BufferedReader(new FileReader("src/main/resources/1.txt"))) {
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
            var result = Trebuchet.solution1(input.toArray(in));
            System.out.println(result);
        }
        {
            // Part2
            var result = Trebuchet.solution2(input.toArray(in));
            System.out.println(result);
        }
    }
}
