package com.rjgc.configs.cas;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "min-concurrent-latency")
@Component
@Data
public class MinConcurrentLatency {

    /**
     * 单次并发最大延迟
     */
    private int latency;
}
