package im.util;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;

public class JsonUtil {
   private static Gson gson = new Gson();
   public static String Object2JsonString(Object obj){ 
	   
	   return gson.toJson(obj);	   
   }
   
   public static String getValueFromJsonObj(ObjectNode node,String key){
	   
	   
	   return node.get(key).toString();
   }
}
