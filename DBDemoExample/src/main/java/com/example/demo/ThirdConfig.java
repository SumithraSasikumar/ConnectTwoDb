package com.example.demo;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@Configuration
@EnableTransactionManagement
public class ThirdConfig {
	@Autowired
    private Environment env; 
	
			@Bean(name="mysql3DataSource")
			 @ConfigurationProperties(prefix = "spring2.mysql3.datasource")
			public DataSource dataSource3() {
				DriverManagerDataSource dataSource = new DriverManagerDataSource();
				dataSource.setDriverClassName(env.getProperty("spring2.mysql3.datasource.driverClassName"));
				dataSource.setUrl(env.getProperty("spring2.mysql3.datasource.url"));
				dataSource.setUsername(env.getProperty("spring2.mysql3.datasource.username"));
				dataSource.setPassword(env.getProperty("spring2.mysql3.datasource.password"));
				return dataSource;
			}
		 
			
			
			@Bean(name="mysql3EntityManager")
			 @PersistenceContext(unitName = "thirdPersistence")
			@Primary
		    public LocalContainerEntityManagerFactoryBean entityManagerFactory( @Qualifier("mysql3DataSource") final DataSource dataSource) {
		        final LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		        entityManagerFactoryBean.setJpaVendorAdapter(this.vendorAdaptor());
		        entityManagerFactoryBean.setDataSource(dataSource);
		        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		        entityManagerFactoryBean.setPersistenceUnitName("thirdPersistence");
		        entityManagerFactoryBean.setPackagesToScan("com.example.*");
		        entityManagerFactoryBean.setJpaProperties(this.jpaHibernateProperties());
		        entityManagerFactoryBean.afterPropertiesSet();
		        return entityManagerFactoryBean;
		       /* LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		        entityManagerFactoryBean.setDataSource(dataSource3());
		        entityManagerFactoryBean.setPackagesToScan("com.example.*");
		        entityManagerFactoryBean.setPersistenceUnitName("thirdPersistence");
		        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		       Properties hibernateProperties = new Properties();
				hibernateProperties.put("hibernate.dialect", env.getProperty("spring2.mysql3.datasource.jpa.database-platform"));
				hibernateProperties.put("hibernate.show_sql", true);
				hibernateProperties.put("hibernate.hbm2ddl.auto", env.getProperty("spring2.mysql3.datasource.jpa.hibernate.ddl-auto"));
				entityManagerFactoryBean.setJpaProperties(hibernateProperties);
		        return entityManagerFactoryBean;*/
		    }
			private HibernateJpaVendorAdapter vendorAdaptor() {
		        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		       
		        return vendorAdapter;
		    }
		 
		    private Properties jpaHibernateProperties() {
		        final Properties properties = new Properties();
		      
		        return properties;
		    }
		    
			@Bean(name="mysql3TransactionManager")
			 public PlatformTransactionManager dbTransactionManager(@Qualifier("mysql3EntityManager") final EntityManagerFactory emf) {
				JpaTransactionManager transactionManager = new JpaTransactionManager();
	            transactionManager.setEntityManagerFactory(
	            		entityManagerFactory(dataSource3()).getObject());
	            return transactionManager;
			    }
	     
	        }

