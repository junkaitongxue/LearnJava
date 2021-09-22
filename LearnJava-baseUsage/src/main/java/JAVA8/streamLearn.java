package JAVA8;

import java.util.*;
import java.util.stream.Collectors;

public class streamLearn {
    public static void main(String[] args) {
//        main01();
//        main02();
//        main03();
//        main04();
//        main05();
//        main06();
        main07();
    }

    private static void main07() {
        List<String> strArr = Arrays.asList("star", "boom",
                "star", "boom", "star");
        // 使用orElse的情形，数值，字符串，引用
        Optional<String> first = strArr.stream().filter(e -> e.startsWith(
                "boom"))
                .findFirst();
        String ret = first.orElse("default");
        System.out.println("ret: " + ret);

        // 使用orElseGet为函数调用
        String ret02 = strArr.stream()
                .filter(e -> "boom1".equals(e))
                .findFirst()
                .orElseGet(streamLearn::inner_main07);
        System.out.println("ret: " + ret02);
    }

    private static String inner_main07(){
        return "default";
    }

    private static void main06() {
        List<String> strArr = Arrays.asList("element01", "element02",
                "element03", "element04", "other");
        strArr.stream().filter(e -> e.startsWith("element"))
                .peek(e -> System.out.print(e + "-")).collect(Collectors.toList());
    }

    private static void main05() {
        List<Integer> numbers = Arrays.asList(-1, -2, 0, -1, 4, 5, 1);
        Integer total = numbers.stream().reduce((t, n) -> t + n).get();
        System.out.println("Total: " + total);
    }

    private static void main04() {
        // collect map to list
        Map<String, String> StringMap = new HashMap<String, String>();
        StringMap.put("key1", "value");
        StringMap.put("key2", "value2");
        int num =(int)StringMap.entrySet().stream().
                filter(m -> m.getKey().startsWith("key"))
                .map(m -> m.getKey() + "_" + m.getValue())
                .count();
        System.out.println(num);
    }

    private static void main03() {
        // collect map to list
        Map<String, String> StringMap = new HashMap<String, String>();
        StringMap.put("key1", "value");
        StringMap.put("key2", "value2");
        StringMap.entrySet().stream().
                filter(m -> m.getKey().startsWith("key"))
                .map(m -> m.getKey() + "_" + m.getValue())
                .collect(Collectors.toList())
                .forEach(e -> System.out.println(e));
    }

    private static void main02() {
        // foreach
        List<String> strArr = Arrays.asList("element01", "element02",
                "element03", "element04", "other");
        strArr.stream().filter(e -> e.startsWith("element")).forEach(e -> System.out.println(e));
        strArr.forEach(e -> System.out.println(e));

    }

    public static void main01() {
        // filter list
        System.out.println("hjk");
        List<String> listA = initList();
        List<String> handledListA = listA.stream()
                .filter(s -> s.startsWith("element"))
                .distinct()
                .sorted(String::compareToIgnoreCase)
                .collect(Collectors.toList());
        System.out.println(handledListA);
    }

    private static List<String> initList() {
        List<String> l = new ArrayList<String>();
        l.add("element01");
        l.add("element02");
        l.add("element04");
        l.add("element03");
        l.add("element04");
        return l;
    }
}
