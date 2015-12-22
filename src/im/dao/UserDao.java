package im.dao;

import im.bean.User;

import java.util.ArrayList;


public interface UserDao {
	
	public User register(User u);

	public ArrayList<User> login(User u);

	public ArrayList<User> refresh(int id);
	public void logout(int id);
}
