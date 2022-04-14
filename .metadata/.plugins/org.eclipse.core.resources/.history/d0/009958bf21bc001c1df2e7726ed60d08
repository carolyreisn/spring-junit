package br.com.nava2.resource;

import java.net.URI;
import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.nava2.constantes.Messages;
import br.com.nava2.entity.Disciplina;
import br.com.nava2.service.DisciplinaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = Messages.SWAGGER_TAG_DISCIPLINA_ENDPOINT)
@RestController
@RequestMapping("disciplinas")
public class DisciplinaResource {
	
	@Autowired
	private DisciplinaService disciplinaService;
	
	@Operation(description = Messages.SWAGGER_GET_ALL)
	@GetMapping
	public ResponseEntity<List<Disciplina>> listarDisciplinas(){
		List<Disciplina> disciplinas = disciplinaService.listaTodasDisciplinas();
		return ResponseEntity.ok().body(disciplinas);
	}
	
	@Operation(description = Messages.SWAGGER_GET_ONE)
	@GetMapping("{id}")
	public ResponseEntity<Disciplina> buscaPorID(@PathVariable Integer id) throws ObjectNotFoundException{
		Disciplina disc = disciplinaService.buscaPorID(id);
		return ResponseEntity.ok().body(disc);
	}
	
	@Operation(description = Messages.SWAGGER_INSERT)
	@PostMapping
	public ResponseEntity<Void> inserir (@RequestBody Disciplina disciplina){
		Disciplina disc = disciplinaService.salvar(disciplina);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(disc.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@Operation(description = Messages.SWAGGER_UPDATE)
	@PutMapping("{id}")
	public ResponseEntity<Void> alterar(@RequestBody Disciplina objdisciplina, @PathVariable Integer id) {
		objdisciplina.setId(id);
		disciplinaService.alterar(objdisciplina);
		return ResponseEntity.noContent().build();
		
	} 
	
	@Operation(description = Messages.SWAGGER_DELETE)
	@DeleteMapping("{id}")
	public ResponseEntity<Void> excluir(@PathVariable Integer id){
		disciplinaService.excluir(id);
		return ResponseEntity.noContent().build();
	}
	

}
