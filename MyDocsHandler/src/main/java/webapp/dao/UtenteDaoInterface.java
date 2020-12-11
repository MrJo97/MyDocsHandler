package webapp.dao;

import java.util.List;

import webapp.model.Utente;

public interface UtenteDaoInterface {		

		public  Utente getUserById(int idUser);
	     
	    public  List<Utente> getAllUsers();
	    
	    public void updateUser(Utente user);
	    
	    public void saveUser(Utente user);
	     
	    public void deleteUser(Utente user);

		public void getSession();

		public void getTransaction();

		public void closeTransaction();
	    
	}

