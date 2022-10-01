package springsecurity.security.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource("classpath:/resources/database.properties")
@EnableTransactionManagement
@ComponentScan(basePackages = {
    "springsecurity.security"
})

public class Appcontext {
	    @Autowired
	    private Environment environment;
	    
	    @Bean
	    public LocalSessionFactoryBean sessionFactory() {
	    	System.out.println("sessionFactory():IN");
	        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
	        
	        sessionFactory.setDataSource(dataSource());
	        
	        sessionFactory.setPackagesToScan(new String[] {
	            "com.webapp.model"
	        });
	        
	        sessionFactory.setHibernateProperties(hibernateProperties());
	        System.out.println("sessionFactory()"+sessionFactory+"OUT");
	        return sessionFactory;
	    }

	    @Bean
	    public DataSource dataSource() {
	    	System.out.println("dataSource():IN");
	        DriverManagerDataSource dataSource = new DriverManagerDataSource();
	        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
	        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
	        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
	        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
	        return dataSource;
	    }

	    private Properties hibernateProperties() {
	        Properties properties = new Properties();
	        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
	        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
	        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
	        properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
	        return properties;
	    }

	    @Bean
	    public HibernateTransactionManager getTransactionManager() {
	        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
	        transactionManager.setSessionFactory(sessionFactory().getObject());
	        return transactionManager;
	    }

		
	}

