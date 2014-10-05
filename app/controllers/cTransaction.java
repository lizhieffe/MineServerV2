package controllers;

import java.util.List;
import models.Session;
import models.Transaction;
import models.User;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.mvc.Controller;
import play.mvc.Result;
import utils.ServiceResponseTemplate;

public class cTransaction extends Controller {
	
	public static Result addTransaction(Long timestamp, Double price, String token) {
		
		if (timestamp == null || price == null || price == 0 || null == token || token.length() == 0)
			return ok(ServiceResponseTemplate.generateFailedSerciveResponseTemplate(1, null, "Invalid parameter"));
		
		User user = Session.getUserByToken(token);
		if (null == user)
			return ok(ServiceResponseTemplate.generateFailedSerciveResponseTemplate(1, null, "Invalid token"));

		Transaction transaction = Transaction.createTransaction(timestamp, price, user);
		
		ObjectNode response = ServiceResponseTemplate.generateSuccessfulSerciveResponseTemplate();
		((ObjectNode)response.get("response")).put("is_success", true);
		((ObjectNode)response.get("response")).put("transaction_id", transaction.id);

		return ok(response);
    }
	
	public static Result getAllTransactions(String token) {
		if (null == token || token.length() == 0)
			return ok(ServiceResponseTemplate.generateFailedSerciveResponseTemplate(1, null, "Invalid parameter"));

		User user = Session.getUserByToken(token);
		if (null == user)
			return ok(ServiceResponseTemplate.generateFailedSerciveResponseTemplate(1, null, "Invalid token"));
		
		List<Transaction> transactions = Transaction.getAllTransaction(user);
		
		ObjectNode response = ServiceResponseTemplate.generateSuccessfulSerciveResponseTemplate();
		((ObjectNode)response.get("response")).put("is_success", true);
		((ObjectNode)response.get("response")).put("transaction_num", transactions.size());

		try {
			if (transactions.size() > 0) {
				JsonNodeFactory factory = JsonNodeFactory.instance;
				
				ArrayNode tmp = new ArrayNode(factory);
				for (Transaction transaction : transactions) {
					ObjectNode tmp1 = new ObjectNode(factory);
					tmp1.put("timestamp", transaction.timestamp);
					tmp1.put("price", transaction.price);
					tmp1.put("id", transaction.id);
					tmp.add(tmp1);
				}
				((ObjectNode)response.get("response")).put("transactions", tmp);
			}	
		} catch (Exception e) {
			System.err.println(e);
		}
		
		return ok(response);
	}
}
