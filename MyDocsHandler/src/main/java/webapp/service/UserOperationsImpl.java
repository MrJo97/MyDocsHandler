package webapp.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.servlet.ModelAndView;

import webapp.controller.Support;
import webapp.dao.CommittenteDaoImpl;
import webapp.dao.DocumentoDaoImpl;
import webapp.dao.UtenteDaoImpl;
import webapp.model.Committente;
import webapp.model.Documento;
import webapp.model.Utente;

public class UserOperationsImpl {

	private JavaMailSender mailSender;
	private UtenteDaoImpl utenteDao;
	private DocumentoDaoImpl documentoDao;
	private CommittenteDaoImpl committenteDao;

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void setUtenteDao(UtenteDaoImpl utenteDao) {
		this.utenteDao = utenteDao;
	}

	public void setDocumentoDao(DocumentoDaoImpl documentoDao) {
		this.documentoDao = documentoDao;
	}

	public void setCommittenteDao(CommittenteDaoImpl committenteDao) {
		this.committenteDao = committenteDao;
	}
	
	public int getMaxIdDocumento() throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException
	{	documentoDao.getSession();
	    documentoDao.getTransaction();
		int id = documentoDao.findMaxId();
		documentoDao.closeTransaction();
		return id;
	}
	
	public int getMaxIdUtente() throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException
	{	utenteDao.getSession();
	    utenteDao.getTransaction();
		int id = utenteDao.findMaxId();
		utenteDao.closeTransaction();
		return id;
	}
	
	public Documento getDocumentById(int id) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException
	{
		documentoDao.getSession();
		documentoDao.getTransaction();
		Documento doc = documentoDao.getDocumentById(id);
		documentoDao.closeTransaction();
		return doc;
	}
	
	public Documento getDocumentByName(String name) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException
	{
		documentoDao.getSession();
		documentoDao.getTransaction();
		Documento doc = documentoDao.getDocumentByName(name);
		documentoDao.closeTransaction();
		return doc;
	}
	
	public List<Documento> getAllDocuments(Utente user) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException
	{
		documentoDao.getSession();
		documentoDao.getTransaction();
		List<Documento> docs = documentoDao.getAllDocuments(user);
		documentoDao.closeTransaction();
		return docs;
	}

