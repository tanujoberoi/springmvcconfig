package com.spring.config;

import java.util.Properties;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
//@EnableTransactionManagement(mode = AdviceMode.ASPECTJ)
@EnableTransactionManagement
@ComponentScan({"com.spring", "com.spring.service"})
@PropertySource("classpath:/application.properties")
@EnableJpaRepositories(basePackages = {"com.spring.repos"})
public class JpaConfig {

    private static final Logger log = LoggerFactory.getLogger(JpaConfig.class);

    private static final String ENV_HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String ENV_HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
    private static final String ENV_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static final String ENV_HIBERNATE_FORMAT_SQL = "hibernate.format_sql";

    private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "entitymanager.packages.to.scan";

    
    @Inject
    private Environment env;

    @Inject
    private DataSource dataSource;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setPackagesToScan("com.spring.model");
        emf.setJpaProperties(jpaProperties());
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(true);
        vendorAdapter.setGenerateDdl(Boolean.TRUE);
        emf.setJpaVendorAdapter(vendorAdapter);
        return emf;
    }
    
/*    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
            LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
            entityManagerFactoryBean.setDataSource(dataSource);
            entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistence.class);
            entityManagerFactoryBean.setPackagesToScan(env.getRequiredProperty(PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN));
                             
            entityManagerFactoryBean.setJpaProperties(jpaProperties());
             
            return entityManagerFactoryBean;
    }*/
    
    
    private Properties jpaProperties() {
        Properties extraProperties = new Properties();
        extraProperties.put(ENV_HIBERNATE_FORMAT_SQL, env.getProperty(ENV_HIBERNATE_FORMAT_SQL));
        extraProperties.put(ENV_HIBERNATE_SHOW_SQL, env.getProperty(ENV_HIBERNATE_SHOW_SQL));
        extraProperties.put(ENV_HIBERNATE_HBM2DDL_AUTO, env.getProperty(ENV_HIBERNATE_HBM2DDL_AUTO));
        if (log.isDebugEnabled()) {
            log.debug(" hibernate.dialect @" + env.getProperty(ENV_HIBERNATE_DIALECT));
        }
        if (env.getProperty(ENV_HIBERNATE_DIALECT) != null) {
            extraProperties.put(ENV_HIBERNATE_DIALECT, env.getProperty(ENV_HIBERNATE_DIALECT));
        }
        return extraProperties;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager(entityManagerFactory().getObject());
    }

/*    @Bean
    public JpaTransactionManager transactionManager() {
            JpaTransactionManager transactionManager = new JpaTransactionManager();
            transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
            return transactionManager;
    }
    */
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
    
 
/*    @Bean
    public DozerBeanMapper getMapper() {
        return new DozerBeanMapper();
    }
    
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }*/
}
