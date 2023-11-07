package com.lex.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

/**
 * @author : Lex Yu
 */
@Configuration
public class BeanConfig {

	@Bean
	public WebClient compressWebClient(){
		WebClient compress_webclient = WebClient.builder()
				.clientConnector(
						new ReactorClientHttpConnector(
								HttpClient.newConnection().compress(true)
						)
				)
				.build();

		return compress_webclient;
	}
}
