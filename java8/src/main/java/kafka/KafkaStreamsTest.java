package kafka;

import com.alibaba.fastjson.JSONObject;
import com.rt.druid.dto.CommodityOriginalDataDto;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KStreamBuilder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * <pre>
 *
 * 【标题】:
 * 【描述】:
 * 【版权】: 润投科技
 * 【作者】: wuys
 * 【时间】: 2017-11-01 17:07
 * </pre>
 */
public class KafkaStreamsTest {

    public static void main(String[] args) {
        String topic = "test_streams";
        String kTable = "stream_table";
        Map<String, Object> props = new HashMap<>();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "my-stream-processing-application");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.1.59:9092,192.168.1.41:9092");
        props.put(StreamsConfig.KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        StreamsConfig config = new StreamsConfig(props);
        //JSONObject.parseObject(value.toString(), CommodityOriginalDataDto.class)
        KStreamBuilder builder = new KStreamBuilder();
//        builder.globalTable(Serdes.String(),Serdes.String(),topic,kTable);
        builder.stream(topic)
                .mapValues(value -> JSONObject.parseObject(value.toString(), CommodityOriginalDataDto.class))
                .foreach((key,value) -> System.out.println("＊＊＊＊＊＊＊＊＊＊＊＊＊" + key + "    *****" + value.toString()));

//        builder.stream(topic).map((key,value) -> );
        KafkaStreams streams = new KafkaStreams(builder, config);
        streams.start();

    }
}
