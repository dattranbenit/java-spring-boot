package com.springboot.tutorial.model;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class Employee {

	private int id;

	@NotBlank(message = "{validation.name.NotEmpty}")
	private String name;

	@Min(value = 18, message = "{validation.age.Maximum}")
	private int age;

	private String gender;
}
