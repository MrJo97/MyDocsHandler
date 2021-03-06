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

import webapp.model.Utente;
import webapp.service.UserOperationsImpl;

@Controller
public class LoginController {

	private UserOperationsImpl userOperationsImpl;

	// setter methods for setter injection
	public void setUserOperationsImpl(UserOperationsImpl userOperationsImpl) {
		this.userOperationsImpl = userOperationsImpl;
	}

	@RequestMapping("/goToLoginForm.html")
	public ModelAndView goToLoginForm() {
		ModelAndView model = new ModelAndView("Login");
		return model;
	}
	
	@RequestMapping("/")
	public ModelAndView loginUser(@ModelAttribute("utente") Utente user, HttpServletRequest request)
			throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException,
			SystemException {
		
		/*System.out.println(user);
		System.out.println("l'utente è già entrato:" + user==null);
		System.out.println(userOperationsImpl.checkUser(user));*/
		
		ModelAndView model = new ModelAndView("HomePage");
		HttpSession appSession = request.getSession();
		
		System.out.println("--------------------------");
		System.out.println(appSession.getAttribute("user"));
		System.out.println("l'utente è già entrato:" + (appSession.getAttribute("user")!=null));
		
		if(appSession.getAttribute("user")!=null){
			System.out.println("Utente già loggato");
			model.setViewName("HomePage");
			model.addObject("destination", "homepage");
			model.addObject("user", user);
			return model;
		}
				
		if (userOperationsImpl.checkUser(user) == null){
			model.setViewName("Login");
			model.addObject("msg", "Credenziali non valide");
			model.addObject("user", user);
		}

		else {
			if (userOperationsImpl.checkUser(user).getPassword().equals(user.getPassword())) 
			{
				user = userOperationsImpl.checkUser(user);
				
				//List<Documento> documents = new ArrayList<Documento>();
				//documents.addAll(user.getDocumenti()); //lazy initialization
				//appSession.setAttribute("documents", documents);
				appSession.setAttribute("user", user);
				System.out.println("Utente memorizzato nell'oggetto session: " + user);
				model.addObject("destination", "homepage");
				model.addObject("user", user);
				//Support.printAll(user);
			} else {
				model.setViewName("Login");
				model.addObject("msg", "Credenziali non valide");
				model.addObject("user", user);
			}
		}
		System.out.println(user.getCommittenti());
		return model;

	}

	@RequestMapping("/logout")
	public ModelAndView logoutUser(HttpServletRequest request) throws SecurityException, RollbackException,
			HeuristicMixedException, HeuristicRollbackException, SystemException {
		ModelAndView model = new ModelAndView("Login");
		HttpSession appSession = request.getSession();
		appSession.removeAttribute("user");
		return model;
	}

}
