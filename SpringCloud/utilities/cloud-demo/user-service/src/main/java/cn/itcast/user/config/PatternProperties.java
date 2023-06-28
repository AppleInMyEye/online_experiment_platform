package cn.itcast.user.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author YuJianhua
 * @create 2023-06-09 17:12
 */
@Data
@Component
@ConfigurationProperties(prefix = "pattern")
public class PatternProperties {

        private String dateformat;
        private String envShareValue;
}
