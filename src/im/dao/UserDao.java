package im.dao;

import im.bean.CreateGroupResult;
import im.bean.LoginResult;
import im.bean.RegResult;
import im.bean.User;

import java.util.ArrayList;
import java.util.List;


public interface UserDao {
	
	public RegResult register(User u);

	public LoginResult login(User u);

	public ArrayList<User> refresh(int id);
	public void logout(int id);
	public CreateGroupResult createGroup(User user, String groupName, List<String> members);
	public User getUserInfo(String userId);

	
}
