package webapp.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import webapp.dao.CommittenteDao;
import webapp.dao.DocumentoDao;
import webapp.dao.RecapitoDao;
import webapp.model.committente.Committente;
import webapp.model.documento.Documento;
import webapp.model.recapito.Recapito;
import webapp.model.utente.Utente;
import webapp.service.UserOperationsImpl;

@Controller
@RequestMapping("/editFile")
public class EditFileController {

	private ApplicationContext context = new ClassPathXmlApplicationContext("services.xml", "daos.xml");

	private UserOperationsImpl userOperationsImpl;

	public void setUserOperationsImpl(UserOperationsImpl userOperationsImpl) {
		this.userOperationsImpl = userOperationsImpl;
	}

	@RequestMapping("/delete{idDocument}")
	public ModelAndView deleteFile(HttpServletRequest request, @PathVariable int idDocument) throws SecurityException,
			RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
		ModelAndView model = new ModelAndView("HomePage");
		HttpSession appSession = request.getSession();
		Utente user = (Utente) appSession.getAttribute("user");
		Documento doc = Support.getDocumentById(user.getDocumenti(), idDocument);
		String absolutePath = System.getProperty("user.dir") + "/" + doc.getPercorso();

		// eliminazione del record nel DB
		userOperationsImpl.delete(doc, user);
		
		// recupero dell'utente e soprattutto delle liste di documenti e di committenti
		// aggiornate.
		user = userOperationsImpl.retrieveUpdatedUser(user.getIdUtente());
		model.addObject("user", user);
		
		// eliminazione del file nella cartella di storage
		userOperationsImpl.deleteFile(absolutePath);

		appSession.setAttribute("user", user);
		List<Documento> documents = (List<Documento>) appSession.getAttribute("documents");
		documents.remove(doc);
		return model;

	}

	@RequestMapping("/showDetails{idDocument}")
	public ModelAndView showDetailsFile(HttpServletRequest request, @PathVariable int idDocument) {
		ModelAndView model = new ModelAndView("DetailsFilePage");
		HttpSession appSession = request.getSession();
		Utente user = (Utente) appSession.getAttribute("user");
		Documento doc = Support.getDocumentById(user.getDocumenti(), idDocument);
		model.addObject("doc", doc);
		return model;
	}

	@RequestMapping("/catchIdDoc{idDocument}")
	public ModelAndView catchIdDoc(@PathVariable int idDocument, HttpServletRequest request) {
		ModelAndView model = new ModelAndView("EditFilePage");
		HttpSession session = request.getSession();
		Utente user = (Utente) session.getAttribute("user");
		List<Documento> documents = user.getDocumenti();
		Documento document = Support.getDocumentById(documents, idDocument);
		model.addObject("document", document);
		model.addObject("registeredCustomer", document.getCommittente());
		model.addObject("idDoc", idDocument);
		model.addObject("operation", "editing");
		return model;
	}

	@RequestMapping("/edit{idDocument}")
	public ModelAndView editFile(@PathVariable int idDocument, @ModelAttribute("documento") Documento newdocument,
			@ModelAttribute("recapito") Recapito contact, @ModelAttribute("committente") Committente customer, HttpServletRequest request) throws SecurityException,
			RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
		ModelAndView model = new ModelAndView("HomePage");
		HttpSession appSession = request.getSession();
		Utente user = (Utente) appSession.getAttribute("user");
		Recapito contact1 = (Recapito) context.getBean("recapito");
		List<Committente> customers = user.getCommittenti();
		List<Documento> documents = user.getDocumenti();
		System.out.println(documents);
		Documento olddocument = Support.getDocumentById(documents, idDocument);
		
		
		String curDir = System.getProperty("user.dir"); // cartella corrente

		System.out.println("olddocument:" + olddocument);
		System.out.println("newdocument:" + newdocument);
		
		newdocument.setPercorso("Storage/User-" + user.getIdUtente() + "/"
				+ Support.changeNameFileWithSameExtension(newdocument.getNomefile(), olddocument.getPercorso()));
		newdocument.setData(Support.getCurrentDate());
		newdocument.setOra(Support.getCurrentTime());
		newdocument.setDimensione(olddocument.getDimensione());
		newdocument.setIdDocumento(olddocument.getIdDocumento());
		newdocument.setUtente(user);
		
		// rinomino il file
		userOperationsImpl.renameFile(curDir, newdocument.getPercorso(), olddocument.getPercorso());

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
			customer.getDocumenti().add(newdocument);
			System.out.println("è stato selezionato un committente registratyo: " + customer);
			System.out.println(customer.getDocumenti().size()); // fin qui dovrebbe essere giusto
		} else {

			customer.setIdCommittente(Support.nextIdCustomer(user));
			customer.setUtente(user);

			// se il committente non è registrato, allora avrà solamente un documento
			// associato.
			List<Documento> list = new ArrayList<Documento>();
			list.add(newdocument);
			customer.setDocumenti(list);

		}
		newdocument.setCommittente(customer);


		//va preventivamente cancellato il document dalla lista dei documenti associati all'utente, altrimenti 
		//la verifica della presenza di un documento con lo stesso nome viene falsata se il client non modifica il nome
		//se documento.
		System.out.println("Dimensione lista documenti prima: " + user.getDocumenti().size());
		user.getDocumenti().remove(olddocument);
		System.out.println("Dimensione lista documenti dopo: " + user.getDocumenti().size());
		//user.setDocumenti(user.getDocumenti().remove(olddocument));
		model = userOperationsImpl.registerDocumentAndCustomer("edit", contact, contact1, newdocument, customer, user, model,
				request);
		if (Support.isTheDocumentAlreadyHere(newdocument.getNomefile(), documents)
				|| Support.isTheCustomerAlreadyHere(customers, request.getParameter("cf")))
		{
			
			user = userOperationsImpl.retrieveUpdatedUser(user.getIdUtente());
			appSession.setAttribute("user", user);
			model.addObject("user", user);
			model.addObject("registeredCustomer", newdocument.getCommittente());
			model.addObject("idDoc", idDocument);
			model.addObject("operation", "editing");
			return model;
			//return catchIdDoc(idDocument, request);
		}
		
		user = userOperationsImpl.retrieveUpdatedUser(user.getIdUtente());
		model.addObject("user", user);
		appSession.setAttribute("user", user);
		List<Documento> sessionDocuments = (List<Documento>) appSession.getAttribute("documents");
		sessionDocuments.add(newdocument);
		return model;

	}

}
