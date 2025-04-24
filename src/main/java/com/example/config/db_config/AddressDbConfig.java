package com.example.config.db_config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.example.entity.Address;
import com.zaxxer.hikari.HikariDataSource;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		basePackages = "com.example.repository.AddressRepository",
		entityManagerFactoryRef = "addressEntityManagerFactory",
		transactionManagerRef = "addressTransactionManager"
		)
public class AddressDbConfig {
	
	@Bean
	@ConfigurationProperties("spring.datasource.db2")
	public DataSourceProperties db2DataSourceProperties() {
		return new DataSourceProperties();
	}
	
	@Bean("addressDataSource")
	@ConfigurationProperties("spring.datasource.db2.configuration")
	public DataSource addressDataSource() {
		return db2DataSourceProperties().initializeDataSourceBuilder()
				.type(HikariDataSource.class)
				.build();
	}
	
	@Bean(name = "addressEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
		EntityManagerFactoryBuilder builder	
			) {
		return builder
				.dataSource(addressDataSource())
				.packages(Address.class)
				.build();
	}
	
	@Bean(name = "addressTransactionManager")
	public PlatformTransactionManager transactionManager(
		@Qualifier("entityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactoryBean 
			) {
		return new JpaTransactionManager(entityManagerFactoryBean.getObject());
	}

}
