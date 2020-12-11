package webapp.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import webapp.dao.CommittenteDaoInterface;

import webapp.model.*;
import webapp.service.CustomerOperationsImpl;
import webapp.service.UserOperationsImpl;

@Controller
@RequestMapping("/fileupload")
public class FileUploadDownloadController {

	private UserOperationsImpl userOperationsImpl;
	private CustomerOperationsImpl customerOperationsImpl;

	private ApplicationContext context = new ClassPathXmlApplicationContext("services.xml", "daos.xml");
	private CommittenteDaoInterface committenteDao;
	
	public void setCommittenteDao(CommittenteDaoInterface committenteDao) {
		this.committenteDao = committenteDao;
	}

	public void setUserOperationsImpl(UserOperationsImpl userOperationsImpl) {
		this.userOperationsImpl = userOperationsImpl;
	}

	
	public void setCustomerOperationsImpl(CustomerOperationsImpl customerOperationsImpl) {
		this.customerOperationsImpl = customerOperationsImpl;
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
	public ModelAndView upload(@ModelAttribute("documento") Documento document,
			HttpServletRequest request) throws Exception {
		/*
		 * mancano: documento: percorso, dimensione, data, ora, idDocumento committente:
		 * idCommittente recapito: idRecapito
		 */
		
		ModelAndView model = new ModelAndView("HomePage");
		HttpSession appSession = request.getSession();
		Utente user = (Utente) appSession.getAttribute("user");
		System.out.println(user);
		Committente customer = (Committente) context.getBean("committente");
		
		/*---------------------------*/
		
		
		//verifica della presenza del committente 
		if((request.getParameter("selectRegisteredCustomer").equals("selectCustomer")))
		{
			customer = customerOperationsImpl.checkCfCustomer(request.getParameter("cf"));
			if(customer == null)
			{	//codice fiscale non corrispondente ad alcun committente 
				customer = (Committente) context.getBean("committente");
				customer.setCf(request.getParameter("cf"));
				model.setViewName("EditFilePage");
				model.addObject("document", document);
				model.addObject("newcustomer", customer);
				model.addObject("msg3", "Il committente associato a questo CF non si è ancora registrato");
				return model;
			}
			else if(Support.find(user.getIdUtente(),customer.getUtenti()))
			{
				customer = (Committente) context.getBean("committente");
				customer.setCf(request.getParameter("cf"));
				model.setViewName("EditFilePage");
				model.addObject("document", document);
				model.addObject("newcustomer", customer);
				model.addObject("msg2", "Questo committente è già presente nella lista dei committenti registrati");
				return model;
			}
		}
		//recupero dell'oggetto committente
		else
		{
			customer = customerOperationsImpl.retrieveCustomerById(Integer.parseInt(request.getParameter("selectRegisteredCustomer")));
		}
		
		//verifica della presenza di un file con lo stesso nome
		if(userOperationsImpl.getDocumentByName(document.getNome())!=null)
		{
			model.setViewName("EditFilePage");
			model.addObject("document", document);
			model.addObject("msg1", "File con questo nome già presente!");
			return model;
		}
		
		
		/*---------------------------*/
		
		
		
		
		
					
		List<Documento> documents = user.getDocumenti();//null se è il primo
		System.out.println("documenti associati all'utente:" + documents);
		System.out.println("committente:" + customer);
		// documento
		MultipartFile file = (MultipartFile) appSession.getAttribute("file");
		document.setPercorso("Storage/User-" + user.getIdUtente() + "/"
				+ Support.changeNameFileWithSameExtension(document.getNome(), file.getOriginalFilename()));
		document.setData(Support.getCurrentDate());
		document.setOra(Support.getCurrentTime());
		document.setDimensione(Integer.parseInt(String.valueOf(file.getSize())));
		document.setUtente(user);
		document.setIdDocumento(userOperationsImpl.getMaxIdDocumento()+1);

		
		System.out.println("documento:" + document);
		
		
		List<Utente> customerUsers = customer.getUtenti(); //null se è il primo
		if(customerUsers != null)
		{
			customerUsers.add(user);
		}
		else
		{	
			customerUsers = new ArrayList<Utente>();
			customerUsers.add(user);
		}
		customer.setUtenti(customerUsers); //quando il committente crea un nuovo profilo devo assicurarmi che 
		//le liste utente e documenti non siano null ma vuote
		System.out.println("lista degli utenti associati al committente: " + customer.getUtenti());
		
		
		List<Committente> userCustomers = user.getCommittenti(); //null se è il primo
		if(userCustomers != null)
		{
			if((request.getParameter("selectRegisteredCustomer").equals("selectCustomer")))
			userCustomers.add(customer);
		}
		else
		{	
			userCustomers = new ArrayList<Committente>();
			userCustomers.add(customer);
		}
		customer.setUtenti(customerUsers); //quando il committente crea un nuovo profilo devo assicurarmi che 
		//le liste utente e documenti non siano null ma vuote
		System.out.println("lista degli utenti associati al committente: " + customer.getUtenti());
		
		
		
		
		List<Documento> customerDocuments = customer.getDocumenti();
		customerDocuments.add(document);
		customer.setDocumenti(customerDocuments);
		
		document.setCommittente(customer);

		// questo metodo modifica l'oggetto ModelAndView se il committente inserito è
		// già registrato o se il nome del file è già stato usato
		// altrimenti vengono memorizzatii dati nel database
		model = userOperationsImpl.registerDocumentAndCustomer("register", document, customer, user, model,
				request);
		if (Support.isTheDocumentAlreadyHere(document.getNome(), documents)
				/*|| Support.isTheCustomerAlreadyHere(customers, request.getParameter("cf"))*/)
			return model;

		// permette di effettuare l'upload del file scelto dall'utente
		userOperationsImpl.uploadFile((byte[]) appSession.getAttribute("bytes"), request, document.getPercorso());
		

		user = userOperationsImpl.retrieveUpdatedUser(user.getIdUtente());
		model.addObject("user", user);
		appSession.setAttribute("user", user);
		/*List<Documento> sessionDocuments = (List<Documento>) appSession.getAttribute("documents");
		sessionDocuments.add(document);*/
		return model;

	}

	@RequestMapping("/download{idDocumento}")
	public ModelAndView download(@PathVariable int idDocumento, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView model = new ModelAndView("Homepage");

		HttpSession appSession = request.getSession();
		Utente user = (Utente) appSession.getAttribute("user");
		//Support.printAll(user);
		
		Documento doc = userOperationsImpl.getDocumentById(idDocumento);
		String absolutePath = System.getProperty("user.dir") + "/" + doc.getPercorso();
		userOperationsImpl.download(absolutePath, response, doc);
		
		return model;
	}

}
