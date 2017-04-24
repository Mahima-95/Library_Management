package com.library.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DbConfiguration {
	@Value("${db.mysql.driver}")
	private String dbDriver;
	@Value("${db.mysql.url}")
	private String dbURL;
	@Value("${db.mysql.username}")
	private String dbUsername;
	@Value("${db.mysql.password}")
	private String dbPassword;

	@Bean
	public DataSource getDataSource() throws SQLException {

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(dbDriver);
		dataSource.setUrl(dbURL);
		dataSource.setUsername(dbUsername);
		dataSource.setPassword(dbPassword);
		return dataSource;
	}

	@Bean(name = "jdbcTemplate")
	public JdbcTemplate getJdbcTemplate() throws SQLException {

		DataSource dataSource = getDataSource();
		System.out.println("conn===" + dataSource.getConnection());
		return new JdbcTemplate(getDataSource());
	}
}
