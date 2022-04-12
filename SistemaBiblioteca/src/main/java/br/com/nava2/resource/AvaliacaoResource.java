package br.com.nava2.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.nava2.entity.Aluno;
import br.com.nava2.entity.AlunoDisciplina;
import br.com.nava2.entity.Avaliacao;
import br.com.nava2.entity.Disciplina;
import br.com.nava2.service.AvaliacaoService;

@RestController
@RequestMapping("avaliacoes")
public class AvaliacaoResource {
	
	@Autowired
	private AvaliacaoService avaliacaoService;
	
	@GetMapping
	public ResponseEntity<List<Avaliacao>> listarAvaliacao() {
		List<Avaliacao> listaAvaliacao = avaliacaoService.findAll();
		return ResponseEntity.ok(listaAvaliacao);
	}
	
	@PostMapping
	public ResponseEntity<Void> inserir(@RequestBody Avaliacao objAvaliacao) {
		objAvaliacao = avaliacaoService.save(objAvaliacao);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(objAvaliacao.getAlunoDisciplina()).toUri();
		return ResponseEntity.created(uri).build();
		
	}
	
	@GetMapping("{idAluno}/{idDisciplina}")
	public ResponseEntity<Avaliacao> buscarAvaliacaoAlunoPorDisciplina(@PathVariable Integer idAluno, @PathVariable Integer idDisciplina) {
		Aluno aluno = new Aluno();
		aluno.setId(idAluno);
		
		Disciplina disciplina = new Disciplina();
		disciplina.setId(idDisciplina);
		
		AlunoDisciplina alunoDisciplina = new AlunoDisciplina();
		alunoDisciplina.setAluno(aluno);
		alunoDisciplina.setDisciplina(disciplina);
		
		Avaliacao avaliacao = avaliacaoService.buscarNotaAlunoDisciplina(alunoDisciplina);
		
		return ResponseEntity.ok(avaliacao);
	
	} 

}
