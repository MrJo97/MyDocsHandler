package webapp.controller;

import java.io.IOException;
import java.util.List;

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


import webapp.model.Documento;
import webapp.model.Utente;
//import webapp.service.CustomerOperationsImpl;
import webapp.service.UserOperationsImpl;

@Controller
@RequestMapping("/searchDocument")
public class SearchFileController
{		
		private UserOperationsImpl userOperationsImpl;
		//private CustomerOperationsImpl customerOperationsImpl;
		
		public void setUserOperationsImpl(UserOperationsImpl userOperationsImpl) {
			this.userOperationsImpl = userOperationsImpl;
		}
		
	/*	public void setCustomerOperationsImpl(CustomerOperationsImpl customerOperationsImpl) {
			this.customerOperationsImpl = customerOperationsImpl;
		}*/
		
		
		@RequestMapping("/search")
		public ModelAndView search(@ModelAttribute("documento") Documento searchedDocument, HttpServletRequest request) throws IOException, SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException
		{
			
			ModelAndView model = new ModelAndView("HomePage");
			HttpSession appSession = request.getSession();
			Utente user = (Utente) appSession.getAttribute("user");
			List<Documento> documents = userOperationsImpl.getAllDocuments(user);
						
			for(int i = documents.size()-1; i > -1; i--)
			{
				System.out.println(documents.get(i));
			}
			System.out.println(searchedDocument);
			
			for(int i = documents.size()-1; i > -1; i--)
			{
				System.out.println(i);
				Documento doc = documents.get(i);
				System.out.println("filtro nome file: " + (request.getParameter("namefile") != "") + i );
				
				//filtro per nome 
				if(request.getParameter("nome") != "")
				{
					if(!(Support.checkChars(doc.getNome(), searchedDocument.getNome())))
					{
						documents.remove(i);
					}
				}
				System.out.println("filtro 2.1: " + (searchedDocument.getTipo()!= null && searchedDocument.getCategoria()!= null) + i );
				//filtro per tipologia di documento
				if(searchedDocument.getTipo()!= null && searchedDocument.getCategoria()!= null)	
					{System.out.println(searchedDocument.getTipo()+"    e    "+searchedDocument.getCategoria() );
					if(!(doc.getTipo().equals(searchedDocument.getTipo()) && 
					 	 doc.getCategoria().equals(searchedDocument.getCategoria())))
						{
							documents.remove(doc);
						}
					}
				System.out.println("filtro 2.2: " + (searchedDocument.getSottotipo()!= null && searchedDocument.getSottocategoria()!= null) + i );
				if(searchedDocument.getSottotipo()!= null && searchedDocument.getSottocategoria()!= null
						&& doc.getSottotipo()!= null && doc.getSottocategoria()!= null)  
					{
					if(!(doc.getSottotipo().equals(searchedDocument.getSottotipo()) &&
						doc.getSottocategoria().equals(searchedDocument.getSottocategoria())))
						{
							documents.remove(doc);
						}
					}
				//filtro per committente associato
				
				//System.out.println("Id  del committente iterato " + doc.getCommittente().getIdCommittente());
				//System.out.println("Id del committente selezionato " + (Integer.parseInt(request.getParameter("selectRegisteredCustomer"))));
				if(!(request.getParameter("selectRegisteredCustomer").equals("selectCustomer")))
				{System.out.println("Gli id sono diversi? " + (doc.getCommittente().getIdCommittente() != Integer.parseInt(request.getParameter("selectRegisteredCustomer"))));
				   if(doc.getCommittente().getIdCommittente() != Integer.parseInt(request.getParameter("selectRegisteredCustomer")))
					{  
					   documents.remove(doc);
					}
						
				}
				
			}
			
			user.setDocumenti(documents);
			model.addObject("document", searchedDocument);
			model.addObject("user",user);
			model.addObject("idCust", request.getParameter("selectRegisteredCustomer"));
			return model;
		}
}