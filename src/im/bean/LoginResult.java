package im.bean;

public class LoginResult {
	
	private ResultCode loginStatus;
	
	



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
