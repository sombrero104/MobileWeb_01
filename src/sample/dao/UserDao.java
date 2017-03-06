package sample.dao;

import java.util.List;

import sample.domain.User;

public interface UserDao {
	
	public List<User> getUserList();
	public User findUserByNo(long no);
	public User findUserById(String username);
	public void addUser(User user);
	public void updateUser(User user);
	public void deleteUser(User user);
	
}
