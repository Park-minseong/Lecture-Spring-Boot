package com.spring.boardWeb.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

//이 클래스를 설정파일로 읽어들이는 어노테이션
@Configuration
//설정내용을 어느 파일에서 읽어올 것인지 결정
//classpath: src/main/resource
@PropertySource("classpath:/application.properties")
public class DataConfiguration {
	//Bean객체로 생성해주는 어노테이션
	@Bean
	//application.properties 파일에서 spring.datasource.hikari로 시작하는 설정들만 읽어오는 설정
	@ConfigurationProperties(prefix="spring.datasource.hikari")
	public HikariConfig hikariConfig() {
		return new HikariConfig();
	}
	
	@Bean
	public DataSource dataSource() throws Exception {
		DataSource dataSource = new HikariDataSource(hikariConfig());
		return dataSource;
	}
	
	@Bean
	@ConfigurationProperties(prefix="spring.jpa")
	public Properties hibernateConfig() {
		return new Properties();
	}

}
