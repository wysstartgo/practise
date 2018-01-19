import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;

/**
 * <pre>
 *
 * 【标题】: 测试fastjson
 * 【描述】:
 * 【版权】: 润投科技
 * 【作者】: wuys
 * 【时间】: 2017-12-20 11:40
 * </pre>
 */
public class FastJsonTest {

    public static void main(String[] args) {
        String str = "{\"456\":\"zjk\",\"789\":\"zgd\",\"123\":\"wuys\"}";
        HashMap hashMap = JSONObject.parseObject(str, HashMap.class);
        System.out.println(hashMap.get("456"));
    }
}
