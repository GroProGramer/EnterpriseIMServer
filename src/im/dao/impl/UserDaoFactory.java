package im.dao.impl;

import im.dao.UserDao;

public class UserDaoFactory {
	private static UserDao dao=new UserDaoImpl();

	public static UserDao getInstance() {
		
		return dao;
	}
}
