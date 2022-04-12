package br.com.nava.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nava.dtos.EnderecoDTO;
import br.com.nava.entities.EnderecoEntity;
import br.com.nava.repositories.EnderecoRepository;

@Service
public class EnderecoService {
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public List<EnderecoDTO> getAll() {
		List<EnderecoEntity> lista = enderecoRepository.findAll();
		
		List<EnderecoDTO> listaDTO = new ArrayList<>();
		
		for (EnderecoEntity enderecoEntity : lista) {
			
			listaDTO.add(enderecoEntity.toDTO());
		}
		
		return listaDTO;
	}
	
	public EnderecoDTO getOne(int id) {
		
		Optional<EnderecoEntity> optionalendereco = enderecoRepository.findById(id);
		EnderecoEntity endereco = optionalendereco.orElse(new EnderecoEntity());
		return endereco.toDTO();

	}
		public EnderecoDTO save(EnderecoEntity endereco) {
		
		return enderecoRepository.save(endereco).toDTO();
		
	}
		public EnderecoDTO update(int id, EnderecoEntity endereco) {
			Optional<EnderecoEntity> optionalendereco = enderecoRepository.findById(id);
			if(optionalendereco.isPresent() ==true) {
				EnderecoEntity enderecoBD = optionalendereco.get();
				enderecoBD.setRua(endereco.getRua());
				enderecoBD.setNumero(endereco.getNumero());
				enderecoBD.setCep(endereco.getCep());
				enderecoBD.setCidade(endereco.getCidade());
				enderecoBD.setEstado(endereco.getEstado());
				enderecoBD.setUsuario(endereco.getUsuario());
				
				return enderecoRepository.save(enderecoBD).toDTO();
			}
			else {
				return new EnderecoDTO();
			}
			
		}
		
		public void delete(int id) {
			
			enderecoRepository.deleteById(id);
			
		}

}