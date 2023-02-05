package com.imedia.project.utilities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app.api.url")
@Setter
@Getter
public class ApiConfig {

    String convert ;
    String latest;
    String symbols ;
    String key;

    @Override
    public String toString() {
        return "ApiConfig{" +
                "convert='" + convert + '\'' +
                ", latest='" + latest + '\'' +
                ", symbols='" + symbols + '\'' +
                ", key='" + key + '\'' +
                '}';
    }
}
