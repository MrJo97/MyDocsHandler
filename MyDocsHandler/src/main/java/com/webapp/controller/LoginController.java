package com.webapp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.webapp.dao.UtenteDao;
import com.webapp.model.documento.Documento;
import com.webapp.model.utente.Utente;
@Controller
public class LoginController {
	
	@Autowired
	private SessionFactory sf;
	
	@RequestMapping("/goToLoginForm.html")
	public ModelAndView goToLoginForm() {
		ModelAndView model = new ModelAndView("Login");
		return model;
	}
	
	

	@RequestMapping("/LoginForm.html")
	public ModelAndView loginUser(@ModelAttribute("user") Utente user, HttpServletRequest request) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException
	{
		ModelAndView model = new ModelAndView("HomePage");
		Session session = sf.openSession();	
		UtenteDao.setSession(session);
		List<Utente> users = UtenteDao.getAllUsers();
		Utente registeredUser = Support.correspondingUser(users, user);
		if(registeredUser == null)
		{
			model.setViewName("Login");
			model.addObject("msg", "Credenziali non valide");
			return model;
		}
		
		//NB: con le relazioni tra entità che abbiamo impostato, questo oggetto contiene 
		//tutte le liste di  committenti, documenti e ultime modifiche associate. 
		user = registeredUser;
		
		//memorizzo tale oggetto all'interno della session:
		HttpSession appSession = request.getSession();
		appSession.setAttribute("user", user);  //Utente è una classe Singleton
		appSession.setAttribute("session", session);
		List<Documento> documents = new ArrayList<Documento>();
		documents.addAll(user.getDocumenti());
		appSession.setAttribute("documents", documents);
		model.addObject("destination", "homepage");
		return model;
	}
	
	@RequestMapping("/logout")
	public ModelAndView logoutUser(HttpServletRequest request) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException
	{
		ModelAndView model = new ModelAndView("Login");
		HttpSession appSession = request.getSession();
		appSession.removeAttribute("user");
		Session session = (Session) appSession.getAttribute("session");
		session.close();
		return model;
	}
	

}


