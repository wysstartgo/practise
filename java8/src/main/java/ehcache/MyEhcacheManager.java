package ehcache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * <pre>
 *
 * 【标题】: ehcache
 * 【描述】:
 * 【版权】: 润投科技
 * 【作者】: wuys
 * 【时间】: 2017-12-15 14:55
 * </pre>
 */
public class MyEhcacheManager {

    public static void main(String[] args) {
        // Creating a CacheManager based on a specified configuration file.
        CacheManager manager = CacheManager.newInstance("src/main/resources/ehcache.xml");
        // obtains a Cache called sampleCache1, which has been preconfigured in the configuration file
        Cache cache = manager.getCache("sampleCache1");
        // puts an element into a cache
        Element element = new Element("key1", "哈哈");
        cache.put(element);
        //The following gets a NonSerializable value from an element with a key of key1.
        Object value = element.getObjectValue();
        System.out.println(value.toString());
        manager.shutdown();
    }
}
