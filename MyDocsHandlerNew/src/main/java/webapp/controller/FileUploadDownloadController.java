package webapp.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import webapp.dao.CommittenteDao;
import webapp.dao.DocumentoDao;
import webapp.dao.RecapitoDao;
import webapp.dao.UtenteDao;
import webapp.model.committente.*;
import webapp.model.documento.*;
import webapp.model.recapito.Recapito;
import webapp.model.utente.Utente;
import webapp.service.UserOperationsImpl;

@Controller
@RequestMapping("/fileupload")
public class FileUploadDownloadController {

	private UserOperationsImpl userOperationsImpl;

	private ApplicationContext context = new ClassPathXmlApplicationContext("services.xml", "daos.xml");

	public void setUserOperationsImpl(UserOperationsImpl userOperationsImpl) {
		this.userOperationsImpl = userOperationsImpl;
	}

	@RequestMapping("/checkFile")
	public ModelAndView checkUploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request)
			throws IOException {
		ModelAndView model = new ModelAndView("HomePage");
		long fileSize = file.getSize();
		long maxFileSize = 2000000;
		Documento document = (Documento) context.getBean("documento");
		//NB: Utente ha scope session quindi memorizza tutti i documenti finora caricati. 
		//Utente user = (Utente) context.getBean("utente"); 
		// error: dimensione del file eccessiva:
		if (fileSize > maxFileSize) {
			model.addObject("msg", "Le dimensioni di questo file superano quelle consentite(2MB)!");
			return model;
		}

		HttpSession appSession = request.getSession();
		Utente user = (Utente) appSession.getAttribute("user");
		appSession.setAttribute("file", file);
		appSession.setAttribute("bytes", file.getBytes());
		model.addObject("doc", document);
		model.addObject("user", user);
		model.addObject("operation", "loading");
		model.setViewName("EditFilePage");
		
		return model;
	}

	@RequestMapping("/upload")
	public ModelAndView upload(@ModelAttribute("recapito") Recapito contact,
			@ModelAttribute("committente") Committente customer, @ModelAttribute("documento") Documento document,
			HttpServletRequest request) throws Exception {
		/*
		 * mancano: documento: percorso, dimensione, data, ora, idDocumento committente:
		 * idCommittente recapito: idRecapito
		 */
		
		ModelAndView model = new ModelAndView("HomePage");
		HttpSession appSession = request.getSession();
		Utente user = (Utente) appSession.getAttribute("user");
		System.out.println(user);
		Recapito contact1 = (Recapito) context.getBean("recapito");
		List<Committente> customers = user.getCommittenti();
		List<Documento> documents = user.getDocumenti();

		// documento
		MultipartFile file = (MultipartFile) appSession.getAttribute("file");
		document.setPercorso("Storage/User-" + user.getIdUtente() + "/"
				+ Support.changeNameFileWithSameExtension(document.getNomefile(), file.getOriginalFilename()));
		document.setData(Support.getCurrentDate());
		document.setOra(Support.getCurrentTime());
		document.setDimensione(String.valueOf(file.getSize()));
		document.setIdDocumento(Support.nextIdDocument(user));
		document.setUtente(user);

		// recapiti
		contact.setCommittente(customer);
		contact1.setTelefono(request.getParameter("telefono1"));
		contact1.setEmail(request.getParameter("email1"));
		contact1.setCommittente(customer);
		List<Recapito> contacts = new ArrayList<Recapito>();
		contacts.add(contact);
		contacts.add(contact1);

		// Committente
		customer.setRecapiti(contacts);
		
		if (!(request.getParameter("selectRegisteredCustomer").equals("selectCustomer"))) {// è stato selezionato un
																							// committente già
																							// registrato
			customer = Support.getCustomerById(customers,
					Integer.parseInt(request.getParameter("selectRegisteredCustomer")));
			customer.getDocumenti().add(document);
			System.out.println("è stato selezionato un committente registratyo: "+ customer);
			System.out.println(customer.getDocumenti().size()); //fin qui dovrebbe essere giusto
		} else {
			
			customer.setIdCommittente(Support.nextIdCustomer(user));
			customer.setUtente(user);
			
			//se il committente non è registrato, allora avrà solamente un documento associato.
			List<Documento> list = new ArrayList<Documento>();
			list.add(document);
			customer.setDocumenti(list);

		}
		document.setCommittente(customer);

		// questo metodo modifica l'oggetto ModelAndView se il committente inserito è
		// già registrato o se il nome del file è già stato usato
		// altrimenti vengono memorizzatii dati nel database
		model = userOperationsImpl.registerDocumentAndCustomer("register",contact, contact1, document, customer, user, model,
				request);
		if (Support.isTheDocumentAlreadyHere(document.getNomefile(), documents)
				|| Support.isTheCustomerAlreadyHere(customers, request.getParameter("cf")))
			return model;

		// permette di effettuare l'upload del file scelto dall'utente
		userOperationsImpl.uploadFile((byte[]) appSession.getAttribute("bytes"), request, document.getPercorso());
		

		user = userOperationsImpl.retrieveUpdatedUser(user.getIdUtente());
		model.addObject("user", user);
		appSession.setAttribute("user", user);
		List<Documento> sessionDocuments = (List<Documento>) appSession.getAttribute("documents");
		sessionDocuments.add(document);
		return model;

	}

	@RequestMapping("/download{idDocument}")
	public ModelAndView download(@PathVariable int idDocument, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView model = new ModelAndView("Homepage");

		HttpSession appSession = request.getSession();
		Utente user = (Utente) appSession.getAttribute("user");
		Support.printAll(user);
		Documento doc = (Support.getDocumentById(user.getDocumenti(), idDocument));
		String absolutePath = System.getProperty("user.dir") + "/" + doc.getPercorso();
		userOperationsImpl.download(absolutePath, response, doc);
		
		return model;
	}

}
