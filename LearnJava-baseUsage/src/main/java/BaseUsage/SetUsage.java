package BaseUsage;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class SetUsage {
    public static void main(String[] args) {
        main01();
    }

    private static void main01() {
        Set s = new HashSet<>(Arrays.asList("element01", "element02", "element03"));
        Set s1 = new HashSet<>(Arrays.asList("element03", "element04"));
        // 交集的差集： A - （A ∪ B）
        s.removeAll(s1);
        System.out.println(s);
        // 交集（A ∩ B）
        Set s2 = new HashSet(Arrays.asList("element02", "element05"));
        s.retainAll(s2);
        System.out.println(s);
        // 并集（A ∪ B）
        s.addAll(s1);
        System.out.println(s);

        // 以上三种都会影响函数调用方的所存储的值，要想不影响原来的比较值可以使用stream去处理
        // 取交集
        Set<String> collect =
                (Set<String>) s.stream().filter(e -> s1.contains(e)).collect(Collectors.toSet());
        System.out.println(collect);

        // 取并集
        Set allSet = new HashSet<>(s);
        System.out.println(allSet);
        s1.forEach(e -> {
            if(!allSet.contains(e)){
                allSet.add(e);
            }
        });
        System.out.println(allSet);

    }

    private static Set<String> initList() {
        Set<String> l = new HashSet<String>(Arrays.asList("element01",
                "element02", "element03"));

        return l;
    }
}

