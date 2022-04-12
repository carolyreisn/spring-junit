package br.com.nava.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nava.dtos.UsuarioDTO;
import br.com.nava.entities.UsuarioEntity;
import br.com.nava.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	public UsuarioRepository usuarioRepository;


	public List<UsuarioDTO> getAll() {
		List<UsuarioEntity> lista = usuarioRepository.findAll();
		
		List<UsuarioDTO> listaDTO = new ArrayList<>();
		
		for (UsuarioEntity usuarioEntity : lista) {
			
			listaDTO.add(usuarioEntity.toDTO());
		}
		
		return listaDTO;
	}
	
	public UsuarioDTO getOne(int id) {
		
		Optional<UsuarioEntity> optionalusuario = usuarioRepository.findById(id);
		UsuarioEntity usuario = optionalusuario.orElse(new UsuarioEntity());
		return usuario.toDTO();

	}
		public UsuarioDTO save(UsuarioEntity usuario) {
		
		return usuarioRepository.save(usuario).toDTO();
		
	}
		public UsuarioDTO update(int id, UsuarioEntity usuario) {
			Optional<UsuarioEntity> optionalusuario = usuarioRepository.findById(id);
			if(optionalusuario.isPresent() ==true) {
				UsuarioEntity usuarioBD = optionalusuario.get();
				usuarioBD.setNome(usuario.getNome());
				usuarioBD.setEmail(usuario.getEmail());
				
				return usuarioRepository.save(usuarioBD).toDTO();
			}
			else {
				return new UsuarioDTO();
			}
			
		}
		
		public void delete(int id) {
			
			usuarioRepository.deleteById(id);
			
		}
}

