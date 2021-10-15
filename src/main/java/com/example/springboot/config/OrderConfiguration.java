package com.example.springboot.config;

import com.example.springboot.model.Order;
import com.example.springboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class OrderConfiguration {
    @Bean(name = "user", initMethod = "init", destroyMethod = "destroy")
    @Scope(value = "singleton")
    public User user() {
        User user = new User("John Doe", "password", 12, "abc@gmail.com");
        return user;
    }

    @Bean(name = "official_user")
    @Autowired
    @Qualifier("user")
    public User order(User user) {
        Order order = new Order();
        order.setUser(user);
        return order.getUser();
    }
}
