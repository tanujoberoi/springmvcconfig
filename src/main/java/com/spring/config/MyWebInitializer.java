package com.spring.config;

import javax.servlet.Filter;

import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MyWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { 
				SpringRootConfig.class,
				DataSourceConfig.class,
				JpaConfig.class,
				SecurityConfig.class,
		//		DataJpaConfig.class,
				Jackson2ObjectMapperConfig.class,
				MessageSourceConfig.class
				};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { SpringWebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
	
    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceEncoding(true);
        

        return new Filter[] { encodingFilter ,new OpenEntityManagerInViewFilter() ,new DelegatingFilterProxy("springSecurityFilterChain") };
    }

}
