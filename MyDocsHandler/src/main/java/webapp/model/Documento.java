package webapp.model;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import webapp.model.Utente;

@Component
@Scope("prototype")
@Entity
public class Documento {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idDocumento;
	private String percorso;
	private String nome;
	private String descrizione;
	private int dimensione;
	private Date data;
	private Time ora;
	private String categoria;
	private String tipo;
	private String sottocategoria;
	private String sottotipo;
	
	@ManyToOne
	@JoinColumn(name = "IdUtente")
	private Utente utente;
	
	@ManyToOne
	@JoinColumn(name = "IdCommittente")
	private Committente committente;
	
	
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
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public int getDimensione() {
		return dimensione;
	}
	public void setDimensione(int dimensione) {
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
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Time getOra() {
		return ora;
	}
	public void setOra(Time ora) {
		this.ora = ora;
	}
	@Override
	public String toString() {
		return "Documento [percorso=" + percorso + ", nome=" + nome + ", descrizione=" + descrizione + ", dimensione="
				+ dimensione + ", data=" + data + ", ora=" + ora + ", categoria=" + categoria + ", tipo=" + tipo
				+ ", sottocategoria=" + sottocategoria + ", sottotipo=" + sottotipo + "]";
	}
	
	
	
	
	}
	
