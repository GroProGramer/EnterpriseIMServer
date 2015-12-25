package im.util;

import im.bean.User;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

/**
 * 数据库工具类
 * 
 * @author way
 * 
 */
public class DButil {
	/**
	 * 连接数据库
	 * 
	 * @return数据库连接对象
	 */
	public static Connection connect() {
		Properties pro = new Properties();
		String driver = null;
		String url = null;
		String username = null;
		String password = null;
		try {
			InputStream is = DButil.class.getClassLoader()
					.getResourceAsStream("DB.properties");
			// System.out.println(is.toString());
			pro.load(is);
			driver = pro.getProperty("driver");
			url = pro.getProperty("url");
			username = pro.getProperty("username");
			password = pro.getProperty("password");
//			 System.out.println(driver + ":" + url + ":" + username + ":"
//			 + password);
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, username,
					password);
			return conn;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 关闭数据库
	 * 
	 * @param conn
	 *            传入数据库连接对象
	 */
	public static void close(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

//	public static void main(String[] args) {
//		Connection con = new DButil().connect();
//		System.out.println(con);
//	}
	
	
	public static boolean checkExistInDB(Connection con,User user){
		PreparedStatement pst= null; 
		String sql="select * from  tb_user where user_id=?";
		try {
			 pst=(PreparedStatement) con.prepareStatement(sql);
			 pst.setString(1, user.getUser_id());
			 ResultSet rs = (ResultSet) pst.executeQuery();
			 if(rs.next()) return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return false;		
	}
	
	public static boolean userIsValid(Connection con,User user){
		boolean result=false;
		PreparedStatement pst= null; 
		ResultSet rs;
		try {
			pst = (PreparedStatement) con.prepareStatement("SELECT * FROM tb_user WHERE user_id = ? AND password = ?");
			pst.setString(1, user.getUser_id());
			pst.setString(2, user.getPassword());
			rs = (ResultSet) pst.executeQuery();
			if (rs.next()) {
				result=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}



















