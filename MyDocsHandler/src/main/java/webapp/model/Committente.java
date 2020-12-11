package webapp.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


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
	private long telefono;
	private String email;
	private String username;
	private String password;
	private String stato;
	
	@ManyToMany
	@JoinTable(name="Incarica",joinColumns=@JoinColumn(name="idCommittente"),inverseJoinColumns=@JoinColumn(name="idUtente"))
	private List<Utente> utenti;
	@OneToMany(mappedBy="committente")
	private List<Documento> documenti = new ArrayList<Documento>();
	@OneToOne(mappedBy="committente")
	private AggiornamentoCommittente aggiornamentoCommittente;
	
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
	public long getTelefono() {
		return telefono;
	}
	public void setTelefono(long telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public List<Utente> getUtenti() {
		return utenti;
	}
	public void setUtenti(List<Utente> utenti) {
		this.utenti = utenti;
	}
	public List<Documento> getDocumenti() {
		return documenti;
	}
	
	public void setDocumenti(List<Documento> documenti) {
		this.documenti = documenti;
	}
	public String getStato() {
		return stato;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public AggiornamentoCommittente getAggiornamentoCommittente() {
		return aggiornamentoCommittente;
	}
	public void setAggiornamentoCommittente(AggiornamentoCommittente aggiornamentoCommittente) {
		this.aggiornamentoCommittente = aggiornamentoCommittente;
	}
	@Override
	public String toString() {
		return "Committente [idCommittente=" + idCommittente + ", cf=" + cf + ", nome=" + nome + ", cognome=" + cognome
				+ ", telefono=" + telefono + ", email=" + email + ", username=" + username + ", password=" + password
				+ ", stato=" + stato + "]";
	}
	

	
	

}
