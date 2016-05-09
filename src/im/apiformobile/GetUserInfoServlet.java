package im.apiformobile;

import im.bean.GetInfoResult;
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
 * Servlet implementation class GetUserInfoServlet
 */
@WebServlet("/GetUserInfoServlet")
public class GetUserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetUserInfoServlet() {
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
		User user=null;
		String result=null;
		GetInfoResult infoResult=new GetInfoResult();
		user=UserDaoFactory.getInstance().getUserInfo(user_id);
		if(user!=null){
			infoResult.setStatus(GetInfoResult.sucess);
		}
		else{
			infoResult.setStatus(GetInfoResult.failed);
		}
		infoResult.setUser(user);
		result=JsonUtil.Object2JsonString(infoResult);
		PrintWriter out = null;
		try {
		    out = response.getWriter();
		    out.write(result);
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
