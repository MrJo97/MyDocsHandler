package webapp.model.documento;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import webapp.model.committente.Committente;
import webapp.model.utente.*;

import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Entity
public class Documento {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idDocumento;
	private String percorso;
	private String nomefile;
	private String descrizione;
	private String dimensione;
	private String data;
	private String ora;
	private String categoria;
	private String tipo;
	private String sottocategoria;
	private String sottotipo;
	private String sottocategoria1;
	private String sottotipo1;
	
	@ManyToOne
	private Utente utente;
	@ManyToOne
	private Committente committente;
	
	
	public String getNomefile() {
		return nomefile;
	}
	public void setNomefile(String nomefile) {
		this.nomefile = nomefile;
	}
	public int getIdDocumento() {
		return idDocumento;
	}
	public void setIdDocumento(int idDocumento) {
		this.idDocumento = idDocumento;
	}
	public String getPercorso() {
		return percorso;
	}
	public void setPercorso(String percorso) {
		this.percorso = percorso;
	}
	
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getDimensione() {
		return dimensione;
	}
	public void setDimensione(String dimensione) {
		this.dimensione = dimensione;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getSottocategoria() {
		return sottocategoria;
	}
	public void setSottocategoria(String sottocategoria) {
		this.sottocategoria = sottocategoria;
	}
	public String getSottotipo() {
		return sottotipo;
	}
	public void setSottotipo(String sottotipo) {
		this.sottotipo = sottotipo;
	}
	public String getSottocategoria1() {
		return sottocategoria1;
	}
	public void setSottocategoria1(String sottocategoria1) {
		this.sottocategoria1 = sottocategoria1;
	}
	public String getSottotipo1() {
		return sottotipo1;
	}
	public void setSottotipo1(String sottotipo1) {
		this.sottotipo1 = sottotipo1;
	}
	public Utente getUtente() {
		return utente;
	}
	public void setUtente(Utente utente) {
		this.utente = utente;
	}
	public Committente getCommittente() {
		return committente;
	}
	public void setCommittente(Committente committente) {
		this.committente = committente;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getOra() {
		return ora;
	}
	public void setOra(String ora) {
		this.ora = ora;
	}
	
	@Override
	public String toString() {
		return "Documento [idDocumento=" + idDocumento + ", percorso=" + percorso + ", nomefile=" + nomefile
				+ ", descrizione=" + descrizione + ", dimensione=" + dimensione + ", data=" + data + ", ora=" + ora
				+ ", categoria=" + categoria + ", tipo=" + tipo + ", sottocategoria=" + sottocategoria + ", sottotipo="
				+ sottotipo + ", sottocategoria1=" + sottocategoria1 + ", sottotipo1=" + sottotipo1 + ", utente="
				+ utente + ", committente=" + committente + "]";
	}
	
	
	
	
	}
	
