package models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

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
	
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="user_id")
	public User user;
	
	public static UserCredential createUserCredential(User user, String password) {
		UserCredential credential = new UserCredential();
		credential.user = user;
		credential.password = password;
		Ebean.save(credential);
		return credential;
	}
}
