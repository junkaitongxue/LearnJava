import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Iterator;

public class JsonObjectLearn {
    public static void main(String[] args) {
        main01();
        main02();

    }

    private static void main02() {
        // 遍历JSONArray
        JSONArray ja = new JSONArray();
        for (int i = 0; i < ja.size(); i++) {
            JSONObject jo = (JSONObject)ja.get(i);
            System.out.println(jo.toString());
        }
    }

    private static void main01() {
        // 遍历JSONObject
        JSONObject jsonObject = new JSONObject();
        Iterator<Object> keys = jsonObject.values().iterator();
        while (keys.hasNext()) {
            String param = (String)keys.next();
            String value = jsonObject.getString(param);
            System.out.println(value);
        }
    }

}
