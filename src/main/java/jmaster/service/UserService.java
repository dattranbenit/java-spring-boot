package jmaster.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import jmaster.controller.LoginController;
import jmaster.dao.PhoneDao;
import jmaster.entity.Phone;
import jmaster.model.PhoneDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jmaster.dao.UserDao;
import jmaster.entity.User;
import jmaster.model.UserDTO;

public interface UserService {
	void insert(UserDTO userDTO);

	void update(UserDTO userDTO);

	void delete(Long id);

	UserDTO get(Long id);

	UserDTO getByUserName(String userName);

	List<UserDTO> search(String name, int start, int length);

	List<UserDTO> getAll();

}

@Transactional
@Service
class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private PhoneDao phoneDao;

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public void insert(UserDTO userDTO) {
		User user = new User();
		user.setName(userDTO.getName());
		user.setAge(userDTO.getAge());
		user.setEnabled(userDTO.getEnabled());
		user.setRole(userDTO.getRole());
		user.setUsername(userDTO.getUsername());
		user.setPassword((userDTO.getPassword()));
		user.setGender(userDTO.getGender());
		user.setAddress(userDTO.getAddress());
		user.setEmail(userDTO.getEmail());
		userDao.insert(user);
	}

	@Override
	public void update(UserDTO userDTO) {
		User user = userDao.get(userDTO.getId());
		if (user != null) {
			user.setName(userDTO.getName());
			user.setAge(userDTO.getAge());
			user.setRole(userDTO.getRole());
			user.setUsername(userDTO.getUsername());
			user.setGender(userDTO.getGender());
			user.setAddress(userDTO.getAddress());
			user.setEnabled(userDTO.getEnabled());
			user.setEmail(userDTO.getEmail());
			userDao.update(user);
		}
	}

	@Override
	public void delete(Long id) {
		User user = userDao.get(id);
		if (user != null) {
			userDao.delete(user);
		}
	}

	@Override
	public UserDTO get(Long id) {
		User user = userDao.get(id);
		return convert(user);
	}

	@Override
	public UserDTO getByUserName(String userName) {
		User user = userDao.getByUserName(userName);
		return convert(user);
	}

	@Override
	public List<UserDTO> search(String name, int start, int length) {
		List<User> users = userDao.search(name, start, length);
		List<UserDTO> userDTOs = new ArrayList<UserDTO>();
		for (User user : users) {
			userDTOs.add(convert(user));
		}
		return userDTOs;
	}

	@Override
	public List<UserDTO> getAll() {
		List<User> users = userDao.getAll();
		List<Phone> phones = phoneDao.getAll();
		List<UserDTO> userDTOs = new ArrayList<UserDTO>();
		for (User user : users) {
			List<Phone> temp = new ArrayList<Phone>();
			for (Phone phone: phones) {
				if (phone.getUser().getUsername().equals(user.getUsername())) {
					temp.add(phone);
					logger.warn(user.getUsername());
				}
			}
			user.setPhoneList(temp);
			userDTOs.add(convert(user));
		}

		return userDTOs;
	}

	private UserDTO convert(User user) {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setName(user.getName());
		userDTO.setAge(user.getAge());
		userDTO.setRole(user.getRole());
		userDTO.setUsername(user.getUsername());
		userDTO.setGender(user.getGender());
		userDTO.setAddress(user.getAddress());
		userDTO.setEnabled(user.getEnabled());
		userDTO.setEmail(user.getEmail());
		userDTO.setPhoneList(user.getPhoneList());
		return userDTO;
	}

}
