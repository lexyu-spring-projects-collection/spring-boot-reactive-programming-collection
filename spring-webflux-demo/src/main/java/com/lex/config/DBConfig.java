package com.lex.config;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.r2dbc.connection.init.CompositeDatabasePopulator;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

/**
 * @author : Lex Yu
 */
@Configuration
public class DBConfig extends AbstractR2dbcConfiguration {
	@Override
	public ConnectionFactory connectionFactory() {
		return ConnectionFactories.get("r2dbc:postgresql://root:p@ssw0rd@localhost:5433/testdb");
	}

	@Bean
	public ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory){
		ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
		initializer.setConnectionFactory(connectionFactory);
		CompositeDatabasePopulator databasePopulator = new CompositeDatabasePopulator();
		databasePopulator.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("schema.sql")));
		databasePopulator.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("data.sql")));
		return initializer;
	}

}
