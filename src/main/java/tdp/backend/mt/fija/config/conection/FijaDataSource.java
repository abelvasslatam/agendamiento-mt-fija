package tdp.backend.mt.fija.config.conection;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(basePackages = {"tdp.backend.mt.fija.main.fija.repository"},
        entityManagerFactoryRef = "fijaEntityManager",
        transactionManagerRef = "fijaTransactionManager")
public class FijaDataSource {
	
	@Autowired
    private Environment env;

    @Bean
    public LocalContainerEntityManagerFactoryBean fijaEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(db2Datasource());
        em.setPackagesToScan(new String[]{"tdp.backend.mt.fija.main.fija.model"});
        em.setPersistenceUnitName("fijaEntityManager");
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        properties.put("hibernate.show-sql", env.getProperty("jdbc.show-sql"));
        em.setJpaPropertyMap(properties);
        return em;
    }

    @Bean
    public DataSource db2Datasource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("db.fija.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("db.fija.datasource.url"));
        dataSource.setUsername(env.getProperty("db.fija.datasource.username"));
        dataSource.setPassword(env.getProperty("db.fija.datasource.password"));

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager fijaTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(fijaEntityManager().getObject());
        return transactionManager;
    }

}
