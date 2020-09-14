package webapp.controller;

import webapp.model.committente.Committente;
import webapp.model.documento.Documento;

public class JSPSupport {
	public static String checkCategory(Documento document, String category, String tag)
	{
			if(tag.equals("radio"))
			{
				if(document.getCategoria() != null)
				{
					
				if(document.getCategoria().equals(category))
					return "checked";
				else
					return "";
				}
				else
					return "";
			}
			else 
			{	
				if(document.getCategoria() != null)
				{
				if(document.getCategoria().equals(category))
					return "";
				else
					return "disabled";
				}
				else
					return "disabled";
			}
	}

	public static String checkSubCategory(Documento document, String subcategory, String tag)
	{
		if(tag.equals("radio"))
		{
			if(document.getSottocategoria() != null)
			{
				if(!document.getSottocategoria().equals(subcategory))
					return "";
				else
					return "checked";	
			}
			else
				return "";
		}
		else 
		{
			if(document.getSottocategoria() != null)
			{
				if(!document.getSottocategoria().equals(subcategory))
					return "disabled";
				else
					return "";	
			}
			else
				return "disabled";
		}
	}

	public static String checkSubCategory1(Documento document, String subcategory1, String tag)
	{
		if(tag.equals("radio"))
		{
			if(document.getSottocategoria1() != null)
			{
				if(!document.getSottocategoria1().equals(subcategory1))
					return "";
				else
					return "checked";	
			}
			else
				return "";
		}
		else 
		{
			if(document.getSottocategoria1() != null)
			{
				if(!document.getSottocategoria1().equals(subcategory1))
					return "disabled";
				else
					return "";	
			}
			else
				return "disabled";
		}
	}






	public static String checkType(Documento document, String category, String type)
	{
		if(document.getCategoria() != null)
		{
			if(document.getCategoria().equals(category) && document.getTipo().equals(type))
				return "selected";
			else
				return "";
		}
		else
			return "";
	}

	public static String checkSubType(Documento document, String subcategory, String subtype)
	{
		if(document.getSottocategoria() != null)
		{
			if(document.getSottocategoria().equals(subcategory) && document.getSottotipo().equals(subtype))
				return "selected";
			else
				return "";	
		}
		else
			return "";
	}

	public static String checkSubType1(Documento document, String subcategory1, String subtype1)
	{
		if(document.getSottocategoria1() != null)
		{
			if(document.getSottocategoria1().equals(subcategory1) && document.getSottotipo1().equals(subtype1))
				return "selected";
			else
				return "";	
		}
		else
			return "";
	}


	public static String checkCustomer(Documento document, Committente customer)
	{
		
		Committente customerDoc = document.getCommittente();
		if(customerDoc != null)
		{
				if(customer.getNome().equals(customerDoc.getNome()) && customer.getCognome().equals(customerDoc.getCognome()))
				{
					return "selected";
				}
				else
				{
					return "";
				}
		}
		else
			return "";
	}

	public static String getActionPath(String operation, Documento document)
	{	
		if(operation.equals("editing"))
		{
			return "/MyDocsHandlerNew/editFile/edit" + document.getIdDocumento();
		}
		else
		{
			return "/MyDocsHandlerNew/fileupload/upload";
		}
	}

}
