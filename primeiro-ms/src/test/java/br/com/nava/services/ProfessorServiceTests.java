package br.com.nava.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.nava.dtos.ProfessorDTO;
import br.com.nava.entities.ProfessorEntity;
import br.com.nava.repositories.ProfessorRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProfessorServiceTests {
	
	@Autowired
	private ProfessorService professorService;
	
//	a anotação @MockBean serve para sinalizar que iremos "MOCKAR(SIMULAR)" a camada de repository
	@MockBean
	private ProfessorRepository professorRepository;
	
	@Test
	void getAllTest() {
		
//		vamos criar uma lista de entidade de professor com o objetivo de retornar o professorRepositoty.findAll()
//		for acionado
		
		List<ProfessorEntity> listaMockada = new ArrayList<ProfessorEntity>();
		
		ProfessorEntity professorEntidade = new ProfessorEntity();
		professorEntidade.setCep("71515-725");
		professorEntidade.setNome("Isis Nicole Pires");
		professorEntidade.setRua("Quadra SHIN QL 11 Conjunto 2");
		professorEntidade.setNumero(353);
		professorEntidade.setId(1);
		
		listaMockada.add(professorEntidade);
		
		when(professorRepository.findAll()).thenReturn(listaMockada);
		
		List<ProfessorDTO> retorno =  professorService.getAll();
		
		assertThat(listaMockada.get(0).getCep()).isEqualTo(retorno.get(0).getCep());
		assertThat(listaMockada.get(0).getNome()).isEqualTo(retorno.get(0).getNome());
		assertThat(listaMockada.get(0).getRua()).isEqualTo(retorno.get(0).getRua());
		assertThat(listaMockada.get(0).getNumero()).isEqualTo(retorno.get(0).getNumero());
		assertThat(listaMockada.get(0).getId()).isEqualTo(retorno.get(0).getId());
		
	}
	
	@Test
	void getOneWhenFoundObjectTest() {
		
		ProfessorEntity professorEntidade = new ProfessorEntity();
		professorEntidade.setCep("71515-725");
		professorEntidade.setNome("Isis Nicole Pires");
		professorEntidade.setRua("Quadra SHIN QL 11 Conjunto 2");
		professorEntidade.setNumero(353);
		professorEntidade.setId(1);
		
		Optional<ProfessorEntity> opcional = Optional.of(professorEntidade);
		
		when (professorRepository.findById(1)).thenReturn(opcional);
		
		ProfessorDTO obj = professorService.getOne(1);
		
		assertThat(obj.getCep()).isEqualTo(professorEntidade.getCep());
		assertThat(obj.getNome()).isEqualTo(professorEntidade.getNome());
		assertThat(obj.getRua()).isEqualTo(professorEntidade.getRua());
		assertThat(obj.getNumero()).isEqualTo(professorEntidade.getNumero());
		assertThat(obj.getId()).isEqualTo(professorEntidade.getId());
		
	}
	
	@Test
	void getOneWhenNotFoundObjectTest() {
		
		Optional<ProfessorEntity> opcional = Optional.empty();
		
		when (professorRepository.findById(1)).thenReturn(opcional);
		
		ProfessorDTO obj = professorService.getOne(1);
		
		ProfessorEntity professorEntidade = new ProfessorEntity();
		
		assertThat(obj.getCep()).isEqualTo(professorEntidade.getCep());
		assertThat(obj.getNome()).isEqualTo(professorEntidade.getNome());
		assertThat(obj.getRua()).isEqualTo(professorEntidade.getRua());
		assertThat(obj.getNumero()).isEqualTo(professorEntidade.getNumero());
		assertThat(obj.getId()).isEqualTo(professorEntidade.getId());
		
		
	}

}
