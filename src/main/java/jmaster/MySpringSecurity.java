package jmaster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class MySpringSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//phan quyen
		http.csrf().disable().authorizeRequests().antMatchers("/admin/**").hasAnyRole("ADMIN")
		.antMatchers("/member/**").hasAnyRole("MEMBER").anyRequest().permitAll()
		//cau hinh giao dien
		.and().formLogin().loginPage("/dang-nhap").loginProcessingUrl("/login")
		.and().exceptionHandling().accessDeniedPage("/access-deny");
	}

	
	public static void main(String[] args) {
		String rawPass = "123456";
		String encode = new BCryptPasswordEncoder().encode(rawPass);
		System.out.println(encode);
		//
		boolean check = new BCryptPasswordEncoder().matches(rawPass, encode);
	}
}
