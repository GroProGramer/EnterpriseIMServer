package im.bean;

public class RegResult {

	RegStatus regstatus;
	
	
	public RegStatus getRegstatus() {
		return regstatus;
	}


	public void setRegstatus(RegStatus regstatus) {
		this.regstatus = regstatus;
	}


	public enum RegStatus{
		RegSucess,
		UserHasExisted,
		LocalDatabaseWrong,
		HXDatabaseWrong
	}
}
