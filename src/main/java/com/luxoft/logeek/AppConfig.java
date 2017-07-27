package com.luxoft.logeek;

import com.luxoft.logeek.config.PersistenceConfig;
import com.p6spy.engine.spy.P6DataSource;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.dialect.Oracle10gDialect;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;
import java.util.function.Supplier;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("com.luxoft.logeek.repository")
@ComponentScan(basePackages = {"com.luxoft.logeek"})
@Import(PersistenceConfig.class)
public class AppConfig {

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setGenerateDdl(true);
		return adapter;
	}

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
			Supplier<Properties> propertiesSupplier,
			DataSource dataSource
	) {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(new P6DataSource(dataSource));
		em.setJpaVendorAdapter(jpaVendorAdapter());
		em.setPackagesToScan("com.luxoft.logeek.entity");
		em.setJpaProperties(propertiesSupplier.get());
		em.setJpaDialect(new HibernateJpaDialect());
		return em;
	}

	/**
	 * Set false for both hibernate.show_sql and hibernate.format_sql
	 * when running benchmarks to prevent JMH log pollution
	 *
	 * @return hibernate props
	 */
	@Bean
	@Profile("!oracle")
	public Supplier<Properties> additionalProperties() {
		return () -> {
			Properties properties = new Properties();
			properties.setProperty(AvailableSettings.SHOW_SQL, "true");
			properties.setProperty(AvailableSettings.FORMAT_SQL, "false");
//		properties.setProperty(AvailableSettings.DIALECT, "false");
			return properties;
		};
	}

	@Bean
	@Profile("oracle")
	public Supplier<Properties> oracleProperties() {
		return () -> {
			Properties properties = new Properties();
			properties.setProperty(AvailableSettings.SHOW_SQL, "true");
			properties.setProperty(AvailableSettings.FORMAT_SQL, "false");
			properties.setProperty(AvailableSettings.DIALECT, Oracle10gDialect.class.getName());
			properties.setProperty(AvailableSettings.HBM2DDL_AUTO, "create");
			return properties;
		};
	}
}
