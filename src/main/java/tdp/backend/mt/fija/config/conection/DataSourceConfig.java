package tdp.backend.mt.fija.config.conection;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DataSourceConfig {
	
	@Bean
	
	@ConfigurationProperties(prefix="spring.datasource")
	public DataSource getDataSourceMT() {
		
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        dataSourceBuilder.url("jdbc:postgresql://sl-us-south-1-portal.33.dblayer.com:56296/compose");
        dataSourceBuilder.username("admin");
        dataSourceBuilder.password("PVWVDQBRVQCQMBJX");
        return dataSourceBuilder.build();
	}
	
	@Bean
	@Primary
	//@ConfigurationProperties(prefix="spring.secondDatasource")
	public DataSource secondaryDataSource() {
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        dataSourceBuilder.url("jdbc:postgresql://sl-us-south-1-portal.6.dblayer.com:22492/compose?currentSchema=ibmx_a07e6d02edaf552&useSSL=false&characterEncoding=utf-8");
        dataSourceBuilder.username("admin");
        dataSourceBuilder.password("GQOYAWZYTAMOWBYC");
        return dataSourceBuilder.build();
	}
}
