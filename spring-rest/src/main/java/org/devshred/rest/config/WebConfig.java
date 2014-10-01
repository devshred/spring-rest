package org.devshred.rest.config;

import org.devshred.rest.RestPackage;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = RestPackage.class)
public class WebConfig {
}
