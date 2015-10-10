package com.spring.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;



@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	  @Autowired
	  DataSource dataSource;
	  

	  @Autowired
	  CustomUserDetailsService customService;
	
	  @Override
	  public void configure(WebSecurity web) throws Exception {
	    web
	      .ignoring()
	         .antMatchers("/resources/**"); // #3
	  }
	  

	  
		@Autowired
		public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
			
			auth.userDetailsService(customService) ;//.passwordEncoder(passwordEncoder());
			
			/*auth.inMemoryAuthentication()
	                   .withUser("mkyong").password("123456").roles("USER");
			  auth.inMemoryAuthentication().withUser("admin").password("123456").roles("ADMIN");
			  auth.inMemoryAuthentication().withUser("dba").password("123456").roles("DBA");*/
			
/*			  auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder())
				.usersByUsernameQuery(
					"select username,password, enabled from user where username=?")
				.authoritiesByUsernameQuery(
					"select username, role from user_roles where username=?");*/
		}

		
		@Bean
		public PasswordEncoder passwordEncoder(){
			PasswordEncoder encoder = new BCryptPasswordEncoder();
			return encoder;
		}
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {

		  http.authorizeRequests()
			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
			.antMatchers("/dba/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_DBA')")
			.and().formLogin()
			.loginPage("/login").failureUrl("/login?error")
		    .usernameParameter("username").passwordParameter("password")	
			.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout")//.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.and()
			  .exceptionHandling().accessDeniedPage("/403")
			 .and()
			.csrf(); 
			
		}
	
/*    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("bill").password("abc123").roles("USER");
        auth.inMemoryAuthentication().withUser("admin").password("root123").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("dba").password("root123").roles("ADMIN","DBA");//dba have two roles.
    }
     
    @Override
    protected void configure(HttpSecurity http) throws Exception {
  
      http.authorizeRequests()
        .antMatchers("/", "/home").permitAll() 
        .antMatchers("/admin/**").access("hasRole('ADMIN')")
        .antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
        .and().formLogin()
        .and().exceptionHandling().accessDeniedPage("/Access_Denied");
  
    }*/
	  
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		web
//            .ignoring()
//			.antMatchers("/*//*//.html///", //
//                         "/css/**", //
//                         "/js/**", //
//                         "/i18n/**",// 
//                         "/libs/**",//
//                         "/img/**", //
//                         "/webjars/**",//
//                         "/ico/**");
//	}

/*	@Override
	protected void configure(HttpSecurity http) throws Exception {
            http   
                .authorizeRequests()   
    			.antMatchers("/api/ping")
    			.permitAll()
    		.and()
                .authorizeRequests()   
    			.antMatchers("/api/**")
    			.authenticated()
    		.and()
    			.authorizeRequests()   
    			.anyRequest()
    			.permitAll()
                .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .httpBasic()
                .and()
                    .csrf()
                    .disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.inMemoryAuthentication()
                    .passwordEncoder(passwordEncoder())
                    .withUser("admin").password("test123").authorities("ROLE_ADMIN")
                    .and()
                        .withUser("test").password("test123").authorities("ROLE_USER");
	}


	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	@Override
	public UserDetailsService userDetailsServiceBean() throws Exception {
		return super.userDetailsServiceBean();
	}

	@Bean
	public PlaintextPasswordEncoder passwordEncoder() {
		PlaintextPasswordEncoder passwordEncoder = new PlaintextPasswordEncoder();
		return passwordEncoder;
	}*/

}
