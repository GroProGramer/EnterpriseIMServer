package im.dao;

import im.bean.LoginResult;
import im.bean.RegResult;
import im.bean.User;

import java.util.ArrayList;


public interface UserDao {
	
	public RegResult register(User u);

	public LoginResult login(User u);

	public ArrayList<User> refresh(int id);
	public void logout(int id);
}
