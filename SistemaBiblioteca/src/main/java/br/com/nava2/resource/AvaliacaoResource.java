package br.com.nava2.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.nava2.constantes.Messages;
import br.com.nava2.entity.Aluno;
import br.com.nava2.entity.AlunoDisciplina;
import br.com.nava2.entity.Avaliacao;
import br.com.nava2.entity.Disciplina;
import br.com.nava2.service.AvaliacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = Messages.SWAGGER_TAG_AVALIACAO_ENDPOINT)
@RestController
@RequestMapping("avaliacoes")
public class AvaliacaoResource {
	
	@Autowired
	private AvaliacaoService avaliacaoService;
	
	@Operation(description = Messages.SWAGGER_GET_ALL)
	@GetMapping
	public ResponseEntity<List<Avaliacao>> listarAvaliacao() {
		List<Avaliacao> listaAvaliacao = avaliacaoService.findAll();
		return ResponseEntity.ok(listaAvaliacao);
	}
	
	@Operation(description = Messages.SWAGGER_BUSCA_POR_PAGINACAO)
	@GetMapping(value = "v1/page")
	public ResponseEntity<Page<Avaliacao>> alunoByPaginationV1(
			@RequestParam(value="v1/page", defaultValue="0") int page,
			@RequestParam(value="linesByPage", defaultValue="10") int linesByPage,
			@RequestParam(value="direction",defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy
			){
		Page<Avaliacao> avaliacao = avaliacaoService.searchByPagination(page, linesByPage, direction, orderBy);
		return ResponseEntity.ok(avaliacao);
	}
	
	@Operation(description = Messages.SWAGGER_BUSCA_POR_PAGINACAO)
	@GetMapping(value = "v2/page")
	public ResponseEntity<Page<Avaliacao>> alunoByPaginationV2(
			@RequestParam(value="v2/page", defaultValue="0") int page,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy
			){
		Page<Avaliacao> avaliacao = avaliacaoService.searchByPagination(page, 30, "DESC", orderBy);
		return ResponseEntity.ok(avaliacao);
	}
	
	@Operation(description = Messages.SWAGGER_INSERT)
	@PostMapping
	public ResponseEntity<Void> inserir(@RequestBody Avaliacao objAvaliacao) {
		objAvaliacao = avaliacaoService.save(objAvaliacao);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(objAvaliacao.getAlunoDisciplina()).toUri();
		return ResponseEntity.created(uri).build();
		
	}
	
	@Operation(description = Messages.SWAGGER_IDALUNO_IDDISCIPLINA)
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
