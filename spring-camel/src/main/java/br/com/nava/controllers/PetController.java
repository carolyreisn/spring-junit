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

import br.com.nava.dtos.PetDto;
import br.com.nava.services.PetService;

@RestController
@RequestMapping(value = "pets")
public class PetController {
	
	@Autowired
	private PetService petService;
	
	
	@GetMapping()
	public ResponseEntity<List<PetDto>> getAll(){
//		return petService.getAll();
		return ResponseEntity.status(HttpStatus.OK).body(petService.getAll());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<PetDto> getOne(@PathVariable int id) {
		
//		return petService.getOne(id);
		return ResponseEntity.status(HttpStatus.OK).body(petService.getOne(id));

	}
	
	@PostMapping()
	public ResponseEntity<PetDto> save(@RequestBody PetDto pet) {
		
//		return petService.save(pet);
		return ResponseEntity.status(HttpStatus.OK).body(petService.save(pet.toEntity()));

	}
	
	@PatchMapping("{id}")
	public ResponseEntity<PetDto> update(@PathVariable int id, 
			@RequestBody PetDto pet) {
		
//		return petService.update(id, pet);
		return ResponseEntity.status(HttpStatus.OK).body(petService.update(id, pet.toEntity()));

	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable int id) {

		petService.delete(id);
	}
	
//	private final String [] PETS = new String[] {
//		"Lilás", "Bagheera", "Madara"	
//	};
	
//	@GetMapping(value = "{id}")
//	public ResponseEntity<PetEntity> getOne(@PathVariable int id){
//		
//		if (id >=0 && id < PETS.length) {
//			PetEntity pet = new PetEntity(id, PETS[id]);
////			System.out.println(pet);
//			return ResponseEntity.ok(pet);
//		}
//		return null;
//	}

}
