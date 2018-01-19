package bloomfilter;


import snowflake.ISnowflakeIdService;
import snowflake.SnowflakeIdServiceImpl;
import util.DateFormatUtil;

import java.util.Date;
import java.util.Properties;
import java.util.concurrent.Executors;

/**
 * <pre>
 *
 * 【标题】: bloomFilter测试
 * 【描述】:
 * 【版权】: 润投科技
 * 【作者】: wuys
 * 【时间】: 2018-01-08 19:01
 * </pre>
 */
public class BloomFilterTest {

    public static void main(String[] args) {
        //hive-storage-api
        BloomFilter bloomFilter = new BloomFilter(10000, 0.0001);
        ISnowflakeIdService iSnowflakeIdService = SnowflakeIdServiceImpl.INSTANCE;
        for (int i=0;i<10000;i++){
            long nextId = iSnowflakeIdService.chatRoomNextId();
            boolean testLong = bloomFilter.testLong(nextId);
            System.out.println("============" + testLong);
            bloomFilter.addLong(nextId);
        }


        Executors.newSingleThreadExecutor().submit(() -> {
            System.out.println("**********************************");
        });

        Date date = new Date(1515403259403L);
        System.out.println(date);

        Properties properties = System.getProperties();
        String property = properties.getProperty("os.name");
        System.out.println(property);
    }


}
