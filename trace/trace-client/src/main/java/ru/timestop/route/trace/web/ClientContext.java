package ru.timestop.route.trace.web;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author t.i.m.e.s.t.o.p
 * @version 1.0.0
 * @since 02.08.2018.
 */
@Configuration
@PropertySource("config/trace-client.properties")
@ComponentScan({"ru.timestop.route.trace.web.client"})
public class ClientContext {
}
