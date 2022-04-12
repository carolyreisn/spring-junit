package br.com.nava2.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class AlunoDisciplina implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7130092172545432210L;

	@ManyToOne
	private Aluno aluno;
	
	@ManyToOne
	private Disciplina disciplina;

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	
	

}
