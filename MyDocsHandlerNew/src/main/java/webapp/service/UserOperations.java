package webapp.service;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import webapp.model.committente.Committente;
import webapp.model.documento.Documento;
import webapp.model.recapito.Recapito;
import webapp.model.utente.Utente;

public interface UserOperations {
	
	public Utente checkUser(Utente user);
	public void setUserStatusAndSendEmail(Utente user);
	public Utente registerUser(int idUser);

	public ModelAndView registerDocumentAndCustomer(String operation, Recapito contact, Recapito contact1, Documento document, Committente customer, Utente user, ModelAndView model, HttpServletRequest request);
	public void uploadFile(byte[] bytes, HttpServletRequest request, String path);
	
}

