package ru.timestop.route.timing.service;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.timestop.route.timing.service.config.DatabaseConfig;

/**
 * @author t.i.m.e.s.t.o.p
 * @version 1.0.0
 * @since 16.06.2018
 */
@Configuration
@Import({DatabaseConfig.class})
@ComponentScan({"ru.timestop.route.timing.service"})
public class ApplicationConfig {
}
