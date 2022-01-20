package com.owly.delivery;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class ApplicationConfig {

    @Bean(name = "sessionFactory")
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        try {
            sessionFactory.setDataSource(dataSource());
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        sessionFactory.setPackagesToScan("com.owly.delivery.entity");
        sessionFactory.setHibernateProperties(hibernateProperties());
        System.out.println("Session initialization successful");
        return sessionFactory;
    }

    @Bean(name = "dataSource")
    public DataSource dataSource() throws FileNotFoundException{
        Properties dbProperties = new Properties();
        String propFileName = "config.properties";
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

        if (inputStream == null){
            throw new FileNotFoundException("DB properties cannot be read, check your config.properties file.");
        }

        try {
            dbProperties.load(inputStream);
        }
        catch (IOException e){
            throw new FileNotFoundException("Cannot load properties");
        }

        String CLOUD_SQL_CONNECTION_NAME = (String)dbProperties.get("connection_name");
        String DB_NAME = "owly";
        String DB_USER = (String)dbProperties.get("user_name");
        String DB_PASS = (String)dbProperties.get("password");
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");

        String configURL =
            String.format("jdbc:mysql://%s/%s?user=%s&password=%s&autoReconnect=true&serverTimezone=UTC&createDatabaseIfNotExist=true",
            CLOUD_SQL_CONNECTION_NAME, DB_NAME, DB_USER, DB_PASS);
        dataSource.setUrl(configURL);
        return dataSource;
    }

    private final Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
        hibernateProperties.setProperty("hibernate.show_sql", "true");
        return hibernateProperties;
    }
}

