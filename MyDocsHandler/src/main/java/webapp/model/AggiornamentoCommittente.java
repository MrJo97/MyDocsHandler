package webapp.model;

import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.List;

//import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
//import javax.persistence.JoinTable;
//import javax.persistence.ManyToMany;
//import javax.persistence.MapsId;
//import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope("prototype")
@Entity//(name = "Aggiornamento_Committente")
@Table(name = "Aggiornamento_Committente")
public class AggiornamentoCommittente implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@OneToOne
	@JoinColumn(name="idCommittente")
	//@MapsId //serve per dire ad hibernate di usare idCommittente della classe Committente come PK della
	// tabella AggiornamentoCommittente. Fonte: https://thorben-janssen.com/hibernate-tips-same-primary-key-one-to-one-association/
	private Committente committente;
	private String cf;
	private String nome;
	private String cognome;
	private long telefono;
	private String email;
	
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
	public Committente getCommittente() {
		return committente;
	}
	public void setCommittente(Committente committente) {
		this.committente = committente;
	}
	@Override
	public String toString() {
		return "CommittenteInAggiornamento [cf=" + cf + ", nome=" + nome + ", cognome=" + cognome + ", telefono="
				+ telefono + ", email=" + email + ", committente=" + committente + "]";
	}
	

	

	
	

}
