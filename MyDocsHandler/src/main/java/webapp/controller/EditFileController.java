package webapp.controller;


import java.io.File;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


//import webapp.dao.DocumentoDaoImpl;
import webapp.model.Committente;
import webapp.model.Documento;
import webapp.model.Utente;
import webapp.service.CustomerOperationsImpl;
import webapp.service.UserOperationsImpl;

@Controller
@RequestMapping("/editFile")
public class EditFileController {

	private ApplicationContext context = new ClassPathXmlApplicationContext("services.xml", "daos.xml");

	private UserOperationsImpl userOperationsImpl;
	private CustomerOperationsImpl customerOperationsImpl;
	//private DocumentoDaoImpl documentoDao;
	//private CommittenteDaoImpl committenteDao;

	
	public void setUserOperationsImpl(UserOperationsImpl userOperationsImpl) {
		this.userOperationsImpl = userOperationsImpl;
	}
	
	public void setCustomerOperationsImpl(CustomerOperationsImpl customerOperationsImpl) {
		this.customerOperationsImpl = customerOperationsImpl;
	}

	@RequestMapping("/delete{idDocumento}")
	public ModelAndView deleteFile(HttpServletRequest request, @PathVariable int idDocumento) throws SecurityException,
			RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
		ModelAndView model = new ModelAndView("HomePage");
		HttpSession appSession = request.getSession();
		Utente user = (Utente) appSession.getAttribute("user");
		Documento doc = userOperationsImpl.getDocumentById(idDocumento);
		String absolutePath = System.getProperty("user.dir") + "/" + doc.getPercorso();

		// eliminazione del record nel DB
		userOperationsImpl.delete(doc, user);
		
		// recupero dell'utente e soprattutto delle liste di documenti e di committenti
		// aggiornate.
		user = userOperationsImpl.retrieveUpdatedUser(user.getIdUtente());
		model.addObject("user", user);
		
		// eliminazione del file nella cartella di storage
		//userOperationsImpl.deleteFile(absolutePath);
		File file = new File(absolutePath);
		file.delete();

		appSession.setAttribute("user", user);
		/*List<Documento> documents = (List<Documento>) appSession.getAttribute("documents");
		documents.remove(doc);*/
		return model;
	}

	@RequestMapping("/showDetails{idDocumento}")
	public ModelAndView showDetailsFile(HttpServletRequest request, @PathVariable int idDocumento) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
		ModelAndView model = new ModelAndView("DetailsFilePage");
		Documento doc = userOperationsImpl.getDocumentById(idDocumento);
				//documentoDao.getDocumentById(idDocumento);
		model.addObject("doc", doc);
		return model;
	}

	@RequestMapping("/catchIdDoc{idDocumento}")
	public ModelAndView catchIdDoc(@PathVariable int idDocumento, HttpServletRequest request) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
		ModelAndView model = new ModelAndView("EditFilePage");
		Documento document = userOperationsImpl.getDocumentById(idDocumento);
		model.addObject("document", document);
		model.addObject("registeredCustomer", document.getCommittente());
		model.addObject("idDoc", idDocumento);
		model.addObject("operation", "editing");
		return model;
	}

	@RequestMapping("/edit{idDocumento}")
	public ModelAndView editFile(@PathVariable int idDocumento, @ModelAttribute("documento") Documento newdocument,
			HttpServletRequest request) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException, ParseException {
		ModelAndView model = new ModelAndView("HomePage");
		HttpSession appSession = request.getSession();
		Utente user = (Utente) appSession.getAttribute("user");
		
		
		/*-------------stesso codice presente anche in UploadDownloadController al metodo upload--------------*/
		
		Committente customer = (Committente) context.getBean("committente");
		//verifica della presenza del committente 
		if((request.getParameter("selectRegisteredCustomer").equals("selectCustomer")))
		{
			customer = customerOperationsImpl.getCustomerByCf(request.getParameter("cf"));
			if(customer == null)
			{	//codice fiscale non corrispondente ad alcun committente 
				customer = (Committente) context.getBean("committente");
				customer.setCf(request.getParameter("cf"));
				model.setViewName("EditFilePage");
				model.addObject("document", newdocument);
				model.addObject("newcustomer", customer);
				model.addObject("operation", "editing");
				model.addObject("msg3", "Il committente associato a questo CF non si è ancora registrato");
				return model;
			}
			else if(Support.find(user.getIdUtente(),customer.getUtenti()))
			{
				customer = (Committente) context.getBean("committente");
				customer.setCf(request.getParameter("cf"));
				model.setViewName("EditFilePage");
				model.addObject("document", newdocument);
				model.addObject("newcustomer", customer);
				model.addObject("operation", "editing");
				model.addObject("msg2", "Questo committente è già presente nella lista dei committenti registrati");
				return model;
			}
		}
		
		//recupero dell'oggetto committente
				else
				{
					customer = customerOperationsImpl.getCustomerById(Integer.parseInt(request.getParameter("selectRegisteredCustomer")));
				}
				
				//verifica della presenza di un file con lo stesso nome
				if(userOperationsImpl.getDocumentByName(newdocument.getNome())!=null)
				{
					if(userOperationsImpl.getDocumentByName(newdocument.getNome()).getIdDocumento() != idDocumento)
					{
					model.setViewName("EditFilePage");
					model.addObject("document", newdocument);
					model.addObject("msg1", "File con questo nome già presente!");
					model.addObject("operation", "editing");
					return model;
					}
				}
				
				
				/*---------------------------*/
		
		
				
		
		
		
		
		/**restituisce null se non lo trova o se si verifica qualche problema
		Committente customer = committenteDao.getCustomerByCf(request.getParameter("cf"));
		/**/
		//List<Committente> customers = user.getCommittenti();
		/*List<Documento> documents = user.getDocumenti();
		System.out.println(documents);*/
		Documento olddocument = userOperationsImpl.getDocumentById(idDocumento);
		
		
		String curDir = System.getProperty("user.dir"); // cartella corrente

		System.out.println("olddocument:" + olddocument);
		System.out.println("newdocument:" + newdocument);
		
		newdocument.setPercorso("Storage/User-" + user.getIdUtente() + "/"
				+ Support.changeNameFileWithSameExtension(newdocument.getNome(), olddocument.getPercorso()));
		newdocument.setData(Support.getCurrentDate());
		newdocument.setOra(Support.getCurrentTime());
		newdocument.setDimensione(olddocument.getDimensione());
		newdocument.setIdDocumento(olddocument.getIdDocumento());		
		newdocument.setCommittente(customer);
		newdocument.setUtente(user);
		
		// rinomino il file  posso creare un metodo del tipo userOperationsImpl.uploadDocument()
		//userOperationsImpl.renameFile(curDir, newdocument.getPercorso(), olddocument.getPercorso());
		File f1 = new File(curDir + "/" + olddocument.getPercorso());
		File f2 = new File(curDir + "/" + newdocument.getPercorso());
		f1.renameTo(f2);
		userOperationsImpl.updateDocument(newdocument);
		//----------------//						
		user = userOperationsImpl.retrieveUpdatedUser(user.getIdUtente());
		model.addObject("user", user);
		appSession.setAttribute("user", user);
		return model;

	}

}
