package ru.timestop.route.trace.server.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author t.i.m.e.s.t.o.p
 * @version 1.0.0
 * @since 03.07.2018
 */
@Configuration
@EnableWebSecurity
@ImportResource({"classpath:config/security-database.xml", "classpath:config/security-config.xml"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .regexMatchers("/v[0-9]+/public/.*").permitAll()
                .anyRequest()
                .authenticated()
                .regexMatchers("/v[0-9]+/private/.*").access("hasRole('ROLE_ADMIN')")
                .and()
                .httpBasic()
                .and()
                .rememberMe()
                .tokenValiditySeconds(5);
    }
}
