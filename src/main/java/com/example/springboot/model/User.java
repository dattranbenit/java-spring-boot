package com.example.springboot.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class User {
    @NotBlank(message = "username cannot be empty")
    private String name;

    @NotBlank(message = "password cannot be empty")
    @Length(min=6, max=10, message="length of password should between 6 to 10 characters")
    private String password;

    @Min(value=0)
    @Max(value=100)
    private Integer grade;

    @Email
    private String email;

    public User(String name, String password, Integer grade, String email) {
        super();
        this.name = name;
        this.password = password;
        this.grade = grade;
        this.email = email;
    }

    public void init() {
        System.out.println("init");
    }

    public void destroy() {
        System.out.println("destroy");
    }
}

