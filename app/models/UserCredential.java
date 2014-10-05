package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.avaje.ebean.Ebean;

import play.db.ebean.Model;

@Entity
public class UserCredential extends Model {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	public Long id;
	
	public String password;
	
	public static UserCredential createUserCredential(String password) {
		UserCredential credential = new UserCredential();
		credential.password = password;
		Ebean.save(credential);
		return credential;
	}
}
