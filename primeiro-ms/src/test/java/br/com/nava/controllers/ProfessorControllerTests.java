package br.com.nava.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.nava.dtos.ProfessorDTO;
import br.com.nava.entities.ProfessorEntity;
import br.com.nava.services.ProfessorService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProfessorControllerTests {
	
//	temos que fazer as requisições para os endpoints do controller que queremos testar
	
//	responsavel por criar as requisições REST para a camada de Controller
	@Autowired
	private MockMvc mockMVC;
	
	@Autowired
	private ProfessorService professorService;
	
	@Test
	void getAllTest() throws Exception {
		
//		armazena o objeto que fará o teste e colher o resultado
		ResultActions response = mockMVC.perform(get("/professores").contentType("application/json"));
//		pegando o resultado via MvcResult
		MvcResult result = response.andReturn();
//		pegando o resultado no formato de string
		String responseStr = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		
		System.out.println(responseStr);
		
		ObjectMapper mapper = new ObjectMapper();
//		converte o resultado da String em um Array de Objetos de Professor DTO
		ProfessorDTO[] lista = mapper.readValue(responseStr,ProfessorDTO[].class);
//		verifica se a lista de retorno não é vazia, se estiver o teste derá falha
		assertThat(lista).isNotEmpty();
		
	}
	
	@Test
	void getOneTest () throws Exception  {
		ResultActions response = mockMVC.perform(get("/professores/3").contentType("application/json"));
		MvcResult result = response.andReturn();
		
		String responseStr = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		System.out.println(responseStr);
		
		ObjectMapper mapper = new ObjectMapper();
		ProfessorDTO professor = mapper.readValue(responseStr, ProfessorDTO.class);
		
		assertThat(professor.getId()).isEqualTo(3);
		assertThat(result.getResponse().getStatus()).isEqualTo(200);
	}
	
	@Test
	void saveTest() throws Exception {
		
		ObjectMapper mapper = new ObjectMapper();
		
		ProfessorDTO professor = createdValidProfessor();
		
		ResultActions response = mockMVC.perform(post("/professores").content(mapper.writeValueAsString(professor)).contentType("application/json"));
		MvcResult result = response.andReturn();
		
		String responseStr = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		
		System.out.println(responseStr);
		
		ProfessorDTO professorSalvo = mapper.readValue(responseStr,ProfessorDTO.class);
		
		assertThat(professorSalvo.getId()).isPositive();
		assertThat(professorSalvo.getCep()).isEqualTo(professor.getCep());
		assertThat(professorSalvo.getNome()).isEqualTo(professor.getNome());
		assertThat(professorSalvo.getRua()).isEqualTo(professor.getRua());
		assertThat(professorSalvo.getNumero()).isEqualTo(professor.getNumero());
	}
	
	@Test
	void updateTest() throws Exception {
		
		ObjectMapper mapper = new ObjectMapper();
		
		ProfessorDTO professor = createdValidProfessor();
		
		ResultActions response = mockMVC.perform(patch("/professores/4").content(mapper.writeValueAsString(professor)).contentType("application/json"));
		MvcResult result = response.andReturn();
		
		String responseStr = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		
		System.out.println(responseStr);
		
		ProfessorDTO professorSalvo = mapper.readValue(responseStr,ProfessorDTO.class);
		
		assertThat(professorSalvo.getId()).isPositive();
		assertThat(professorSalvo.getCep()).isEqualTo(professor.getCep());
		assertThat(professorSalvo.getNome()).isEqualTo(professor.getNome());
		assertThat(professorSalvo.getRua()).isEqualTo(professor.getRua());
		assertThat(professorSalvo.getNumero()).isEqualTo(professor.getNumero());
	}
	@Test
	void deleteTest() throws Exception  {
		
		ProfessorEntity obj = this.createdValidProfessor().toEntity();		
		ProfessorDTO dto = this.professorService.save(obj);
		
		ResultActions response = mockMVC.perform(delete("/professores/"+dto.getId()).contentType("application/json"));
		MvcResult result = response.andReturn();
		
		assertThat(result.getResponse().getStatus()).isEqualTo(200);
	}
	
	private ProfessorDTO createdValidProfessor() {
		
		ProfessorDTO professorValido = new ProfessorDTO();
		professorValido.setCep("65058-431");
		professorValido.setNome("Carlos Eduardo Diogo Fábio Viana");
		professorValido.setRua("Rua Pinheiro");
		professorValido.setNumero(968);
		
		return professorValido;
		
	}

	

}
