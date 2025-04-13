package com.org.shopping.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    DataSourceConfiguration dataSourceConfiguration;

    public DataSourceConfig(DataSourceConfiguration dataSourceConfiguration) {
        this.dataSourceConfiguration = dataSourceConfiguration;
    }

    @Bean
    public DataSource getDataSource(){
        dataSourceConfiguration.setup();
        return DataSourceBuilder.create()
                .driverClassName("org.mariadb.jdbc.Driver")
                .url("jdbc:mariadb://localhost:3306/ecommerce_shopping")
                .username("root")
                .password("monkey3110")
                .build();
    }
}
