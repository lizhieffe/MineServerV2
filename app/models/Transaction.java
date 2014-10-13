package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.avaje.ebean.Ebean;

import play.db.ebean.Model;

@Entity
public class Transaction extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	public Long id;
		
	public Long timestamp;
	
	public Double price;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	public User user;
	
	public static Transaction createTransaction(Long timestamp, Double price, User user) {
		Transaction transaction = new Transaction();
		transaction.timestamp = timestamp;
		transaction.price = price;
		transaction.user = user;
		Ebean.save(transaction);
		return transaction;
	}
	
	public static void deleteTransaction(Long id) {
		Transaction transaction = Ebean.find(Transaction.class, id);
		Ebean.delete(transaction);
	}
	
	public static List<Transaction> getAllTransaction(User user) {
		List<Transaction> list = Ebean.find(Transaction.class).where().eq("user_id", user.id).findList();
		return list;
	}
}
