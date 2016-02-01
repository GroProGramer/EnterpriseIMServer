package im.util;

import java.util.List;

import im.bean.User;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import example.comm.Constants;
import example.jersey.apidemo.EasemobChatGroups;
import example.jersey.apidemo.EasemobIMUsers;

public class HXUtil {

	public static ObjectNode createSingleHXUser(User user){
		/**
         * 向环信后台注册IM用户[单个]
         */
        ObjectNode datanode = JsonNodeFactory.instance.objectNode();
        datanode.put("username",user.getUser_id());
        datanode.put("password",user.getPassword());
        ObjectNode createNewIMUserSingleNode = EasemobIMUsers.createNewIMUserSingle(datanode);
        /*if (null != createNewIMUserSingleNode) {
            //LOGGER.info("注册IM用户[单个]: " + createNewIMUserSingleNode.toString());
        	return user;
        }*/
		return createNewIMUserSingleNode;
	}
	
	public static ObjectNode createGroup(User user,String groupName,List<String> members){
		ObjectNode dataObjectNode = JsonNodeFactory.instance.objectNode();
		dataObjectNode.put("groupname", groupName);
		dataObjectNode.put("desc", "");
		dataObjectNode.put("approval", true);
		dataObjectNode.put("public", true);
		dataObjectNode.put("maxusers", 1000);
		dataObjectNode.put("owner", user.getUser_id());
		ArrayNode arrayNode = JsonNodeFactory.instance.arrayNode();
		for(String member:members){
			arrayNode.add(member);
		}
		dataObjectNode.put("members", arrayNode);
		ObjectNode creatChatGroupNode = EasemobChatGroups.creatChatGroups(dataObjectNode);
		return creatChatGroupNode;
		
	}
	
	public static ObjectNode deleteSingleHXUser(User user){
		
		ObjectNode deleteIMUserByUserNameNode = EasemobIMUsers.deleteIMUserByUserName(user.getUser_id());
		return deleteIMUserByUserNameNode;
		
	}
	
	public static ObjectNode deleteGroup(String toDelChatgroupid){
		ObjectNode deleteChatGroupNode =  EasemobChatGroups.deleteChatGroups(toDelChatgroupid) ;
		return deleteChatGroupNode;
		
	}
	
	public static ObjectNode userLogin(User user){
		return EasemobIMUsers.imUserLogin(user.getUser_id(), user.getPassword());
	}
}





