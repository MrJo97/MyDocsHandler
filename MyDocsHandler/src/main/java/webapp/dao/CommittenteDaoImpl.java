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


@Component
public class CommittenteDaoImpl implements CommittenteDaoInterface{
	private static Session session;
	private Transaction tx;
	private SessionFactory sf;
	
	public static void setSession(Session session) 
	{
		CommittenteDaoImpl.session = session;
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
	
	
	
	
	public Committente getCustomerById(int idCustomer) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
    	
		Committente customer = session.get(Committente.class, idCustomer);
    	
    	return customer;
          }
	
	public Committente getCustomerByCf(String cf) {
		Committente customer=null;
		try 
		{
				customer = session.createQuery("FROM Committente WHERE cf='" + cf + "'", Committente.class).getSingleResult();
		}
		catch(NoResultException e)
		{
			
			System.out.println("nessun match");
		}
		return customer;
	}
	
	public Committente getCustomerByUsername(String username) {
		Committente customer = null;
		try 
		{
			customer = session.createQuery("FROM Committente WHERE username='" + username + "'", Committente.class).getSingleResult();
		}
		catch(NoResultException e)
		{
			
			System.out.println("nessun match");
		}
		return customer;
	}
     
    public List<Committente> getAllCustomers() throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
       
       List<Committente> customers = session.createQuery("from Customer", Committente.class).getResultList();
       return customers;
       
    }
    
    public int findMaxId()
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
    }
    
    
    public void updateCustomer(Committente customer) {
    	session.saveOrUpdate(customer);
    }
    
    public void saveCustomer(Committente customer) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
    	session.save(customer);
    }
     
    public void deleteCustomer(Committente customer) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
       	session.remove(customer);
    }



}
