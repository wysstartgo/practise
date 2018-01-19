package kafka;

import com.alibaba.fastjson.JSONObject;
import com.rt.druid.dto.CommodityOriginalDataDto;
import com.rt.druid.vo.MarketRespVo;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.KeyValueStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.DateFormatUtil;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MyProcessor implements Processor<String, String> {

    private static Logger LOGGER = LoggerFactory.getLogger(MyProcessor.class);

    private long oneMinuteTime = TimeUnit.MINUTES.toMillis(1);

    private String splitComma = ",";

    private ProcessorContext context;

    private KeyValueStore kvStore;

    private long lastTime;// 最后一条的时间

    private long maxDelay = TimeUnit.SECONDS.toMillis(5);// 超过这个时间的数据 就会被丢弃

    private Object LOCK = new Object();

    @Override
    @SuppressWarnings("unchecked")
    public void init(ProcessorContext context) {
        this.context = context;
        this.context.schedule(1000);
        this.kvStore = (KeyValueStore) context.getStateStore("COUNTS");
    }

    @Override
    public void process(String dummy, String line) {
        synchronized (LOCK) {
            System.out.println("============in");
            CommodityOriginalDataDto codd = JSONObject.parseObject(line, CommodityOriginalDataDto.class);
            long dealTime = codd.getDealTime();
            // 一分钟的聚合
            long mod = dealTime % oneMinuteTime;
            // 时间为
            long timePeriod = dealTime - mod;
            // 最后一条的时间
            if (timePeriod >= lastTime) {
                lastTime = timePeriod - maxDelay;
            } else {
                //LOGGER.error("超过最大延时时间，数据将被丢弃!");
                System.out.println("超过最大延时时间，数据将被丢弃!");
                return;
            }
            // 行情代码
            String prodCode = codd.getProdCode();
            // 要往kvStore中存放的key
            String key = new StringBuilder(prodCode).append(splitComma).append(timePeriod).toString();
            // 根据key取到data
            String data = (String) this.kvStore.get(key);
            if (data == null) {
                MarketRespVo mrv = new MarketRespVo();
                mrv.setTimestamp(timePeriod);
                // 一分钟内的开盘价
                mrv.setOpenPx(codd.getNewPrice());
                // 一分钟内的收盘价
                mrv.setClosePx(codd.getNewPrice());
                // 一分钟内的最高价
                mrv.setHighPx(codd.getNewPrice());
                // 一分钟内的最低价
                mrv.setLowPx(codd.getNewPrice());
                mrv.setProdCode(prodCode);
                this.kvStore.put(key, JSONObject.toJSONString(mrv));
            } else {
                // 上一条
                MarketRespVo marketRespVo = JSONObject.parseObject(data, MarketRespVo.class);
                // 一分钟内的开盘价，保持不变
                // 一分钟内的收盘价
                marketRespVo.setClosePx(codd.getNewPrice());
                // 一分钟内的最高价
                marketRespVo.setHighPx(
                        marketRespVo.getHighPx() > codd.getNewPrice() ? marketRespVo.getHighPx() : codd.getNewPrice());
                // 一分钟内的最低价
                marketRespVo.setLowPx(
                        marketRespVo.getLowPx() < codd.getNewPrice() ? marketRespVo.getLowPx() : codd.getNewPrice());
                this.kvStore.put(key, JSONObject.toJSONString(marketRespVo));
            }
        }
    }

    @Override
    public void punctuate(long timestamp) {
        System.out.println("********************in");
        // 在这里提交已经聚合完成的数据
        //long curTimeMills = System.currentTimeMillis();
        //long mod = curTimeMills % oneMinuteTime;
        // 时间范围
        //long curStandardTime = curTimeMills - mod;
        // 将小于该时间的进行向下流转
        KeyValueIterator iter = this.kvStore.all();
        while (iter.hasNext()) {
            KeyValue entry = (KeyValue) iter.next();
            String key = (String) entry.key;
            String[] arr = key.split(splitComma);
            String prodCode = arr[0];
            Long time = Long.valueOf(arr[1]);
            if (time < lastTime) {
                Object kvKey = entry.key;
                Object value = entry.value;
                if (value == null) {
                    this.kvStore.delete(kvKey);
                    continue;
                }
                context.forward(kvKey, value.toString());
                this.kvStore.delete(kvKey);
                System.out.println("punctuate!!!!!!!!!!!!!!!!!" + "prodCode:" + prodCode + ";time:" + time);
            }
        }
        iter.close();
        context.commit();

    }

    @Override
    public void close() {
        this.kvStore.close();
    }

    public static void main(String[] args) {
        long oneMinuteTime = TimeUnit.MINUTES.toMillis(1);
        // long oneSecondTime = TimeUnit.SECONDS.toMillis(1);
        // IntStream.range(1,200000).forEach(i ->
        // System.out.println(System.currentTimeMillis() % oneMinuteTime));
        for (int i = 0; i < 20000000; i++) {
            long curTime = System.currentTimeMillis();
            long mod = curTime % oneMinuteTime;
            long timePeriod = curTime - mod;
            String formatSdfymdhmsSSS = DateFormatUtil.formatSdfymdhmsSSS(new Date(timePeriod));
            System.out.println(formatSdfymdhmsSSS);
        }
    }
};