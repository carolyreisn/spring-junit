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

import br.com.nava.dtos.UsuarioDTO;
import br.com.nava.entities.UsuarioEntity;
import br.com.nava.services.UsuarioService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UsuarioControllerTests {
	
	@Autowired
	private MockMvc mockMVC;
	
	@Autowired
	UsuarioService usuarioService;
	
	@Test
	void getAllTest() throws Exception {
		
		ResultActions response = mockMVC.perform(get("/usuarios").contentType("application/json"));

		MvcResult result = response.andReturn();

		String responseStr = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		
		System.out.println(responseStr);
		
		ObjectMapper mapper = new ObjectMapper();

		UsuarioDTO[] lista = mapper.readValue(responseStr,UsuarioDTO[].class);

		assertThat(lista).isNotEmpty();
	}
	
	@Test
	void getOneTest () throws Exception  {
		ResultActions response = mockMVC.perform(get("/usuarios/1").contentType("application/json"));
		MvcResult result = response.andReturn();
		
		String responseStr = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		System.out.println(responseStr);
		
		ObjectMapper mapper = new ObjectMapper();
		UsuarioDTO usuario = mapper.readValue(responseStr, UsuarioDTO.class);
		
		assertThat(usuario.getId()).isEqualTo(1);
		assertThat(result.getResponse().getStatus()).isEqualTo(200);
	}
	
	@Test
	void saveTest() throws Exception {
		
		ObjectMapper mapper = new ObjectMapper();
		
		UsuarioDTO usuario = createdValidUsuario();
		
		ResultActions response = mockMVC.perform(post("/usuarios").content(mapper.writeValueAsString(usuario)).contentType("application/json"));
		MvcResult result = response.andReturn();
		
		String responseStr = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		
		System.out.println(responseStr);
		
		UsuarioDTO usuarioSalvo = mapper.readValue(responseStr,UsuarioDTO.class);
		
		assertThat(usuarioSalvo.getId()).isPositive();
		assertThat(usuarioSalvo.getNome()).isEqualTo(usuario.getNome());
		assertThat(usuarioSalvo.getEmail()).isEqualTo(usuario.getEmail());
	}
	
	@Test
	void updateTest() throws Exception {
		
		ObjectMapper mapper = new ObjectMapper();
		
		UsuarioDTO usuario = createdValidUsuario();
		
		ResultActions response = mockMVC.perform(patch("/usuarios/5").content(mapper.writeValueAsString(usuario)).contentType("application/json"));
		MvcResult result = response.andReturn();
		
		String responseStr = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		
		System.out.println(responseStr);
		
		UsuarioDTO usuarioSalvo = mapper.readValue(responseStr,UsuarioDTO.class);
		
		assertThat(usuarioSalvo.getId()).isPositive();
		assertThat(usuarioSalvo.getNome()).isEqualTo(usuario.getNome());
		assertThat(usuarioSalvo.getEmail()).isEqualTo(usuario.getEmail());
	}
	
	@Test
	void deleteTest() throws Exception  {
		
		UsuarioEntity obj = this.createdValidUsuario().toEntity();
		UsuarioDTO dto = this.usuarioService.save(obj);
		
		ResultActions response = mockMVC.perform(delete("/usuarios/"+dto.getId()).contentType("application/json"));
		MvcResult result = response.andReturn();
		
		assertThat(result.getResponse().getStatus()).isEqualTo(200);
	}
	
	private UsuarioDTO createdValidUsuario() {
		
		UsuarioDTO usuarioValido = new UsuarioDTO();
		usuarioValido.setEmail("Antonella Clara Nunes Reis");
		usuarioValido.setNome("antonellaclaranunes@eptvreis.com.br");
		
		return usuarioValido;
	}

}
