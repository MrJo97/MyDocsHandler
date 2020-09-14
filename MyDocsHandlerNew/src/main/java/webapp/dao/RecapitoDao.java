package webapp.dao;

import java.util.List;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import webapp.model.committente.Committente;
import webapp.model.recapito.Recapito;

@Component
public class RecapitoDao {

	
		private static ApplicationContext context;
		private static Session session;
		public static void setSession(Session session) {
			RecapitoDao.session = session;
		}

		public static Recapito getContact(int idContact) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
	    	
			Recapito contact = session.get(Recapito.class, idContact);
	    	
	    	return contact;
	          }
	     
	    public static List<Recapito> getAllContacts() throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
	       
	       List<Recapito> contacts = session.createQuery("from Recapito", Recapito.class).getResultList();
	       return contacts;
	       
	    }
	    
	     
	    public static void updateContact(Recapito contact) {
	    	session.saveOrUpdate(contact);
	    }
	    
	    public static void saveContact(Recapito contact) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
	    	session.save(contact);
	    }
	     
	    public static void deleteContact(Recapito contact) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
	       	session.remove(contact);
	    }

}
