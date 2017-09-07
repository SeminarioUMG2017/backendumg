package com.seminarioUMG.seminario.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	private static final String RESOURCE_ID = "my_rest_api";
	
	 
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.resourceId(RESOURCE_ID).stateless(false);
	}
	   @Override
	   public void configure(HttpSecurity http) throws Exception {
			http
			.csrf().disable()
			.formLogin().loginPage("/login").permitAll()
			.and().requestMatchers().antMatchers("/login","/verify/**","/resources/**","/home/**")
			.and()
			.authorizeRequests()		
				.antMatchers("/resources/**").permitAll()
				.antMatchers("/verify/**").permitAll()
				.antMatchers("/alumno/**").permitAll();
			
		}
	
	
}
