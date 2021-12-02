package jmaster.model;

import jmaster.entity.Phone;
import lombok.Data;

import java.util.List;

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
	private List<Phone> phoneList;
}
