package br.com.nava.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.nava.dtos.VendaDTO;
import br.com.nava.entities.ProdutoEntity;
import br.com.nava.entities.UsuarioEntity;
import br.com.nava.repositories.ProdutoRepository;
import br.com.nava.repositories.UsuarioRepository;
import br.com.nava.services.VendaService;

@SpringBootTest
@AutoConfigureMockMvc
public class VendaControllerTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	VendaService vendaService;

	@Test
	void getAllTest() throws Exception {

		ResultActions response = mockMvc.perform(get("/vendas").contentType("application/json"));

		MvcResult result = response.andReturn();

		String responseStr = result.getResponse().getContentAsString(StandardCharsets.UTF_8);

		System.out.println(responseStr);

		ObjectMapper mapper = new ObjectMapper();

		VendaDTO[] lista = mapper.readValue(responseStr, VendaDTO[].class);

		assertThat(lista).isNotEmpty();

	}

	@Test
	void getOneTest() throws Exception {
		ResultActions response = mockMvc.perform(get("/vendas/1").contentType("application/json"));
		MvcResult result = response.andReturn();

		String responseStr = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		System.out.println(responseStr);

		ObjectMapper mapper = new ObjectMapper();
		VendaDTO venda = mapper.readValue(responseStr, VendaDTO.class);

		assertThat(venda.getId()).isEqualTo(1);
		assertThat(result.getResponse().getStatus()).isEqualTo(200);
	}

	@Test
	void saveTest() throws Exception {

		ObjectMapper mapperVenda = new ObjectMapper();
		
		VendaDTO venda = createdValidVenda();
	
		ResultActions responseVenda = mockMvc.perform(
				post("/vendas").content(mapperVenda.writeValueAsString(venda)).contentType("application/json"));
		MvcResult resultVenda = responseVenda.andReturn();

		String responseStrVenda = resultVenda.getResponse().getContentAsString(StandardCharsets.UTF_8);

		System.out.println(responseStrVenda);

		VendaDTO vendaSalvo = mapperVenda.readValue(responseStrVenda, VendaDTO.class);

		assertThat(vendaSalvo.getId()).isPositive();
		assertThat(vendaSalvo.getValorTotal()).isEqualTo(venda.getValorTotal());
		assertThat(resultVenda.getResponse().getStatus() ).isEqualTo(200);

	}

	@Test
	void updateTest() throws Exception {
		
		ObjectMapper mapper = new ObjectMapper();
		
		VendaDTO venda = createdValidVenda();
		
		ResultActions response = mockMvc.perform(patch("/vendas/21").content(mapper.writeValueAsString(venda)).contentType("application/json"));
		MvcResult resultVenda = response.andReturn();
		
		String responseStr = resultVenda.getResponse().getContentAsString(StandardCharsets.UTF_8);
		
		System.out.println(responseStr);
		
		VendaDTO vendaSalvo = mapper.readValue(responseStr,VendaDTO.class);
		
		assertThat(vendaSalvo.getId()).isPositive();
		assertThat(vendaSalvo.getValorTotal()).isEqualTo(venda.getValorTotal());		

	}
	
	@Test
	void deleteTest() throws Exception  {
		ResultActions response = mockMvc.perform(delete("/vendas/20").contentType("application/json"));
		MvcResult result = response.andReturn();
		
		assertThat(result.getResponse().getStatus()).isEqualTo(200);
	}
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	private VendaDTO createdValidVenda() {
		VendaDTO vendaValida = new VendaDTO();
		vendaValida.setValorTotal(550.45f);
		
		List<UsuarioEntity> usuarios = usuarioRepository.findAll();
		
		List<ProdutoEntity> produtos = produtoRepository.findAll();
		
		vendaValida.setUsuario(usuarios.get(1));
		vendaValida.setProdutos(produtos);
		
		return vendaValida;
	}
	
}
