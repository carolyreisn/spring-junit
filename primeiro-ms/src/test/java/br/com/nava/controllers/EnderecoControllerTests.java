package br.com.nava.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.nava.dtos.EnderecoDTO;
import br.com.nava.entities.EnderecoEntity;
import br.com.nava.services.EnderecoService;

@SpringBootTest
@AutoConfigureMockMvc
public class EnderecoControllerTests {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	EnderecoService enderecoService;

	@Test
	void getAllTest() throws Exception {

		ResultActions response = mockMvc.perform(get("/enderecos").contentType("application/json"));

		MvcResult result = response.andReturn();

		String responseStr = result.getResponse().getContentAsString(StandardCharsets.UTF_8);

		System.out.println(responseStr);

		ObjectMapper mapper = new ObjectMapper();

		EnderecoDTO[] lista = mapper.readValue(responseStr, EnderecoDTO[].class);

		assertThat(lista).isNotEmpty();

	}

	@Test
	void getOneTest() throws Exception {
		ResultActions response = mockMvc.perform(get("/enderecos/1").contentType("application/json"));
		MvcResult result = response.andReturn();

		String responseStr = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		System.out.println(responseStr);

		ObjectMapper mapper = new ObjectMapper();
		EnderecoDTO endereco = mapper.readValue(responseStr, EnderecoDTO.class);

		assertThat(endereco.getId()).isEqualTo(1);
		assertThat(result.getResponse().getStatus()).isEqualTo(200);
	}

	@Test
	void saveTest() throws Exception {

		ObjectMapper mapper = new ObjectMapper();

		EnderecoDTO endereco = createdValidEndereco();

		ResultActions response = mockMvc.perform(
				post("/enderecos").content(mapper.writeValueAsString(endereco)).contentType("application/json"));
		MvcResult result = response.andReturn();

		String responseStr = result.getResponse().getContentAsString(StandardCharsets.UTF_8);

		System.out.println(responseStr);

		EnderecoDTO enderecoSalvo = mapper.readValue(responseStr, EnderecoDTO.class);

		assertThat(enderecoSalvo.getId()).isPositive();
		assertThat(enderecoSalvo.getRua()).isEqualTo(endereco.getRua());
		assertThat(enderecoSalvo.getNumero()).isEqualTo(endereco.getNumero());
		assertThat(enderecoSalvo.getCep()).isEqualTo(endereco.getCep());
		assertThat(enderecoSalvo.getCidade()).isEqualTo(endereco.getCidade());
		assertThat(enderecoSalvo.getEstado()).isEqualTo(endereco.getEstado());

	}

	@Test
	void updateTest() throws Exception {
		
		ObjectMapper mapper = new ObjectMapper();
		
		EnderecoDTO endereco = createdValidEndereco();
		
		ResultActions response = mockMvc.perform(patch("/enderecos/3").content(mapper.writeValueAsString(endereco)).contentType("application/json"));
		MvcResult result = response.andReturn();
		
		String responseStr = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		
		System.out.println(responseStr);
		
		EnderecoDTO enderecoSalvo = mapper.readValue(responseStr,EnderecoDTO.class);
		
		assertThat(enderecoSalvo.getId()).isPositive();
		assertThat(enderecoSalvo.getRua()).isEqualTo(endereco.getRua());
		assertThat(enderecoSalvo.getNumero()).isEqualTo(endereco.getNumero());
		assertThat(enderecoSalvo.getCep()).isEqualTo(endereco.getCep());
		assertThat(enderecoSalvo.getCidade()).isEqualTo(endereco.getCidade());
		assertThat(enderecoSalvo.getEstado()).isEqualTo(endereco.getEstado());
	}
	
	@Test
	void deleteTest() throws Exception  {
		
		EnderecoEntity obj = this.createdValidEndereco().toEntity();
		EnderecoDTO dto = this.enderecoService.save(obj);
		
		ResultActions response = mockMvc.perform(delete("/enderecos/"+dto.getId()).contentType("application/json"));
		MvcResult result = response.andReturn();
		
		assertThat(result.getResponse().getStatus()).isEqualTo(200);
	}
	
	private EnderecoDTO createdValidEndereco() {
		
		EnderecoDTO enderecoValido = new EnderecoDTO();
		enderecoValido.setRua("Rua Marabá");
		enderecoValido.setNumero(415);
		enderecoValido.setCep("76876-572");
		enderecoValido.setCidade("Ariquemes");
		enderecoValido.setEstado("RO");

		return enderecoValido;
	}

}
