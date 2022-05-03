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

import br.com.nava.dtos.ProdutoDTO;
import br.com.nava.entities.ProdutoEntity;
import br.com.nava.services.ProdutoService;

@SpringBootTest
@AutoConfigureMockMvc
public class ProdutoControllerTests {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	ProdutoService produtoService;

	@Test
	void getAllTest() throws Exception {

		ResultActions response = mockMvc.perform(get("/produtos").contentType("application/json"));

		MvcResult result = response.andReturn();

		String responseStr = result.getResponse().getContentAsString(StandardCharsets.UTF_8);

		System.out.println(responseStr);

		ObjectMapper mapper = new ObjectMapper();

		ProdutoDTO[] lista = mapper.readValue(responseStr, ProdutoDTO[].class);

		assertThat(lista).isNotEmpty();

	}

	@Test
	void getOneTest() throws Exception {
		ResultActions response = mockMvc.perform(get("/produtos/1").contentType("application/json"));
		MvcResult result = response.andReturn();

		String responseStr = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		System.out.println(responseStr);

		ObjectMapper mapper = new ObjectMapper();
		ProdutoDTO produto = mapper.readValue(responseStr, ProdutoDTO.class);

		assertThat(produto.getId()).isEqualTo(1);
		assertThat(result.getResponse().getStatus()).isEqualTo(200);
	}

	@Test
	void saveTest() throws Exception {

		ObjectMapper mapper = new ObjectMapper();

		ProdutoDTO produto = createdValidProduto();

		ResultActions response = mockMvc.perform(
				post("/produtos").content(mapper.writeValueAsString(produto)).contentType("application/json"));
		MvcResult result = response.andReturn();

		String responseStr = result.getResponse().getContentAsString(StandardCharsets.UTF_8);

		System.out.println(responseStr);

		ProdutoDTO produtoSalvo = mapper.readValue(responseStr, ProdutoDTO.class);

		assertThat(produtoSalvo.getId()).isPositive();
		assertThat(produtoSalvo.getDescricao()).isEqualTo(produto.getDescricao());
		assertThat(produtoSalvo.getPreco()).isEqualTo(produto.getPreco());

	}

	@Test
	void updateTest() throws Exception {
		
		ObjectMapper mapper = new ObjectMapper();
		
		ProdutoDTO produto = createdValidProduto();
		
		ResultActions response = mockMvc.perform(patch("/produtos/4").content(mapper.writeValueAsString(produto)).contentType("application/json"));
		MvcResult result = response.andReturn();
		
		String responseStr = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		
		System.out.println(responseStr);
		
		ProdutoDTO produtoSalvo = mapper.readValue(responseStr,ProdutoDTO.class);
		
		assertThat(produtoSalvo.getId()).isPositive();
		assertThat(produtoSalvo.getDescricao()).isEqualTo(produto.getDescricao());
		assertThat(produtoSalvo.getPreco()).isEqualTo(produto.getPreco());
	}
	
	@Test
	void deleteTest() throws Exception  {
		
		ProdutoEntity obj = this.createdValidProduto().toEntity();
		ProdutoDTO dto = this.produtoService.save(obj);
		
		ResultActions response = mockMvc.perform(delete("/produtos/"+dto.getId()).contentType("application/json"));
		MvcResult result = response.andReturn();
		
		assertThat(result.getResponse().getStatus()).isEqualTo(200);
	}
	
	private ProdutoDTO createdValidProduto() {
		ProdutoDTO produtoValido = new ProdutoDTO();
		produtoValido.setNome("Banana");
		produtoValido.setPreco(1.99f);
		produtoValido.setDescricao("Nanica");
		
		return produtoValido;
	}

}

