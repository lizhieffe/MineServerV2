package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.avaje.ebean.Ebean;

import play.db.ebean.Model;

@Entity
public class User extends Model {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	public Long id;
	
	public String username;
	
	public String lastName;
	
	public String firstName;
	
	public boolean gender;	// 0 = female, 1 = male
	
	public Integer birthday;
	
	public String password;
	
	public Long spouseUserId;
	
//	@OneToOne(mappedBy="user", cascade=CascadeType.ALL)
//	public UserCredential credential;
	
	@OneToMany(fetch=FetchType.EAGER)
	public List<Transaction> transactions;
	
	public static User createUser(String username, String password, String lastName, String firstName, String gender) {
		
		User user = new User();
		user.username = username;
		user.lastName = lastName;
		user.firstName = firstName;
		user.gender = gender.equalsIgnoreCase("m") ? true : false;
		user.password = password;
		Ebean.save(user);
		return user;
	}
	
	public static User getUserByUsername(String username) {
		List<User> list = Ebean.find(User.class).where().eq("username", username).findList();
		if (null == list || list.size() == 0)
			return null;
		else
			return list.get(0);
	}
}
