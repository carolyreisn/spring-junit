package br.com.nava2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.nava2.entity.AlunoDisciplina;
import br.com.nava2.entity.Avaliacao;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, AlunoDisciplina> {
	
	Avaliacao findByAlunoDisciplina(AlunoDisciplina alunoDisciplina);

}