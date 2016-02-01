package im.bean;

import java.io.Serializable;

public class Group implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String group_id;// 唯一id
	private String creator_id;
	private int group_member_count;
	private String description;
	private int img;// 头像图标
	private String avatar;
	public String getGroup_id() {
		return group_id;
	}
	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}
	public String getCreator_id() {
		return creator_id;
	}
	public void setCreator_id(String creator_id) {
		this.creator_id = creator_id;
	}
	public int getGroup_member_count() {
		return group_member_count;
	}
	public void setGroup_member_count(int group_member_count) {
		this.group_member_count = group_member_count;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getImg() {
		return img;
	}
	public void setImg(int img) {
		this.img = img;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	
	
	 
}
