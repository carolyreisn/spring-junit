package br.com.nava2.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "avaliacoes")
public class Avaliacao implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3257399438198644204L;
	@EmbeddedId
	private AlunoDisciplina alunoDisciplina;
	private String conceito;
	
	public AlunoDisciplina getAlunoDisciplina() {
		return alunoDisciplina;
	}
	
	public void setAlunoDisciplina(AlunoDisciplina alunoDisciplina) {
		this.alunoDisciplina = alunoDisciplina;
	}
	
	public String getConceito() {
		return conceito;
	}
	
	public void setConceito(String conceito) {
		this.conceito = conceito;
	}

}
