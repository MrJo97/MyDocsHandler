package com.webapp.dao;

import java.util.List;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.webapp.model.utente.Utente;

public interface UtenteDaoInterface {		

		public  Utente getUser(int idUser);
	     
	    public  List<Utente> getAllUsers();
	    
	    public void updateUser(Utente user, String[] params);
	    
	    public void saveUser(Utente user);
	     
	    public void deleteUser(Utente user);
	}

