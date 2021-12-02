package jmaster.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@Table(name = "user")
@NoArgsConstructor
@RequiredArgsConstructor
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonNull
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "enabled")
	private Boolean enabled;

	@Column(name = "age")
	private String age;

	@Column(name = "username", unique = true)
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "gender")
	private String gender;

	@Column(name = "address")
	private String address;

	@Column(name = "role")
	private String role;

	@Column(name = "email")
	private String email;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Phone> phoneList;

}
