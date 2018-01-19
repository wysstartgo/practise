package cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.Weigher;

import java.util.Date;
import java.util.concurrent.ExecutionException;

/**
 * <pre>
 *
 * 【标题】:
 * 【描述】:
 * 【版权】: 润投科技
 * 【作者】: wuys
 * 【时间】: 2018-01-15 17:24
 * </pre>
 */
public class GuavaCacheTest3 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        LoadingCache<String, Date> build = CacheBuilder.newBuilder()
                .maximumSize(10)
                .weigher(new Weigher<String, Date>() {
                    @Override
                    public int weigh(String key, Date value) {
                        return value.hashCode();
                    }
                })
                .build(new CacheLoader<String, Date>() {
            @Override
            public Date load(String key) throws Exception {
                //TODO loadFromDb
                return new Date();
            }
        });
        for (int i = 0;i<100;i++){
            try {
                build.get(i + "");
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        Thread.sleep(10000);
        System.out.println(build.get(1 + ""));
        System.out.println(build.get(99 + ""));
    }
}
