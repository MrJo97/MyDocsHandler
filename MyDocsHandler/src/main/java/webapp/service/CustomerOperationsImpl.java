package webapp.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import webapp.controller.Support;
import webapp.dao.AggiornamentoCommittenteDaoImpl;
import webapp.dao.CommittenteDaoImpl;
import webapp.dao.DocumentoDaoImpl;
import webapp.dao.UtenteDaoImpl;
import webapp.model.Committente;
import webapp.model.AggiornamentoCommittente;
import webapp.model.Documento;
import webapp.model.Utente;

public class CustomerOperationsImpl implements CustomerOperationsInterface {
	
	private JavaMailSender mailSender;
	private UtenteDaoImpl utenteDao;
	private DocumentoDaoImpl documentoDao;
	private CommittenteDaoImpl committenteDao;
	private AggiornamentoCommittenteDaoImpl aggiornamentoCommittenteDao;
	
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void setUtenteDao(UtenteDaoImpl utenteDao) {
		this.utenteDao = utenteDao;
	}

	public void setDocumentoDao(DocumentoDaoImpl documentoDao) {
		this.documentoDao = documentoDao;
	}

	public void setCommittenteDao(CommittenteDaoImpl committenteDao) {
		this.committenteDao = committenteDao;
	}
	
	public void setAggiornamentoCommittenteDao(AggiornamentoCommittenteDaoImpl aggiornamentoCommittenteDao) {
		this.aggiornamentoCommittenteDao = aggiornamentoCommittenteDao;
	}

	public Committente checkCfCustomer(String cf) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
		
		committenteDao.getSession();
		committenteDao.getTransaction();
		Committente registeredCustomer = committenteDao.getCustomerByCf(cf);
		committenteDao.closeTransaction();
		return registeredCustomer;
	}
	
	public Committente checkUsernameCustomer(Committente customer) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
		
		committenteDao.getSession();
		committenteDao.getTransaction();
		Committente registeredCustomer = committenteDao.getCustomerByUsername(customer.getUsername());
		committenteDao.closeTransaction();
		
		if (registeredCustomer != null)
			System.out.println(registeredCustomer.getUtenti());
		return registeredCustomer;
	}
	
	
	public void setCustomerStatusAndSendEmail(Committente customer) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
		committenteDao.getSession();
		committenteDao.getTransaction();
		//List<Committente> customers = committenteDao.getAllCustomers();
		//sql: SELECT MAX(idCommittente) from Committente
		//int idCustomer = Support.nextIdUser(customers);// id del committente da registrare
		int idCustomer = committenteDao.findMaxId()+1;
		String email = customer.getEmail();
		String subject = "MyDocsHandler - Registrazione Account - invio email automatica";
		String text = "Per registrare il tuo account clicca sul seguente link: "
				+ "http://localhost:8080/MyDocsHandler/RegisterCustomer" + idCustomer;
		this.sendEmail(email, subject, text);
		customer.setStato("in attesa");
		customer.setIdCommittente(idCustomer);
		
		customer.setDocumenti((List<Documento>) new ArrayList<Documento>());
		customer.setUtenti((List<Utente>) new ArrayList<Utente>());
		
		System.out.println(customer);

		committenteDao.saveCustomer(customer);
		committenteDao.closeTransaction();
	}
	
	public Committente registerCustomer(int idCustomer) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
		Committente newCustomer = null;
		committenteDao.getSession();
		committenteDao.getTransaction();
		Committente customer = committenteDao.getCustomerById(idCustomer);
		if (customer.getStato().equals("in attesa")) {
			customer.setStato("registrato");
			committenteDao.updateCustomer(customer);
			newCustomer = customer;
		}
		committenteDao.closeTransaction();

		return newCustomer;
	}
	
	public Committente getCustomerById(int idCustomer) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException
	{
		committenteDao.getSession();
		committenteDao.getTransaction();
		Committente customer = committenteDao.getCustomerById(idCustomer);
		committenteDao.closeTransaction();
		return customer;
	}
	
	public void sendEmail(String email, String subject, String text) {
		SimpleMailMessage simpleMessage = new SimpleMailMessage();
		simpleMessage.setTo(email);
		simpleMessage.setSubject(subject);
		simpleMessage.setText(text);
		mailSender.send(simpleMessage);
	}

	
	public AggiornamentoCommittente getUpdatingCustomerByCf(AggiornamentoCommittente customer) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
		aggiornamentoCommittenteDao.getSession();
		aggiornamentoCommittenteDao.getTransaction();
		AggiornamentoCommittente updatingCustomer = aggiornamentoCommittenteDao.getUpdatingCustomerByCf(customer.getCf());
		aggiornamentoCommittenteDao.closeTransaction();
		return updatingCustomer;
	}

	public void saveUpdatingCustomer(AggiornamentoCommittente updatingCustomer) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
		aggiornamentoCommittenteDao.getSession();
		aggiornamentoCommittenteDao.getTransaction();
		
		//se modifiche già presenti => sovrascrivo le modifiche
		if(aggiornamentoCommittenteDao.getUpdatingCustomerByFk(updatingCustomer.getCommittente().getIdCommittente()) != null)
		{
			aggiornamentoCommittenteDao.updateCustomer(updatingCustomer);
		}
		//altrimenti le salvo semplicemente 
		else
		{
			aggiornamentoCommittenteDao.saveCustomer(updatingCustomer);
		}
		
		aggiornamentoCommittenteDao.closeTransaction();
	
	}
	
	public Committente updateUpdatingCustomer(int idCustomer) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException
	{
		//aggiornamento del commitente
		aggiornamentoCommittenteDao.getSession();
		aggiornamentoCommittenteDao.getTransaction();
		AggiornamentoCommittente updatingCustomer = aggiornamentoCommittenteDao.getUpdatingCustomerByFk(idCustomer);
		aggiornamentoCommittenteDao.closeTransaction();		
		
		committenteDao.getSession();
		committenteDao.getTransaction();
		Committente customer = committenteDao.getCustomerById(idCustomer);
		customer.setCf(updatingCustomer.getCf());
		customer.setCognome(updatingCustomer.getCognome());
		customer.setNome(updatingCustomer.getNome());
		customer.setEmail(updatingCustomer.getEmail());
		customer.setTelefono(updatingCustomer.getTelefono());
		committenteDao.updateCustomer(customer);
		committenteDao.closeTransaction();	
		
		//rimozione del record nella tabella Aggionamento_committente
		aggiornamentoCommittenteDao.getSession();
		aggiornamentoCommittenteDao.getTransaction();
		updatingCustomer.setCommittente(customer);
		aggiornamentoCommittenteDao.deleteCustomer(updatingCustomer);
		aggiornamentoCommittenteDao.closeTransaction();
		
		return customer;
	}

}
