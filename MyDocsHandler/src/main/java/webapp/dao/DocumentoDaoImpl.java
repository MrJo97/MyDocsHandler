package webapp.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import webapp.model.Committente;
import webapp.model.Documento;
import webapp.model.Utente;


@Component
public class DocumentoDaoImpl implements DocumentoDaoInterface{
	private static Session session;
	private Transaction tx;
	private SessionFactory sf;
	
	public static void setSession(Session session) 
	{
		DocumentoDaoImpl.session = session;
	}

	public void getSession()
	{
		session = sf.getCurrentSession();
	}
	
	public void setSf(SessionFactory sf) {
		this.sf = sf;
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
	
	 public int findMaxId()
	    {
	    	int maxId = 0;
	    		
	    	try
	    	{
	    		maxId =	(Integer) session.createQuery("SELECT MAX(idDocumento) FROM Documento").getSingleResult();//.g;et(0);
	    	}
	    	catch(NullPointerException e)
			{
				System.out.println("nessun match");
			}
	    	return maxId;
	    }
	
	public Documento getDocumentById(int id) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
    	
		Documento document = session.get(Documento.class, id);
    	return document;
    }
	
	public Documento getDocumentByName(String name) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
    	Documento document = null;
		try {
		document = session.createQuery("FROM Documento WHERE nome ='" + name +"'", Documento.class).getSingleResult();
    	}
		catch(NoResultException e)
		{
			
			System.out.println("nessun match");
		}
		return document;
    }
     
    public List<Documento> getAllDocuments(Utente user) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
    	List<Documento> documents = null;
    	try
      	{
    	documents = session.createQuery("FROM Documento WHERE idUtente=" + user.getIdUtente(), Documento.class).getResultList();       
      	}
    	catch(NoResultException e)
		{
			
			System.out.println("lista vuota");
		}
    	return documents;
       
    }
    
    public List<Documento> searchDocuments(Documento document, Committente customer) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
    	List<Documento> documents = null;
    	try
      	{
    		if(document.getSottocategoria()==null && document.getSottotipo()==null)
    			documents = session.createQuery("FROM Documento JOIN Committente ON Documento.IdCommittente = "+customer.getIdCommittente()
    			+ " WHERE (Documento.nome='" + document.getNome() +"'"+" AND Documento.categoria='"+document.getCategoria()+"'"
    			+" AND Documento.tipo='"+document.getTipo()+"')", Documento.class).getResultList();       
    		else
    			documents = session.createQuery("FROM Documento JOIN Committente ON Documento.IdCommittente = "+customer.getIdCommittente()
    			+ " WHERE (Documento.nome='" + document.getNome() +"'"+" AND Documento.categoria='"+document.getCategoria()+"'"
    			+" AND Documento.tipo='"+document.getTipo()+"'"+" AND Documento.sottocategoria='"+document.getSottocategoria()+"'"
    			+" AND Documento.sottotipo='"+document.getSottotipo()+"')", Documento.class).getResultList();
      	}
    	catch(NoResultException e)
		{
			System.out.println("lista vuota");
		}
    	catch(NullPointerException e)
		{
			System.out.println("lista vuota");
		}
    	return documents;
       
    }
    
     
    public void updateDocument(Documento document) {
        session.saveOrUpdate(document);
    }
    
    public void saveDocument(Documento document) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
    	session.save(document);
        
    }
     
    public void deleteDocument(Documento document) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
    	session.remove(document);
    	
    }

}



