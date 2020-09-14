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
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
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


@Controller
@RequestMapping("/fileupload")
public class FileUploadDownloadController {
	
	private ApplicationContext context = new ClassPathXmlApplicationContext("services.xml", "daos.xml");
	
	@RequestMapping("/checkFile")
	public ModelAndView checkUploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException
	{
		ModelAndView model = new ModelAndView("HomePage");
		long fileSize = file.getSize();
		long maxFileSize = 2000000;
		Documento document = (Documento) context.getBean("documento");
		//inizializzo questi campi in modo tale che una volta che l'utente viene indirizzato 
		//a EditFilePage.jsp i campi della form possano essere comunque riempiti correttamente.
		/*document.setNome("");
		document.setDescrizione("");*/
		
		//test
		System.out.println("Lunghezza massima: " + maxFileSize);
		System.out.println("Lunghezza file: " + fileSize);
		System.out.println("Nome file: " + file.getOriginalFilename());
		
		
		//error: dimensione del file eccessiva:
		if(fileSize > maxFileSize)
		{model.addObject("msg", "Le dimensioni di questo file superano quelle consentite(2MB)!");
		return model;
		}
		
		HttpSession appSession = request.getSession();
		appSession.setAttribute("file", file);
		appSession.setAttribute("bytes", file.getBytes());
		model.addObject("doc", document);
		model.addObject("operation", "loading");
		model.setViewName("EditFilePage");
		return model;
	}
	
	
	@RequestMapping("/upload")
	public ModelAndView upload(HttpServletRequest request) throws Exception
	{	
		System.out.println("");	
		System.out.println("");
		System.out.println("");
		System.out.println("NUOVO DOCUMENTO");
		System.out.println("");
		ModelAndView model = new ModelAndView("HomePage");
		HttpSession appSession = request.getSession();
		Session session = (Session) appSession.getAttribute("session");
		Transaction tx = session.beginTransaction();
		MultipartFile file = (MultipartFile) appSession.getAttribute("file");
		
		Utente user = (Utente) appSession.getAttribute("user");
		Documento document = (Documento) context.getBean("documento");
		System.out.println("Stampa del documento PRIMA della modifica: " + document);
		Committente customer = (Committente) context.getBean("committente");
		Recapito contact = (Recapito) context.getBean("recapito");
		Recapito contact1 = (Recapito) context.getBean("recapito");
		List<Committente> customers = user.getCommittenti();
		List<Documento> documents = user.getDocumenti();
		
		//test
		String curDir = System.getProperty("user.dir"); //cartella corrente
	    System.out.println("\nIn questo momento ti trovi nella directory:" + " - " + curDir);
	        
	    //percorso relativo e nome del file
		String relativePath = "Storage/User-"+user.getIdUtente()+"/";
		String initName = file.getOriginalFilename();
		int indexDot = initName.lastIndexOf(".");
		String extension;
		
		if(indexDot != -1)
		{
			extension = initName.substring(indexDot);
		}
		else
		{
			extension = "";
		}
		
		String namefile = request.getParameter("name");
		relativePath = relativePath + namefile + extension;
		document.setNome(namefile);
		document.setPercorso(relativePath);
		
		
	
		//Committente
		//è stato selezionato un committente già registrato
		if(!(request.getParameter("selectRegisteredCustomer").equals("selectCustomer")))
		{
			customer = Support.getCustomerById(customers, Integer.parseInt(request.getParameter("selectRegisteredCustomer")));
		}
		else
		{
			customer.setCognome(request.getParameter("surname"));
			customer.setCf(request.getParameter("CF"));
			customer.setNome(request.getParameter("nameCust"));
			//customer.setRecapiti(contacts);
			customer.setUtente(user);			
		}
		
		//Recapito
				contact.setTelefono(request.getParameter("tel1"));
				contact.setEmail(request.getParameter("email1"));
				contact.setCommittente(customer);
				contact1.setTelefono(request.getParameter("tel2"));
				contact1.setEmail(request.getParameter("email2"));
				contact1.setCommittente(customer);
				List<Recapito> contacts = new ArrayList<Recapito>();
				contacts.add(contact);
				contacts.add(contact1);
				customer.setRecapiti(contacts);
				
		
		//Documento
		String typeDoc = request.getParameter("type");
		
		//progetto preliminare
		switch(typeDoc)
		{
		case "preliminary":
			//test
			System.out.println("Hai scelto Progetto preliminare");
			
			document.setCategoria("Progetto preliminare");
			document.setTipo(request.getParameter("ppElaborate"));
			
			if(request.getParameter("ppElaborate").equals("e"))
			{
				switch(request.getParameter("type1"))
				{
				case "pwElab":
					document.setSottocategoria("Opere e lavori puntuali");
					document.setSottotipo(request.getParameter("pwElaborate"));
					break;
				case "nwElab":
					document.setSottocategoria("Opere e lavori a rete");
					document.setSottotipo(request.getParameter("nwElaborate"));
					break;	
				}
			}
			break;
		
		
		//progetto definitivo
		case "definitive":
			//test
			System.out.println("Hai scelto Progetto definitivo");
		
			document.setCategoria("Progetto definitivo");
			document.setTipo(request.getParameter("dpElaborate"));
			switch(request.getParameter("dpElaborate"))
			{
			case "b":
				if(request.getParameter("type1").equals("techRep"))
				{
					document.setSottocategoria("Relazioni tecniche");
					document.setSottotipo(request.getParameter("technicalReport"));
					
				}
				break;
			case "d":
				if(request.getParameter("type1").equals("dpgElab"))
				{
					document.setSottocategoria("Elaborati grafici");
					document.setSottotipo(request.getParameter("dpgElaborate"));
				
					if(request.getParameter("dpgElaborate").equals("h"))
					{					 
						switch(request.getParameter("type2"))
						{
						case "siElab":
							document.setSottocategoria1("Studi e indagini");
							document.setSottotipo1(request.getParameter("siElaborate"));
							break;
						case "artworkElab":
							document.setSottocategoria1("Opere d'arte");
							document.setSottotipo1(request.getParameter("artworkElaborate"));
							break;
						case "leiElab":
							document.setSottocategoria1("Inserimenti paesaggistici ed ambientali");
							document.setSottotipo1(request.getParameter("leiElaborate"));
							break;
						case "implantElab":
							document.setSottocategoria1("Impianti");
							document.setSottotipo1(request.getParameter("implantElaborate"));
							break;
						}
					}
				}
				break;
			}
			break;		
		
		
		//progetto esecutivo
		case "executive":
			document.setCategoria("Progetto esecutivo");
			document.setTipo(request.getParameter("epElaborate"));
			switch(request.getParameter("epElaborate"))
			{
				case "c":
					if(request.getParameter("type1").equals("epgElab"))
					{
						document.setSottocategoria("Elaborati grafici");
						document.setSottotipo(request.getParameter("epgElaborate"));
					}
					break;
			
				case "d":
					switch(request.getParameter("type1"))
					{
					
					case "sepEi Slab":	
						document.setSottocategoria1("Progetto esecutivo delle strutture");
						document.setSottotipo(request.getParameter("sepElaborate"));
						break;
					case "iepElab":
						document.setSottocategoria("Progetto esecutivo degli impianti");
						document.setSottotipo(request.getParameter("iepElaborate"));
						break;
					}
					break;
				case "e":
			
					if(request.getParameter("type1").equals("mpElab"))
					{
						document.setSottocategoria("Piano di manutenzione");
						document.setSottotipo(request.getParameter("mpElaborate"));
					}
					break;
			}
		}
			
			//test
			System.out.println("Hai scelto Progetto esecutivo");
		
		
		
		
		//data e ora di caricamento
		String date = Support.getCurrentDate();
		String time = Support.getCurrentTime();
		document.setData(date);
		document.setOra(time);
		
		
		
		//modifica delle proprietà comuni (nome e percorso sono stati settati alle righe 122/123)
		document.setDescrizione(request.getParameter("description"));
		document.setDimensione(String.valueOf(file.getSize()));
		document.setUtente(user);
		
		
		
		if(Support.isTheDocumentAlreadyHere(namefile, documents) || Support.isTheCustomerAlreadyHere(customers, request.getParameter("surname"), request.getParameter("nameCust")))
		{		tx.commit();
				model.setViewName("EditFilePage");
				
			if(Support.isTheDocumentAlreadyHere(namefile, documents))
				model.addObject("msg1", "File con questo nome già presente!");
			
			if(Support.isTheCustomerAlreadyHere(customers, request.getParameter("surname"), request.getParameter("nameCust")))
				model.addObject("msg2", "Committente con questo nome già presente!");	
			
			if((request.getParameter("selectRegisteredCustomer").equals("selectCustomer")))
				model.addObject("registeredCustomer", document.getCommittente());
			
			model.addObject("newCustomer", document.getCommittente()); 
			model.addObject("document", document);  //popolazione della form con i dati inseriti dall'utente. 
			model.addObject("operation", "loading");
			return model;
		}
		else
		{System.out.println("è stato selezionato un committente registrato?: " + !(request.getParameter("selectRegisteredCustomer").equals("selectCustomer")));
			if((request.getParameter("selectRegisteredCustomer").equals("selectCustomer")))
			{session.clear();
			document.setCommittente(customer);
			customers.add(customer);
		//salvataggio dati del committente nel database
			CommittenteDao.setSession(session);
			CommittenteDao.saveCustomer(customer);
			
		//salvataggio dei dati del registro caricamenti e modifiche nel database 
			RecapitoDao.setSession(session);
			RecapitoDao.saveContact(contact);
			RecapitoDao.saveContact(contact1);
			}
		//salvataggio dei dati del documento nel database 
			//document.setCommittente(customer);
			//documents.add(document);
			DocumentoDao.setSession(session);
			DocumentoDao.saveDocument(document);
			
			tx.commit();
		}
		
		
		
		
		
	//caricamento del file nella cartella di archiviazione		
	//noi al momento la facciamo in un modo, ma in seguito
	//procederemo in un'altra maniera
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);		
		List fileItems = upload.parseRequest(request);
		Iterator i = fileItems.iterator();
		File file1;
		while (i.hasNext()) 
		{
			FileItem fi = (FileItem) i.next();
			if ( !fi.isFormField () ) 
			{
				// elemento i-esimo (NB: ogni elemento corrisponde ad un file)
				// per quanto ci riguarda noi permettiamo il caricamento di un solo file per
				// volta (altrimenti
				// dovremo pensarla più in grande...).
				// nel caso la lista abbia piu elementi è necessario interrompere l'upload e
				// reindirizzare
				// l'utente alla homePage segnalando l'errore.
				// scrittura del file nella cartella specificata da filePath
				file1 = new File(relativePath);
				fi.write(file1);
			} 					
		}
		//---------//*/
		//lancia una eccezione perchè la variabile "file" è già stata letta
		//una volta
		//byte[] bytes = file.getBytes();
						
