package JAVA8;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MapUsage {
    public static void main(String[] args) {
//        learn_computeIfAbsent();
        learn_getOrDefault();
    }

    private static void learn_getOrDefault() {
        Map<String, String> hm = new HashMap<>();
        hm.put("hello", null);
        // 找不到hello的值则使用默认值world，如果原来存在hello且值为null的话则不会获取到默认值，还是null
        System.out.println(hm.getOrDefault("hello", "world")) ;
        // 建议这样使用
        System.out.println(Optional.ofNullable(hm.get("hello")).orElse("world"));
    }

    private static void learn_computeIfAbsent() {
        Map<String, String> hm = new HashMap<>();
        hm.put("huangjunkai", null);

        hm.computeIfAbsent("huangjunkai", String::toUpperCase);
        hm.computeIfAbsent("zhangyuxin", MapUsage::toUpperCase);
        hm.computeIfAbsent("love", key -> key.toUpperCase());
        System.out.println(hm.toString());
    }

    private static String toUpperCase(String source){
        return source.toUpperCase();
    }

}
