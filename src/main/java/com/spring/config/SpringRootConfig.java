package com.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({ "com.spring" , "com.spring.service" , "com.spring.config" })
public class SpringRootConfig {

}
