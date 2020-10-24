package webapp.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.mail.SendFailedException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.MailSendException;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import webapp.dao.UtenteDao;
import webapp.model.documento.Documento;
import webapp.model.utente.Utente;
import webapp.service.UserOperationsImpl;

@Controller
public class RegistrationController {
	

	private UserOperationsImpl userOperationsImpl;	
	
	//setter methods for setter injection
	public void setUserOperationsImpl(UserOperationsImpl userOperationsImpl) {
		this.userOperationsImpl = userOperationsImpl;
	}

	
	
	@RequestMapping("/goToRegistrationForm.html")
	public ModelAndView goToRegistrationForm() {
		ModelAndView model = new ModelAndView("Registration");
		return model;
	}

	@RequestMapping("/RegistrationForm.html")
		public ModelAndView checkCredentials(@ModelAttribute("utente") Utente user, BindingResult result) 
	{	
		System.out.println(user);
		ModelAndView model = new ModelAndView("HomePage"); 
		System.out.println(userOperationsImpl.checkUser(user));
		if (userOperationsImpl.checkUser(user) != null) 
		{// utente già registrato
			model.setViewName("Registration");
			model.addObject("user", user);
			if (userOperationsImpl.checkUser(user).getStato().equals("pendingRegistration"))
				model.addObject("msg", "Utente in attesa di registrazione");
			else
			{
				model.addObject("msg", "Utente già registrato");
			}
				
			return model;
		}
		else
		{
			try 
			{
				userOperationsImpl.setUserStatusAndSendEmail(user);
			} 
			catch (MailSendException me) 
			{
				Support.detectInvalidAddress(me, model);
				return model;
			}
		
			model.setViewName("EmailDeliveryWarningPage");
			model.addObject("email", user.getEmail());
			return model;
		}
	}

	@RequestMapping("/RegisterUser{idUser}")
	public ModelAndView registerUser(@PathVariable int idUser, HttpServletRequest request) throws SecurityException,
			RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
		
		ModelAndView model = new ModelAndView("CorrectRegistrationWarningPage");
		HttpSession appSession = request.getSession();
		Utente user = userOperationsImpl.registerUser(idUser);
		if(user== null)
		{
			model.setViewName("AlreadyRegisteredUserWarningPage");
			return model;
		}	    
		List<Documento> documents = new ArrayList<Documento>();
		System.out.println(user.getCommittenti());
		documents.addAll(user.getDocumenti());
		appSession.setAttribute("documents", documents);
		appSession.setAttribute("user", user);
		model.addObject("destination", "homepage");
		model.addObject("user", user);
		return model;
	}	
}
