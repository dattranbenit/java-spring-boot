package jmaster.model;

import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
	private Long id;
	private String username;
	private String password;
	private String role;
}
