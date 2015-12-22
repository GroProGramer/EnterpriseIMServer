package im.bean;

import im.util.Constants;

public class RegResult {

	boolean regstatus;
	User newuser;
	public RegResult(User newuser){
		this.newuser=newuser;
		if(newuser==null) regstatus=Constants.registerFailed;
		else regstatus=Constants.registerSuccess;
	}
}
