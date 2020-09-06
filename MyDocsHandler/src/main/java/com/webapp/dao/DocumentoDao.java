package com.webapp.dao;

import java.util.List;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.webapp.model.documento.Documento;


@Component
public class DocumentoDao {
	private static Session session;
	public static void setSession(Session session) {
		DocumentoDao.session = session;
	}

	public static Documento getDocument(String path) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
    	
		Documento document = session.get(Documento.class, path);
    	
    	return document;
          }
     
    public static List<Documento> getAllDocuments() throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
      	List<Documento> documents = session.createQuery("from Document", Documento.class).getResultList();       
      return documents;
       
    }
    
     
    public static void updateDocument(Documento document) {
        session.saveOrUpdate(document);
    }
    
    public static void saveDocument(Documento document) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
    	session.save(document);
        
    }
     
    public static void deleteDocument(Documento document) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
    	session.remove(document);
    	
    }

}



