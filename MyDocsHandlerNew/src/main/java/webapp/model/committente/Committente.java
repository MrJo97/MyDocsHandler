package webapp.model.committente;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import webapp.model.documento.Documento;
import webapp.model.recapito.Recapito;
import webapp.model.utente.Utente;

@Component
@Scope("prototype")
@Entity
public class Committente {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idCommittente;
	private String cf;
	private String nome;
	private String cognome;
	
	@ManyToOne
	private Utente utente;
	@OneToMany(mappedBy="committente")
	private List<Documento> documenti = new ArrayList<Documento>();
	@OneToMany(mappedBy="committente")
	private List<Recapito> recapiti = new ArrayList<Recapito>();
	
	
	public int getIdCommittente() {
		return idCommittente;
	}
	public void setIdCommittente(int idCommittente) {
		this.idCommittente = idCommittente;
	}
	public String getCf() {
		return cf;
	}
	public void setCf(String cf) {
		this.cf = cf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public Utente getUtente() {
		return utente;
	}
	public void setUtente(Utente utente) {
		this.utente = utente;
	}
	public List<Documento> getDocumenti() {
		return documenti;
	}
	public void setDocumenti(List<Documento> documenti) {
		this.documenti = documenti;
	}
	
	
	public List<Recapito> getRecapiti() {
		return recapiti;
	}
	public void setRecapiti(List<Recapito> recapiti) {
		this.recapiti = recapiti;
	}
	@Override
	public String toString() {
		return "Committente [cf=" + cf + ", nome=" + nome + ", cognome=" + cognome + "]";
	}

	
	

}
