package tdp.backend.mt.fija.config.conection;

import javax.sql.DataSource;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(basePackages = {"tdp.backend.mt.fija.main.mt.repository"},
        entityManagerFactoryRef = "mtEntityManager",
        transactionManagerRef = "mtTransactionManager")
public class MtDataSource {
	
	@Autowired
    private Environment env;
	
	@Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean mtEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(mtDatasource());
        em.setPackagesToScan(new String[]{"tdp.backend.mt.fija.main.mt.model"});
        em.setPersistenceUnitName("mtEntityManager");
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("spring.jpa.database-platform", env.getProperty("spring.jpa.database-platform"));
        properties.put("spring.jpa.show-sql",env.getProperty("spring.jpa.show-sql"));
        properties.put("spring.jpa.properties.hibernate.format_sql",env.getProperty("spring.jpa.properties.hibernate.format_sql"));
        em.setJpaPropertyMap(properties);
        return em;
    }
	
	@Primary
    @Bean
    public DataSource mtDatasource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("db.mt.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("db.mt.datasource.url"));
        dataSource.setUsername(env.getProperty("db.mt.datasource.username"));
        dataSource.setPassword(env.getProperty("db.mt.datasource.password"));

        return dataSource;
    }

    @Primary
    @Bean
    public PlatformTransactionManager mtTransactionManager() {

        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(mtEntityManager().getObject());
        return transactionManager;
    }

}
