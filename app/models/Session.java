package models;

import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.avaje.ebean.Ebean;

import play.db.ebean.Model;

@Entity
public class Session extends Model {

	@Id
	public Long id;
	
	public String token;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	public User user;
	
	public Long timestamp;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static Session createSession(User user) {
		Session session = new Session();
		session.token = UUID.randomUUID().toString();
		session.timestamp = System.currentTimeMillis() / 1000L;
		session.user = user;
		Ebean.save(session);
		return session;
	}
	
	public static User getUserByToken(String token) {
		List<Session> list = Ebean.find(Session.class).where().eq("token", token).order().desc("timestamp").findList();
		if (null == list || list.size() == 0)
			return null;
		else
			return list.get(0).user;
	}
}
