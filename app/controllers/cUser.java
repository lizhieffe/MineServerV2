package controllers;

import java.util.List;
import models.Session;
import models.User;

import com.avaje.ebean.Ebean;
import com.fasterxml.jackson.databind.node.ObjectNode;

import play.mvc.Controller;
import play.mvc.Result;
import utils.ServiceResponseTemplate;

public class cUser extends Controller {

	public static Result signup(String username, String password, String lastName, String firstName, String gender) {

		ObjectNode response = null;
		
		if (username == null || username.length() == 0 || password == null || password.length() == 0
				|| lastName == null || lastName.length() == 0 || firstName == null || firstName.length() == 0
				|| gender == null || gender.length() == 0 || (!gender.equalsIgnoreCase("m") && !gender.equalsIgnoreCase("f"))) {
			response = ServiceResponseTemplate.generateFailedSerciveResponseTemplate(1, null, "Invalid parameter");
			System.out.println(username + password + lastName + firstName + gender);
			return ok(response);
		}
			
		List<User> list = Ebean.find(User.class).where().eq("username", username).findList();
		if (null != list && list.size() != 0)
			return ok(ServiceResponseTemplate.generateFailedSerciveResponseTemplate(1, null, "username already exist"));
		
		User user = User.createUser(username, password, lastName, firstName, gender);
		Session session = Session.createSession(user);
		
		response = ServiceResponseTemplate.generateSuccessfulSerciveResponseTemplate();
		((ObjectNode)response.get("response")).put("is_success", true);
		((ObjectNode)response.get("response")).put("user_id", user.id);
		((ObjectNode)response.get("response")).put("token", session.token);
		
		return ok(response);
    }
	
	public static Result signin(String username, String password) {

		ObjectNode response = null;
		
		if (username == null || username.length() == 0 || password == null || password.length() == 0) {
			response = ServiceResponseTemplate.generateFailedSerciveResponseTemplate(1, null, "Invalid parameter");
			return ok(response);
		}
		
		User user = User.getUserByUsername(username);
		if (null == user)
			return ok(ServiceResponseTemplate.generateFailedSerciveResponseTemplate(1, null, "username doesn't exist"));
		
		if (!password.equals(user.credential.password))
			return ok(ServiceResponseTemplate.generateFailedSerciveResponseTemplate(1, null, "username and password don't match"));
		
		Session session = Session.createSession(user);
		
		response = ServiceResponseTemplate.generateSuccessfulSerciveResponseTemplate();
		((ObjectNode)response.get("response")).put("is_success", true);
		((ObjectNode)response.get("response")).put("user_id", user.id);
		((ObjectNode)response.get("response")).put("token", session.token);

		return ok(response);
    }
}
