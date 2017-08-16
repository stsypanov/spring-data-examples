package com.luxoft.logeek;

import com.luxoft.logeek.config.CustomOracleDialect;
import com.luxoft.logeek.config.CustomH2Dialect;
import com.luxoft.logeek.config.CustomPostgresDialect;
import com.luxoft.logeek.config.PersistenceConfig;
import com.luxoft.logeek.repository.BaseJpaRepositoryImpl;
import com.p6spy.engine.spy.P6DataSource;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
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
@EnableJpaRepositories(value = "com.luxoft.logeek.repository", repositoryBaseClass = BaseJpaRepositoryImpl.class)
@ComponentScan(basePackages = {"com.luxoft.logeek"})
@Import(PersistenceConfig.class)
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
	@Profile("h2")
	public Supplier<Properties> additionalProperties() {
		return () -> {
			Properties properties = new Properties();
			properties.setProperty(AvailableSettings.SHOW_SQL, "true");
			properties.setProperty(AvailableSettings.FORMAT_SQL, "false");
			properties.setProperty(AvailableSettings.DIALECT, CustomH2Dialect.class.getName());
//		properties.setProperty(AvailableSettings.DIALECT, "false");
			return properties;
		};
	}

	/**
	 * Set false for both hibernate.show_sql and hibernate.format_sql
	 * when running benchmarks to prevent JMH log pollution
	 *
	 * @return hibernate props
	 */
	@Bean
	@Profile("postgres")
	public Supplier<Properties> postgresProperties() {
		return () -> {
			Properties properties = new Properties();
			properties.setProperty(AvailableSettings.SHOW_SQL, "true");
			properties.setProperty(AvailableSettings.FORMAT_SQL, "false");
			properties.setProperty(AvailableSettings.DIALECT, CustomPostgresDialect.class.getName());
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
			properties.setProperty(AvailableSettings.DIALECT, CustomOracleDialect.class.getName());
			properties.setProperty(AvailableSettings.HBM2DDL_AUTO, "create");
			return properties;
		};
	}
}
