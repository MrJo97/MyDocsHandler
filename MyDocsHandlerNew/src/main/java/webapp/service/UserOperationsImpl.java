package webapp.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
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
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import webapp.controller.Support;
import webapp.dao.CommittenteDao;
import webapp.dao.DocumentoDao;
import webapp.dao.RecapitoDao;
import webapp.dao.UtenteDao;
import webapp.dao.UtenteDaoInterface;
import webapp.model.committente.Committente;
import webapp.model.documento.Documento;
import webapp.model.recapito.Recapito;
import webapp.model.utente.Utente;

public class UserOperationsImpl implements UserOperations {

	private JavaMailSender mailSender;
	private UtenteDaoInterface utenteDao;
	private DocumentoDao documentoDao;
	private CommittenteDao committenteDao;
	private RecapitoDao recapitoDao;

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void setUtenteDao(UtenteDaoInterface utenteDao) {
		this.utenteDao = utenteDao;
	}

	public void setDocumentoDao(DocumentoDao documentoDao) {
		this.documentoDao = documentoDao;
	}

	public void setCommittenteDao(CommittenteDao committenteDao) {
		this.committenteDao = committenteDao;
	}

	public void setRecapitoDao(RecapitoDao recapitoDao) {
		this.recapitoDao = recapitoDao;
	}

	public Utente retrieveUpdatedUser(int idUser) {
		utenteDao.getSession();
		utenteDao.getTransaction();
		Utente user = utenteDao.getUserById(idUser);
		utenteDao.closeTransaction();
		return user;
	}

