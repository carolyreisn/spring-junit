package br.com.nava2.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.nava2.entity.Turma;
import br.com.nava2.repository.TurmaRepository;

@Service
public class TurmaService {
	
	@Autowired
	TurmaRepository turmaRepository;
	
	public List<Turma> listaTodasTurmas() {
		return turmaRepository.findAll();
	}
	
	public Page<Turma> buscaPorPaginacao(int pagina, int linhasPorPagina, String direction, String orderBy){
		PageRequest pageRequest = PageRequest.of(pagina, linhasPorPagina, Direction.valueOf(direction), orderBy);
		return new PageImpl<>(turmaRepository.findAll(),pageRequest, linhasPorPagina);
	}
	
	public Turma buscaPorID(Integer id)throws ObjectNotFoundException {
		Optional<Turma> turma = turmaRepository.findById(id);
		return turma.orElseThrow(() -> new ObjectNotFoundException(null, "Turma não encontrado"));
		
	}
	
	public Turma salvar(Turma turma) {
		return turmaRepository.save(turma);
	}
	
	public Turma alterar(Turma objTurma) {
		Turma turma = buscaPorID(objTurma.getId());
		turma.setNome(objTurma.getNome());
		return salvar(turma);
	}
	
	public void excluir(Integer id) {
		turmaRepository.deleteById(id);
	}

}
