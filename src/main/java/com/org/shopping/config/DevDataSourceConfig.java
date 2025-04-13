package com.org.shopping.config;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class DevDataSourceConfig implements DataSourceConfiguration {
    @Override
    public void setup() {
        System.out.println("Setting up Dev DataSource");
    }
}
