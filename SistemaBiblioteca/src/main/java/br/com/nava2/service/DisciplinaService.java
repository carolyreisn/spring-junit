package br.com.nava2.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nava2.entity.Disciplina;
import br.com.nava2.repository.DisciplinaRepository;

@Service
public class DisciplinaService {
	
	@Autowired
	private DisciplinaRepository disciplinaRepository;
	
	public List<Disciplina> listaTodasDisciplinas() {
		return disciplinaRepository.findAll();
	}
	
	public Disciplina buscaPorID(Integer id) throws ObjectNotFoundException{
		Optional<Disciplina> disciplina = disciplinaRepository.findById(id);
		return disciplina.orElseThrow(() -> new ObjectNotFoundException(null, "Disciplina não encontrada"));
	}
	
	public Disciplina salvar (Disciplina disciplina) {
		return disciplinaRepository.save(disciplina);
	}
	
	public Disciplina alterar(Disciplina objDisciplina) {
		Disciplina disc = buscaPorID(objDisciplina.getId());
		disc.setNome(objDisciplina.getNome());
		return salvar(disc);
	}
	
	public void excluir(Integer id) {
		disciplinaRepository.deleteById(id);
	}

}
