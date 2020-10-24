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

import webapp.model.committente.Committente;
import webapp.model.documento.Documento;
import webapp.model.utente.Utente;

@Component
public class CommittenteDao {

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
		session = sf.getCurrentSession();
	}

	public void getTransaction() {
		tx = session.beginTransaction();
		// return tx;
	}

	public void closeTransaction() {
		tx.commit();
	}

	public Committente getCustomer(int idCustomer) throws SecurityException, RollbackException, HeuristicMixedException,
			HeuristicRollbackException, SystemException {

		Committente customer = session.get(Committente.class, idCustomer);
		
		
		/*Committente customer = session
				.createQuery("from Committente customer LEFT JOIN FETCH customer.documenti WHERE customer.cf='" + cf + "'",
						Committente.class)
				.getSingleResult();
		customer = session
				.createQuery("from Committente customer LEFT JOIN FETCH customer.recapiti WHERE customer.cf='" + cf + "'",
						Committente.class)
				.getSingleResult();
		customer = session
				.createQuery("from Committente customer LEFT JOIN FETCH customer.utente WHERE customer.cf='" + cf + "'",
						Committente.class)
				.getSingleResult();*/

		return customer;
	}
	
	public List<Committente> getAllCustomers() throws SecurityException, RollbackException, HeuristicMixedException,
			HeuristicRollbackException, SystemException {

		List<Committente> customers = session.createQuery("from Committente", Committente.class).getResultList();

	/*	List<Committente> customers = session
				.createQuery("from Committente customer LEFT JOIN FETCH customer.documenti", Committente.class)
				.getResultList();
		customers = session
				.createQuery("from Committente customer LEFT JOIN FETCH customer.recapiti", Committente.class)
				.getResultList();
		customers = session
				.createQuery("from Committente customer LEFT JOIN FETCH customer.utente", Committente.class)
				.getResultList();*/
		return customers;

	}

	public void updateCustomer(Committente customer) {
		session.saveOrUpdate(customer);
	}

	public void saveCustomer(Committente customer) throws SecurityException, RollbackException, HeuristicMixedException,
			HeuristicRollbackException, SystemException {
		session.save(customer);
	}

	public void deleteCustomer(Committente customer) throws SecurityException, RollbackException,
			HeuristicMixedException, HeuristicRollbackException, SystemException {
		session.remove(customer);
	}

}
