package br.com.nava2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nava2.entity.AlunoDisciplina;
import br.com.nava2.entity.Avaliacao;
import br.com.nava2.repository.AvaliacaoRepository;

@Service
public class AvaliacaoService {
	
	@Autowired
	AvaliacaoRepository avaliacaoRepository;
	
	public Avaliacao save(Avaliacao avaliacao) {
		return avaliacaoRepository.save(avaliacao);
	}
	
	public List<Avaliacao> findAll() {
		return avaliacaoRepository.findAll();
	}
	
	public Avaliacao buscarNotaAlunoDisciplina (AlunoDisciplina alunoDisciplina) {
		return avaliacaoRepository.findByAlunoDisciplina(alunoDisciplina);
	}

}
