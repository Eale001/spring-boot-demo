package com.eale.elastics.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Admin
 * @Date 2021/3/9
 * @Description //ElasticsConfig
 * @Version 1.0
 **/
@Configuration
public class ElasticsConfig {

    @Bean
    public RestHighLevelClient restHighLevelClient(){
        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("192.168.11.247", 9200, "http")
                )
        );
        return restHighLevelClient;
    }


}
