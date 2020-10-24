package webapp.dao;

import java.util.List;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import webapp.model.documento.Documento;
import webapp.model.utente.Utente;


@Component
public class DocumentoDao {
	private SessionFactory sf; // inizializzato dalla setter injection nel file di configurazione
	private Session session;
	private Transaction tx;
	
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public void setTx(Transaction tx) {
		this.tx = tx;
	}

	public void getSession() {
		try
		{
			if(session.isOpen())
			{
				System.out.println("recupero della sessione in DocumentoDao");
				session = sf.getCurrentSession();
			}
			else
			{
				System.out.println("apertura della sessione in DocumentoDao");
				session = sf.openSession();
			
			}
		}
		
		catch (NullPointerException|HibernateException e)
		{
			System.out.println("apertura della sessione in DocumentoDao");
				session = sf.openSession();
		}
	}

	public void getTransaction() 
	{
		tx = session.beginTransaction();
		//return tx;
	}

	public void closeTransaction() 
	{
		tx.commit();
	}

	/*public Documento getDocument(String path) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
    	
		//Documento document = session.get(Documento.class, path);
		Documento document = session.createQuery("from Documento doc LEFT JOIN FETCH doc.utente WHERE doc.path='"+path + "'", Documento.class).getSingleResult();
		document = session.createQuery("from Documento doc LEFT JOIN FETCH doc.committente WHERE doc.path='"+path + "'", Documento.class).getSingleResult();
    	return document;
          }*/
     
   /* public List<Documento> getAllDocuments() throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
     // 	List<Documento> documents = session.createQuery("from Documento", Documento.class).getResultList();       
    	List<Documento> documents = session.createQuery("from Documento doc LEFT JOIN FETCH doc.utente", Documento.class).getResultList();
    	documents = session.createQuery("from Documento doc LEFT JOIN FETCH doc.committente", Documento.class).getResultList();
      	return documents;
       
    }*/
    
     
    public void updateDocument(Documento document) {
        session.saveOrUpdate(document);
    }
    
    public void saveDocument(Documento document) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
    	session.save(document);
        
    }
    public void saveOrUpdateDocument(Documento document) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
    	session.saveOrUpdate(document);
        
    }
     
    public void deleteDocument(Documento document) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
    	session.remove(document);
    	
    }

}



