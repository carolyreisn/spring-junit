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

import br.com.nava.entities.ProfessorEntity;

//permite manupular o banco de dados com rolback
@DataJpaTest
@ExtendWith(SpringExtension.class)
public class ProfessorRepositoryTests {
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	@Autowired
	private TestEntityManager testEntityManager;
	
	@Test
	void findByIdWhenFoundTest() {
		
		ProfessorEntity professorEntidade = createValidProfessor();
		
//		vai persistir a entidade no banco de dados para testar o findByID
//		ao final do teste, esta entidade será deletada
		testEntityManager.persist(professorEntidade);
		
//		buscas a entidade no banco de dados para testar o findById
		
//		execução do findById
		Optional<ProfessorEntity> professor = professorRepository.findById(professorEntidade.getId());
		
//		validando a resposta - se o objeto encontrado não é nulo
		assertThat(professor).isNotNull();
		
	}
	
	@Test
	void findByIdNotFoundTest() {
		
//		buscar uma entidade na qual não existe no banco de dados
		Optional<ProfessorEntity> professor = professorRepository.findById(1);
		
//		temos que verificar se o opcional não possui valores, ou seja, isPresent() possuir um valor falso
		assertThat(professor.isPresent()).isFalse();
	}
	
	@Test
	void findAllTest() {
		
		ProfessorEntity professorEntidade = createValidProfessor();
		
//		salvando temporiamente o professor no banco de dados
		testEntityManager.persist(professorEntidade);
		
//		execução
		List<ProfessorEntity> professores = this.professorRepository.findAll();
		
//		verificar
		assertThat(professores.size()).isEqualTo(1);
	}
	
	@Test
	void saveTest() {
		
		ProfessorEntity professorEntidade = createValidProfessor();
		
		testEntityManager.persist(professorEntidade);
		
		ProfessorEntity professorSalvo = professorRepository.save(professorEntidade);
		
//		validação
		assertThat(professorSalvo.getId()).isNotNull();
		assertThat(professorSalvo.getNome()).isEqualTo(professorEntidade.getNome());
		assertThat(professorSalvo.getNumero()).isEqualTo(professorEntidade.getNumero());
		assertThat(professorSalvo.getRua()).isEqualTo(professorEntidade.getRua());
		assertThat(professorSalvo.getCep()).isEqualTo(professorEntidade.getCep());
		assertThat(professorSalvo.getCpf()).isEqualTo(professorEntidade.getCpf());
	}
	
	@Test
	void deleteById() {
		
		ProfessorEntity professorEntidade = createValidProfessor();
		
//		salvando temporariamente o professor no banco de dados
		ProfessorEntity professorTemporario = testEntityManager.persist(professorEntidade);
		
//		execução
		professorRepository.deleteById(professorTemporario.getId());
		
//		verificação
//		busca o professor deletado e compara a reposta
		Optional<ProfessorEntity> deletado = professorRepository.findById(professorTemporario.getId());
		
		assertThat(deletado.isPresent()).isFalse();
		
	}
	
	private ProfessorEntity createValidProfessor() {
		
		// instanciando o novo objeto do tipo ProfessorEntity
		ProfessorEntity professorEntidade = new ProfessorEntity();
		
		// colocando valores nos atributos de ProfessorEntity
		professorEntidade.setCep("71515-725");
		professorEntidade.setNome("Isis Nicole Pires");
		professorEntidade.setRua("Quadra SHIN QL 11 Conjunto 2");
		professorEntidade.setNumero(353);
//		professorEntidade.setId(1);
		
		// retornando este novo objeto criado
		return professorEntidade;
	}
	
	

}
