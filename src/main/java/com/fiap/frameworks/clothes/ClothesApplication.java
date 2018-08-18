package com.fiap.frameworks.clothes;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.Ticker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.jms.ConnectionFactory;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableJms
@EnableSwagger2
@EnableCaching
public class ClothesApplication {

	private static final int MAXIMUM_SIZE = 10000;

	@Bean
	@Primary
	public CacheManager cacheManager(Ticker ticker) {
		CaffeineCache ordersCache = new CaffeineCache("orders", Caffeine.newBuilder()
				.expireAfterWrite(10, TimeUnit.SECONDS)
				.maximumSize(MAXIMUM_SIZE)
				.ticker(ticker)
				.build());

		SimpleCacheManager manager = new SimpleCacheManager();
		manager.setCaches(Collections.singletonList(ordersCache));
		return manager;
	}

	@Bean
	public Ticker ticker() {
		return Ticker.systemTicker();
	}

	@Bean
	public Docket apiDoc() {
		return new Docket(DocumentationType.SWAGGER_2) //
				.select() //
				.apis(RequestHandlerSelectors.basePackage("com.fiap.frameworks.clothes"))
				.paths(PathSelectors.any()) //
				.build()//
				.pathMapping("/");
	}

	@Bean
	public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory, DefaultJmsListenerContainerFactoryConfigurer configurer) {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		factory.setConcurrency("2-10");
		configurer.configure(factory, connectionFactory);
		return factory;
	}

	@Bean
	public MessageConverter jacksonJmsMessageConverter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("_type");
		return converter;
	}

	public static void main(String[] args) {
		SpringApplication.run(ClothesApplication.class, args);
	}
}
