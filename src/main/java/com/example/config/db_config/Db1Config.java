package com.example.config.db_config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.example.entity.Student;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		basePackages = "com.example.repository",
		entityManagerFactoryRef = "entityManagerFactory",
		transactionManagerRef = "transactionManager"
		)
public class Db1Config {
	@Primary
	@Bean
	@ConfigurationProperties("spring.datasource.db1")
	public DataSourceProperties db1DataSourceProperties() {
		return new DataSourceProperties();
	}
	
	@Primary
	@Bean
	@ConfigurationProperties("spring.datasource.db1.configuration")
	public DataSource dataSource() {
		return db1DataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
	}
	
	@Primary
	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {
		return builder
				.dataSource(dataSource())
				.packages(Student.class).build();
	}
	
	@Primary
	@Bean(name = "transactionManager")
	public PlatformTransactionManager transactionManager(
			final @Qualifier("entityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
		return new JpaTransactionManager(entityManagerFactoryBean.getObject());
	}
}