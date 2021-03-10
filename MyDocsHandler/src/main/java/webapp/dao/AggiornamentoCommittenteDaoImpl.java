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

import webapp.model.AggiornamentoCommittente;
//import webapp.model.Committente;


@Component
public class AggiornamentoCommittenteDaoImpl implements AggiornamentoCommittenteDaoInterface{
	private static Session session;
	private Transaction tx;
	private SessionFactory sf;
	
	public static void setSession(Session session) 
	{
		AggiornamentoCommittenteDaoImpl.session = session;
	}
	
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	public void getSession()
	{
		session = sf.getCurrentSession();
	}
	
	public Transaction getTransaction()
	{
		tx = session.beginTransaction();
		return tx;
	}
	
	public void closeTransaction() throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException
	{
		tx.commit();
	} 
	
	public AggiornamentoCommittente getUpdatingCustomerByCf(String cf) {
		AggiornamentoCommittente customer=null;
		try 
		{
				customer = session.createQuery("FROM AggiornamentoCommittente WHERE cf='" + cf + "'", AggiornamentoCommittente.class).getSingleResult();
		}
		catch(NoResultException e)
		{
			
			System.out.println("nessun match");
		}
		return customer;
	}

     
    public List<AggiornamentoCommittente> getAllUpdatingCustomers() throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
       
       List<AggiornamentoCommittente> customers = session.createQuery("from AggiornamentoCommittente", AggiornamentoCommittente.class).getResultList();
       return customers;  
    }
    
   /* public int findMaxId()
    {
    	int maxId = 0;
    		
    	try
    	{
    		maxId =	(Integer) session.createQuery("SELECT MAX(idCommittente) FROM Committente").getSingleResult();//.g;et(0);
    	}
    	catch(NullPointerException e)
		{
			System.out.println("nessun match");
		}
    	return maxId;
    }*/
	public AggiornamentoCommittente getUpdatingCustomerByFk(int idCustomer) {
		AggiornamentoCommittente customer=null;
		try 
		{
				customer = session.createQuery("FROM AggiornamentoCommittente WHERE idCommittente='" + idCustomer + "'", AggiornamentoCommittente.class).getSingleResult();
		}
		catch(NoResultException e)
		{
			
			System.out.println("nessun match");
		}
		return customer;
	}
    
  /*  public void updateCustomerByFk(AggiornamentoCommittente updatingCustomer) {
    	session.createQuery("UPDATE * from Aggiornamento_Committente WHERE idCommittente = "+updatingCustomer.getCommittente().getIdCommittente()).executeUpdate();
    	//session.saveOrUpdate(updatingCustomer);
    }*/
    
    public void saveCustomer(AggiornamentoCommittente updatingCustomer) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
    	session.save(updatingCustomer);
    }
     
    public void deleteCustomer(AggiornamentoCommittente updatingCustomer) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
       	System.out.println("eliminazione del record in Aggiornamento_Committente");
       	System.out.println(updatingCustomer);
    	session.remove(updatingCustomer);//non funziona...
       	//session.createQuery("DELETE * FROM AggiornamentoCommittente WHERE idCommittente = " + updatingCustomer.getCommittente().getIdCommittente());
    	System.out.println("eliminato");
    }

    //mettiamo la FK idCommittente come PK
	public void updateCustomer(AggiornamentoCommittente updatingCustomer) {
		session.saveOrUpdate(updatingCustomer);
	}



}
