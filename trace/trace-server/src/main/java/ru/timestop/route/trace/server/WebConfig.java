package ru.timestop.route.trace.server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import ru.timestop.route.timing.web.ClientContext;
import ru.timestop.route.trace.service.ApplicationConfig;

/**
 * @author t.i.m.e.s.t.o.p
 * @version 1.0.0
 * @since 26.06.2018
 */
@Configuration
@EnableWebMvc
@EnableWebMvcSecurity
@ComponentScan({"ru.timestop.route.trace"})
@Import({ApplicationConfig.class, ClientContext.class})
public class WebConfig extends WebMvcConfigurerAdapter {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/config/**").addResourceLocations("/");
    }
}
