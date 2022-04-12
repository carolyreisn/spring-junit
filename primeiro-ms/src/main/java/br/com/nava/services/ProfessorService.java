package br.com.nava.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nava.dtos.ProfessorDTO;
import br.com.nava.entities.ProfessorEntity;
import br.com.nava.repositories.ProfessorRepository;

@Service
public class ProfessorService {
	
	@Autowired
	private ProfessorRepository professorRepository;

//	public void mostrar() {
//		System.out.println("mostrar");
//	}
	
	public List<ProfessorDTO> getAll(){
		List<ProfessorEntity> lista = professorRepository.findAll();
		
		List<ProfessorDTO> listaDTO = new ArrayList<>();
		
		for (ProfessorEntity professorEntity : lista) {
//			ProfessorDTO dto = new ProfessorDTO();
//			dto.setId(professorEntity.getId());
//			dto.setCep(professorEntity.getCep());
//			dto.setCpf(professorEntity.getCpf());
//			dto.setNome(professorEntity.getNome());
//			dto.setNumero(professorEntity.getNumero());
//			dto.setRua(professorEntity.getRua());
//			listaDTO.add(dto);
			
			listaDTO.add(professorEntity.toDTO());
		}
		
		return listaDTO;
			
	}
		
		
	
		
	
//	public ProfessorEntity getOne(int id, ArrayList<ProfessorEntity> listaProfessor) {
//		
//		int indice = findIndex(id, listaProfessor);
//		return (indice >= 0 ? listaProfessor.get(indice) : null);
//	}
	
	public ProfessorDTO getOne(int id) {
		
		Optional<ProfessorEntity> optional = professorRepository.findById(id);
		ProfessorEntity professor = optional.orElse(new ProfessorEntity());
		return professor.toDTO();
	}
	
	public ProfessorDTO save(ProfessorEntity professor) {
		
		return professorRepository.save(professor).toDTO();
		
	}

//	 // variável professor contém os dados vindo da requisição 
//	public ProfessorEntity update(int id, ProfessorEntity professor, 
//				ArrayList<ProfessorEntity> listaProfessor ) {
//		
//		int indice = findIndex(id, listaProfessor);
//		
//		if (indice >= 0) {
//			
//			listaProfessor.get(indice).setNome( professor.getNome() );
//			listaProfessor.get(indice).setCpf( professor.getCpf() );
//			listaProfessor.get(indice).setRua( professor.getRua() );
//			listaProfessor.get(indice).setNumero( professor.getNumero() );
//			listaProfessor.get(indice).setCep( professor.getCep() );
//			
//			return listaProfessor.get(indice);
//		}
//		
//		return null;
//	}
	public ProfessorDTO update(int id, ProfessorEntity professor) {
		
		Optional<ProfessorEntity> optional = professorRepository.findById(id);
		
		if(optional.isPresent()== true) {
			ProfessorEntity professorBD = optional.get();
			professorBD.setNome(professor.getNome());
			professorBD.setCep(professor.getCep());
			professorBD.setCpf(professor.getCpf());
			professorBD.setNumero(professor.getNumero());
			professorBD.setRua(professor.getRua());
			
			return professorRepository.save(professorBD).toDTO();
		}
		else {
			return new ProfessorEntity().toDTO();
		}
	}
	
//		public void delete(int id, ArrayList<ProfessorEntity> listaProfessor) {
//		
//			int indice = findIndex(id, listaProfessor);
//		
//			if (indice >= 0) listaProfessor.remove(indice);
//	}
		
		
		public void delete(int id) {
			
			professorRepository.deleteById(id);
	}
		
//	public int findIndex(int id, ArrayList<ProfessorEntity> listaProfessor) {
//		
//		for (int i = 0; i < listaProfessor.size(); i++) {
//			if (listaProfessor.get(i).getId() == id) {
//				return i;
//			}
//		}
//		
//		return -1;
//	}
	
}
