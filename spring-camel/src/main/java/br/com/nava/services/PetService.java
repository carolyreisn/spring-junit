package br.com.nava.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nava.dtos.PetDto;
import br.com.nava.entities.PetEntity;
import br.com.nava.repositories.PetRepository;

@Service
public class PetService {
	
	@Autowired
	public PetRepository petRepository;


	public List<PetDto> getAll() {
		List<PetEntity> lista = petRepository.findAll();
		
		List<PetDto> listaDTO = new ArrayList<>();
		
		for (PetEntity petEntity : lista) {
			
			listaDTO.add(petEntity.toDTO());
		}
		
		return listaDTO;
	}
	
	public PetDto getOne(int id) {
		
		Optional<PetEntity> optionalpet = petRepository.findById(id);
		PetEntity pet = optionalpet.orElse(new PetEntity());
		return pet.toDTO();

	}
		public PetDto save(PetEntity pet) {
		
		return petRepository.save(pet).toDTO();
		
	}
		public PetDto update(int id, PetEntity pet) {
			Optional<PetEntity> optionalpet = petRepository.findById(id);
			if(optionalpet.isPresent() ==true) {
				PetEntity petBD = optionalpet.get();
				petBD.setNome(pet.getNome());
				
				return petRepository.save(petBD).toDTO();
			}
			else {
				return new PetDto();
			}
			
		}
		
		public void delete(int id) {
			
			petRepository.deleteById(id);
			
		}
}
