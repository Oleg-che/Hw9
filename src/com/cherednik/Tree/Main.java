package com.cherednik.Tree;

public class Main {

    public static void main(String[] args) {
        MyMap<String, String> map = new MyTreeMap<>();
        map.put("30", "523");
        map.put("1234", "124");
        map.put("34", "324");
        map.put("3", "432");
        map.put("43", "234");
        map.put("2", "134");
        map.put("1", "324");
        map.put("5", "32");
        map.put("53","987");
        map.put("9","43");
        map.put("40","482");
        System.out.println(map.size());
        System.out.println(map.containsKey("34"));
        System.out.println(map.get("53"));
        System.out.println(map.setNode());
        System.out.println(map);

//        TreeMap<Integer, Integer> map = new TreeMap<>();
//            map.put(1, 5);
//            map.put(4, 10);
//            map.put(15, 3);
//        System.out.println(map);
    }
}
