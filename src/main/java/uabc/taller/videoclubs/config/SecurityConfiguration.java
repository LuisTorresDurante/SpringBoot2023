package uabc.taller.videoclubs.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import uabc.taller.videoclubs.servicios.UserService;

@Configuration
public class SecurityConfiguration {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserService userService;
	
	@Bean
	public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/","/assets/**").permitAll().anyRequest().authenticated()
		.and().formLogin().loginPage("/login").defaultSuccessUrl("/",true).permitAll().and().logout();
		
		return http.build();
		
	}
	
	@Autowired
	public void configureGlobal (AuthenticationManagerBuilder build) throws Exception {
		build.userDetailsService(userService).passwordEncoder(passwordEncoder);
	}
	
}

	

