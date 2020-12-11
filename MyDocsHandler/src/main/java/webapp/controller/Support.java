package webapp.controller;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
import java.util.List;

import javax.mail.SendFailedException;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import org.springframework.mail.MailSendException;
import org.springframework.web.servlet.ModelAndView;

import webapp.model.Committente;
import webapp.model.Documento;
import webapp.model.Utente;

public class Support {
	
	public static boolean find(int id, List<Utente> users)
	{	boolean result = false;
		for(int i = 0; i< users.size();i++)
		{
			if(users.get(i).getIdUtente()==id)
			{result = true;}
		}
		return result;
	}
	
	public static String changeNameFileWithSameExtension(String namefile, String initName)
	{
		String newName;
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
		newName = namefile + extension;
		return newName;
	}
	
	public static Documento getDocumentByPath(List<Documento> docs, String path)
	{Documento doc = null;
	for(int i=0;i<docs.size();i++ )
		{
			if(docs.get(i).getPercorso() == path)
			{
				doc=docs.get(i);
			}
		}
	return doc;
	}
	
	public static Committente getCustomerById(List<Committente> customers, int idCustomer)
	{Committente customer = null;
		for(int i=0;i<customers.size();i++ )
			{
				if(customers.get(i).getIdCommittente() == idCustomer)
				{
					customer=customers.get(i);
				}
			}
		return customer;
	}
	
	public static Date getCurrentDate() {
		Calendar calendar = Calendar.getInstance();
		      //Returns current time in millis
		long dateInMill = calendar.getTimeInMillis();
		
		Date date = new Date(dateInMill);
		
		System.out.println(date);
		
		/**Calendar calendario = Calendar.getInstance();
		int day = calendario.get(Calendar.DAY_OF_MONTH);
		// il numero restituito per il mese deve essere incrementato di 1 poichè viene
		// restituito il numero del mese
		// precedente
		int month = calendario.get(Calendar.MONTH) + 1;
		int year = calendario.get(Calendar.YEAR);
		String data = day + "-" + month + "-" + year;
		return data;**/
		return date;
	}

	public static Time getCurrentTime() {
		Calendar calendar = Calendar.getInstance();
	      //Returns current time in millis
	long dateInMill = calendar.getTimeInMillis();
	Time time = new Time(dateInMill);
		
		/**Calendar calendario = Calendar.getInstance();
		int hour = calendario.get(Calendar.HOUR_OF_DAY);
		int minutes = calendario.get(Calendar.MINUTE);
		int seconds = calendario.get(Calendar.SECOND);
		String ora = hour + "." + minutes + "." + seconds;
		return ora;**/
	return time;
	}
	
	public static boolean isTheDocumentAlreadyHere(String namefile, List<Documento> docs)
	{boolean isHere = false;
		for(int i = 0; i<docs.size() ; i++)
		{
			if((docs.get(i).getNome()).equals(namefile))
				isHere = true;
		}
	return isHere;
	}
	
	public static Utente getUserByEmail(List<Utente> users, String email)
	{
		Utente user1 = null;
		if(users!=null)
		{
			for(int i = 0;i < users.size();i++)
			{
				if(users.get(i).getEmail().equals(email))
				{user1 = users.get(i);
				}
			}
		}
		return user1;
	}
	 
	public static boolean isTheCustomerAlreadyHere(List<Committente> customers, String cf)
	{boolean isHere = false;
	for(int i = 0; i<customers.size() ; i++)
	{
		if((customers.get(i).getCf()).equals(cf))
				isHere = true;
	}
	return isHere;
	}
	
	
	//metodo di supporto per l'operazione di login
	public static Utente correspondingUser(List<Utente> users, Utente user) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException
	{
	
		Utente user1 = null;
	if(users!=null)
	{
		for(int i = 0;i < users.size();i++)
		{
			if(users.get(i).getEmail().equals(user.getEmail()))
			{user1 = users.get(i);
			}
		}
	}
	return user1;
	}
	

	
	//metodi di supporto per la logica di business per la registrazione dell'utente
	public static boolean isHere(List<Utente> users, Utente user) {
		if (users != null) {
			for (int i = 0; i < users.size(); i++) {
				if (users.get(i).getEmail().equals(user.getEmail()))
					return true;
			}
		}
		return false;

	}
	
	public static int nextIdUser(List<Utente> users)
	{
		int idUser = 0;
		for(int i = 0; i < users.size(); i++)
		{
			if(users.get(i).getIdUtente() > idUser)
				idUser=users.get(i).getIdUtente();
		}
		return idUser+1;
	}
	public static int nextIdCustomer(Utente user)
	{
		int idCustomer = 0;
		for(int i = 0; i < user.getCommittenti().size(); i++)
		{
			if(user.getCommittenti().get(i).getIdCommittente() > idCustomer)
				idCustomer=user.getCommittenti().get(i).getIdCommittente();
		}
		return idCustomer+1;
	}
	/*public static int  nextIdDocument(Utente user)
	{
		int idDocument = 0;
		for(int i = 0; i < user.getDocumenti().size(); i++)
		{
			if(user.getDocumenti().get(i).getPath() > idDocument)
				idDocument=user.getDocumenti().get(i).getIdDocumento();
		}
		return idDocument+1;
	}*/
	
	

		
	//eccezione: indirizzo non corretto. 
	public static void detectInvalidAddress(MailSendException me, ModelAndView model) {
		Exception[] messageExceptions = me.getMessageExceptions();
		if (messageExceptions.length > 0) {
			Exception messageException = messageExceptions[0];
			if (messageException instanceof SendFailedException) {
				model.setViewName("Registration");
				model.addObject("msg", "Indirizzo email non corretto");
			}
		}
	}
	
	
	public static void printAll(Utente user) 
	{
		System.out.println("----------------- Dati utente -----------------");
		System.out.println(user);
		System.out.println("-----------------------------------------------------");
		System.out.println("----------------- Lista Documenti -----------------");
		System.out.println("-----------------------------------------------------");
		for(int i = 0; i < user.getDocumenti().size(); i++)
		{
			System.out.println(user.getDocumenti().get(i));
		}
		System.out.println("-----------------------------------------------------");
		System.out.println("----------------- Lista Committenti -----------------");
		System.out.println("-----------------------------------------------------");
		for(int i = 0; i < user.getCommittenti().size(); i++)
		{
			System.out.println(user.getCommittenti().get(i));
		}
		System.out.println("-----------------------------------------------------");
		System.out.println("----------------- Lista recapiti-----------------");
		System.out.println("-----------------------------------------------------");
		for(int i = 0; i < user.getCommittenti().size(); i++)
		{
			/*for(int j = 0; j <user.getCommittenti().get(i).getRecapiti().size(); j++)
			{
				System.out.println(user.getCommittenti().get(i).getRecapiti().get(j));
			}*/
		}
		
	}
	
	public static boolean checkChars(String string, String prefix)
	{
		boolean isPrefix = false;
		if(prefix.length()>string.length())
		{
			isPrefix = false;
		}
		else
		{
			if(string.substring(0, prefix.length()).equals(prefix))
			{
				isPrefix = true;
			}
			else
			{
				isPrefix = false;
			}
		}
		return isPrefix;
	}
	
}
