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

import br.com.nava.entities.ProdutoEntity;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class ProdutoRepositoryTests {
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	@Autowired
	TestEntityManager testEntityManager;
	
	@Test
	void findByIdWhenFoundTest() {
		
		ProdutoEntity produtoEntidade = createValidProduto();

		testEntityManager.persist(produtoEntidade);
		
		Optional<ProdutoEntity> produto = produtoRepository.findById(produtoEntidade.getId());
		
		assertThat(produto).isNotEmpty();
		
	}
	
	@Test
	void findByIdNotFoundTest() {
		
		Optional<ProdutoEntity> produto = produtoRepository.findById(1);
		
		assertThat(produto.isPresent()).isFalse();
	}
	
	@Test
	void findAllTest() {
		
		ProdutoEntity produtoEntidade = createValidProduto();
		
		testEntityManager.persist(produtoEntidade);
		
		List<ProdutoEntity> produtoes = this.produtoRepository.findAll();
		
		assertThat(produtoes.size()).isEqualTo(1);
	}
	
	@Test
	void saveTest() {
		
		ProdutoEntity produtoEntidade = createValidProduto();
		
		testEntityManager.persist(produtoEntidade);
		
		ProdutoEntity produtoSalvo = produtoRepository.save(produtoEntidade);
		
		assertThat(produtoSalvo.getId()).isPositive();
		assertThat(produtoSalvo.getNome()).isEqualTo(produtoEntidade.getNome());
		assertThat(produtoSalvo.getPreco()).isEqualTo(produtoEntidade.getPreco());
		assertThat(produtoSalvo.getDescricao()).isEqualTo(produtoEntidade.getDescricao());

	}
	
	@Test
	void deleteById() {
		
		ProdutoEntity produtoEntidade = createValidProduto();
		

		ProdutoEntity produtoTemporario = testEntityManager.persist(produtoEntidade);
		
		produtoRepository.deleteById(produtoTemporario.getId());
		
		Optional<ProdutoEntity> deletado = produtoRepository.findById(produtoTemporario.getId());
		
		assertThat(deletado.isPresent()).isFalse();
		
	}
	
	private ProdutoEntity createValidProduto() {
		
		ProdutoEntity produtoValido = new ProdutoEntity();
		produtoValido.setNome("Banana");
		produtoValido.setPreco(1.99f);
		produtoValido.setDescricao("Nanica");

		return produtoValido;
	}

}
