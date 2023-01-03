package ru.stqa.pft.sanbox;

import java.util.Arrays;
import java.util.List;

public class Collections {
    public static void main (String[] args){
        String [] langs = {"Java","C#","Python","PHP"};

        List<String> language = Arrays.asList("Java","C#","Python","PHP");

        for(String l : language){
            System.out.println("Я хочу выучить "+l);
        }
    }
}
