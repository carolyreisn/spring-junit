package br.com.nava2.resource;

import java.net.URI;
import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.nava2.constantes.Messages;
import br.com.nava2.entity.Aluno;
import br.com.nava2.service.AlunoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = Messages.SWAGGER_TAG_ALUNO_ENDPOINT)
@RestController
@RequestMapping("aluno")
public class AlunoResource {
	
	@Autowired
	private AlunoService alunoService;
	
	@Operation(description = Messages.SWAGGER_GET_ALL)
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Aluno>> listarAluno() {
		List<Aluno> alunos = alunoService.listaTodosAlunos();
		return ResponseEntity.ok().body(alunos);
	}
	
	@Operation(description = Messages.SWAGGER_BUSCA_POR_PAGINACAO)
	@GetMapping(value = "page")
	public ResponseEntity<Page<Aluno>> alunoByPagination(
			@RequestParam(value="page", defaultValue="0") int page,
			@RequestParam(value="linesByPage", defaultValue="10") int linesByPage,
			@RequestParam(value="direction",defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy
			){
		Page<Aluno> alunos = alunoService.searchByPagination(page, linesByPage, direction, orderBy);
		return ResponseEntity.ok(alunos);
		
	}
	
	
	@Operation(description = Messages.SWAGGER_GET_ONE)
	@RequestMapping(value ="{id}", method = RequestMethod.GET)
	public ResponseEntity<Aluno> buscaPorID(@PathVariable Integer id) throws ObjectNotFoundException {
		Aluno aluno = alunoService.buscaPorID(id);
		return ResponseEntity.ok().body(aluno);
	}
	
	@Operation(description = Messages.SWAGGER_INSERT)
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> inserir(@RequestBody Aluno objAluno) {
		Aluno aluno = alunoService.salvar(objAluno);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(aluno.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@Operation(description = Messages.SWAGGER_DELETE)
	@RequestMapping(value="{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluir(@PathVariable Integer id) {
		alunoService.excluir(id);
		return ResponseEntity.noContent().build();
	}
	
	@Operation(description = Messages.SWAGGER_UPDATE)
	@RequestMapping(value ="{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> alterar(@RequestBody Aluno objAluno, @PathVariable Integer id) {
		objAluno.setId(id);
		alunoService.alterar(objAluno);
		return ResponseEntity.noContent().build();
		
	}

}
