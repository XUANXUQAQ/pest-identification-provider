package com.rjgc.configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "database")
@Data
@Component
public class DatabaseIdConfig {

    private boolean idStartFromZero;
}
