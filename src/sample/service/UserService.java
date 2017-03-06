package sample.service;

import java.util.List;
import javax.transaction.Transactional;
import sample.domain.User;

@Transactional
public interface UserService {

	public List<User> getUserList();
	public User findUserByNo(long no);
	public User findUserById(String username);
	public void addUser(User user);
	public void updateUser(User user);
	public void deleteUser(User user);
	
}
