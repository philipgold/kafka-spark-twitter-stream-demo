package io.philipg.spark.consumer;

import io.philipg.spark.consumer.service.SparkConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SparkConsumerApplication implements CommandLineRunner {

	@Autowired
	private SparkConsumerService sparkConsumerService;

    public static void main(String[] args){
		SpringApplication.run(SparkConsumerApplication.class, args);
	}


	@Override
	public void run(String... strings) throws Exception {
		sparkConsumerService.run();
	}
}
