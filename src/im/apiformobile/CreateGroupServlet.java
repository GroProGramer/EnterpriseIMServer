package im.apiformobile;

import im.bean.CreateGroupResult;
import im.bean.GroupMembers;
import im.bean.User;
import im.dao.impl.UserDaoFactory;
import im.util.JsonUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateGroupServlet
 */
@WebServlet("/CreateGroupServlet")
public class CreateGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateGroupServlet() {
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
		String respString;
		CreateGroupResult result;
		List<String> members;
		String  group_name=request.getParameter("groupName");
		members=((GroupMembers)JsonUtil.getGsonInstance().fromJson(request.getParameter("members"), GroupMembers.class)).getMembers();
		User user=new User();
		String  user_id=request.getParameter("user_id");
		String  password=request.getParameter("password");
		user.setUser_id(user_id);
		user.setPassword(password);
		result=UserDaoFactory.getInstance().createGroup(user, group_name,members);
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
		} 
	}
	
	
	
	

}
