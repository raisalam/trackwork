package com.example.trackwork.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("rais")
				.password("{noop}welcome@786").roles("ADMIN");
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		/**
		http.antMatcher("/**").authorizeRequests().anyRequest().hasRole("USER")
				.and().formLogin().loginPage("/login.html")
				.failureUrl("/login.html?error=1").loginProcessingUrl("/login")
				.permitAll().and().logout()
				.logoutSuccessUrl("/All.html");

		 http.authorizeRequests()
				.antMatchers("/api/v1/signup/**").permitAll()
				.anyRequest().authenticated().and()
				 .formLogin();
		 **/
		http.authorizeRequests()

				.antMatchers("/All.html").hasRole("ADMIN")
				.antMatchers("/Bhuvan.html").hasRole("ADMIN")
				.antMatchers(HttpMethod.POST, "/**").hasRole("ADMIN")
				.antMatchers(HttpMethod.PUT, "/**").hasRole("ADMIN")
				.antMatchers(HttpMethod.PATCH, "/**").hasRole("ADMIN")
				.antMatchers(HttpMethod.DELETE, "/**").hasRole("ADMIN")
				.and()
				.formLogin();

		http.csrf().disable();
	}


}