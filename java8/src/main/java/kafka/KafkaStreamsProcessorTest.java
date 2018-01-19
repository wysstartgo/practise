package kafka;

import com.alibaba.fastjson.JSONObject;
import com.rt.druid.dto.CommodityOriginalDataDto;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStreamBuilder;
import org.apache.kafka.streams.processor.TopologyBuilder;
import org.apache.kafka.streams.state.Stores;

import java.util.HashMap;
import java.util.Map;

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
public class KafkaStreamsProcessorTest {

    public static void main(String[] args) {
        String topic = "test_streams1";
        //String topic = "wys_test";
        String resultTopic = "test_streams_result";
        String kTable = "stream_table";
        String source = "RT-Market";
        String processer1 = "processer1";
        String sink1 = "sink1";
        Map<String, Object> props = new HashMap<>();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "my-stream-processing-application");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.1.59:9092,192.168.1.41:9092");
        props.put(StreamsConfig.KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        StreamsConfig config = new StreamsConfig(props);
        //JSONObject.parseObject(value.toString(), CommodityOriginalDataDto.class)
        TopologyBuilder builder = new TopologyBuilder();
        builder.addSource(source,topic)
                .addProcessor(processer1,MyProcessor::new,source)
                .addStateStore(Stores.create("COUNTS").withStringKeys().withStringValues().inMemory().build(), processer1)
                //.connectProcessorAndStateStores(processer1, "COUNTS")
                .addSink(sink1,resultTopic,processer1);

//        KStreamBuilder builder = new KStreamBuilder();
//        builder.globalTable(Serdes.String(),Serdes.String(),topic,kTable);
//        builder.stream(topic)
//                .mapValues(value -> JSONObject.parseObject(value.toString(), CommodityOriginalDataDto.class))
//                .foreach((key,value) -> System.out.println("＊＊＊＊＊＊＊＊＊＊＊＊＊" + key + "    *****" + value.toString()));

//        builder.stream(topic).map((key,value) -> );
        KafkaStreams streams = new KafkaStreams(builder, config);
        streams.start();

    }
}
