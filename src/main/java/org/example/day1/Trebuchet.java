package org.example.day1;

import java.util.ArrayList;

public class Trebuchet {
    private char is_named_char(String str) {
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
    public int solution1(String[] input) {
        int result = 0;
        for (var line : input) {
            char first = 0;
            char last = 0;
            for (int i = 0; i < line.length(); i++) {
                char curr = line.charAt(i);
                if(!(curr >= '0' && curr <= '9')) {
                    continue;
                }
                else if(first == 0) {
                    first = curr;
                }
                else {
                    last = curr;
                }
            }
            if(last == 0)
                last = first;
            result += ((first - '0') * 10) + (last - '0');
        }
        return result;
    }
    public int solution2(String[] input) {
        int result = 0;
        for (var line : input) {
            char first = 0;
            char last = 0;
            for (int i = 0; i < line.length(); i++) {
                char curr = line.charAt(i);
                var named_num = is_named_char(line.substring(i));
                if(!(curr >= '0' && curr <= '9') && named_num == 0) {
                    continue;
                }
                else if(first == 0 && named_num != 0) {
                    first = named_num;
                }
                else if(first == 0) {
                    first = curr;
                }
                else if(named_num != 0) {
                    last = named_num;
                }
                else {
                    last = curr;
                }
            }
            if(last == 0)
                last = first;
            result += ((first - '0') * 10) + (last - '0');
        }
        return result;
    }
}
