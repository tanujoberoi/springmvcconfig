package com.spring.config;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * Different configurations for different stages.
 *
 * In development stage using an embedded database to get better performance.
 *
 * In production, container managed DataSource is highly recommended.
 *
 * @author Hantsy Bai<hantsy@gmail.com>
 *
 */
@Configuration
@PropertySource("classpath:/application.properties")
public class DataSourceConfig {

    private static final String ENV_JDBC_PASSWORD = "jdbc.password";
    private static final String ENV_JDBC_USERNAME = "jdbc.username";
    private static final String ENV_JDBC_URL = "jdbc.url";
    
    private static final String PROPERTY_NAME_DATABASE_DRIVER = "db.driver";
    private static final String PROPERTY_NAME_DATABASE_PASSWORD = "db.password";
    private static final String PROPERTY_NAME_DATABASE_URL = "db.url";
    private static final String PROPERTY_NAME_DATABASE_USERNAME = "db.username";
         

    @Inject
    private Environment env;
    
    
    @Bean(name = "dataSource")
    public DataSource dataSource() {
    	DriverManagerDataSource dataSource = new DriverManagerDataSource();
        
        dataSource.setDriverClassName(env.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));
        dataSource.setUrl(env.getRequiredProperty(PROPERTY_NAME_DATABASE_URL));
        dataSource.setUsername(env.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME));
        dataSource.setPassword(env.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD));
         
        return dataSource;
    }

/*    @Bean
    @Profile("dev")
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .build();
    }

    @Bean
    @Profile("staging")
    public DataSource testDataSource() {
        BasicDataSource bds = new BasicDataSource();
        bds.setDriverClassName("com.mysql.jdbc.Driver");
        bds.setUrl(env.getProperty(ENV_JDBC_URL));
        bds.setUsername(env.getProperty(ENV_JDBC_USERNAME));
        bds.setPassword(env.getProperty(ENV_JDBC_PASSWORD));
        return bds;
    }

    @Bean
    @Profile("prod")
    public DataSource prodDataSource() {
        JndiObjectFactoryBean ds = new JndiObjectFactoryBean();
        ds.setLookupOnStartup(true);
        ds.setJndiName("jdbc/postDS");
        ds.setCache(true);

        return (DataSource) ds.getObject();
    }*/

}
