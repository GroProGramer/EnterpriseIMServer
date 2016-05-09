package im.bean;

public class GetInfoResult {
     public static final int sucess=1;
     public static final int failed=0;
     private int status;
     private User user;
     
     
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
     
}
