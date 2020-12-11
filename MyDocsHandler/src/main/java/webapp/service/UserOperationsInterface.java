package webapp.service;



import javax.servlet.http.HttpServletRequest;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import org.springframework.web.servlet.ModelAndView;

import webapp.model.Committente;
import webapp.model.Documento;
import webapp.model.Utente;

public interface UserOperationsInterface {
	
	public Utente checkUser(Utente user) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException;
	public void setUserStatusAndSendEmail(Utente user) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException;
	public Utente registerUser(int idUser) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException;

	public ModelAndView registerDocumentAndCustomer(String operation, Documento document, Committente customer, Utente user, ModelAndView model, HttpServletRequest request) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException;
	public void uploadFile(byte[] bytes, HttpServletRequest request, String path);
	
}

