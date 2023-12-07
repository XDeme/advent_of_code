package org.example.day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;

public class GearRatios {
    public static boolean is_valid_symbol(char a) {
        return !Character.isDigit(a) && a != '.';
    }

    public static int get_first_digit(String str, int end) {
        int first_digit = end;
        for (; first_digit >= 0; --first_digit) {
            if(!Character.isDigit(str.charAt(first_digit)))
                break;
        }
        return first_digit + 1;
    }

    public static int get_last_digit(String str, int start) {
        int last_digit = start;
        for(;last_digit < str.length(); ++last_digit) {
            if(!Character.isDigit(str.charAt(last_digit)))
                break;
        }
        return last_digit - 1;
    }

    record Coordinate(int x, int y){}

    // I don't know a better way to check around an index
    public static boolean  is_valid_number(String[] input, int i, int j) {
        if(j >= 1 && i >= 1 && is_valid_symbol(input[i-1].charAt(j-1)))
            return true;
        else if(i >= 1 && is_valid_symbol(input[i-1].charAt(j)))
            return true;
        else if(j < input[i].length() -1 && i >= 1 && is_valid_symbol(input[i-1].charAt(j+1)))
            return true;

        else if(j >= 1 && is_valid_symbol(input[i].charAt(j-1)))
            return true;
        else if(j < input[i].length() - 1 && is_valid_symbol(input[i].charAt(j+1)))
            return true;

        else if(j >= 1 && i < input.length - 1 && is_valid_symbol(input[i+1].charAt(j-1)))
            return true;
        else if(i < input.length - 1 && is_valid_symbol(input[i+1].charAt(j)))
            return true;
        else if(i < input.length -1 && j < input[i+1].length() - 1 && is_valid_symbol(input[i+1].charAt(j+1)))
            return true;
        return false;
    }

    public static int solution1(String[] input) {
        int sum = 0;
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length(); j++) {
                if(!Character.isDigit(input[i].charAt(j)))
                    continue;
                int last_digit_idx = get_last_digit(input[i], j) + 1;
                int num = Integer.parseInt(input[i].substring(j, last_digit_idx));
                boolean valid = false;
                for (int k = j; k < last_digit_idx; k++) {
                    valid = is_valid_number(input, i, k);
                   if(valid)
                        break;
                }
                if(valid) {
                    sum += num;
                    j = last_digit_idx - 1;
                }
            }

        }
        return sum;
    }

    public static long solution2(String[] input) {
        long sum = 0;
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length(); j++) {
                if(!(input[i].charAt(j) == '*'))
                    continue;

                ArrayList<Coordinate> indexes = new ArrayList<>();
                if(j >= 1 && i >= 1 && Character.isDigit(input[i-1].charAt(j-1))) {
                    var first_digit = get_first_digit(input[i-1], j-1);
                    indexes.add(new Coordinate(first_digit, i-1));
                }
                if(i >= 1 && Character.isDigit(input[i-1].charAt(j))) {
                    var first_digit = get_first_digit(input[i-1], j);
                    var cord = new Coordinate(first_digit, i-1);
                    if(!indexes.contains(cord))
                        indexes.add(cord);
                }
                if(i >= 1 && j < input[i-1].length() - 1 && Character.isDigit(input[i-1].charAt(j+1))) {
                    var first_digit = get_first_digit(input[i - 1], j + 1);
                    var cord = new Coordinate(first_digit, i - 1);
                    if (!indexes.contains(cord))
                        indexes.add(cord);
                }

                if(j >= 1 && Character.isDigit(input[i].charAt(j-1))) {
                    var first_digit = get_first_digit(input[i], j-1);
                    var cord = new Coordinate(first_digit, i);
                    if(!indexes.contains(cord))
                        indexes.add(cord);
                }
                if(j < input[i].length() - 1 && Character.isDigit(input[i].charAt(j+1))) {
                    var first_digit = get_first_digit(input[i], j+1);
                    var cord = new Coordinate(first_digit, i);
                    if (!indexes.contains(cord))
                        indexes.add(cord);
                }

                if(i < input.length - 1 && j >= 1 && Character.isDigit(input[i+1].charAt(j-1))) {
                    var first_digit = get_first_digit(input[i+1], j-1);
                    var cord = new Coordinate(first_digit, i+1);
                    if(!indexes.contains(cord))
                        indexes.add(cord);
                }
                if(i < input.length -1 && Character.isDigit(input[i+1].charAt(j))) {
                    var first_digit = get_first_digit(input[i+1], j);
                    var cord = new Coordinate(first_digit, i+1);
                    if(!indexes.contains(cord))
                        indexes.add(cord);
                }
                if(i < input.length - 1 && j < input[i+1].length() - 1 && Character.isDigit(input[i+1].charAt(j+1))) {
                    var first_digit = get_first_digit(input[i+1], j+1);
                    var cord = new Coordinate(first_digit, i+1);
                    if(!indexes.contains(cord))
                        indexes.add(cord);
                }

                if(indexes.size() != 2)
                    continue;
                var cord1 = indexes.get(0);
                var cord2 = indexes.get(1);
                var first_num = Integer.parseInt(input[cord1.y].substring(cord1.x, get_last_digit(input[cord1.y], cord1.x) + 1));
                var second_num = Integer.parseInt(input[cord2.y].substring(cord2.x, get_last_digit(input[cord2.y], cord2.x) + 1));
                sum += (first_num * second_num);
            }
        }
        return sum;
    }

    public static void setup() {
        ArrayList<String> inputs = new ArrayList<>(100);
        try(var br = new BufferedReader(new FileReader("src/main/resources/3.txt"))) {
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
                System.out.println(GearRatios.solution1(inputs.toArray(in)));
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }

        {
            try {
                System.out.println(GearRatios.solution2(inputs.toArray(in)));
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
