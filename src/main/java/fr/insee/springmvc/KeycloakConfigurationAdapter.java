package fr.insee.springmvc;


import javax.annotation.PostConstruct;

import org.keycloak.adapters.KeycloakConfigResolver;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

@KeycloakConfiguration
@Profile("keycloak")
public class KeycloakConfigurationAdapter extends KeycloakWebSecurityConfigurerAdapter {

	@PostConstruct
	void postConstruct() throws Exception {
		System.setProperty("javax.net.ssl.trustStore", "src/main/resources/trustStore.jks");
	}
	
	@Bean
	public KeycloakConfigResolver KeycloakConfigResolver() {
	    return new KeycloakSpringBootConfigResolver();
	}
	
	@Bean
	@Override
	protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
		return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder manager) throws Exception {
		manager.authenticationProvider(keycloakAuthenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		super.configure(http);
		http.authorizeRequests()
			.antMatchers("/static/**").permitAll()
			.anyRequest().authenticated();
	}
}
