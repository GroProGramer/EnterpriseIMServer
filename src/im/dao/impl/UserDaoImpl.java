package im.dao.impl;

import im.bean.LoginResult;
import im.bean.RegResult;
import im.bean.User;
import im.dao.UserDao;
import im.util.Constants;
import im.util.DButil;
import im.util.DateUtil;
import im.util.HXUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;


public class UserDaoImpl implements UserDao {

	@Override
	public RegResult register(User u) {
		RegResult regResult=new RegResult();
		String user_id;
		Connection con = DButil.connect();
		if(DButil.checkExistInDB(con, u)){
			//return null;
			regResult.setRegstatus(RegResult.RegStatus.UserHasExisted);
			return regResult;
		}
		
		
		String sql = "insert into tb_user(user_id,password) values(?,?)";
		
		ObjectNode node=HXUtil.createSingleHXUser(u);
      if(node.get("statusCode").toString().equals("200")){//向环信注册成功
    	  //System.out.println(node.get("statusCode").toString());
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, u.getUser_id());
			ps.setString(2, u.getPassword());
			int res = ps.executeUpdate();
			if (res > 0) {	
				regResult.setRegstatus(RegResult.RegStatus.RegSucess);
				return regResult;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			regResult.setRegstatus(RegResult.RegStatus.LocalDatabaseWrong);
			HXUtil.deleteSingleHXUser(u);//本地数据库注册失败，则必须也将环信服务端的账号也删除
		} finally {
			DButil.close(con); 
		}
		
		return regResult;//向本地数据库注册用户失败
      }
      else {
    	  regResult.setRegstatus(RegResult.RegStatus.HXDatabaseWrong);
    	  return regResult;//向环信注册用户失败
      }
	}

	/*@Override
	public ArrayList<User> login(User user) {
		Connection con = DButil.connect();
		String sql = "select * from user where _id=? and _password=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, user.getId());
			ps.setString(2, user.getPassword());
			ResultSet rs = ps.executeQuery();
			if (rs.first()) {
				setOnline(user.getId());
				ArrayList<User> refreshList = refresh(user.getId());
				return refreshList;
			}
		} catch (SQLException e) {
			// e.printStackTrace();
		} finally {
			DButil.close(con);
		}
		return null;
	}*/

	
	/*public User findMe(int id) {
		User me = new User();
		Connection con = DButil.connect();
		String sql = "select * from user where _id=?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.first()) {
				me.setId(rs.getInt("_id"));
				me.setEmail(rs.getString("_email"));
				me.setName(rs.getString("_name"));
				me.setImg(rs.getInt("_img"));
			}
			return me;
		} catch (SQLException e) {
			// e.printStackTrace();
		} finally {
			DButil.close(con);
		}
		return null;
	}*/

	/*
	public ArrayList<User> refresh(int id) {
		ArrayList<User> list = new ArrayList<User>();
		User me = findMe(id);
		list.add(me);// ������Լ�
		Connection con = DButil.connect();
		String sql = "select * from _? ";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.first()) {
				do {
					User friend = new User();
					friend.setId(rs.getInt("_qq"));
					friend.setName(rs.getString("_name"));
					friend.setIsOnline(rs.getInt("_isOnline"));
					friend.setImg(rs.getInt("_img"));
					friend.setGroup(rs.getInt("_group"));
					list.add(friend);
				} while (rs.next());
			}
			return list;
		} catch (SQLException e) {
			// e.printStackTrace();
		} finally {
			DButil.close(con);
		}
		return null;
	}*/

	/**
	 * ����״̬Ϊ����
	 * 
	 * @param id
	 */
	public void setOnline(int id) {
		Connection con = DButil.connect();
		try {
			String sql = "update user set _isOnline=1 where _id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
			updateAllOn(id);// �������б�״̬Ϊ����
		} catch (SQLException e) {
			// e.printStackTrace();
		} finally {
			DButil.close(con);
		}
	}

	/**
	 * ע��ɹ��󣬴���һ���û��?������û�����
	 * 
	 * @param id
	 */
	public void createFriendtable(int id) {
		Connection con = DButil.connect();
		try {
			String sql = "create table _" + id
					+ " (_id int auto_increment not null primary key,"
					+ "_name varchar(20) not null,"
					+ "_isOnline int(11) not null default 0,"
					+ "_group int(11) not null default 0,"
					+ "_qq int(11) not null default 0,"
					+ "_img int(11) not null default 0)";
			PreparedStatement ps = con.prepareStatement(sql);
			int res = ps.executeUpdate();
			System.out.println(res);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DButil.close(con);
		}
	}

	@Override
	/**
	 * ���߸���״̬Ϊ����
	 */
	public void logout(int id) {
		Connection con = DButil.connect();
		try {
			String sql = "update user set _isOnline=0 where _id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
			updateAllOff(id);
			// System.out.println(res);
		} catch (SQLException e) {
			// e.printStackTrace();
		} finally {
			DButil.close(con);
		}
	}

	/**
	 * ���������û���״̬Ϊ����
	 * 
	 * @param id
	 */
	public void updateAllOff(int id) {
		Connection con = DButil.connect();
		try {
			String sql = "update _? set _isOnline=0 where _qq=?";
			PreparedStatement ps = con.prepareStatement(sql);
			for (int offId : getAllId()) {
				ps.setInt(1, offId);
				ps.setInt(2, id);
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			// e.printStackTrace();
		} finally {
			DButil.close(con);
		}
	}

	/**
	 * ���������û�״̬Ϊ����
	 * 
	 * @param id
	 */
	public void updateAllOn(int id) {
		Connection con = DButil.connect();
		try {
			String sql = "update _? set _isOnline=1 where _qq=?";
			PreparedStatement ps = con.prepareStatement(sql);
			for (int OnId : getAllId()) {
				ps.setInt(1, OnId);
				ps.setInt(2, id);
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			// e.printStackTrace();
		} finally {
			DButil.close(con);
		}
	}

	public List<Integer> getAllId() {
		Connection con = DButil.connect();
		List<Integer> list = new ArrayList<Integer>();
		try {
			String sql = "select _id from user";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.first()) {
				do {
					int id = rs.getInt("_id");
					list.add(id);
				} while (rs.next());
			}
			// System.out.println(list);
			return list;
		} catch (SQLException e) {
			// e.printStackTrace();
		} finally {
			DButil.close(con);
		}
		return null;
	}

	public static void main(String[] args) {
		User u = new User();
		UserDaoImpl dao = new UserDaoImpl();
		// //user.id��mysql��ݿ�������
		// //�������ݿ���������ͻ�ƿ�,user�����û���,������Ǻ����б�
		// ģ��ע��
		// u.setName("С��");
		// u.setPassword("123456");
		// u.setEmail("153342219@qq.com");
		// System.out.println(dao.register(u));
		//ģ��ע��
		// u.setName("���");
		// u.setPassword("123456");
		// u.setEmail("153342229@qq.com");
		// System.out.println(dao.register(u));

		// //ģ���½
//		 u.setId(2031);
//		 u.setPassword("123456");
//		 System.out.println(dao.login(u));
		
		
		 //ģ��ǳ�
		 dao.logout(2018);
		 System.out.println("=================");
		 
		// dao.setOnline(2016);
		// dao.getAllId();
		// List<User> list = dao.refresh(2016);
		// System.out.println(list);

	}

	@Override
	public ArrayList<User> refresh(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("null")
	@Override
	public LoginResult login(User u) {
		// TODO Auto-generated method stub
		LoginResult result = new LoginResult();
		Connection con = DButil.connect();
		try{
		  if(DButil.checkExistInDB(con, u)==false){
			//return null;
			  result.setLoginStatus(LoginResult.ResultCode.UserNotExisted);
		  }
		  if(!DButil.userIsValid(con, u)){
			  result.setLoginStatus(LoginResult.ResultCode.UserNameOrPassswordWrong);
		  }
		  JsonNode node=(JsonNode) HXUtil.userLogin(u).get("statusCode");
		  
		  if("200".equals(node.toString())){
			  result.setLoginStatus(LoginResult.ResultCode.LoginSucess);
		  }
		}finally{
			DButil.close(con); 
		}
		return result;
	}

}
