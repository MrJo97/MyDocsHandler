package webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import webapp.model.Committente;
//import webapp.model.Utente;
import webapp.service.CustomerOperationsImpl;
//import webapp.service.UserOperationsImpl;

@Controller
public class LoginProfileController {

	private CustomerOperationsImpl customerOperationsImpl;	

	// setter methods for setter injection
	public void setCustomerOperationsImpl(CustomerOperationsImpl customerOperationsImpl) {
		this.customerOperationsImpl = customerOperationsImpl;
	}

	@RequestMapping("/goToLoginProfileForm.html")
	public ModelAndView goToLoginProfileForm() {
		ModelAndView model = new ModelAndView("LoginCustomerProfilePage");
		return model;
	}

	
	@RequestMapping("/loginCustomer")
	public ModelAndView loginCustomer(@ModelAttribute("committente") Committente customer, HttpServletRequest request)
			throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException,
			SystemException {
		System.out.println(customer);
		System.out.println(customerOperationsImpl.getCustomerByCf(customer.getCf()));
		ModelAndView model = new ModelAndView("CustomerProfilePage");
		
		if (customerOperationsImpl.checkUsernameCustomer(customer) == null) {
			model.setViewName("LoginCustomerProfilePage");
			model.addObject("msg", "Credenziali non valide");
			model.addObject("customer", customer);
		}

		else {
			if (customerOperationsImpl.checkUsernameCustomer(customer).getPassword().equals(customer.getPassword())) 
			{
				customer = customerOperationsImpl.checkUsernameCustomer(customer);
				HttpSession appSession = request.getSession();
				//List<Documento> documents = new ArrayList<Documento>();
				//documents.addAll(user.getDocumenti()); //lazy initialization
				//appSession.setAttribute("documents", documents);
				appSession.setAttribute("customer", customer);
				System.out.println("Committente memorizzato nell'oggetto session: " + customer);
				model.addObject("destination", "homepage");
				model.addObject("customer", customer);
				//Support.printAll(user);
			} else {
				model.setViewName("LoginCustomerProfilePage");
				model.addObject("msg", "Credenziali non valide");
				model.addObject("customer", customer);
			}
		}
		return model;

	}

	@RequestMapping("/logoutCustomer")
	public ModelAndView logoutUser(HttpServletRequest request) throws SecurityException, RollbackException,
			HeuristicMixedException, HeuristicRollbackException, SystemException {
		ModelAndView model = new ModelAndView("LoginCustomerProfilePage");
		HttpSession appSession = request.getSession();
		appSession.removeAttribute("customer");
		return model;
	}
}
