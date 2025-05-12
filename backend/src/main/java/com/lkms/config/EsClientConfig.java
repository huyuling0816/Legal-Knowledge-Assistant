package com.lkms.config;
import co.elastic.clients.elasticsearch.ElasticsearchAsyncClient;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class EsClientConfig {
    private final EsConfig elasticSearchConfig;

    public EsClientConfig(EsConfig elasticSearchConfig) {
        this.elasticSearchConfig = elasticSearchConfig;
    }

    // 该Bean用于http连接
    @Bean
    public RestClient restClientHttp() {

        // 拆分地址
        List<HttpHost> hostLists = new ArrayList<>();
        String[] hostArray = elasticSearchConfig.getAddress().split(",");
        for (String temp : hostArray) {
            String host = temp.split(":")[0];
            String port = temp.split(":")[1];
            hostLists.add(new HttpHost(host, Integer.parseInt(port), elasticSearchConfig.getSchema()));
        }

        // 转换成 HttpHost 数组
        HttpHost[] httpHost = hostLists.toArray(new HttpHost[]{});
        // 设置密码
        final CredentialsProvider credentialsProvider =
                new BasicCredentialsProvider();
//        credentialsProvider.setCredentials(AuthScope.ANY,
//                new UsernamePasswordCredentials(elasticSearchConfig.getUsername(), elasticSearchConfig.getPassword()));
        // 构建连接对象
        RestClientBuilder builder = RestClient.builder(httpHost);
        // 异步连接延时配置
        builder.setRequestConfigCallback(requestConfigBuilder -> {
            requestConfigBuilder.setConnectTimeout(elasticSearchConfig.getConnectTimeout());
            requestConfigBuilder.setSocketTimeout(elasticSearchConfig.getSocketTimeout());
            requestConfigBuilder.setConnectionRequestTimeout(elasticSearchConfig.getConnectionRequestTimeout());
            return requestConfigBuilder;
        });

        // 异步连接数配置
        builder.setHttpClientConfigCallback(httpClientBuilder -> {
            httpClientBuilder.setMaxConnTotal(elasticSearchConfig.getMaxConnectNum());
            httpClientBuilder.setMaxConnPerRoute(elasticSearchConfig.getMaxConnectPerRoute());
            return httpClientBuilder;
        });
        builder.setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
            @Override
            public HttpAsyncClientBuilder customizeHttpClient(
                    HttpAsyncClientBuilder httpClientBuilder) {
                return httpClientBuilder
                        .setDefaultCredentialsProvider(credentialsProvider);
            }
        });
        builder.setHttpClientConfigCallback(hc -> hc.setDefaultCredentialsProvider(credentialsProvider));
        return builder.build();

    }

    @Bean
    public ElasticsearchTransport elasticsearchTransport(RestClient restClient) {
        return new RestClientTransport(
                restClient, new JacksonJsonpMapper());
    }

    @Bean
    public ElasticsearchClient elasticsearchClient(ElasticsearchTransport transport) {
        return new ElasticsearchClient(transport);
    }

    @Bean
    public synchronized ElasticsearchAsyncClient elasticsearchAsyncClient(ElasticsearchTransport transport) {
        return new ElasticsearchAsyncClient(transport);
    }

}
