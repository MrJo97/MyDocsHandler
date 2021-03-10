package webapp.dao;

import java.util.List;

//import javax.persistence.NoResultException;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import webapp.model.AggiornamentoCommittente;

public interface AggiornamentoCommittenteDaoInterface {

	public AggiornamentoCommittente getUpdatingCustomerByCf(String cf);

    public List<AggiornamentoCommittente> getAllUpdatingCustomers() throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException;
    
    public void updateCustomer(AggiornamentoCommittente updatingCustomer);
    
    public void saveCustomer(AggiornamentoCommittente updatingCustomer) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException;
     
    public void deleteCustomer(AggiornamentoCommittente updatingCustomer) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException; 

}
