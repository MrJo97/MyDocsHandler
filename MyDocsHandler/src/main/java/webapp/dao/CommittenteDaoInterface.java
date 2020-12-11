package webapp.dao;

import java.util.List;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import webapp.model.Committente;


public interface CommittenteDaoInterface {
	public Committente getCustomerById(int idUser) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException;
    
	public Committente getCustomerByCf(String cf);
	
	public  List<Committente> getAllCustomers() throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException;
    
    public void updateCustomer(Committente customer);
    
    public void saveCustomer(Committente customer) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException;
     
    public void deleteCustomer(Committente customer) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException;

}
