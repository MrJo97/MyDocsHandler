package webapp.controller;


import java.util.ArrayList;
import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import org.springframework.mail.MailSendException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import webapp.model.Committente;
import webapp.model.Documento;
import webapp.model.Utente;
import webapp.service.CustomerOperationsImpl;
import webapp.service.UserOperationsImpl;

@Controller
public class CreationProfileController {
	

	private CustomerOperationsImpl customerOperationsImpl;	
	
	//setter methods for setter injection
	public void setCustomerOperationsImpl(CustomerOperationsImpl customerOperationsImpl) {
		this.customerOperationsImpl = customerOperationsImpl;
	}

	
	
	@RequestMapping("/goToCreationForm.html")
	public ModelAndView goToCreationForm() {
		ModelAndView model = new ModelAndView("NewCustomerProfilePage");
		return model;
	}

	@RequestMapping("/checkExistingCustomer")
		public ModelAndView checkCredentials(@ModelAttribute("committente") Committente customer, BindingResult result) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException 
	{	
		System.out.println(customer);
		ModelAndView model = new ModelAndView("HomePage"); 
		System.out.println(customerOperationsImpl.checkCfCustomer(customer.getCf()));
		if (customerOperationsImpl.checkCfCustomer(customer.getCf()) != null) 
		{// utente già registrato
			model.setViewName("NewCustomerProfilePage");
			model.addObject("customer", customer);
			if (customerOperationsImpl.checkCfCustomer(customer.getCf()).getStato().equals("in attesa"))
				model.addObject("msg", "Committente in attesa di registrazione");
			else
			{
				model.addObject("msg", "Committente già registrato");
			}
				
			return model;
		}
		else if(customerOperationsImpl.checkUsernameCustomer(customer) != null)
		{
			model.setViewName("NewCustomerProfilePage");
			model.addObject("customer", customer);
		    model.addObject("msg", "Username già in uso");
			return model;
		}
		else
		{
			try 
			{
				customerOperationsImpl.setCustomerStatusAndSendEmail(customer);
			} 
			catch (MailSendException me) 
			{
				Support.detectInvalidAddress(me, model);
				return model;
			}
		
			model.setViewName("EmailDeliveryWarningPage");
			model.addObject("email", customer.getEmail());
			return model;
		}
	}

	@RequestMapping("/RegisterCustomer{idCustomer}")
	public ModelAndView registerUser(@PathVariable int idCustomer, HttpServletRequest request) throws SecurityException,
			RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
		
		ModelAndView model = new ModelAndView("CorrectCreationWarningPage");
		HttpSession appSession = request.getSession();
		Committente customer = customerOperationsImpl.registerCustomer(idCustomer);
		if(customer == null)
		{
			model.setViewName("ExistingCustomerProfileWarningPage");
			return model;
		}	    
		//List<Documento> documents = new ArrayList<Documento>();
		//System.out.println(customer.getU());
		//documents.addAll(customer.getDocumenti());
		//appSession.setAttribute("documents", documents);
		appSession.setAttribute("customer", customer);
		model.addObject("destination", "homepage");//rivedere
		model.addObject("customer", customer);
		return model;
	}	
	
	@RequestMapping("/CustomerProfile{idCustomer}")
	public ModelAndView customerProfile(@PathVariable int idCustomer, HttpServletRequest request) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException
	{
		ModelAndView model = new ModelAndView("CustomerProfilePage");
		Committente customer = customerOperationsImpl.retrieveCustomerById(idCustomer);
		model.addObject("customer", customer);
		return model;
	}
	
}
