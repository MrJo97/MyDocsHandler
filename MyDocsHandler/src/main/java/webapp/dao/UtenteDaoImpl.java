package webapp.dao;

import java.util.List;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import webapp.model.Utente;

@Component
public class UtenteDaoImpl {
	private static Session session;
	private Transaction tx;
	private static SessionFactory sf;
	
	public static void setSession(Session session)
	{
		UtenteDaoImpl.session = session;
	}
	
	public static void setSf(SessionFactory sf) {
		UtenteDaoImpl.sf = sf;
	}

	public void getSession()
	{
		session = sf.getCurrentSession();
	}
	
	public Transaction getTransaction()
	{
		tx = (Transaction) session.beginTransaction();
		return tx;
	}
	
	public void closeTransaction() throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException
	{
		tx.commit();
	}
	

	public Utente getUserById(int idUser) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
    	
		Utente user = session.get(Utente.class, idUser);
    	
    	return user;
          }
     
    public List<Utente> getAllUsers() throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
       
       List<Utente> users = session.createQuery("from Utente", Utente.class).getResultList();
       
       return users;
       
    }
    
     
    public void updateUser(Utente user) {
    	session.update(user);
    }
    
    public void saveUser(Utente user) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
    	
        session.save(user);
        
    }
     
    public void deleteUser(Utente user) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
    	
    	session.remove(user);
    	
    }

	
}
