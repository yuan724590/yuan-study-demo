package yuan.study.demo.configuration;

import org.apache.http.HttpHost;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.sniff.Sniffer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;


@Configuration
public class ElasticsearchConfiguration {

    @Value("${spring.elasticsearch.host:127.0.0.1}")
    private String esHost;

    @Value("${spring.elasticsearch.port:9200}")
    private Integer esPort;

    @Value("${spring.elasticsearch.scheme:http}")
    private String esScheme;

    @Value("${spring.elasticsearch.connectTimeoutMills:5000}")
    private Integer connectTimeoutMills;

    @Value("${spring.elasticsearch.socketTimeoutMills:5000}")
    private Integer socketTimeoutMills;

    @Value("${spring.elasticsearch.connectionRequestTimeout:5000}")
    private Integer connectionRequestTimeout;

    @Value("${spring.elasticsearch.maxConnectPerRoute:5}")
    private Integer maxConnectPerRoute;

    @Value("${spring.elasticsearch.maxConnectTotal:2}")
    private Integer maxConnectTotal;

    @Value("${spring.elasticsearch.sniffIntervalMillis:180000}")
    private Integer sniffIntervalMillis;

    @Bean
    @Scope("singleton")
    @Primary
    public RestHighLevelClient getRestHighLevelClient() {
        HttpHost httpHost = new HttpHost(esHost, esPort, esScheme);
        //设置请求配置回调
        RestClientBuilder.HttpClientConfigCallback httpClientConfigCallback = getHttpClientConfigCallback();
        //获取请求httpClient配置回调
        RestClientBuilder.RequestConfigCallback requestConfigCallback = getRequestConfigCallback();
        RestClientBuilder restClientBuilder = RestClient.builder(httpHost)
                .setHttpClientConfigCallback(httpClientConfigCallback)
                .setRequestConfigCallback(requestConfigCallback);
        //嗅探器, 从集群中获取当前节点的列表，并通过调用RestClient＃setNodes来更新它们(默认情况下每隔5分钟)
        Sniffer.builder(restClientBuilder.build()).setSniffIntervalMillis(sniffIntervalMillis).build();
        return new RestHighLevelClient(restClientBuilder.build());
    }

    /**
     * 设置请求配置回调
     */
    private RestClientBuilder.RequestConfigCallback getRequestConfigCallback(){
        return e -> {
            e.setConnectTimeout(connectTimeoutMills);
            e.setSocketTimeout(socketTimeoutMills);
            e.setConnectionRequestTimeout(connectionRequestTimeout);
            return e;
        };
    }

    /**
     * 获取请求httpClient配置回调
     */
    private RestClientBuilder.HttpClientConfigCallback getHttpClientConfigCallback(){
        return e -> {
            //修改线程数为2, 默认会本地检测到的处理器数量一样多（取决于Runtime.getRuntime().availableProcessors()的返回值）
            IOReactorConfig reactorConfig = IOReactorConfig.custom().setIoThreadCount(2).build();
            e.setMaxConnTotal(maxConnectTotal);
            e.setMaxConnPerRoute(maxConnectPerRoute);
            e.setDefaultIOReactorConfig(reactorConfig);
            return e;
        };
    }
}
