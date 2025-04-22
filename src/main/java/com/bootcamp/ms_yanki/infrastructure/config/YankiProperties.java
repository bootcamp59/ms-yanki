package com.bootcamp.ms_yanki.infrastructure.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "yanki")
@Getter
@Setter
public class YankiProperties {

    private String msDebitCardPath;
}
