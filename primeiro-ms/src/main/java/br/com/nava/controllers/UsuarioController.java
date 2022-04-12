package br.com.nava.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nava.dtos.UsuarioDTO;
//import br.com.nava.entities.UsuarioEntity;
import br.com.nava.services.UsuarioService;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

//	private ArrayList<UsuarioEntity> listaUsuario = new ArrayList<>();
//	private int contador = 0;
	
	@Autowired
	private UsuarioService usuarioService;
	
	
	@GetMapping()
	public ResponseEntity<List<UsuarioDTO>> getAll(){
//		return usuarioService.getAll();
		return ResponseEntity.status(HttpStatus.OK).body(usuarioService.getAll());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<UsuarioDTO> getOne(@PathVariable int id) {
		
//		return usuarioService.getOne(id);
		return ResponseEntity.status(HttpStatus.OK).body(usuarioService.getOne(id));

	}
	
	@PostMapping()
	public ResponseEntity<UsuarioDTO> save(@RequestBody UsuarioDTO usuario) {
		
//		return usuarioService.save(usuario);
		return ResponseEntity.status(HttpStatus.OK).body(usuarioService.save(usuario.toEntity()));

	}
	
	@PatchMapping("{id}")
	public ResponseEntity<UsuarioDTO> update(@PathVariable int id, 
			@RequestBody UsuarioDTO usuario) {
		
//		return usuarioService.update(id, usuario);
		return ResponseEntity.status(HttpStatus.OK).body(usuarioService.update(id, usuario.toEntity()));

	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable int id) {

		usuarioService.delete(id);
	}
	

}