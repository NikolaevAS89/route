package ru.timestop.route.trace.service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @author t.i.m.e.s.t.o.p
 * @version 1.0.0
 * @since 01.07.2018
 */
@Configuration
@ImportResource({"classpath:db/config/database-trace.xml"})
public class DatabaseConfig {

}
