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
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;


@Configuration
@EnableTransactionManagement
public class Config {
	@Autowired
    private Environment env; 
	
			@Bean(name="postgresDataSource")
			 @ConfigurationProperties(prefix = "spring3.postgres.datasource")
			public DataSource dataSource() {
				DriverManagerDataSource dataSource = new DriverManagerDataSource();
				dataSource.setDriverClassName(env.getProperty("spring3.postgres.datasource.driverClassName"));
				dataSource.setUrl(env.getProperty("spring3.postgres.datasource.url"));
				dataSource.setUsername(env.getProperty("spring3.postgres.datasource.username"));
				return dataSource;
			}
		 
			@Bean(name="postgresEntityManager")
			 @PersistenceContext(unitName = "firstPersistence")
			@Primary
			  public LocalContainerEntityManagerFactoryBean entityManagerFactory(
			            @Qualifier("postgresDataSource") final DataSource dataSource) {
				final LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		        entityManagerFactoryBean.setJpaVendorAdapter(this.vendorAdaptor());
		        entityManagerFactoryBean.setDataSource(dataSource);
		        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		        entityManagerFactoryBean.setPersistenceUnitName("firstPersistence");
		        entityManagerFactoryBean.setPackagesToScan("com.example.*");
		        entityManagerFactoryBean.setJpaProperties(this.jpaHibernateProperties());
		        entityManagerFactoryBean.afterPropertiesSet();
		        return entityManagerFactoryBean;
			}
			
			private HibernateJpaVendorAdapter vendorAdaptor() {
		        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		       
		        return vendorAdapter;
		    }
		 
		    private Properties jpaHibernateProperties() {
		        final Properties properties = new Properties();
		      
		        return properties;
		    }

		 
			@Bean(name="postgresTransactionManager")
			public PlatformTransactionManager dbTransactionManager(@Qualifier("postgresEntityManager") final EntityManagerFactory emf) {
				JpaTransactionManager transactionManager = new JpaTransactionManager();
	            transactionManager.setEntityManagerFactory(
	            		entityManagerFactory(dataSource()).getObject());
	            return transactionManager;
			
				
			}
			}





	 
   /* @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.mysql")
    public DataSource oraclesqlDataSource() {
        return DataSourceBuilder
                    .create()
                    .build();
    }
 
    @Primary
    @Bean(name = "mysqlEntityManager")
    public LocalContainerEntityManagerFactoryBean oraclesqlEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                    .dataSource(oraclesqlDataSource())
                    .packages(EmployeeEntity.class) // you can also give the package where the Entities are given rather than giving Entity class
                    .persistenceUnit("mysqlPU")
                    .build();
    }
 
    @Primary
    @Bean(name = "mysqlTransactionManager")
    public PlatformTransactionManager oraclesqlTransactionManager(@Qualifier("mysqlEntityManager") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }





*/


