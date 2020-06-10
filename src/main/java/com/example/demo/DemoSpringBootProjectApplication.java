package com.example.demo;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import com.example.demo.models.Quote;

@SpringBootApplication
@EnableScheduling
public class DemoSpringBootProjectApplication {

	private static final Logger log = LoggerFactory.getLogger(DemoSpringBootProjectApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringBootProjectApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate () {
		return new RestTemplate();
	}

	//consuming web service using rest template
	@Bean
	public CommandLineRunner runner(RestTemplate restTemplate) throws Exception {
		return args -> {
			Quote quote = restTemplate.getForObject("https://gturnquist-quoters.cfapps.io/api/random", Quote.class);
			log.info(quote.toString());
		};
	}
	// list of beans provided by spring boot and this application
//	@Bean
//	public CommandLineRunner commandLineRunner(ApplicationContext context) {
//		return args -> {
//			System.out.println("beans provided by spring boot:");
//			String[] beanNames = context.getBeanDefinitionNames();
//			Arrays.sort(beanNames);
//			for(String beanName: beanNames) {
//				System.out.println(beanName);
//			}
//		};
//	}

}