		byte[] bytes = (byte[]) appSession.getAttribute("bytes");
		File uploadFile = new File(relativePath);
		BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(uploadFile));;
		outputStream.write(bytes);
		outputStream.close();
	//fine dell'upload		
	 
		
		//appSession.setAttribute("user", user);
		model.setViewName("HomePage");
		
		customer.setIdCommittente(Support.nextIdCustomer(user));
		document.setIdDocumento(Support.nextIdDocument(user));
		document.setCommittente(customer);
		documents.add(document);
		customer.setDocumenti(documents);
		
		user.setCommittenti(customers);
		user.setDocumenti(documents);
		List<Documento> sessionDocuments = (List<Documento>) appSession.getAttribute("documents");
		sessionDocuments.add(document);
		//Support.printAll(user);
		//model.addObject("destination", "homepage");
		return model;
		
}
	
	@RequestMapping("/download{idDocument}")
	public ModelAndView download(@PathVariable int idDocument, HttpServletRequest request, HttpServletResponse response) throws Exception
	{ModelAndView model = new ModelAndView("Homepage");
	
		HttpSession appSession = request.getSession();	
		Utente user = (Utente) appSession.getAttribute("user");
		Documento doc = (Support.getDocumentById(user.getDocumenti(), idDocument));
		String nomeFile = doc.getNome();
		String absolutePath = System.getProperty("user.dir") + "/" + doc.getPercorso();
		System.out.println(absolutePath);
		FileInputStream inStream = new FileInputStream(absolutePath); //percorso assoluto
	        
	    //response.setContentType("application/zip");//modifico il MIME Type
		response.setContentType("text/html");//modifico il MIME Type
	    response.setContentLength(inStream.available());/*fa una stima del numero di bytes che possono essere
	   														letti da questo flusso di input*/
	        
	    //questo mi serve per identificare il TIPO di dato
	    response.addHeader("Content-Disposition", "attachment; filename="+ nomeFile);
	    response.addHeader("Content-Transfer-Encoding", "binary");
	    response.addHeader("Content-Description", "File Transfer");


	    //Leggo il file
	    byte [] data = new byte[inStream.available()]; //crea un array di bytes di dimensione data da
	    inStream.read(data);//legge il file e trasferisce i byte nell'array

	    //ricavo lo stream di output con il quale inviare i dati a client
	    //Apro lo stream per avviare il download
	    ServletOutputStream out = response.getOutputStream();
	    out.write(data);//l'insieme dei byte che formano il file, vengono inviati al client 
	    out.flush();
	    out.close();
	    inStream.close();
	    return model;
	}
		
}

