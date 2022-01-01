package jmaster.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import jmaster.security.UserDetailsServiceImpl;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/user/book").hasAnyRole("PREMIUM_MEMBER")
                //user nao co role premium thi duoc access, khi nao vao url moi check
                .anyRequest().authenticated().and()
                .formLogin().loginPage("/login")
                .usernameParameter("username").passwordParameter("password")//dat ten param username vs password theo form thymeleaf
                .defaultSuccessUrl("/home", true)
                .failureUrl("/login?error=not-found").permitAll()// success and fail login
                .and().logout().logoutSuccessUrl("/login").permitAll()
                .and().exceptionHandling().accessDeniedPage("/home?info=not-premium");//user ko co role premium chuyen den denied page
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("admin").password("{noop}123").roles("ADMIN");
        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(NoOpPasswordEncoder.getInstance());//data for authentication
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers("/css/**")
                .antMatchers("/images/**");
    }
}

