package webapp.model.recapito;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import webapp.model.committente.Committente;

@Component
@Scope("prototype")
@Entity
public class Recapito {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idRecapito;
	private String telefono;
	private String email;
	
	
	@ManyToOne
	private Committente committente;
	
	public int getIdRecapito() {
		return idRecapito;
	}
	public void setIdRecapito(int idRecapito) {
		this.idRecapito = idRecapito;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
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
		return "Recapito [idRecapito=" + idRecapito + ", telefono=" + telefono + ", email=" + email + "]";
	}
	
	
	

}
