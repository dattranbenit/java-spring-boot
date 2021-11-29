package jmaster.model;

import lombok.Data;

@Data
public class UserDTO {
	private Long id;
	private String name;
	private String age;
	private String role;
	private Boolean enabled;
	private String username;
	private String password;
	private String address;
	private String gender;
	private String phone;
	private String email;

}
