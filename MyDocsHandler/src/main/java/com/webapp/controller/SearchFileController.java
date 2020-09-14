package com.webapp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import com.webapp.model.documento.Documento;
import com.webapp.model.utente.Utente;

@Controller
@RequestMapping("/searchDocument")
public class SearchFileController
{		
		private ApplicationContext context = new ClassPathXmlApplicationContext("services.xml", "daos.xml");
		//@Autowired
		//private SessionFactory sf;
		
		@RequestMapping("/search")
		public ModelAndView search(HttpServletRequest request) throws IOException, SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException
		{
			
			ModelAndView model = new ModelAndView("HomePage");
			HttpSession appSession = request.getSession();
			Utente user = (Utente) appSession.getAttribute("user");
			String gg = request.getParameter("j");
			Documento searchedDocument = (Documento) context.getBean("documento");
			List<Documento> documents = new ArrayList<Documento>();
			documents.addAll((List<Documento>) appSession.getAttribute("documents"));
		//	Support.printAll(user1);
			System.out.println(documents.size());
			
			//progetto preliminare
			if(request.getParameter("type") != null)
			{
				switch(request.getParameter("type"))
			{
			case "preliminary":
				//test
				System.out.println("Hai scelto Progetto preliminare");
				
				searchedDocument.setCategoria("Progetto preliminare");
				searchedDocument.setTipo(request.getParameter("ppElaborate"));
				
				if(request.getParameter("ppElaborate").equals("e"))
				{
					switch(request.getParameter("type1"))
					{
					case "pwElab":
						searchedDocument.setSottocategoria("Opere e lavori puntuali");
						searchedDocument.setSottotipo(request.getParameter("pwElaborate"));
						break;
					case "nwElab":
						searchedDocument.setSottocategoria("Opere e lavori a rete");
						searchedDocument.setSottotipo(request.getParameter("nwElaborate"));
						break;	
					}
				}
				break;
			
			
			//progetto definitivo
			case "definitive":
				//test
				System.out.println("Hai scelto Progetto definitivo");
			
				searchedDocument.setCategoria("Progetto definitivo");
				searchedDocument.setTipo(request.getParameter("dpElaborate"));
				switch(request.getParameter("dpElaborate"))
				{
				case "b":
					if(request.getParameter("type1").equals("techRep"))
					{
						searchedDocument.setSottocategoria("Relazioni tecniche");
						searchedDocument.setSottotipo(request.getParameter("technicalReport"));
						
					}
					break;
				case "d":
					if(request.getParameter("type1").equals("dpgElab"))
					{
						searchedDocument.setSottocategoria("Elaborati grafici");
						searchedDocument.setSottotipo(request.getParameter("dpgElaborate"));
					
						if(request.getParameter("dpgElaborate").equals("h"))
						{					 
							switch(request.getParameter("type2"))
							{
							case "siElab":
								searchedDocument.setSottocategoria1("Studi e indagini");
								searchedDocument.setSottotipo1(request.getParameter("siElaborate"));
								break;
							case "artworkElab":
								searchedDocument.setSottocategoria1("Opere d'arte");
								searchedDocument.setSottotipo1(request.getParameter("artworkElaborate"));
								break;
							case "leiElab":
								searchedDocument.setSottocategoria1("Inserimenti paesaggistici ed ambientali");
								searchedDocument.setSottotipo1(request.getParameter("leiElaborate"));
								break;
							case "implantElab":
								searchedDocument.setSottocategoria1("Impianti");
								searchedDocument.setSottotipo1(request.getParameter("implantElaborate"));
								break;
							}
						}
					}
					break;
				}
				break;		
			
			
			//progetto esecutivo
			case "executive":
				searchedDocument.setCategoria("Progetto esecutivo");
				searchedDocument.setTipo(request.getParameter("epElaborate"));
				switch(request.getParameter("epElaborate"))
				{
					case "c":
						if(request.getParameter("type1").equals("epgElab"))
						{
							searchedDocument.setSottocategoria("Elaborati grafici");
							searchedDocument.setSottotipo(request.getParameter("epgElaborate"));
						}
						break;
				
					case "d":
						switch(request.getParameter("type1"))
						{
						
						case "sepElab":	
							searchedDocument.setSottocategoria1("Progetto esecutivo delle strutture");
							searchedDocument.setSottotipo(request.getParameter("sepElaborate"));
							break;
						case "iepElab":
							searchedDocument.setSottocategoria("Progetto esecutivo degli impianti");
							searchedDocument.setSottotipo(request.getParameter("iepElaborate"));
							break;
						}
						break;
					case"e":
				
						if(request.getParameter("type1").equals("mpElab"))
						{
							searchedDocument.setSottocategoria("Piano di manutenzione");
							searchedDocument.setSottotipo(request.getParameter("mpElaborate"));
						}
						break;
				}
			}
			}
			
			
			System.out.println("FILTRI");
			//List<Documento> newList = new ArrayList<Documento>();
			//filtro la lista per tipo di documento:
			for(int i = documents.size()-1; i > -1; i--)
			{
				System.out.println(i);
				Documento doc = documents.get(i);
				System.out.println("filtro 1: " + (request.getParameter("namefile") != "") + i );
				
				//filtro per nome 
				if(request.getParameter("namefile") != "")
				{
					//Support.checkChars() restituisce true se il primo argomento
					//è un prefisso del secondo
					if(!(Support.checkChars(doc.getNome(), request.getParameter("namefile"))))
					{
						documents.remove(i);
					}
				}
				System.out.println("filtro 2.1: " + (searchedDocument.getTipo()!= null && searchedDocument.getCategoria()!= null) + i );
				//filtro per tipologia di documento
				if(searchedDocument.getTipo()!= null && searchedDocument.getCategoria()!= null)	
					{
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
				System.out.println("filtro 2.3: " + (searchedDocument.getSottotipo1()!= null && searchedDocument.getSottocategoria1()!= null) + i );
				if(searchedDocument.getSottotipo1()!= null && searchedDocument.getSottocategoria1()!= null
						&& doc.getSottotipo1()!= null && doc.getSottocategoria1()!= null)  
				{
					if(!(doc.getSottotipo1().equals(searchedDocument.getSottotipo1()) &&
						doc.getSottocategoria1().equals(searchedDocument.getSottocategoria1())))
						{
							documents.remove(doc);
						}
				}
				System.out.println("filtro 3: " + !(request.getParameter("selectRegisteredCustomer").equals("selectCustomer")) + i );
				System.out.println(doc.getCommittente().getIdCommittente());
				//filtro per committente associato
				
				System.out.println("Id  del committente iterato " + doc.getCommittente().getIdCommittente());
				//System.out.println("Id del committente selezionato " + (Integer.parseInt(request.getParameter("selectRegisteredCustomer"))));
				if(!(request.getParameter("selectRegisteredCustomer").equals("selectCustomer")))
				{System.out.println("Gli id sono diversi? " + (doc.getCommittente().getIdCommittente() != Integer.parseInt(request.getParameter("selectRegisteredCustomer"))));
				   if(doc.getCommittente().getIdCommittente() != Integer.parseInt(request.getParameter("selectRegisteredCustomer")))
					{  
					   documents.remove(doc);
					}
						
				}
				
			}
			System.out.println("dimensione finale della lista " + documents.size());
			user.setDocumenti(documents);
			model.addObject("document",searchedDocument);
			model.addObject("user",user);
			return model;

		}
	}