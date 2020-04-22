package com.cherednik.TopDomain;
/*
1.) Есть документ со списком URL:
https://drive.google.com/open?id=1wVBKKxpTKvWwuCzqY1cVXCQZYCsdCXTl
Вывести топ 10 доменов которые встречаются чаще всего. В документе могут встречается пустые и недопустимые строки.
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    private static Map<String, Integer> urlMap = new HashMap<>();
    private static final int NUMBER_OF_DOMAIN = 10;

    public static void main(String[] args) throws FileNotFoundException {

        Scanner in = new Scanner(new File("urls.txt"));
        while (in.hasNextLine()) {
            String s = in.nextLine();
            if (s.contains("/")) {
                s = s.substring(0, s.indexOf("/"));
            }
            if (s.startsWith("m.") || s.startsWith("www.")) {
                s = s.substring(s.indexOf(".") + 1);
            }
            if (urlMap.containsKey(s)) {
                urlMap.replace(s, (urlMap.get(s) + 1));
            } else {
                urlMap.put(s, 0);
            }
        }
        getTopDomain();
    }

    private static void getTopDomain() {
        List<Map.Entry<String, Integer>> list = new ArrayList<>(urlMap.entrySet());
        list.sort((o1, o2) -> Integer.compare(o2.getValue(), o1.getValue()));
        System.out.println("Top 10 The most visited domains:");
        for (int i = 0; i < NUMBER_OF_DOMAIN; i++) {
            System.out.println(list.get(i).getKey());
        }
    }

}
