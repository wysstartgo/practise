import com.google.common.collect.ImmutableMap;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.network.InetAddresses;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Collections;


/**
 * <pre>
 *
 * 【标题】: https://blog.csdn.net/u011781521/article/details/77853571?locationNum=9&fps=1
 * 【描述】:
 * 【版权】: 润投科技
 * 【作者】: wuys
 * 【时间】: 2018-05-10 16:51
 * </pre>
 */
public class Main {

    public static void main(String[] args) throws InterruptedException, IOException {
        RestClientBuilder restClientBuilder = RestClient.builder(new HttpHost("192.168.1.154", 9200, "http"))
                .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                    public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                        //return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                        return httpClientBuilder;
                    }
                });
        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(restClientBuilder);
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("tags_ids", "330");
        SearchRequest searchRequest = new SearchRequest("rt-article");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder().query(matchQueryBuilder);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest);
        System.out.println(searchResponse.toString());
    }

    /**
     * restClient low level
     * @throws Exception
     */
    private static void testLowRestClient() throws Exception{
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
//        credentialsProvider.setCredentials(AuthScope.ANY,
//                new UsernamePasswordCredentials("elastic", "changeme"));
        RestClient restClient = RestClient.builder(new HttpHost("192.168.1.154", 9200, "http"))
                .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                    public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                        //return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                        return httpClientBuilder;
                    }
                }).build();
        String method = "POST";
        String endpoint = "/rt-article/rt-article-source_group_id/_search";
        NStringEntity nStringEntity = new NStringEntity("{" + "\"query\":{\n" +
                "     \"query_string\": {\n" +
                "       \"query\": \"*\"\n" +
                "     }\n" +
                "   }" + "}", ContentType.APPLICATION_JSON);
        Response response = restClient.performRequest(method, endpoint, Collections.EMPTY_MAP, nStringEntity);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    /**
     * transport
     */
    private static void testTransport(){
        Settings settings = Settings.builder()
                .put("cluster.name", "rt-es")// 集群名
                .put("client.transport.sniff", true)
                // 自动把集群下的机器添加到列表中
                .build();
        PreBuiltTransportClient transportClient = new PreBuiltTransportClient(settings);
        TransportAddress transportAddress =  new TransportAddress(InetAddresses.forString("192.168.1.154"),9300);
        transportClient.addTransportAddresses(transportAddress);
        long start = System.currentTimeMillis();
//        Long id = 1L;
//        ImmutableMap<String, ? extends Serializable> map = ImmutableMap.of("id", 1, "name", "socket");
//        IndexRequestBuilder irb = transportClient.prepareIndex().setIndex("test_content").setType("content");
//        IndexResponse indexResponse = irb.setSource(map).setId(id + "").execute().actionGet();
        QueryStringQueryBuilder queryStringQueryBuilder = QueryBuilders.queryStringQuery("tags_ids:330");
        SearchResponse searchResponse = transportClient.prepareSearch("rt_article").setQuery(queryStringQueryBuilder)
                .setTypes("rt_article_source_group_id").setFetchSource(false).setFrom(0).setSize(10).execute()
                .actionGet();
        long fetchTime = System.currentTimeMillis() - start;
        System.out.println(fetchTime);
        System.out.println(searchResponse.toString());
    }


}
