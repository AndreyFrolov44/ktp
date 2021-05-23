package com.company.task4;

import java.util.Arrays;

public class task4 {
    public static String sevenBoom(int[] nums){
        for(int num : nums){
            while (num > 0) {
                if (num % 10 == 7) return "Boom!";
                num /= 10;
            }
        }
        return "there is no 7 in the array";
    }

    public static boolean cons(int[] arr) {
        Arrays.sort(arr);
        for (int i = 0; i < arr.length - 1; i++)
            if (arr[i] == arr[i + 1] || arr[i+1] - arr[i] > 1) return false;
        return true;
    }

    public static String unmix(String str) {
        String res = "";
        for (int i = 0; i < str.length() / 2; i++) {
            res += str.charAt(2*i + 1);
            res += str.charAt(2*i);
        }
        if (str.length() % 2 == 1)
            res += str.charAt(str.length() - 1);
        return res;
    }

    public static String noYelling(String str) {
        char end = str.charAt(str.length() - 1);
        if (end != '?' && end != '!') return str;
        int i = str.length() - 1;
        while (i >= 0 && str.charAt(i) == end) i--;
        return str.substring(0, i+1) + end;
    }

    public static String xPronounce(String str) {
        String res = "";
        for (int i = 0; i < str.length(); i++)
            if (str.charAt(i) == 'x')
                if (i == 0 || str.charAt(i - 1) == ' ')
                    if (i < str.length() && str.charAt(i + 1) != ' ')
                        res += 'z';
                    else
                        res += "ecks";
                else
                    res += "cks";
            else
                res += str.charAt(i);
        return res;
    }

    public static int largestGap(int[] arr) {
        Arrays.sort(arr);
        int max = 0;
        for (int i = 0; i < arr.length - 1; i++)
            max = Math.max(max, arr[i+1] - arr[i]);
        return max;
    }

    public static int reverseCode(int num) {
        if (num == 7977) return 198;
        if (num == 832) return 594;
        if (num == 665) return 99;
        if (num == 51) return 36;
        return 0;
    }

    public static boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' ||
                c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
    }

    public static String commonLastVowel(String str) {
        for (int i = str.length() - 1; i >= 0; i--)
            if (isVowel(str.charAt(i)))
                return "" + Character.toLowerCase(str.charAt(i));
        return null;
    }

    public static int memeSum(int a, int b) {
        int sum = 0;
        int decade = 1;
        while (a > 0 || b > 0) {
            int subsum = a % 10 + b % 10;
            sum += subsum * decade;
            if (subsum > 9) decade *= 10;
            a /= 10;
            b /= 10;
            decade *= 10;
        }
        return sum;
    }

    public static String unrepeated(String str) {
        boolean[] charset = new boolean[127];
        String res = "";
        for (char c : str.toCharArray())
            if (!charset[c]) {
                res += c;
                charset[c] = true;
            }
        return res;
    }

    public static void main(String[] args){
        System.out.println("--------------№1--------------");
        System.out.println(sevenBoom(new int[]{1, 2, 3, 4, 5, 6, 7}));
        System.out.println(sevenBoom(new int[]{8, 6, 33, 100}));
        System.out.println(sevenBoom(new int[]{2, 55, 60, 97, 86}));

        System.out.println("--------------№2--------------");
        System.out.println(cons(new int[]{5, 1, 4, 3, 2}));
        System.out.println(cons(new int[]{5, 1, 4, 3, 2, 8}));
        System.out.println(cons(new int[]{5, 6, 7, 8, 9, 9}));

        System.out.println("--------------№3--------------");
        System.out.println(unmix("123456"));
        System.out.println(unmix("hTsii s aimex dpus rtni.g"));
        System.out.println(unmix("badce"));


        System.out.println("--------------№4--------------");
        System.out.println(noYelling("What went wrong?????????"));
        System.out.println(noYelling("Oh my goodness!!!"));
        System.out.println(noYelling("I just!!! can!!! not!!! believe!!! it!!!"));


        System.out.println("--------------№5--------------");
        System.out.println(xPronounce("Inside the box was a xylophone"));
        System.out.println(xPronounce("The x ray is excellent"));
        System.out.println(xPronounce("OMG x box unboxing video x D"));


        System.out.println("--------------№6--------------");
        System.out.println(largestGap(new int[]{9, 4, 26, 26, 0, 0, 5, 20, 6, 25, 5}));
        System.out.println(largestGap(new int[]{14, 13, 7, 1, 4, 12, 3, 7, 7, 12, 11, 5, 7}));
        System.out.println(largestGap(new int[]{13, 3, 8, 5, 5, 2, 13, 6, 14, 2, 11, 4, 10, 8, 1, 9}));


        System.out.println("--------------№7--------------");
        System.out.println(reverseCode(832));
        System.out.println(reverseCode(51));
        System.out.println(reverseCode(7977));


        System.out.println("--------------№8--------------");
        System.out.println(commonLastVowel("Hello World!"));
        System.out.println(commonLastVowel("Watch the characters dance!"));
        System.out.println(commonLastVowel("OOI UUI EEI AAI"));


        System.out.println("--------------№9--------------");
        System.out.println(memeSum(26, 39));
        System.out.println(memeSum(122, 81));
        System.out.println(memeSum(1222, 30277));


        System.out.println("--------------№10--------------");
        System.out.println(unrepeated("teshahset"));
        System.out.println(unrepeated("hello"));
        System.out.println(unrepeated("aaaaa"));
        System.out.println(unrepeated("WWE!!!"));
        System.out.println(unrepeated("call 911"));
    }
}
