package com.webapp.controller;

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

import com.webapp.dao.UtenteDao;
import com.webapp.model.documento.Documento;
import com.webapp.model.utente.Utente;

@Controller
public class RegistrationController {
	
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private SessionFactory sf;
	
	private ApplicationContext context = new ClassPathXmlApplicationContext("services.xml", "daos.xml");
	
	
	@RequestMapping("/goToRegistrationForm.html")
	public ModelAndView goToRegistrationForm() {
		ModelAndView model = new ModelAndView("Registration");
		return model;
	}

	@RequestMapping("/RegistrationForm.html")
		public ModelAndView checkCredentials(@ModelAttribute("utente") Utente user, BindingResult result) throws SecurityException, RollbackException,
			HeuristicMixedException, HeuristicRollbackException, SystemException {
		
		ModelAndView model = new ModelAndView("HomePage");
		/*if(result.hasErrors())
		{	model.setViewName("Registration");
			return model;
		}*/
		// creazione dell'istanza di ApplicationContext per la DI
		// ApplicationContext context = new ClassPathXmlApplicationContext(new String[]
		// { "services.xml", "daos.xml" });

		//ilseguente oggetto non lo uso. è solo per fa capire che la DI funziona
		//anche se in questo esempio specifico non ha molto senso, in quanto se modifico il 
		//nome della classe User sono costratto a modificare il tipo di questo oggetto.
		//per questo motivo sarebbe opportuno usare, come tipo, una interfaccia
		//che viene opportunamente estesa da User. 
		System.out.println("Assegnazione corretta");
		
		// creazione dell'istanza
		//Configuration con = new Configuration();
		//con.configure("hibernate.cfg.xml");
		//con.addAnnotatedClass(User.class);
		//SessionFactory sf = con.buildSessionFactory();
		Session session = sf.openSession();

		// appSession.setAttribute("context", context);
		Transaction tx = session.beginTransaction();
		UtenteDao.setSession(session);

		List<Utente> users = UtenteDao.getAllUsers();

		//test
		System.out.println(user);

		if (Support.isHere(users, user)) {// utente già registrato
			model.setViewName("Registration");
			user = Support.getUserByEmail(users, user.getEmail());
			model.addObject("user", user);
			if (user.getStato().equals("pendingRegistration"))
				model.addObject("msg", "Utente in attesa di registrazione");
			else
			{
				model.addObject("msg", "Utente già registrato");
			}
				
			return model;
		}

		// invio della mail all'indirizzo inserito
		int idUser = Support.nextIdUser(users);// id dell'utente da registrare
		String email = user.getEmail();
		String subject = "Registrazione Account - invio email automatica";
		String text = "Per registrare il tuo account clicca sul seguente link: "
				+ "http://localhost:8080/MyDocsHandler/RegisterUser" + idUser;


		SimpleMailMessage simpleMessage = new SimpleMailMessage();
		simpleMessage.setTo(user.getEmail());
		simpleMessage.setSubject(subject);
		simpleMessage.setText(text);
		try {
			mailSender.send(simpleMessage);
		} catch (MailSendException me) {
			Support.detectInvalidAddress(me, model);
			return model;
		}
		
		user.setStato("pendingRegistration");
		//salvataggio nella base di dati
		UtenteDao.saveUser(user);
		tx.commit();

		
		model.setViewName("EmailDeliveryWarningPage");
		model.addObject("email", user.getEmail());
		
		//sf.close();
		return model;
	}

	@RequestMapping("/RegisterUser{idUser}")
	public ModelAndView registerUser(@PathVariable int idUser, HttpServletRequest request) throws SecurityException,
			RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
		// nb: idUser è l'id dell'utente che dovrà essere registrato

		// creazione dell'istanza
		//Configuration con = new Configuration();
		//con.configure("hibernate.cfg.xml");
		//con.addAnnotatedClass(User.class);
		//SessionFactory sf = con.buildSessionFactory();
		Session session = sf.openSession();

		ModelAndView model = new ModelAndView("CorrectRegistrationWarningPage");
		// memorizzo all'interno dell'oggetto HttpSession tutti i dati utili durante la
		// sessione per la DI e la gestione delle transazioni con hibernate
		HttpSession appSession = request.getSession();
		appSession.setAttribute("session", session);
		// Se arrivo qui vuol dire che l'utente ha cliccato sul link che gli
		// è arrivato. A questo punto cancello il record in WaitinUser
		// e lo salvo in RegisteredUser

		// vediamo se l'oggetto di tipo SessionFactory è ancora disponibile
		// nell'oggetto session
		Transaction tx = session.beginTransaction();
		UtenteDao.setSession(session);
		Utente user = UtenteDao.getUser(idUser);
		
		//test
		System.out.println("isUtente: " + idUser);
		System.out.println(user.toString());
		
		if (user.getStato().equals("pendingRegistration")) {
			user.setStato("Registered");
			UtenteDao.saveUser(user);
			tx.commit();
		} else {
			model.setViewName("AlreadyRegisteredUserWarningPage");
		}
		
	//creazione della cartella di archiviazione
		String curDir = System.getProperty("user.dir");
        System.out.println("\nIn questo momento ti trovi nella directory:" + " - " + curDir);
        
        
		String generalFolderPath = "Storage/User-";
		String thisFolderPath = generalFolderPath + user.getIdUtente();
		File storageFolder = new File(thisFolderPath);
	    storageFolder.mkdir();
		System.out.println("Cartelle generate");
	    
		List<Documento> documents = new ArrayList<Documento>();
		documents.addAll(user.getDocumenti());
		appSession.setAttribute("documents", documents);
		appSession.setAttribute("user", user);
		model.addObject("destination", "homepage");
		return model;
	}

	

	
	
	
	
	
	
}
