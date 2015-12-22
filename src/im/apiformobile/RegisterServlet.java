package im.apiformobile;

import im.bean.RegResult;
import im.bean.User;
import im.dao.impl.UserDaoFactory;
import im.util.JsonUtil;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public RegisterServlet() {
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
		User newuser=new User();
		User result;
		RegResult regresult;
		String respString;
		String  user_id=request.getParameter("user_id");
		String  password=request.getParameter("password");
		newuser.setUser_id(user_id);
		newuser.setPassword(password);
		result=UserDaoFactory.getInstance().register(newuser);
		regresult=new RegResult(result);
		respString=JsonUtil.Object2JsonString(regresult);
		
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
		} 
	}

}
