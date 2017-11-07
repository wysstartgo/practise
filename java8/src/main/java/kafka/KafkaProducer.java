package kafka;

import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * <pre>
 *
 * 【标题】:
 * 【描述】:
 * 【版权】: 润投科技
 * 【作者】: wuys
 * 【时间】: 2017-11-03 10:58
 * </pre>
 */
public class KafkaProducer {

    public static void main(String[] args) {
        String brokers = "192.168.1.59:9092,192.168.1.41:9092";
        Properties props = new Properties();
        props.put("bootstrap.servers", brokers);
        //props.put("serializer.class", this.serializerClass);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        // （1）acks=0： 设置为0表示producer不需要等待任何确认收到的信息。副本将立即加到socket
        // buffer并认为已经发送。没有任何保障可以保证此种情况下server已经成功接收数据，同时重试配置不会发生作用（因为客户端不知道是否失败）回馈的offset会总是设置为-1；
        // （2）acks=1：
        // 这意味着至少要等待leader已经成功将数据写入本地log，但是并没有等待所有follower是否成功写入。这种情况下，如果follower没有成功备份数据，而此时leader又挂掉，则消息会丢失。
        // （3）acks=-1   all：
        // 这意味着leader需要等待所有备份都成功写入日志，这种策略会保证只要有一个备份存活就不会丢失数据。这是最强的保证。
        // （4）其他的设置，例如acks=2也是可以的，这将需要给定的acks数量，但是这种策略一般很少用。
        props.put("acks", "1");
        props.put("retries",0);
        org.apache.kafka.clients.producer.KafkaProducer kafkaProducer = new org.apache.kafka.clients.producer.KafkaProducer(props);
        TestStreamDto testStreamDto = new TestStreamDto("PAMU","原油",System.currentTimeMillis(),199.9,39.5);
        kafkaProducer.send(new ProducerRecord("test_streams",testStreamDto.getProdCode(), JSONObject.toJSONString(testStreamDto)));
    }
}

class TestStreamDto {

    String prodCode;

    String prodName;

    long timestamp;

    double highPx;

    double lowPx;

    public TestStreamDto(String prodCode, String prodName, long timestamp, double highPx, double lowPx) {
        this.prodCode = prodCode;
        this.prodName = prodName;
        this.timestamp = timestamp;
        this.highPx = highPx;
        this.lowPx = lowPx;
    }

    public String getProdCode() {
        return prodCode;
    }

    public void setProdCode(String prodCode) {
        this.prodCode = prodCode;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public double getHighPx() {
        return highPx;
    }

    public void setHighPx(double highPx) {
        this.highPx = highPx;
    }

    public double getLowPx() {
        return lowPx;
    }

    public void setLowPx(double lowPx) {
        this.lowPx = lowPx;
    }
}
