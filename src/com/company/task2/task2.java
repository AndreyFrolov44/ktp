package com.company.task2;

public class task2 {
    public static int oppositeHouse(int num, int length){
        if(num % 2 != 0)
            return length * 2 - 1 - num + 2;
        else
            return length * 2 - num + 1;
    }

    public static String nameShuffle(String name){
        String[] nameList = name.split(" ");
        String nameReverse = "";
        for (int i = nameList.length - 1; i >= 0; i--){
            nameReverse += nameList[i] + " ";
        }
        return nameReverse;
    }

    public static float discount(float price, float sale){
        return price * (1 - sale / 100);
    }

    public static int differenceMaxMin(int[] arr){
        int min, max;
        min = max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min)
                min = arr[i];
            if(arr[i] > max){
                max = arr[i];
            }
        }
        return max - min;
    }

    public static int equal(int a, int b, int c){
        int i = 0;
        if(a == b ) i++;
        if(b == c) i++;
        if(a == c) i++;
        return i;
    }

    public static String reverse(String str){
        String newStr = "";
        for(int i = str.length() - 1; i >= 0; i--)
            newStr += str.charAt(i);
        return newStr;
    }

    public static int programmers(int first, int second, int last){
        int min, max;
        min = max = first;

        if(min > second) min = second;
        if(min > last) min = last;
        if(max < second) max = second;
        if(max < last) max = last;

        return max - min;
    }

    public static boolean getXO(String str){
        int x, o;
        x = o = 0;
        char[] cStr = str.toLowerCase().toCharArray();
        for(char s : cStr){
            if(s == 'x') x++;
            if(s == 'o') o++;
        }

        if(x == o) return true;
        else return false;
    }

    public static String bomb(String str){
        str = str.toLowerCase();
        if(str.indexOf("bomb") >= 0) return "DUCK!";
        return "Relax, there's no bomb.";
    }

    public static boolean sameAscii(String first, String second){
        char[] arrFirst = first.toCharArray();
        char[] arrSecond = second.toCharArray();
        int sumFirst = 0, sunSecond = 0;

        for(char cFirst : arrFirst)
            sumFirst += (int) cFirst;
        for(char cSecond : arrSecond)
            sunSecond += (int) cSecond;

        if(sumFirst == sunSecond) return true;
        return false;
    }

    public static void main(String[] args){
        System.out.println("--------------№1--------------");
        System.out.println(oppositeHouse(1, 3));
        System.out.println(oppositeHouse(2, 3));
        System.out.println(oppositeHouse(3, 5));
        System.out.println(oppositeHouse(5, 46));

        System.out.println("--------------№2--------------");
        System.out.println(nameShuffle("Donald Trump"));
        System.out.println(nameShuffle("Rosie O'Donnell"));
        System.out.println(nameShuffle("Seymour Butts"));

        System.out.println("--------------№3--------------");
        System.out.println(discount(1500, 50));
        System.out.println(discount(89, 20));
        System.out.println(discount(100, 75));

        System.out.println("--------------№4--------------");
        System.out.println(differenceMaxMin(new int[]{10, 4, 1, 4, -10, -50, 32, 21}));
        System.out.println(differenceMaxMin(new int[]{44, 32, 86, 19}));

        System.out.println("--------------№5--------------");
        System.out.println(equal(3, 4, 3));
        System.out.println(equal(1, 1, 1));
        System.out.println(equal(3, 4, 1));

        System.out.println("--------------№6--------------");
        System.out.println(reverse("Hello World"));
        System.out.println(reverse("The quick brown fox."));
        System.out.println(reverse("Edabit is really helpful!"));

        System.out.println("--------------№7--------------");
        System.out.println(programmers(147, 33, 526));
        System.out.println(programmers(33, 72, 74));
        System.out.println(programmers(1, 5, 9));

        System.out.println("--------------№8--------------");
        System.out.println(getXO("ooxx"));
        System.out.println(getXO("xooxx"));
        System.out.println(getXO("ooxXm"));
        System.out.println(getXO("zpzpzpp"));
        System.out.println(getXO("zzoo"));

        System.out.println("--------------№9--------------");
        System.out.println(bomb("There is a bomb."));
        System.out.println(bomb("Hey, did you think there is a BOMB?"));
        System.out.println(bomb("This goes boom!!!"));

        System.out.println("--------------№10--------------");
        System.out.println(sameAscii("a", "a"));
        System.out.println(sameAscii("AA", "B@"));
        System.out.println(sameAscii("EdAbIt", "EDABIT"));
    }
}
