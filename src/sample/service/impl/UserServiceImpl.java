package sample.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import sample.dao.UserDao;
import sample.domain.User;
import sample.domain.UserAuth;
import sample.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao userdao;
	
	@Autowired 
    private PasswordEncoder passwordEncoder;
	
	public List<User> getUserList() {
		return userdao.getUserList();
	}
	
	public User findUserByNo(long no) {
		return userdao.findUserByNo(no);
	}
	
	public User findUserById(String username) {
		return userdao.findUserById(username);
	}
	
	public void addUser(User user) {
		// 패스워드 암호화
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		// 디폴트 권한
		user.addAuthorities(new UserAuth("ROLE_USER"));
		
		userdao.addUser(user);
	}
	
	public void updateUser(User user) {
		userdao.updateUser(user);
	}
	
	public void deleteUser(User user) {
		userdao.deleteUser(user);
	}
	
}
