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


import br.com.nava.entities.VendaEntity;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class VendaRepositoryTests {
	
	@Autowired
	VendaRepository vendaRepository;
	
	@Autowired
	TestEntityManager testEntityManager;
	
	@Test
	void findByIdWhenFoundTest() {
		
		VendaEntity vendaEntidade = createValidVenda();

		testEntityManager.persist(vendaEntidade);
		
		Optional<VendaEntity> venda = vendaRepository.findById(vendaEntidade.getId());
		
		assertThat(venda).isNotEmpty();
		
	}
	
	@Test
	void findByIdNotFoundTest() {
		
		Optional<VendaEntity> venda = vendaRepository.findById(1);
		
		assertThat(venda.isPresent()).isFalse();
	}
	
	@Test
	void findAllTest() {
		
		VendaEntity vendaEntidade = createValidVenda();
		
		testEntityManager.persist(vendaEntidade);
		
		List<VendaEntity> vendaes = this.vendaRepository.findAll();
		
		assertThat(vendaes.size()).isEqualTo(1);
	}
	
	@Test
	void saveTest() {
		
		VendaEntity vendaEntidade = createValidVenda();
		
		testEntityManager.persist(vendaEntidade);
		
		VendaEntity vendaSalvo = vendaRepository.save(vendaEntidade);
		
		assertThat(vendaSalvo.getId()).isPositive();
		assertThat(vendaSalvo.getValorTotal()).isEqualTo(vendaEntidade.getValorTotal());

	}
	
	@Test
	void deleteById() {
		
		VendaEntity vendaEntidade = createValidVenda();
		

		VendaEntity vendaTemporario = testEntityManager.persist(vendaEntidade);
		
		vendaRepository.deleteById(vendaTemporario.getId());
		
		Optional<VendaEntity> deletado = vendaRepository.findById(vendaTemporario.getId());
		
		assertThat(deletado.isPresent()).isFalse();
		
	}
	

	private VendaEntity createValidVenda() {
		
		VendaEntity vendaValida = new VendaEntity();
			vendaValida.setValorTotal(550.45f);
			
			return vendaValida;
	}


}
