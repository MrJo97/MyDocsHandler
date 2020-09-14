package webapp.model.utente;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import webapp.model.committente.*;
import webapp.model.documento.*;

@Component
@Scope("session")
@Entity
public class Utente {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idUtente;
	private String email;
	private String password;
	private String stato;
	
	
	@OneToMany(mappedBy="utente")
	private List<Documento> documenti = new ArrayList<Documento>();
	@OneToMany(mappedBy="utente")
	private List<Committente> committenti = new ArrayList<Committente>();
	
	
	public int getIdUtente() {
		return idUtente;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getStato() {
		return stato;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}
	public List<Documento> getDocumenti() {
		return documenti;
	}
	public void setDocumenti(List<Documento> documenti) {
		this.documenti = documenti;
	}
	public List<Committente> getCommittenti() {
		return committenti;
	}
	public void setCommittenti(List<Committente> committenti) {
		this.committenti = committenti;
	}
	@Override
	public String toString() {
		return "Utente [email=" + email + ", password=" + password + ", stato=" + stato + "]";
	}
	
	
	



}
