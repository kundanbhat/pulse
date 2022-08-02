package com.ie.Pulse.security;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;


@SuppressWarnings("deprecation")
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	 @Autowired
	    ClientRegistrationRepository clientRegistrationRepository;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
        .antMatchers("/*").permitAll()
        .anyRequest().authenticated()
        .and()
        .logout()
        .logoutSuccessHandler(oidcLogoutSuccessHandler())
        //.logoutSuccessUrl("/")
        .invalidateHttpSession(true)
        .clearAuthentication(true)
        .deleteCookies("JSESSIONID")
        .and()
        .oauth2Login();
        
        
        
        
	}
	
	private OidcClientInitiatedLogoutSuccessHandler oidcLogoutSuccessHandler() { 
	    OidcClientInitiatedLogoutSuccessHandler successHandler = new OidcClientInitiatedLogoutSuccessHandler(clientRegistrationRepository);
	    successHandler.setPostLogoutRedirectUri("http://localhost:8080/");
	    return successHandler;
	}

}
