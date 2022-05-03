package br.com.nava.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.nava.entities.UsuarioEntity;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class UsuarioRepositoryTests {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	TestEntityManager testEntityManager;
	
	@Test
	void findByIdWhenFoundTest() {
		
		UsuarioEntity usuarioEntidade = createValidUsuario();

		testEntityManager.persist(usuarioEntidade);
		
		Optional<UsuarioEntity> usuario = usuarioRepository.findById(usuarioEntidade.getId());
		
		assertThat(usuario).isNotEmpty();
		
	}
	
	@Test
	void findByIdNotFoundTest() {
		
		Optional<UsuarioEntity> usuario = usuarioRepository.findById(1);
		
		assertThat(usuario.isPresent()).isFalse();
	}
	
	@Test
	void findAllTest() {
		
		UsuarioEntity usuarioEntidade = createValidUsuario();
		
		testEntityManager.persist(usuarioEntidade);
		
		List<UsuarioEntity> usuarioes = this.usuarioRepository.findAll();
		
		assertThat(usuarioes.size()).isEqualTo(1);
	}
	
	@Test
	void saveTest() {
		
		UsuarioEntity usuarioEntidade = createValidUsuario();
		
		testEntityManager.persist(usuarioEntidade);
		
		UsuarioEntity usuarioSalvo = usuarioRepository.save(usuarioEntidade);
		
		assertThat(usuarioSalvo.getId()).isPositive();
		assertThat(usuarioSalvo.getNome()).isEqualTo(usuarioEntidade.getNome());
		assertThat(usuarioSalvo.getEmail()).isEqualTo(usuarioEntidade.getEmail());

	}
	
	@Test
	void deleteById() {
		
		UsuarioEntity usuarioEntidade = createValidUsuario();
		

		UsuarioEntity usuarioTemporario = testEntityManager.persist(usuarioEntidade);
		
		usuarioRepository.deleteById(usuarioTemporario.getId());
		
		Optional<UsuarioEntity> deletado = usuarioRepository.findById(usuarioTemporario.getId());
		
		assertThat(deletado.isPresent()).isFalse();
		
	}
	
	private UsuarioEntity createValidUsuario() {
		
		UsuarioEntity usuarioValido = new UsuarioEntity();
		usuarioValido.setEmail("Antonella Clara Nunes Reis");
		usuarioValido.setNome("antonellaclaranunes@eptvreis.com.br");
		
		return usuarioValido;
	}


}
