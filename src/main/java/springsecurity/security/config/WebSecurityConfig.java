package springsecurity.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebSecurity

public class WebSecurityConfig  {  
	
	
      
/*
 * configure the in authentication and user name and password
 * store and manage user credentials
 */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) 
      throws Exception {
    	auth.inMemoryAuthentication()
        .withUser("admin")
        .password(passwordEncoder().encode("admin123"))
        .roles("ADMIN")
        .and()
        .withUser("user")
        .password(passwordEncoder().encode("user123"))
        .roles("USER");
    }

    /*
     * Specify the encryption algorithm
     */
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	
//	/*
//	 * Configure access using roles
//	 */
//	protected void configure(HttpSecurity http) throws Exception {
//		http.httpBasic()
//          .and()
//          .authorizeRequests()
//          .antMatchers("/admin").access("hasRole('ADMIN')")
//          .antMatchers("/user").access("hasRole('USER')")
//          .and()
//          .formLogin();
//		   
//	   
////	}

	 @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	
		http
		.authorizeRequests()
		   .antMatchers("/", "/login").permitAll()
		   .antMatchers("/home").access("hasRole('USER')")
		   .antMatchers("/home").access("hasRole('ADMIN')")
		   .anyRequest().authenticated()
			.and()
				.formLogin().loginPage("/login")
				.defaultSuccessUrl("/home")
				.failureUrl("/login?error")
				.usernameParameter("username").passwordParameter("password")				
			.and()
				.logout().logoutSuccessUrl("/login?logout");
		return http.build();
		
	}
	
	
}