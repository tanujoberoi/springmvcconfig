package com.spring.config;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.fasterxml.jackson.databind.ObjectMapper;



@EnableWebMvc //<mvc:annotation-driven />
@Configuration
@ComponentScan({ "com.spring.web" })
public class SpringWebConfig extends WebMvcConfigurerAdapter {  //extends SpringDataWebConfiguration
	

	@Inject
	private ObjectMapper objectMapper;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**")
                        .addResourceLocations("/resources/");
	}
	
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
            configurer.favorPathExtension(true)
                    .useJaf(false)
                    .ignoreAcceptHeader(true)
                    .mediaType("html", MediaType.TEXT_HTML)
                    .mediaType("json", MediaType.APPLICATION_JSON)
                    .defaultContentType(MediaType.TEXT_HTML);
    }
	
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
    
	@Bean
	public InternalResourceViewResolver viewResolver() {
		
		
		InternalResourceViewResolver viewResolver 
                         = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
/*    @Bean
    public ViewResolver contentNegotiatingViewResolver(
                    ContentNegotiationManager manager) {
             
            List< ViewResolver > resolvers = new ArrayList< ViewResolver >();
             
            InternalResourceViewResolver r1 = new InternalResourceViewResolver();
            r1.setPrefix("/WEB-INF/pages/");
            r1.setSuffix(".jsp");
            r1.setViewClass(JstlView.class);
            resolvers.add(r1);
             
            JsonViewResolver r2 = new JsonViewResolver();
            resolvers.add(r2);
             
            ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
            resolver.setViewResolvers(resolvers);
            resolver.setContentNegotiationManager(manager);
     return resolver;
     
    }
    
    *//**
    * View resolver for returning JSON in a view-based system. Always returns a
    * {@link MappingJacksonJsonView}.
    *//*
    public class JsonViewResolver implements ViewResolver {
    	
            public View resolveViewName(String viewName, Locale locale)
                            throws Exception {
                            MappingJackson2JsonView view = new MappingJackson2JsonView();
                            view.setPrettyPrint(true);
                            return view;
            }
    }*/
    
    
/*    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        List<HttpMessageConverter<?>> messageConverters = messageConverters();
        converters.addAll(messageConverters);
    }

    @Bean
    public ExceptionHandlerExceptionResolver exceptionHandlerExceptionResolver() {
        ExceptionHandlerExceptionResolver exceptionHandlerExceptionResolver = new ExceptionHandlerExceptionResolver();
        exceptionHandlerExceptionResolver.setMessageConverters(messageConverters());

        return exceptionHandlerExceptionResolver;
    }

    private List<HttpMessageConverter<?>> messageConverters() {
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();

        MappingJackson2HttpMessageConverter jackson2Converter = new MappingJackson2HttpMessageConverter();
        jackson2Converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON));
        jackson2Converter.setObjectMapper(objectMapper);

        messageConverters.add(jackson2Converter);
        return messageConverters;
    }
    
    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver getMultipartResolver() {
        return new CommonsMultipartResolver();
    }
 
    @Bean(name = "messageSource")
    public ReloadableResourceBundleMessageSource getMessageSource() {
        ReloadableResourceBundleMessageSource resource = new ReloadableResourceBundleMessageSource();
        resource.setBasename("classpath:messages");
        resource.setDefaultEncoding("UTF-8");
        return resource;
    }*/
    
}
