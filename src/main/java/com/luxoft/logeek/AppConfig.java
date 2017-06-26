package com.luxoft.logeek;

import com.p6spy.engine.spy.P6DataSource;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("com.luxoft.logeek.repository")
@ComponentScan(basePackages = {"com.luxoft.logeek"})
public class AppConfig {

//	@Bean
//	public DataSource actualDataSource() {
//		return new EmbeddedDatabaseBuilder()
//				.setType(EmbeddedDatabaseType.H2)
//				.build();
//	}

//	@Bean
//	public DataSource actualDataSource() {
//		DriverManagerDataSource ds = new DriverManagerDataSource();
//				ds.setDriverClassName("org.apache.derby.jdbc.ClientDriver40");
//				ds.setUrl("jdbc:derby://localhost:1527/testdb");
//		return ds;
//	}

	@Bean
	public DataSource actualDataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("org.postgresql.Driver");
		ds.setUrl("jdbc:postgresql://localhost:5432/postgres");
		ds.setUsername("postgres");
		ds.setPassword("postgres");
		return ds;
	}

	@Bean
	public P6DataSource dataSource(DataSource actualDataSource) {
		return new P6DataSource(actualDataSource);
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
//		adapter.setDatabase(Database.H2);
		adapter.setDatabase(Database.POSTGRESQL);
		adapter.setGenerateDdl(true);
		return adapter;
	}

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource(actualDataSource()));
		em.setJpaVendorAdapter(jpaVendorAdapter());
		em.setPackagesToScan("com.luxoft.logeek.entity");
		em.setJpaProperties(additionalProperties());
		em.setJpaDialect(new HibernateJpaDialect());
		return em;
	}

	/**
	 * Set false for both hibernate.show_sql and hibernate.format_sql
	 * when running benchmarks to prevent JMH log pollution
	 *
	 * @return hibernate props
	 */
	private Properties additionalProperties() {
		Properties properties = new Properties();
		properties.setProperty(AvailableSettings.SHOW_SQL, "true");
		properties.setProperty(AvailableSettings.FORMAT_SQL, "false");
		return properties;
	}
}
