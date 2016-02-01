package im.bean;

public class CreateGroupResult {

	CreateStatus status;
	
	public CreateStatus getStatus() {
		return status;
	}

	public void setStatus(CreateStatus status) {
		this.status = status;
	}

	public enum CreateStatus{
		Sucess,
		Failed
	}
}