	public Utente checkUser(Utente user) {
		Utente registeredUser = null;
		/*
		 * utenteDao.getSession(); System.out.println("************************");
		 */

		utenteDao.getSession();
		utenteDao.getTransaction();
		List<Utente> users = utenteDao.getAllUsers();
		utenteDao.closeTransaction();

		try {
			registeredUser = Support.correspondingUser(users, user);
		} catch (SecurityException | RollbackException | HeuristicMixedException | HeuristicRollbackException
				| SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (registeredUser != null)
			System.out.println(registeredUser.getCommittenti());
		return registeredUser;
	}

	public void setUserStatusAndSendEmail(Utente user) {
		utenteDao.getSession();
		utenteDao.getTransaction();
		List<Utente> users = utenteDao.getAllUsers();
		int idUser = Support.nextIdUser(users);// id dell'utente da registrare
		String email = user.getEmail();
		String subject = "Registrazione Account - invio email automatica";
		String text = "Per registrare il tuo account clicca sul seguente link: "
				+ "http://localhost:8080/MyDocsHandlerNew/RegisterUser" + idUser;
		this.sendEmail(email, subject, text);
		user.setStato("pendingRegistration");

		// utenteDao.getTransaction();
		utenteDao.saveUser(user);
		utenteDao.closeTransaction();
		// utenteDao.closeSession();
	}

	public Utente registerUser(int idUser) {
		Utente newUser = null;
		utenteDao.getSession();
		utenteDao.getTransaction();
		Utente user = utenteDao.getUserById(idUser);
		if (user.getStato().equals("pendingRegistration")) {
			user.setStato("Registered");
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

	public ModelAndView registerDocumentAndCustomer(String operation,Recapito contact, Recapito contact1, Documento document,
			Committente customer, Utente user, ModelAndView model, HttpServletRequest request) {

		if (Support.isTheDocumentAlreadyHere(document.getNomefile(), user.getDocumenti())
				|| (Support.isTheCustomerAlreadyHere(user.getCommittenti(), customer.getCf())
						&& (request.getParameter("selectRegisteredCustomer").equals("selectCustomer")))) {
			model.setViewName("EditFilePage");
			if (Support.isTheDocumentAlreadyHere(document.getNomefile(), user.getDocumenti()))
				model.addObject("msg1", "File con questo nome già presente!");

			if (Support.isTheCustomerAlreadyHere(user.getCommittenti(), customer.getCf())
					&& (request.getParameter("selectRegisteredCustomer").equals("selectCustomer")))
				model.addObject("msg2", "Committente con questo nome già presente!");

			if (!(request.getParameter("selectRegisteredCustomer").equals("selectCustomer")))
				model.addObject("registeredCustomer", customer);
			else
				model.addObject("newCustomer", customer);

			model.addObject("document", document); // popolazione della form con i dati inseriti dall'utente.
			model.addObject("operation", "loading");

		} else {
			try {
				System.out.println("è stato selezionato un committente registrato?: "
						+ !(request.getParameter("selectRegisteredCustomer").equals("selectCustomer")));
				if ((request.getParameter("selectRegisteredCustomer").equals("selectCustomer"))) {// è stato selezionato
																									// un committente
																									// registrato
					committenteDao.getSession();
					committenteDao.getTransaction();
					committenteDao.saveCustomer(customer);
					committenteDao.closeTransaction();

					recapitoDao.getSession();
					recapitoDao.getTransaction();
					recapitoDao.saveContact(contact);
					recapitoDao.saveContact(contact1);
					recapitoDao.closeTransaction();
					System.out.println(customer.getDocumenti().size());
				}
				
					documentoDao.getSession();
					documentoDao.getTransaction();
					if(operation.equals("register")) 
						documentoDao.saveDocument(document);
					else
						documentoDao.updateDocument(document);
					documentoDao.closeTransaction();
				
				

			} catch (SecurityException | RollbackException | HeuristicMixedException | HeuristicRollbackException
					| SystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return model;
	}

	public ModelAndView editRegisteredCustomer(List<Committente> customers, Recapito contact, Recapito contact1,
			Committente newcustomer, Utente user, ModelAndView model, HttpServletRequest request) {

		if (Support.isTheCustomerAlreadyHere(customers, newcustomer.getCf())) {
			model.setViewName("EditCustomerPage");
			model.addObject("warning", "Committente già presente!");
			model.addObject("customer", newcustomer);
			model.addObject("contact", contact);
			model.addObject("contact1", contact1);
		} else {
			try {
				committenteDao.getSession();
				committenteDao.getTransaction();
				committenteDao.updateCustomer(newcustomer);
				committenteDao.closeTransaction();

				recapitoDao.getSession();
				recapitoDao.getTransaction();
				recapitoDao.updateContact(contact);
				recapitoDao.updateContact(contact1);
				recapitoDao.closeTransaction();

			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return model;
	}

	public void download(String absolutePath, HttpServletResponse response, Documento doc) {

		try {
			FileInputStream inStream = new FileInputStream(absolutePath);

			// response.setContentType("application/zip");//modifico il MIME Type
			response.setContentType("text/html");// modifico il MIME Type
			response.setContentLength(inStream.available());/*
															 * fa una stima del numero di bytes che possono essere letti
															 * da questo flusso di input
															 */

			// questo mi serve per identificare il TIPO di dato
			response.addHeader("Content-Disposition", "attachment; filename=" + doc.getNomefile());
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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void delete(Documento doc, Utente user) {
		try {
			documentoDao.getSession();
			documentoDao.getTransaction();
			documentoDao.deleteDocument(doc);
			documentoDao.closeTransaction();

			System.out.println("dimnesione lista documenti associati al committente:"
					+ doc.getCommittente().getDocumenti().size());
			if (doc.getCommittente().getDocumenti().size() == 1) {
				recapitoDao.getSession();
				recapitoDao.getTransaction();
				recapitoDao.deleteContact(doc.getCommittente().getRecapiti().get(0));
				recapitoDao.deleteContact(doc.getCommittente().getRecapiti().get(1));
				recapitoDao.closeTransaction();

				committenteDao.getSession();
				committenteDao.getTransaction();
				committenteDao.deleteCustomer(doc.getCommittente());
				committenteDao.closeTransaction();

				user.getCommittenti().remove(doc.getCommittente());
			}
		} catch (SecurityException | RollbackException | HeuristicMixedException | HeuristicRollbackException
				| SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
			List fileItems = upload.parseRequest(request);
			Iterator i = fileItems.iterator();
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
			;
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
