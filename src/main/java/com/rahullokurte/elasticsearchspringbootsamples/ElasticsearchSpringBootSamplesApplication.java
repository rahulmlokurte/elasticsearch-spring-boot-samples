package com.rahullokurte.elasticsearchspringbootsamples;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ElasticsearchSpringBootSamplesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElasticsearchSpringBootSamplesApplication.class, args);
	}

}
