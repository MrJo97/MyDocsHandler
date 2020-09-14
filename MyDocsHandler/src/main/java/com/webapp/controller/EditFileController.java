package com.webapp.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.webapp.dao.CommittenteDao;
import com.webapp.dao.DocumentoDao;
import com.webapp.dao.RecapitoDao;
import com.webapp.model.committente.Committente;
import com.webapp.model.documento.Documento;
import com.webapp.model.recapito.Recapito;
import com.webapp.model.utente.Utente;

@Controller
@RequestMapping("/editFile")
public class EditFileController {
	
	private ApplicationContext context = new ClassPathXmlApplicationContext("services.xml", "daos.xml");
	
	@RequestMapping("/delete{idDocument}")
	public ModelAndView deleteFile(HttpServletRequest request, @PathVariable int idDocument) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException
	{
		ModelAndView model = new ModelAndView("HomePage");
		HttpSession appSession = request.getSession();
		
		//recupero dell'oggetto Session per hibernate ed inizio della transazione
		Session session = (Session) appSession.getAttribute("session");
		Transaction tx = session.beginTransaction();
		Utente user = (Utente) appSession.getAttribute("user");
		Documento doc = Support.getDocumentById(user.getDocumenti(), idDocument);
		String absolutePath = System.getProperty("user.dir") + "/" + doc.getPercorso();
		
		System.out.println(absolutePath);
		
		//eliminazione del file
		File file = new File(absolutePath); 
		file.delete();
		
		//eliminazione del record nel DB
		DocumentoDao.setSession(session);
		DocumentoDao.deleteDocument(doc);
		
		
		List<Documento> documents = (List<Documento>) appSession.getAttribute("documents");
		documents.remove(doc);
		List<Documento> docs = user.getDocumenti();
		docs.remove(doc);
		user.setDocumenti(docs);
		
		System.out.println("prima "+doc.getCommittente().getDocumenti().size());
		doc.getCommittente().getDocumenti().remove(doc);
		System.out.println("dopo "+doc.getCommittente().getDocumenti().size());
		if(doc.getCommittente().getDocumenti().size() == 0)
		{
			RecapitoDao.setSession(session);
			RecapitoDao.deleteContact(doc.getCommittente().getRecapiti().get(0));
			RecapitoDao.deleteContact(doc.getCommittente().getRecapiti().get(1));
			CommittenteDao.setSession(session);
			CommittenteDao.deleteCustomer(doc.getCommittente());
			tx.commit();
			user.getCommittenti().remove(doc.getCommittente());
		}
		tx.commit();
	
		//appSession.setAttribute("user", user);  Non serve! perchè user ha bean scope session 
		//e quindi per questa sessione esiste solo una istanza dell'oggetto utente, pertanto
		//ogni modifica ai suoi riferimenti (user ad esempio) va a modificare lo stesso oggetto
		//che è già presente nell'oggetto HttpSession
		return model;
		
	}
	
	@RequestMapping("/showDetails{idDocument}")
	public ModelAndView showDetailsFile(HttpServletRequest request, @PathVariable int idDocument)
	{
		ModelAndView model = new ModelAndView("DetailsFilePage");
		HttpSession appSession = request.getSession();
		Utente user = (Utente) appSession.getAttribute("user");
		Documento doc = Support.getDocumentById(user.getDocumenti(), idDocument);
		//test
		System.out.println("Id del documento" + idDocument);
		System.out.println(doc);
		Committente cust = doc.getCommittente();
		List<Recapito> recs = cust.getRecapiti();
		System.out.println(cust);
		//System.out.println(recs.size());
		for(int i = 0; i < recs.size(); i++)
		{
			System.out.println(i);
			System.out.println(recs.get(i));
		}
		appSession.setAttribute("doc", doc);
		model.addObject("doc", doc);
		return model;
		
	}
	
	@RequestMapping("/catchIdDoc{idDocument}")
	public ModelAndView catchIdDoc(@PathVariable int idDocument, HttpServletRequest request)
	{
		ModelAndView model = new ModelAndView("EditFilePage");
		HttpSession session = request.getSession();
		Utente user = (Utente) session.getAttribute("user");
		List<Documento> documents = user.getDocumenti();
		Documento document = Support.getDocumentById(documents, idDocument);
		model.addObject("doc", document);
		//in questo punto dobbiamo recuperare il documento corrispondente all'id
		//e inviarlo al client in modo tale da poter riempire le form. 
		//vedo se è possibile fare qualcosa con la jstl.
		model.addObject("idDoc", idDocument);
		model.addObject("operation", "editing");
		return model;
	}
	