	public void updateDocument(Documento document) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException
	{
	documentoDao.getSession();
	documentoDao.getTransaction();
	documentoDao.updateDocument(document);
	documentoDao.closeTransaction();
	}
	public Utente retrieveUpdatedUser(int idUser) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
		utenteDao.getSession();
		utenteDao.getTransaction();
		Utente user = utenteDao.getUserById(idUser);
		utenteDao.closeTransaction();
		return user;
	}

	public Utente checkUser(Utente user) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
		Utente registeredUser = null;
		utenteDao.getSession();
		utenteDao.getTransaction();
		registeredUser = utenteDao.getUserByEmail(user.getEmail());
		utenteDao.closeTransaction();
		/*if (registeredUser != null)
			System.out.println(registeredUser.getCommittenti());*/
		return registeredUser;
	}

	public void setUserStatusAndSendEmail(Utente user) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
		utenteDao.getSession();
		utenteDao.getTransaction();
		List<Utente> users = utenteDao.getAllUsers();
		int idUser = utenteDao.findMaxId();
				//Support.nextIdUser(users);// id dell'utente da registrare
		String email = user.getEmail();
		String subject = "Registrazione Account - invio email automatica";
		String text = "Per registrare il tuo account clicca sul seguente link: "
				+ "http://localhost:8080/MyDocsHandler/RegisterUser" + idUser;
		this.sendEmail(email, subject, text);
		user.setStato("in attesa");

		utenteDao.saveUser(user);
		utenteDao.closeTransaction();
	}

	public Utente registerUser(int idUser) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
		Utente newUser = null;
		utenteDao.getSession();
		utenteDao.getTransaction();
		Utente user = utenteDao.getUserById(idUser);
		if (user.getStato().equals("in attesa")) {
			user.setStato("registrato");
			// utenteDao.getTransaction();
			utenteDao.updateUser(user);
			// utenteDao.closeTransaction();

			String thisFolderPath = System.getProperty("user.dir") + "Storage/User-" + user.getIdUtente();
			this.createFolder(thisFolderPath);
			newUser = user;
		}
		utenteDao.closeTransaction();

		return newUser;
	}

	public ModelAndView registerDocumentAndCustomer(String operation, Documento document,
			Committente customer, Utente user, ModelAndView model, HttpServletRequest request) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {

		documentoDao.getSession();
		documentoDao.getTransaction();
		if (documentoDao.getDocumentById(document.getIdDocumento())!=null)
				/*|| (Support.isTheCustomerAlreadyHere(user.getCommittenti(), customer.getCf())
						&& (request.getParameter("selectRegisteredCustomer").equals("selectCustomer")))*/ 
		{
			
				model.addObject("msg1", "File con questo nome già presente!");
			    model.setViewName("EditFilePage");
			
			/*if (Support.isTheDocumentAlreadyHere(document.getNome(), user.getDocumenti()))
				model.addObject("msg1", "File con questo nome già presente!");*/

			/*if (Support.isTheCustomerAlreadyHere(user.getCommittenti(), customer.getCf())
					&& (request.getParameter("selectRegisteredCustomer").equals("selectCustomer")))
				model.addObject("msg2", "Committente con questo nome già presente!");*/

            /*if (!(request.getParameter("selectRegisteredCustomer").equals("selectCustomer")))
				model.addObject("registeredCustomer", customer);
			else
				model.addObject("newCustomer", customer);*/

			model.addObject("document", document); // popolazione della form con i dati inseriti dall'utente.
			model.addObject("operation", "loading");//sto effettuando il caricamento del file e non la sua modifica
			documentoDao.closeTransaction();
		} else {
			//try {
			/*	System.out.println("è stato selezionato un committente registrato?: "
						+ !(request.getParameter("selectRegisteredCustomer").equals("selectCustomer")));*/
				/*if ((request.getParameter("selectRegisteredCustomer").equals("selectCustomer"))) {// è stato selezionato
																									// un committente
					*/	// registrato
			
			if(operation.equals("register")) 
				documentoDao.saveDocument(document);
			else
				documentoDao.updateDocument(document);
			documentoDao.closeTransaction();
			//aggiorniamo la lista dei committenti associati all'utente e viceversa
			
					committenteDao.getSession();
					committenteDao.getTransaction();
					committenteDao.updateCustomer(customer);
					committenteDao.closeTransaction();
					
					utenteDao.getSession();
					utenteDao.getTransaction();
					utenteDao.updateUser(user);
					utenteDao.closeTransaction();			
					
		}
					
				
				

		/*	} catch (SecurityException | RollbackException | HeuristicMixedException | HeuristicRollbackException
					| SystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		

		return model;
	}

/*	public ModelAndView editRegisteredCustomer(/*List<Committente> customers, Committente newcustomer, Utente user, ModelAndView model, HttpServletRequest request) throws RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {

		if (Support.isTheCustomerAlreadyHere(customers, newcustomer.getCf())) {
			model.setViewName("EditCustomerPage");
			model.addObject("warning", "Committente già presente!");
			model.addObject("customer", newcustomer);
		} else {
			try {
				committenteDao.getSession();
				committenteDao.getTransaction();
				committenteDao.updateCustomer(newcustomer);
				committenteDao.closeTransaction();

			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return model;
	}*/

	public void download(String absolutePath, HttpServletResponse response, Documento doc) throws IOException {

		//try {
			FileInputStream inStream = new FileInputStream(absolutePath);

			// response.setContentType("application/zip");//modifico il MIME Type
			response.setContentType("text/html");// modifico il MIME Type
			response.setContentLength(inStream.available());/*
															 * fa una stima del numero di bytes che possono essere letti
															 * da questo flusso di input
															 */

			// questo mi serve per identificare il TIPO di dato
			response.addHeader("Content-Disposition", "attachment; filename=" + doc.getNome());
			response.addHeader("Content-Transfer-Encoding", "binary");
			response.addHeader("Content-Description", "File Transfer");

			// Leggo il file
			byte[] data = new byte[inStream.available()]; // crea un array di bytes di dimensione data da
			inStream.read(data);// legge il file e trasferisce i byte nell'array

			// ricavo lo stream di output con il quale inviare i dati a client
			// Apro lo stream per avviare il download
			ServletOutputStream out = response.getOutputStream();
			out.write(data);// l'insieme dei byte che formano il file, vengono inviati al client
			out.flush();
			out.close();
			inStream.close();
		/*} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

	public void delete(Documento doc, Utente user) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
			documentoDao.getSession();
			documentoDao.getTransaction();
			List<Documento> docs = documentoDao.getAllDocumentsByCustomer(doc.getCommittente());
			System.out.println(docs.size());
			documentoDao.closeTransaction();
			if(docs.size()==1)
			{	
				utenteDao.getSession();
				utenteDao.getTransaction();
				List<Committente> userCustomers = user.getCommittenti();
				System.out.println(userCustomers);
				System.out.println(doc.getCommittente());
				for(int i =0; i<userCustomers.size();i++)
				{
					if(userCustomers.get(i).getIdCommittente()==doc.getCommittente().getIdCommittente())
					userCustomers.remove(userCustomers.get(i));
				}
				user.setCommittenti(userCustomers);
				utenteDao.updateUser(user);
				utenteDao.closeTransaction();
				
				
				Committente customer = doc.getCommittente();
				committenteDao.getSession();
				committenteDao.getTransaction();
				List<Utente> customerUsers = customer.getUtenti();
				System.out.println(customerUsers);
				System.out.println(user);
				for(int i =0; i<customerUsers.size();i++)
				{
					if(customerUsers.get(i).getIdUtente()==user.getIdUtente())
						customerUsers.remove(customerUsers.get(i));
				}
				//customerUsers.remove(user);
				customer.setUtenti(customerUsers);
				committenteDao.updateCustomer(customer);
				committenteDao.closeTransaction();
				
			}	
		
			documentoDao.getSession();
			documentoDao.getTransaction();
			documentoDao.deleteDocument(doc);
			documentoDao.closeTransaction();
	}
	
	public List<Documento> searchDocuments(Documento document, Committente customer) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException
	{
		documentoDao.getSession();
		documentoDao.getTransaction();
		List<Documento> documents = documentoDao.searchDocuments(document, customer);
		documentoDao.closeTransaction();
		return documents;
	}

	public void sendEmail(String email, String subject, String text) {
		SimpleMailMessage simpleMessage = new SimpleMailMessage();
		simpleMessage.setTo(email);
		simpleMessage.setSubject(subject);
		simpleMessage.setText(text);
		mailSender.send(simpleMessage);
	}

	public void createFolder(String path) {
		File storageFolder = new File(path);
		storageFolder.mkdir();
	}

	public void uploadFile(byte[] bytes, HttpServletRequest request, String path) {
		// caricamento del file nella cartella di archiviazione
		// noi al momento la facciamo in un modo, ma in seguito
		// procederemo in un'altra maniera (vedere se si può fare qualcosa con i bean)
		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<FileItem> fileItems = upload.parseRequest(request);
			Iterator<FileItem> i = fileItems.iterator();
			File file1;
			while (i.hasNext()) {
				FileItem fi = (FileItem) i.next();
				if (!fi.isFormField()) {
					// elemento i-esimo (NB: ogni elemento corrisponde ad un file)
					file1 = new File(path);
					fi.write(file1);
				}
			}
			File uploadFile = new File(path);
			BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(uploadFile));
			outputStream.write(bytes);
			outputStream.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void deleteFile(String path) {
		File file = new File(path);
		file.delete();
	}

	public void renameFile(String curDir, String newpath, String oldpath) {
		File f1 = new File(curDir + "/" + oldpath);
		File f2 = new File(curDir + "/" + newpath);
		f1.renameTo(f2);
	}
}
