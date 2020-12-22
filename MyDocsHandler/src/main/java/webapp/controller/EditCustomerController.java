package webapp.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import webapp.model.Committente;
import webapp.model.AggiornamentoCommittente;
import webapp.model.Utente;
import webapp.service.CustomerOperationsImpl;
import webapp.service.UserOperationsImpl;

@Controller
@RequestMapping("/editCustomer")
public class EditCustomerController {

	//private ApplicationContext context = new ClassPathXmlApplicationContext("services.xml", "daos.xml");
	//private UserOperationsImpl userOperationsImpl;
	private CustomerOperationsImpl customerOperationsImpl;

	public void setCustomerOperationsImpl(CustomerOperationsImpl customerOperationsImpl) {
		this.customerOperationsImpl = customerOperationsImpl;
	}
	
	@RequestMapping("/showDetailsCustomerForUser{idCustomer}")
	public ModelAndView showDetailsCustomerForUser(HttpServletRequest request, @PathVariable int idCustomer) {
		ModelAndView model = new ModelAndView("DetailsCustomerPage");
		HttpSession appSession = request.getSession();
		Utente user = (Utente) appSession.getAttribute("user");
		Committente customer = Support.getCustomerById(user.getCommittenti(), idCustomer);
		model.addObject("customer", customer);
		return model;
	}

	@RequestMapping("/showDetailsCustomer{idCustomer}")
	public ModelAndView showDetailsCustomer(HttpServletRequest request, @PathVariable int idCustomer) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
		ModelAndView model = new ModelAndView("CustomerProfilePage");
		//HttpSession appSession = request.getSession();
		//Utente user = (Utente) appSession.getAttribute("user");
		//Committente customer = Support.getCustomerById(user.getCommittenti(), idCustomer);
		Committente customer = customerOperationsImpl.getCustomerById(idCustomer);
		model.addObject("customer", customer);
		return model;
	}

	@RequestMapping("/catchIdCustomer{idCustomer}")
	public ModelAndView catchIdCustomer(@PathVariable int idCustomer, HttpServletRequest request) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
		ModelAndView model = new ModelAndView("EditCustomerPage");
		HttpSession session = request.getSession();
		Utente user = (Utente) session.getAttribute("user");
		//List<Committente> customers = user.getCommittenti();
		//Committente customer = Support.getCustomerById(customers, idCustomer);
		Committente customer = customerOperationsImpl.getCustomerById(idCustomer);
		model.addObject("customer", customer);
		model.addObject("operation", "editing");
		return model;
	}

	@RequestMapping("/edit{idCustomer}")
	public ModelAndView editCustomer(@PathVariable int idCustomer,	@ModelAttribute("aggiornamentoCommittente") AggiornamentoCommittente updatingCustomer,
		   HttpServletRequest request) throws SecurityException,RollbackException, HeuristicMixedException, 
		   HeuristicRollbackException, SystemException {
		
		ModelAndView model = new ModelAndView("EmailDeliveryWarningPage");		
		Committente customer = customerOperationsImpl.getCustomerById(idCustomer);
		updatingCustomer.setCommittente(customer);
		
		Committente customer1 = customerOperationsImpl.checkCfCustomer(request.getParameter("cf"));
		AggiornamentoCommittente updatingCustomer1 = customerOperationsImpl.getUpdatingCustomerByCf(updatingCustomer);
		if(customer1 != null)
		{
			if(customer1.getIdCommittente() != customer.getIdCommittente())
			{//committente con questo cf già presente 
				model.setViewName("EditCustomerPage");
				model.addObject("customer", customer);
				model.addObject("msg", "Committente con questo codice fiscale già presente!");
				return model;
			}
		}
		if(updatingCustomer1 != null)
		{
			if(updatingCustomer1.getCommittente().getIdCommittente() != customer.getIdCommittente())
			{//committente in aggiornamento con questo cf già presente 
				model.setViewName("EditCustomerPage");
				model.addObject("customer", customer);
				model.addObject("msg", "Committente con questo codice fiscale già presente!");
				return model;
			}
		}
		//salvo i dati aggiornati del committente temporaneamente in modo da non perdere le modifiche 
		customerOperationsImpl.saveUpdatingCustomer(updatingCustomer);
		//questo metodo permette di salvare quest dati nella tabella Aggiornamento_Committente 
		//se questo committente NON aveva già fatto delle modifiche non confermate. altrimenti aggiorna
		//tali modifiche, fatte in precedenza ma non confermate, con quelle attuali. 
		
		
		String subject = "MyDocsHandler - Modifica email - invio email automatica";
		String text = "&Eacute; stato scelto questo indirizzo email. Inoltre sono stati impostati i diversi campi some segue:\n"
				+ "Nome: " + updatingCustomer.getNome() + "\n"
				+ "Cognome: " + updatingCustomer.getCognome() + "\n"
				+ "Codice fiscale: " + updatingCustomer.getCf() + "\n"
				+ "Telefono: " + updatingCustomer.getTelefono() + "\n " + "\n "
				+ "Per salvare le modifiche appena effettuate clicca sul seguente link: "
				+ "http://localhost:8080/MyDocsHandler/editCustomer/UpdateCustomer" + idCustomer;
	
		//in UpdateCustomer vado ad aggiornare i valori del record nella tabella committente con i valori del record
		//associato nella tabella AggiornamentoCommittente, ed elimino quest'ultimo record. 
		customerOperationsImpl.sendEmail(updatingCustomer.getEmail(), subject, text);
		model.addObject("email", updatingCustomer.getEmail());
		System.out.println(updatingCustomer);
	
		return model;
	}

	@RequestMapping("/UpdateCustomer{idCustomer}")
	public ModelAndView updateCustomer(@PathVariable int idCustomer) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException
	{
		ModelAndView model = new ModelAndView("CustomerProfilePage");
		Committente customer = customerOperationsImpl.updateUpdatingCustomer(idCustomer);
		model.addObject("customer", customer);
		return model;
	}
	
}