	@RequestMapping("/edit{idDocument}")
	public ModelAndView editFile(@PathVariable int idDocument, HttpServletRequest request) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException
	{
		ModelAndView model = new ModelAndView("HomePage");			
			HttpSession appSession = request.getSession();
			Session session = (Session) appSession.getAttribute("session");
			Transaction tx = session.beginTransaction();
			Utente user = (Utente) appSession.getAttribute("user");
			List<Documento> documents = user.getDocumenti();
			Documento olddocument = Support.getDocumentById(documents, idDocument);
			Documento newdocument = (Documento) context.getBean("documento");
			//verifica che un file con quel nome non sia già presente
				
				
				
				//test
				String curDir = System.getProperty("user.dir"); //cartella corrente
		        System.out.println("\nIn questo momento ti trovi nella directory:" + " - " + curDir);
		        
		        //percorso relativo e nome del file
				/*String relativePath = "Storage/User"+user.getIdUser()+"/";
				String initName = file.getOriginalFilename();
				int indexDot = initName.lastIndexOf(".");
				String extension;
				if(indexDot != -1)
					{extension = initName.substring(indexDot);}
				else
					{extension = "";}
				String namefile = request.getParameter("name");
				relativePath = relativePath + namefile + extension;*/
		        
		        
				
		        String namefile = request.getParameter("name");
		        String relativePath = "Storage/User-"+user.getIdUtente()+"/";
				String oldpath = olddocument.getPercorso(); 
				int indexDot = oldpath.lastIndexOf(".");
				String extension;
				String newpath;
				if(indexDot != -1)
					{extension = oldpath.substring(indexDot);}
				else
					{extension = "";}
				newpath = relativePath + namefile + extension;
				
		        
				
				//rinomino il file
		        File f1 = new File(curDir + "/" + oldpath);
		        File f2 = new File(curDir + "/" + newpath);
		        f1.renameTo(f2);
		
		        
		        
		        
		        
			//Committente
			Committente custom = (Committente) context.getBean("committente");
			List<Committente> customers = user.getCommittenti();
			//è stato selezionato un committente già registrato
			if(!(request.getParameter("selectRegisteredCustomer").equals("selectCustomer")))
			{
			custom = Support.getCustomerById(customers,Integer.parseInt(request.getParameter("selectRegisteredCustomer")));
			}
			//modifica delle proprietà esclusive
			else
			{
				custom.setCognome(request.getParameter("surname"));
				custom.setCf(request.getParameter("CF"));
				custom.setNome(request.getParameter("nameCust"));
				custom.setUtente(user);				
				
			}	
			
			//Recapito
			Recapito contact = (Recapito) context.getBean("recapito");
			Recapito contact1 = (Recapito) context.getBean("recapito");
			contact.setTelefono(request.getParameter("tel1"));
			contact.setEmail(request.getParameter("email1"));
			contact1.setTelefono(request.getParameter("tel2"));
			contact1.setEmail(request.getParameter("email2"));
			
			
			//Documento
			String typeDoc = request.getParameter("type");
			
			//progetto preliminare
			switch(typeDoc)
			{
			case "preliminary":
				//test
				System.out.println("Hai scelto Progetto preliminare");
				
				newdocument.setCategoria("Progetto preliminare");
				newdocument.setTipo(request.getParameter("ppElaborate"));
				
				if(request.getParameter("ppElaborate").equals("e"))
				{
					switch(request.getParameter("type1"))
					{
					case "pwElab":
						newdocument.setSottocategoria("Opere e lavori puntuali");
						newdocument.setSottotipo(request.getParameter("pwElaborate"));
						break;
					case "nwElab":
						newdocument.setSottocategoria("Opere e lavori a rete");
						newdocument.setSottotipo(request.getParameter("nwElaborate"));
						break;	
					}
				}
				break;
			
			
			//progetto definitivo
			case "definitive":
				//test
				System.out.println("Hai scelto Progetto definitivo");
			
				newdocument.setCategoria("Progetto definitivo");
				newdocument.setTipo(request.getParameter("dpElaborate"));
				switch(request.getParameter("dpElaborate"))
				{
				case "b":
					if(request.getParameter("type1").equals("techRep"))
					{
						newdocument.setSottocategoria("Relazioni tecniche");
						newdocument.setSottotipo(request.getParameter("technicalReport"));
						
					}
					break;
				case "d":
					if(request.getParameter("type1").equals("dpgElab"))
					{
						newdocument.setSottocategoria("Elaborati grafici");
						newdocument.setSottotipo(request.getParameter("dpgElaborate"));
					
						if(request.getParameter("dpgElaborate").equals("h"))
						{					 
							switch(request.getParameter("type2"))
							{
							case "siElab":
								newdocument.setSottocategoria1("Studi e indagini");
								newdocument.setSottotipo1(request.getParameter("siElaborate"));
								break;
							case "artworkElab":
								newdocument.setSottocategoria1("Opere d'arte");
								newdocument.setSottotipo1(request.getParameter("artworkElaborate"));
								break;
							case "leiElab":
								newdocument.setSottocategoria1("Inserimenti paesaggistici ed ambientali");
								newdocument.setSottotipo1(request.getParameter("leiElaborate"));
								break;
							case "implantElab":
								newdocument.setSottocategoria1("Impianti");
								newdocument.setSottotipo1(request.getParameter("implantElaborate"));
								break;
							}
						}
					}
					break;
				}
				break;		
			
			
			//progetto esecutivo
			case "executive":
				newdocument.setCategoria("Progetto esecutivo");
				newdocument.setTipo(request.getParameter("epElaborate"));
				switch(request.getParameter("epElaborate"))
				{
					case "c":
						if(request.getParameter("type1").equals("epgElab"))
						{
							newdocument.setSottocategoria("Elaborati grafici");
							newdocument.setSottotipo(request.getParameter("epgElaborate"));
						}
						break;
				
					case "d":
						switch(request.getParameter("type1"))
						{
						
						case "sepElab":	
							newdocument.setSottocategoria1("Progetto esecutivo delle strutture");
							newdocument.setSottotipo(request.getParameter("sepElaborate"));
							break;
						case "iepElab":
							newdocument.setSottocategoria("Progetto esecutivo degli impianti");
							newdocument.setSottotipo(request.getParameter("iepElaborate"));
							break;
						}
						break;
					case"e":
				
						if(request.getParameter("type1").equals("mpElab"))
						{
							newdocument.setSottocategoria("Piano di manutenzione");
							newdocument.setSottotipo(request.getParameter("mpElaborate"));
						}
						break;
				}
			}
				
				//test
				System.out.println("Hai scelto Progetto esecutivo");
			
			
			
				System.out.println("fino a qui ci siamo1?");
			//data e ora di caricamento
			String date = Support.getCurrentDate();
			String time = Support.getCurrentTime();
			newdocument.setData(date);
			newdocument.setOra(time);
			


			
System.out.println("fino a qui ci siamo2?");
			
		//modifica delle proprietà comuni
			newdocument.setIdDocumento(idDocument);
			newdocument.setDescrizione(request.getParameter("description"));
			newdocument.setUtente(user);
			newdocument.setCommittente(custom);
			newdocument.setNome(namefile);
			newdocument.setPercorso(newpath);
			newdocument.setDimensione(olddocument.getDimensione());
			
System.out.println("fino a qui ci siamo3?");

		//rimuovo il documento dalla lista in modo da verificare se ci sono altri documenti
	    //aventi il nome inserito dall'utente
			documents.remove(olddocument);
		//verifica
			if(Support.isTheDocumentAlreadyHere(namefile, documents) || Support.isTheCustomerAlreadyHere(customers, request.getParameter("surname"), request.getParameter("nameCust")))
			{
				tx.commit();
				model.setViewName("EditFilePage");
				if(Support.isTheDocumentAlreadyHere(namefile, documents))
					model.addObject("msg1", "File con questo nome già presente!");
				if(Support.isTheCustomerAlreadyHere(customers, request.getParameter("surname"), request.getParameter("nameCust")))
					model.addObject("msg2", "Committente con questo nome già presente!");	
				
				documents.add(olddocument);
				model.addObject("doc", newdocument);  //popolazione della form con i dati inseriti dall'utente. 
				model.addObject("operation", "editing");
				return model;
			}
			else
			{
			//salvataggio dei dati del documento nel database
				newdocument.setCommittente(custom);
				documents.add(newdocument);
				session.clear();
				DocumentoDao.setSession(session);
				//DocumentDao.updateDocument(newdocument);
				/*DocumentoDao.deleteDocument(olddocument);
				DocumentoDao.saveDocument(newdocument);*/
				DocumentoDao.updateDocument(newdocument);
				
				//salvataggio dei dati del registro caricamenti e modifiche nel database 
				contact.setCommittente(custom);
				RecapitoDao.setSession(session);
				RecapitoDao.saveContact(contact);
				/*if(contact1.getTelefono() != null || contact1.getEmail() != null)*/
				RecapitoDao.saveContact(contact1);
				
				//custom.setDocuments(documents);
				//salvataggio dati del committente nel database
				if((request.getParameter("selectRegisteredCustomer").equals("selectCustomer")))
				{customers.add(custom);
				CommittenteDao.setSession(session);
				CommittenteDao.saveCustomer(custom);
				}
			}
		//fine verifica	
			user.setCommittenti(customers);
			user.setDocumenti(documents);
			tx.commit();
			
			model.addObject("operation", "editing");
			//model.addObject("destination", "homepage");
			return model;
			
	}

}
