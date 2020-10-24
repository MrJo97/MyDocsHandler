package webapp.dao;

import java.util.List;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import webapp.model.utente.Utente;

public interface UtenteDaoInterface {	
	
	
		public void getTransaction();
		
		public void closeTransaction();
	
		public void getSession();
		
		public void closeSession();
		
		public void getSessionAndTransaction();
		
		public void closeSessionAndTransaction();

		public Utente getUserById(int idUser);
	     
	    public List<Utente> getAllUsers();
	    
	    public void updateUser(Utente user);
	    
	    public void saveUser(Utente user);
	     
	    public void deleteUser(Utente user);
	    
	    
	}

