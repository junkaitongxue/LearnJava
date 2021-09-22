package JAVA8;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class OptionalLearn {
    public static void main(String[] args) {
        main01();
    }

    public static void print(Object o){
        System.out.println(o);
    }

    private static void main01() {
        Map<String, String> hm = new HashMap<>();
        print(Optional.ofNullable(hm.get("hello")).orElse("world"));
        Map<String, String> hm01 = null;
        Optional.ofNullable(hm01).map(h -> h.get("hello")).map(h -> h.getBytes()).orElse(null);
    }


}
