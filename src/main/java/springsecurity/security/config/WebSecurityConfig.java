package springsecurity.security.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity

public class WebSecurityConfig {

	@Autowired
	private DataSource dataSource;

	/*
	 * configure the in authentication and user name and password store and manage
	 * user credentials
	 */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		/*
		 * IN memory authentication
		 */
//    	auth.inMemoryAuthentication()
//        .withUser("admin")
//        .password(passwordEncoder().encode("admin123"))
//        .roles("ADMIN")
//        .and()
//        .withUser("user")
//        .password(passwordEncoder().encode("user123"))
//        .roles("USER");

		/*
		 * Database authentication
		 */

		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select email as principal," + " password as credentails,"
						+ " true from tracker.app_user where email=?")
				.authoritiesByUsernameQuery(
						"select appusr.email as principal," + "usrrole.role_name from tracker.user_role usrrole,"
								+ "tracker.app_user appusr where usrrole.role_id " + "= appusr.role_id and appusr.email =?")
				.passwordEncoder(passwordEncoder());

	}

	/*
	 * Specify the encryption algorithm
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/*
	 * 
	 * 
	 * configure authorization of home page using user roles
	 */
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/", "/login").permitAll() // permit all the requests for the two links
				.antMatchers("/home/*").hasRole("ADMIN").anyRequest().authenticated().and().formLogin()
				.loginPage("/login").defaultSuccessUrl("/home").failureUrl("/login?error").usernameParameter("username")
				.passwordParameter("password").and().logout().logoutSuccessUrl("/login?logout");
		return http.build();

	}

}