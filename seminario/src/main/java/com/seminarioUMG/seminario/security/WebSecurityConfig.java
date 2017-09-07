package com.seminarioUMG.seminario.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter  {
	
	@Autowired
	private ClientDetailsService clientDetailsService;
	
	@Autowired
	@Qualifier("userDetailsService")
	private UserService userDetailsService;
	
	
	
	@Autowired
    protected void registerAuthentication(
            final AuthenticationManagerBuilder auth) throws Exception {
		System.out.println(auth);
        auth.userDetailsService(userDetailsService);
        auth.authenticationProvider(authenticationProvider());
        
    }


	@Override
      public void configure(WebSecurity web) throws Exception {			
        web.ignoring()
          .antMatchers(HttpMethod.OPTIONS);
        
      }

@Override
@Bean
public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
}


@Bean
public TokenStore tokenStore() {
	return new InMemoryTokenStore();
}

@Bean
@Autowired
public TokenStoreUserApprovalHandler userApprovalHandler(TokenStore tokenStore){
	TokenStoreUserApprovalHandler handler = new TokenStoreUserApprovalHandler();
	handler.setTokenStore(tokenStore);
	handler.setRequestFactory(new DefaultOAuth2RequestFactory(clientDetailsService));
	handler.setClientDetailsService(clientDetailsService);
	return handler;
}

@Bean
@Autowired
public ApprovalStore approvalStore(TokenStore tokenStore) throws Exception {
	TokenApprovalStore store = new TokenApprovalStore();
	store.setTokenStore(tokenStore);
	return store;
}

@Bean
public DaoAuthenticationProvider authenticationProvider() {
	DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
	authenticationProvider.setUserDetailsService(userDetailsService);
	authenticationProvider.setPasswordEncoder(passwordEncoder());
	return authenticationProvider;
}

@Bean
public PasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder();
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
