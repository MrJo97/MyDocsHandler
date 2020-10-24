package webapp.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
//import java.util.LinkedHashSet;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.collection.spi.PersistentCollection;
import org.hibernate.engine.spi.SessionImplementor;
//import org.hibernate.mapping.Collection;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import webapp.model.committente.Committente;
import webapp.model.utente.Utente;

//@Repository
//@Transactional
//@EnableTransactionManagement
@Component
public class UtenteDao implements UtenteDaoInterface {

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

	public void closeSession() {
		session.close();
	}

	public void getTransaction() {
		tx = session.beginTransaction();
		// return tx;
	}

	public void closeTransaction() {
		tx.commit();
	}

	public void getSessionAndTransaction() {
		this.getSession();
		this.getTransaction();
	}

	public void closeSessionAndTransaction() {
		this.closeTransaction();
		this.closeSession();
	}

	public Utente getUserById(int idUser) {
		// this.getSessionAndTransaction();
		Utente user = session.get(Utente.class, idUser);

		//Hibernate.initialize(user);
		// this.closeSessionAndTransaction();

		// Utente user = session.createQuery("from Utente user LEFT JOIN FETCH
		// user.documenti WHERE user.idUtente="+idUser, Utente.class).getSingleResult();
		// user = session.createQuery("from Utente user LEFT JOIN FETCH user.committenti
		// WHERE user.idUtente="+idUser, Utente.class).getSingleResult();
		return user;
	}

	public List<Utente> getAllUsers() {
		List<Utente> users = session.createQuery("from Utente", Utente.class).getResultList();

		// List<Utente> users = (List<Utente>) session.find(Utente.class,Utente.class);
		/*for (int i = 0; i < users.size(); i++) {
			users.get(i).getCommittenti().size();
			for (int j = 0; j < users.get(i).getCommittenti().size(); j++) {
				users.get(i).getCommittenti().get(j).getDocumenti().size();
				users.get(i).getCommittenti().get(j).getRecapiti().size();
			}
			users.get(i).getDocumenti().size();
		}*/
		/*
		 * SessionImplementor sessioni = (SessionImplementor) sf.openSession(); Session
		 * s = (Session) sessioni; for(Object user: users){ PersistentCollection us =
		 * (PersistentCollection) user;
		 * 
		 * // first attach the collections owner to the new persistent context
		 * s.buildLockRequest(LockOptions.NONE).lock(us.getOwner()); // in Hibernate 3.5
		 * it may be replaced by: s.lock(coll.getOwner(), LockMode.NONE);
		 * 
		 * Hibernate.initialize(us); }
		 */

		/*
		 * this.closeTransaction(); for(int i = 0; i<users.size();i++) {
		 * Hibernate.initialize(users.get(i));
		 * Hibernate.initialize(users.get(i).getCommittenti());
		 * Hibernate.initialize(users.get(i).getDocumenti()); }
		 */
		/*
		 * List<Utente> users = session.
		 * createQuery("from Utente user LEFT JOIN FETCH user.documenti LEFT JOIN FETCH user.committenti"
		 * , Utente.class).getResultList() questo non è concesso...viene lanciato:
		 * MultipleBagFetchException: cannot simultaneously fetch multiple bags:
		 */;
		// facciamolo quindi uno alla volta
		// List<Utente> users = session.createQuery("from Utente user LEFT JOIN FETCH
		// user.documenti", Utente.class).getResultList();
		/*
		 * Collection users = new LinkedHashSet(
		 * session.createQuery("from Utente user LEFT JOIN FETCH user.documenti",
		 * Utente.class).getResultList()); System.out.println(users.size()); for(int i =
		 * 0; i < users.size(); i++)
		 * 
		 * { /*System.out.println("========================================");
		 * System.out.println("========================================");
		 * System.out.println("========================================");
		 * System.out.println("Utente numero: " + i);
		 * System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		 * System.out.println("Committenti prima: " + users.get(i).getCommittenti());
		 * System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		 */
		System.out.println("========================================");
		// FETCH
		// users.get(i).setCommittenti(session.createQuery("from Committente customer
		// LEFT JOIN FETCH customer.documenti", Committente.class).getResultList());
		System.out.println("========================================");
		/*
		 * System.out.println("Committenti medio: " + users.get(i).getCommittenti());
		 * System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		 */
		// users.get(i).setCommittenti(session.createQuery("from Committente customer
		// LEFT JOIN FETCH customer.recapiti", Committente.class).getResultList());
		System.out.println("========================================");
		// System.out.println("Committenti dopo: " + users.get(i).getCommittenti());
		// }
		return users;
	}

	public void updateUser(Utente user) {
		// this.getSessionAndTransaction();
		session.update(user);
		// this.closeSessionAndTransaction();
	}

	public void saveUser(Utente user) {
		// this.getSessionAndTransaction();
		session.save(user);
		// this.closeSessionAndTransaction();

	}

	public void deleteUser(Utente user) {
		// this.getSessionAndTransaction();
		session.remove(user);
		// this.getSessionAndTransaction();
	}
}
