//package com.mavi.restrailwaysecurity.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//
//import javax.sql.DataSource;
//import java.util.Properties;
//
//@Configuration
//@ConfigurationProperties(prefix = "spring.datasource")
//public class JpaConfig {
//
//    private final String url;
//    private final String username;
//    private final String password;
//    private final String driverClassName;
//
//    public JpaConfig(@Value("${spring.datasource.url}") String url,
//                     @Value("${spring.datasource.username}") String username,
//                     @Value("${spring.datasource.password}") String password,
//                     @Value("${spring.datasource.driver-class-name}") String driverClassName) {
//        this.url = url;
//        this.username = username;
//        this.password = password;
//        this.driverClassName = driverClassName;
//    }
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
//        emf.setDataSource(dataSource());
//        emf.setPackagesToScan("com.mavi.restrailwaysecurity.entity");
//        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//        emf.setJpaProperties(additionalProperties());
//        return emf;
//    }
//
//    @Bean
//    public DataSource dataSource() {
//        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
//        dataSourceBuilder.driverClassName(driverClassName);
//        dataSourceBuilder.url(url);
//        dataSourceBuilder.username(username);
//        dataSourceBuilder.password(password);
//        return dataSourceBuilder.build();
//    }
//
//    private Properties additionalProperties() {
//        Properties properties = new Properties();
//        properties.setProperty("hibernate.hbm2ddl.auto", "update");
//        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
//        return properties;
//    }
//}
