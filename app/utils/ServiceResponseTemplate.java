package utils;

import play.libs.Json;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ServiceResponseTemplate {

	public static ObjectNode generateSuccessfulSerciveResponseTemplate() {
		ObjectNode result = Json.newObject();
		ObjectNode error = Json.newObject();
		ObjectNode response = Json.newObject();
		
		error.put("code", 0);
		error.put("type", "none");	
		result.put("error", error);
		result.put("response", response);
		
		return result;
	}
	
	public static ObjectNode generateFailedSerciveResponseTemplate(int code, String type, String msg) {
		ObjectNode result = Json.newObject();
		ObjectNode error = Json.newObject();
		
		error.put("code", code);
		error.put("type", type);
		error.put("message", msg);
		result.put("error", error);
		
		return result;
	}
	
	public static ObjectNode generateFailedSerciveResponseTemplate() {
		int code = 1;
		String type = "unknown";
		String msg = "Mine Server Error";
		
		return generateFailedSerciveResponseTemplate(code, type, msg);
	}
	
}
