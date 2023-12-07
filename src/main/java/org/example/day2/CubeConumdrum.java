package org.example.day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.TreeMap;

public class CubeConumdrum {

    final static TreeMap<String, Integer> cube_limit = new TreeMap<>();
    static {
        cube_limit.put("red", 12);
        cube_limit.put("green", 13);
        cube_limit.put("blue", 14);
    }

    private static boolean is_set_valid(String set) {
        var pairs = set.split(",");
        for (var pair : pairs) {
            var count_color = pair.substring(1).split(" ");
            int count = Integer.parseInt(count_color[0]);
            if(count > cube_limit.get(count_color[1])) {
                return false;
            }
        }
        return true;
    }
    private static TreeMap<String, Integer> min_color_numbers(String[] sets) {
        TreeMap<String, Integer> min_numbers = new TreeMap<>();
        min_numbers.put("red", 0);
        min_numbers.put("green", 0);
        min_numbers.put("blue", 0);
        for (var set : sets) {
            var pairs = set.split(",");
            for (var pair : pairs) {
                var count_color = pair.substring(1).split(" ");
                int count = Integer.parseInt(count_color[0]);
                String color = count_color[1];
                min_numbers.put(color, Math.max(count, min_numbers.get(color)));
            }
        }
        return min_numbers;
    }

    public static int solution1(String[] input) {
        int sum = 0;
        for(var line : input) {
            int space_idx = line.indexOf(' ');
            int colon_idx = line.indexOf(':', space_idx);
            int game_id = Integer.parseInt(line.substring(space_idx+1, colon_idx));
            var sets = line.substring(colon_idx+1).split(";");
            boolean game_valid = true;
            for(var s : sets) {
                if(!is_set_valid(s)) {
                    game_valid = false;
                    break;
                }
            }
            if(game_valid) {
                sum += game_id;
            }
        }
        return sum;
    }

    public static int solution2(String[] inputs) {
        int sum = 0;
        for(var line : inputs) {
            int colon_idx = line.indexOf(':');
            var sets = line.substring(colon_idx+1).split(";");
            var min_numbers = min_color_numbers(sets);
            sum += min_numbers.get("red") * min_numbers.get("blue") * min_numbers.get("green");
        }
        return sum;
    }

    public static void setup() {
        ArrayList<String> inputs = new ArrayList<>(100);
        try(var br = new BufferedReader(new FileReader("src/main/resources/2.txt"))) {
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
                System.out.println(CubeConumdrum.solution1(inputs.toArray(in)));
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }

        {
            try {
                System.out.println(CubeConumdrum.solution2(inputs.toArray(in)));
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
