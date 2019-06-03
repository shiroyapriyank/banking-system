package com.bank.operation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		//Bcrypting the admin and user password
		String adminPassword = "Admin@123";
		String userPassword = "User@123";
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPasswordForAdmin = passwordEncoder.encode(adminPassword);
		String hashedPasswordForUser = passwordEncoder.encode(userPassword);
		//doing in memory authentication with hard code values.
		auth.inMemoryAuthentication().withUser("admin").password(hashedPasswordForAdmin).roles("ADMIN").and().withUser("user")
				.password(hashedPasswordForUser).roles("USER");
	}
	
	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

}
