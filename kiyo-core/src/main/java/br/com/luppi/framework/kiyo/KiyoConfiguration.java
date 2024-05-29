package br.com.luppi.framework.kiyo;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@ComponentScan("br.com.luppi.framework.kiyo")
@PropertySources({
    @PropertySource(value = "${applicationPropertiesResource}", ignoreResourceNotFound = true)
})
public class KiyoConfiguration {
}
