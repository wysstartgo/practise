package cache;

import com.google.common.cache.*;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * <pre>
 *
 * 【标题】: guava cache
 * 【描述】:
 * 【版权】: 润投科技
 * 【作者】: wuys
 * 【时间】: 2018-01-15 16:01
 * </pre>
 */
public class GuavaCacheTest1 {

    public static void main(String[] args) {
        /**
         * CacheBuilder.refreshAfterWrite(long,
         * TimeUnit)可以为缓存增加自动定时刷新功能。和expireAfterWrite相反，refreshAfterWrite通过定时刷新可以让缓存项保持可用，
         * 但请注意：缓存项只有在被检索时才会真正刷新（如果CacheLoader.refresh实现为异步，那么检索不会被刷新拖慢）。
         * 因此，如果你在缓存上同时声明expireAfterWrite和refreshAfterWrite，缓存并不会因为刷新盲目地定时重置，如果缓存项没有被检索，
         * 那刷新就不会真的发生，缓存项在过期时间后也变得可以回收。
         */
        LoadingCache<String, String> build = CacheBuilder.newBuilder().maximumSize(1000)
                .expireAfterWrite(10, TimeUnit.SECONDS).refreshAfterWrite(2, TimeUnit.SECONDS)
                .removalListener(new RemovalListener<Object, Object>() {
                    @Override
                    public void onRemoval(RemovalNotification<Object, Object> notification) {
                        System.out.println("============remove!");
                    }
                }).build(new CacheLoader<String, String>() {
                    public String load(String key) {
                        System.out.println("*****************" + key);
                        return "mmmm";
                    }

                    /**
                     * 重新加载
                     * 
                     * @param key
                     * @param oldValue
                     * @return
                     * @throws Exception
                     */
                    @Override
                    public ListenableFuture<String> reload(String key, String oldValue) throws Exception {
                        System.out.println("66666666666666666666666666:" + oldValue);
                        return Futures.immediateFuture("KKKKKKKKKK");
                    }
                });
        // boolean continued = true;

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    int i = 0;
                    while (i < 10000000000L) {
                        System.out.println(build.get("123") + Thread.currentThread().getName());
                        i++;
                    }
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        try {
            System.out.println(build.get("123"));
            System.out.println(build.get("123"));
            System.out.println(build.get("123"));
            try {
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(build.get("123"));
            System.out.println(build.get("123"));
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
