package webapp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import webapp.dao.CommittenteDao;
import webapp.dao.RecapitoDao;
import webapp.model.committente.Committente;
import webapp.model.documento.Documento;
import webapp.model.utente.Utente;
import webapp.service.UserOperations;
import webapp.service.UserOperationsImpl;
import webapp.model.recapito.Recapito;

@Controller
@RequestMapping("/editCustomer")
public class EditCustomerController {

	private ApplicationContext context = new ClassPathXmlApplicationContext("services.xml", "daos.xml");
	private UserOperationsImpl userOperationsImpl;
	

	public void setUserOperationsImpl(UserOperationsImpl userOperationsImpl) {
		this.userOperationsImpl = userOperationsImpl;
	}

	@RequestMapping("/showDetailsCustomer{idCustomer}")
	public ModelAndView showDetailsCustomer(HttpServletRequest request, @PathVariable int idCustomer) {
		ModelAndView model = new ModelAndView("DetailsCustomerPage");
		HttpSession appSession = request.getSession();
		Utente user = (Utente) appSession.getAttribute("user");
		Committente customer = Support.getCustomerById(user.getCommittenti(), idCustomer);
		model.addObject("customer", customer);
		return model;
	}

	@RequestMapping("/catchIdCustomer{idCustomer}")
	public ModelAndView catchIdCustomer(@PathVariable int idCustomer, HttpServletRequest request) {
		ModelAndView model = new ModelAndView("EditCustomerPage");
		HttpSession session = request.getSession();
		Utente user = (Utente) session.getAttribute("user");
		List<Committente> customers = user.getCommittenti();
		Committente customer = Support.getCustomerById(customers, idCustomer);
		model.addObject("customer", customer);
		model.addObject("contact", customer.getRecapiti().get(0));
		model.addObject("contact1", customer.getRecapiti().get(1));
		model.addObject("operation", "editing");
		return model;
	}

	@RequestMapping("/edit{idCustomer}")
	public ModelAndView editCustomer(@PathVariable int idCustomer, @ModelAttribute("recapito") Recapito contact,
			@ModelAttribute("committente") Committente newcustomer, HttpServletRequest request) throws SecurityException,
			RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
		
		ModelAndView model = new ModelAndView("HomePage");
		HttpSession appsession = request.getSession();
		Utente user = (Utente) appsession.getAttribute("user");
		//Session session = (Session) appsession.getAttribute("session");
		//Transaction tx = session.beginTransaction();
		List<Committente> customers = user.getCommittenti();
		Committente oldcustomer = Support.getCustomerById(customers, idCustomer);
		//Committente newcustomer = (Committente) context.getBean("committente");
		//Recapito contact = (Recapito) context.getBean("recapito");
		Recapito contact1 = (Recapito) context.getBean("recapito");
		System.out.println(newcustomer);
	//	System.out.println(request.getParameter("surname").toString());

		//newcustomer.setNome(request.getParameter("nameCust").toString());
		//newcustomer.setCognome(request.getParameter("surname").toString());
		//newcustomer.setCf(request.getParameter("CF").toString());
		newcustomer.setIdCommittente(oldcustomer.getIdCommittente());
		newcustomer.setUtente(user);
		newcustomer.setDocumenti(oldcustomer.getDocumenti());

		System.out.println("Recapito1 vecchio: " + oldcustomer.getRecapiti().get(0));
		System.out.println("Recapito2 vecchio: " + oldcustomer.getRecapiti().get(1));
		System.out.println("id del nuovo committente: " + newcustomer.getIdCommittente());
		List<Recapito> contacts = newcustomer.getRecapiti();
		//contact.setTelefono(request.getParameter("tel1").toString());
		//contact.setEmail(request.getParameter("email1").toString());
		contact.setIdRecapito(oldcustomer.getRecapiti().get(0).getIdRecapito());
		contact.setCommittente(newcustomer);
		contact1.setTelefono(request.getParameter("telefono1").toString());
		contact1.setEmail(request.getParameter("email1").toString());
		contact1.setIdRecapito(oldcustomer.getRecapiti().get(1).getIdRecapito());
		contact1.setCommittente(newcustomer);

		System.out.println("id del nuovo recapito1: " + contact.getIdRecapito());
		System.out.println("id del nuovo recapito2: " + contact1.getIdRecapito());
		System.out.println("Committente vecchio: " + oldcustomer);
		contacts.add(contact);
		contacts.add(contact1);
		
		customers.remove(oldcustomer);

		model = userOperationsImpl.editRegisteredCustomer(customers, contact, contact1, newcustomer, user, model, request);
		// test
		/*System.out.println("----------------Stampa committenti---------------");
		for (int i = 0; i < customers.size(); i++) {
			System.out.println(customers.get(i));
		}*/

		if (Support.isTheCustomerAlreadyHere(customers, newcustomer.getCf())) {
			/*model.setViewName("EditCustomerPage");
			model.addObject("warning", "Committente già presente!");
			model.addObject("customer", newcustomer);
			model.addObject("contact",  newcustomer.getRecapiti().get(0) contact);
			model.addObject("contact1", newcustomer.getRecapiti().get(1)  contact1);*/
			return model;
		}
		user = userOperationsImpl.retrieveUpdatedUser(user.getIdUtente());
		appsession.setAttribute("user", user);
		model.addObject("user", user);
		//customers.add(newcustomer);
		//session.clear();

		/**
		 * //impostarlo nuovamente dopo RecapitoDao.setSession(session);
		 * RecapitoDao.updateContact(contact); RecapitoDao.updateContact(contact1);
		 * CommittenteDao.setSession(session);
		 * CommittenteDao.updateCustomer(newcustomer);
		 **/

		// a questo punto è necessario aggiornare anche la lista di documenti
		// che verrà mostrata alla homepage:
		/*List<Documento> documents = user.getDocumenti();
		for (int i = 0; i < documents.size(); i++) {
			if (documents.get(i).getCommittente().getIdCommittente() == oldcustomer.getIdCommittente()) {
				documents.get(i).setCommittente(newcustomer);
			}
		}*/
		
		//tx.commit();
		// model.addObject("destination", "homepage");
		return model;
	}
}
