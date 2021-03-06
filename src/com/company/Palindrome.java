package com.company;

public class Palindrome {
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if(isPalindrome(args[i])) System.out.println(args[i] + " - полиндром");
            else System.out.println(args[i] + " - не полиндром");
        }
    }
    public static String reverseString(String str){
        String newStr = "";
        for(int i = str.length() - 1; i >= 0; i--)
            newStr += str.charAt(i);
        return newStr;
    }
    public static boolean isPalindrome(String s){
        return s.equals(reverseString(s));
    }
}
