package com.rjgc.configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zhaoyunjie
 * @date 2021-04-10 01:18
 */
@Component
@Data
@ConfigurationProperties(prefix = "security")
public class CsrfProperties {

    /**
     * 是否开启csrf防护
     */
    private Boolean csrfDisabled;
}
