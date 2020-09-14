package webapp.dao;

import java.util.List;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import webapp.model.committente.Committente;
import webapp.model.documento.Documento;


@Component
public class CommittenteDao {
	private static ApplicationContext context;
	private static Session session;
	public static void setSession(Session session) {
		CommittenteDao.session = session;
	}

	public static Committente getCustomer(String cf) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
    	
		Committente customer = session.get(Committente.class, cf);
    	
    	return customer;
          }
     
    public static List<Committente> getAllCustomers() throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
       
       List<Committente> customers = session.createQuery("from Customer", Committente.class).getResultList();
       return customers;
       
    }
    
     
    public static void updateCustomer(Committente customer) {
    	session.saveOrUpdate(customer);
    }
    
    public static void saveCustomer(Committente customer) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
    	session.save(customer);
    }
     
    public static void deleteCustomer(Committente customer) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
       	session.remove(customer);
    }

}
