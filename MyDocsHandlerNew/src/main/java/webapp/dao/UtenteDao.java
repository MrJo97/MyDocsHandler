package webapp.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import webapp.model.utente.Utente;

@Component
public class UtenteDao {
	private static ApplicationContext context;
	private static Session session;
	public static void setSession(Session session) {
		UtenteDao.session = session;
	}

	public static Utente getUser(int idUser) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
    	
		Utente user = session.get(Utente.class, idUser);
    	
    	return user;
          }
     
    public static List<Utente> getAllUsers() throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
       
       List<Utente> users = session.createQuery("from Utente", Utente.class).getResultList();
       
       return users;
       
    }
    
     
    public static void updateUser(Utente user, String[] params) {
    	session.update(user);
    }
    
    public static void saveUser(Utente user) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
    	
        session.save(user);
        
    }
     
    public static void deleteUser(Utente user) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
    	
    	session.remove(user);
    	
    }
}
