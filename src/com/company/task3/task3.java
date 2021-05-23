package com.company.task3;

import java.util.Arrays;

public class task3 {
    public  static String millionsRounding(String[][] population){
        String[] populationStr = new String[population.length];
        for(int i = 0; i < population.length; i++){
            double p = Double.parseDouble(population[i][1])/1000000;
            if(p != 0)
                populationStr[i] = population[i][0] + ", " + Double.toString(1000000*(Math.round(p)));
            else
                populationStr[i] = population[i][0] + ", " + Double.toString(1000000*(Math.ceil(p)));
        }
        return Arrays.toString(populationStr);
    }

    public static double[] otherSides(int side){
        double firstSide, secondSide;

        firstSide = Math.round(side * 2 * 100) / 100;
        secondSide = Double.valueOf(Math.round(Math.sqrt(firstSide * firstSide - side * side) * 100)) / 100;

        return new double[] {firstSide, secondSide};
    }

    public static String rps(String firstPlayer, String secondPlayer){
        if(firstPlayer == "rock" && secondPlayer == "paper") return "Player 2 wins";
        else if(firstPlayer == "rock" && secondPlayer == "rock") return "TIE";
        else if(firstPlayer == "scissors" && secondPlayer == "rock") return "Player 2 wins";
        else if(firstPlayer == "paper" && secondPlayer == "scissors") return "Player 2 wins";
        else if(firstPlayer == "scissors" && secondPlayer == "scissors") return "TIE";
        else if(firstPlayer == "paper" && secondPlayer == "paper") return "TIE";
        else return "Player 1 wins";
    }

    public static int warOfNumbers(int[] nums){
        int even = 0, odd = 0;

        for(int num : nums){
            if(num % 2 == 0) even += num;
            else odd += num;
        }

        return Math.abs(even - odd);
    }

    public static String reverseCase(String str){
        char[] chars = str.toCharArray();

        for(int i = 0; i < chars.length; i++){
            char c = chars[i];
            if(Character.isUpperCase(c)) chars[i] = Character.toLowerCase(c);
            else if(Character.isLowerCase(c)) chars[i] = Character.toUpperCase(c);
        }

        return new String(chars);
    }

    public static String inatorInator(String str){
        char lastChar = str.charAt(str.length() - 1);
        String vowels = "aeiouy";

        if(vowels.indexOf(Character.toLowerCase(lastChar)) < 0) str += "inator " + str.length() + "000";
        else str += "-inator " + str.length() + "000";

        return str;
    }

    public static boolean doesBrickFit(int a, int b, int c, int w, int h){
        if(a == w && b == h || a == h && b == w || b == w && c == h || b == h && c == w || a == w && c == h || a == h && c == w)
            return true;
        else return false;
    }

    public static double totalDistance(double fuel, double consumption, int people, boolean air){
        double percent = 1 + Double.valueOf(people * 5) / 100;
        if(air) percent += 0.1;

        return fuel / (consumption * percent) * 100;
    }

    public static double mean(int[] nums){
        double sum = 0.f;

        for(int num : nums)
            sum += num;

        return sum / nums.length;
    }

    public static boolean parityAnalysis(int num){
        int sum = 0;
        int cNum = num;

        while(cNum != 0){
            sum += cNum % 10;
            cNum /= 10;
        }

        return (sum % 2 == 0 && num % 2 == 0) || (sum % 2 != 0 && num % 2 != 0);
    }

    public static void main(String[] args){
        System.out.println("--------------№1--------------");
        System.out.println(millionsRounding(new String[][] {
                new String[] {"Nice", "942208"},
                new String[] {"Abu Dhabi", "1482816"},
                new String[] {"Naples", "2186853"},
                new String[] {"Vatican City", "572"}
        }));

        System.out.println("--------------№2--------------");
        System.out.println(Arrays.toString(otherSides(1)));
        System.out.println(Arrays.toString(otherSides(12)));
        System.out.println(Arrays.toString(otherSides(2)));
        System.out.println(Arrays.toString(otherSides(3)));

        System.out.println("--------------№3--------------");
        System.out.println(rps("rock", "paper"));
        System.out.println(rps("paper", "rock"));
        System.out.println(rps("paper", "scissors"));
        System.out.println(rps("scissors", "scissors"));
        System.out.println(rps("scissors", "paper"));

        System.out.println("--------------№4--------------");
        System.out.println(warOfNumbers(new int[]{2, 8, 7, 5}));
        System.out.println(warOfNumbers(new int[]{12, 90, 75}));
        System.out.println(warOfNumbers(new int[]{5, 9, 45, 6, 2, 7, 34, 8, 6, 90, 5, 243}));

        System.out.println("--------------№5--------------");
        System.out.println(reverseCase("Happy Birthday"));
        System.out.println(reverseCase("MANY THANKS"));
        System.out.println(reverseCase("sPoNtAnEoUs"));

        System.out.println("--------------№6--------------");
        System.out.println(inatorInator("Shrink"));
        System.out.println(inatorInator("Doom"));
        System.out.println(inatorInator("EvilClone"));

        System.out.println("--------------№7--------------");
        System.out.println(doesBrickFit(1, 1, 1, 1, 1));
        System.out.println(doesBrickFit(1, 2, 1, 1, 1));
        System.out.println(doesBrickFit(1, 2, 2, 1, 1));

        System.out.println("--------------№8--------------");
        System.out.println(totalDistance(70.0, 7.0, 0, false));
        System.out.println(totalDistance(36.1, 8.6, 3, true));
        System.out.println(totalDistance(55.5, 5.5, 5, false));

        System.out.println("--------------№9--------------");
        System.out.println(mean(new int[]{1, 0, 4, 5, 2, 4, 1, 2, 3, 3, 3}));
        System.out.println(mean(new int[]{2, 3, 2, 3}));
        System.out.println(mean(new int[]{3, 3, 3, 3,}));

        System.out.println("--------------№10--------------");
        System.out.println(parityAnalysis(243));
        System.out.println(parityAnalysis(12));
        System.out.println(parityAnalysis(3));
    }
}
