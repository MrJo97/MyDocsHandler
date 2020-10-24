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
import webapp.model.recapito.Recapito;

@Component
public class RecapitoDao {

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
				System.out.println("recupero della sessione in RecapitoDao");
				session = sf.getCurrentSession();
			}
			else
			{
				System.out.println("apertura della sessione in RecapitoDao");
				session = sf.openSession();
			}
		}
		
		catch (NullPointerException|HibernateException e)
		{
			System.out.println("apertura della sessione in RecapitoDao");
				session = sf.openSession();
		}
	}

	public void getTransaction() {
		tx = session.beginTransaction();
		// return tx;
	}

	public void closeTransaction() {
		tx.commit();
	}

	public Recapito getContact(int idContact) throws SecurityException, RollbackException, HeuristicMixedException,
			HeuristicRollbackException, SystemException {

		Recapito contact = session.get(Recapito.class, idContact);

		return contact;
	}

	public List<Recapito> getAllContacts() throws SecurityException, RollbackException, HeuristicMixedException,
			HeuristicRollbackException, SystemException {

		List<Recapito> contacts = session.createQuery("from Recapito", Recapito.class).getResultList();
		return contacts;

	}

	public void updateContact(Recapito contact) {
		session.saveOrUpdate(contact);
	}

	public void saveContact(Recapito contact) throws SecurityException, RollbackException, HeuristicMixedException,
			HeuristicRollbackException, SystemException {
		session.save(contact);
	}

	public void deleteContact(Recapito contact) throws SecurityException, RollbackException, HeuristicMixedException,
			HeuristicRollbackException, SystemException {
		session.remove(contact);
	}

}
