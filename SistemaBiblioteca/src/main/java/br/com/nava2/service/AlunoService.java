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

import br.com.nava2.entity.Aluno;
import br.com.nava2.repository.AlunoRepository;

@Service
public class AlunoService {
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	public List<Aluno> listaTodosAlunos() {
		return alunoRepository.findAll();
	}
	
	public Page<Aluno> searchByPagination (int page, int linesByPage, String direction, String orderBy){
		PageRequest pageRequest = PageRequest.of(page, linesByPage, Direction.valueOf(direction), orderBy);
		return new PageImpl<>(alunoRepository.findAll(),pageRequest,linesByPage);
		
	}
	
	public Aluno buscaPorID(Integer id) throws ObjectNotFoundException {
		Optional<Aluno> aluno = alunoRepository.findById(id);
		return aluno.orElseThrow(() -> new ObjectNotFoundException(null, "Aluno não encontrado"));

	}
	
	public Aluno salvar(Aluno aluno) {
		return alunoRepository.save(aluno);
	}
	
	public Aluno alterar(Aluno objAluno) {
		Aluno aluno = buscaPorID(objAluno.getId());
		aluno.setNome(objAluno.getNome());
		aluno.setTurma(objAluno.getTurma());
		aluno.setDisciplinas(objAluno.getDisciplinas());
		return salvar(aluno);
	}
	
	public void excluir(Integer id) {
		alunoRepository.deleteById(id);
	}
	
	

}
