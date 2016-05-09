package im.bean;

public class LoginResult {
	
	private ResultCode loginStatus;
	private User user;
	



	
	public User getUser() {
		return user;
	}





	public void setUser(User user) {
		this.user = user;
	}





	public ResultCode getLoginStatus() {
		return loginStatus;
	}





	public void setLoginStatus(ResultCode loginStatus) {
		this.loginStatus = loginStatus;
	}





	public enum ResultCode{
		UserNotExisted,
		UserNameOrPassswordWrong,
		LoginSucess
	}
}
