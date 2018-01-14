package com.example.demo.config;

import com.example.demo.DemoApplication;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackageClasses = DemoApplication.class)
public class JpaConfig implements TransactionManagementConfigurer {

    @Value("${dataSource.driverClassName}")
    private String driver;
    @Value("${dataSource.url}")
    private String url;
    @Value("{dataSource.username}")
    private String username;
    @Value("{dataSource.password}")
    private String password;
    @Value("{hibernate.dialect}")
    private String dialect;
    @Value("${hibernate.hbm2ddl.auto}")
    private String hbm2ddlAuto;

    @Bean
    public DataSource configureDataSource(){
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(driver);
        config.setJdbcUrl(url);
        config.setUsername("root");
        config.setPassword("root");
        return new HikariDataSource(config);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean(){
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        localContainerEntityManagerFactoryBean.setDataSource(configureDataSource());
        localContainerEntityManagerFactoryBean.setPackagesToScan("homework.src.main.java.com.example.demo");
        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Properties JPAproperties = new Properties();
        JPAproperties.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
        JPAproperties.put(Environment.HBM2DDL_AUTO, hbm2ddlAuto);
        localContainerEntityManagerFactoryBean.setJpaProperties(JPAproperties);

        return localContainerEntityManagerFactoryBean;
    }

    @Bean
    public PlatformTransactionManager platformTransactionManager(){
        return new JpaTransactionManager();
    }

    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return null;
    }
}
