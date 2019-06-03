package com.bank.operation.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServer extends AuthorizationServerConfigurerAdapter{

	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.checkTokenAccess("isAuthenticated()");
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory().withClient("my-trusted-client") // this is Authorization username
		.authorizedGrantTypes("client_credentials","password") // grant type is password
		.authorities("ROLE_CLIENT","ROLE_TRUSTED_CLIENT")
		.scopes("read","write","trust") //this is the scope given
		.resourceIds("oauth2-resource")
		.accessTokenValiditySeconds(2*60*60) // validity of access token
		.secret("$2a$10$LD1jTGQWzW8hnoEcQPf7p.jziTagy7gJ0n9FOeyiv2y9.ARFy8bQ.") // password for authorization is "secret" but it needs to be bcrypted
		.refreshTokenValiditySeconds(6*60*60); // if token expire it will refresh it and generate a new access token
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager);
	}

	
}
