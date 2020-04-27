package com.gourab.learning.springsecurityjdbc.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
			.dataSource(dataSource)
			.usersByUsernameQuery("select u.username,u.password,u.enabled from users u where u.username=?")
			.authoritiesByUsernameQuery("select a.username,a.authority from authorities a where a.username=?");
			/*.withDefaultSchema()
			.withUser(
					User.withUsername("gourab")
						.password("gourab1!")
						.roles("ADMIN")
					)
			.withUser(
					User.withUsername("ruma")
					.password("ruma2@")
					.roles("USER")
				)
			.withUser(
					User.withUsername("juhita")
					.password("juhita3#")
					.roles("USER")
				);*/
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/admin").hasRole("ADMIN")
			.antMatchers("/user").hasAnyRole("ADMIN","USER")
			.antMatchers("/").permitAll()
			.and()
			.formLogin();
		
	}
	
	@Bean
	public PasswordEncoder getNoOpPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}
