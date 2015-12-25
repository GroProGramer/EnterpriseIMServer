package im.apiformobile;

import im.bean.LoginResult;
import im.bean.User;
import im.dao.impl.UserDaoFactory;
import im.util.Constants;
import im.util.DButil;
import im.util.HXUtil;
import im.util.JsonUtil;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String  user_id=request.getParameter("user_id");
		String  password=request.getParameter("password");
		LoginResult result=new LoginResult();
		Connection conn;
		//LoginResult login;
		String respString = null;
		User user=new User();
		user.setUser_id(user_id);
		user.setPassword(password);
		/*conn=(Connection) DButil.connect();
		if(DButil.checkExistInDB(conn, user)==false){
			result.setLoginStatus(LoginResult.ResultCode.UserNotExisted);
		}
		else {
			if("200".equals(HXUtil.userLogin(user).get("statusCode").toString())){
				result.setLoginStatus(LoginResult.ResultCode.LoginSucess);
				HttpSession session=request.getSession();
				session.setAttribute(user_id, session.getId());
			}
			
			
		}*/
		result=UserDaoFactory.getInstance().login(user);
		respString=JsonUtil.Object2JsonString(result);
		PrintWriter out = null;
		try {
		    out = response.getWriter();
		    out.write(respString);
			//response.getOutputStream().write(jsonStr.getBytes("utf-8"));
		} catch (IOException e) {
		    e.printStackTrace();
		} finally {
		    if (out != null) {
		        out.close();
		    }
		    //if(conn!=null) DButil.close(conn);
		} 
	}

}
















