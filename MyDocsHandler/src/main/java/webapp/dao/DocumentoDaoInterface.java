package webapp.dao;

import java.util.List;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import webapp.model.Documento;
import webapp.model.Utente;

public interface DocumentoDaoInterface {

	public  Documento getDocumentById(int id) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException;
    
    public  List<Documento> getAllDocuments(Utente user) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException;
    
    public void updateDocument(Documento user);
    
    public void saveDocument(Documento user) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException;
     
    public void deleteDocument(Documento user) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException;
}
