package gittest;

import java.util.ArrayList;

public class A {
    public static void main(String[] args) {
        String a = "abcdefghijklmnopqrstuvwx";
        String z = a.replaceAll("(..).", "$1");
        System.out.println(z);
        ArrayList<String> cards = new ArrayList<>();
        for (int i = 0; i <z.length(); i+= 2){
            cards.add(z.substring(i, Math.min(z.length(), i + 2)));
        }
        System.out.println(cards);
    }
    }

