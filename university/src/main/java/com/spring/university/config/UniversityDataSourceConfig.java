package com.spring.university.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableJpaRepositories(basePackages = {
		"com.spring.university.repositories" }, entityManagerFactoryRef = "PrimaryEntityManagerFactory", transactionManagerRef = "PrimaryTransactionManager")
public class UniversityDataSourceConfig {

	@Bean("PrimaryDataSource")
	public DataSource getDataSource() {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		try {
			dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/web_customer_tracker?useSSL=FALSE");
			dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
			dataSource.setUser("root");
			dataSource.setPassword("Divyadb9");
			dataSource.setMinPoolSize(5);
			dataSource.setMaxPoolSize(10);
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return dataSource;
	}

	@Bean("PrimaryEntityManagerFactory")
	public EntityManagerFactory getEntityManagerFactory() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setJpaVendorAdapter(adapter);
		factoryBean.setDataSource(getDataSource());
		factoryBean.setPackagesToScan("com.spring.university.pojo");
		factoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		factoryBean.afterPropertiesSet();
		return factoryBean.getObject();
	}

	@Bean("PrimaryTransactionManager")
	public PlatformTransactionManager getTransactionManager() {
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(getEntityManagerFactory());
		txManager.setDataSource(getDataSource());
		return txManager;
	}
}
