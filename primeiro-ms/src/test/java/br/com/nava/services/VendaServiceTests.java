package br.com.nava.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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

import br.com.nava.dtos.VendaDTO;
import br.com.nava.entities.VendaEntity;
import br.com.nava.repositories.VendaRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class VendaServiceTests {
	
	@Autowired
	private VendaService vendaService;

	@MockBean
	private VendaRepository vendaRepository;

	@Test
	void getAllTest() {

		List<VendaEntity> listaMockada = new ArrayList<VendaEntity>();

		VendaEntity vendaEntidade = createValidVenda();

		listaMockada.add(vendaEntidade);

		when(vendaRepository.findAll()).thenReturn(listaMockada);

		List<VendaDTO> retorno = vendaService.getAll();

		isVendaValid(retorno.get(0), listaMockada.get(0));

	}
	
	@Test
	void getOneWhenFoundObjectTest() {
		
		VendaEntity vendaEntidade = createValidVenda();
		
		Optional<VendaEntity> opcional = Optional.of(vendaEntidade);
		
		when (vendaRepository.findById(1)).thenReturn(opcional);
		
		VendaDTO obj = vendaService.getOne(1);
		
		isVendaValid(obj, vendaEntidade);
		
	}
	
	@Test
	void getOneWhenNotFoundObjectTest() {
		
		Optional<VendaEntity> opcional = Optional.empty();
		
		when (vendaRepository.findById(1)).thenReturn(opcional);
		
		VendaDTO obj = vendaService.getOne(1);
		
		VendaEntity vendaEntidade = new VendaEntity();
		
		isVendaValid(obj, vendaEntidade);
	}
	
	void saveTest() {
		
		VendaEntity vendaEntidade = createValidVenda();
		
		when (vendaRepository.save(vendaEntidade)).thenReturn(vendaEntidade);
		
		VendaDTO vendaSalvo = vendaService.save(vendaEntidade);

		isVendaValid(vendaSalvo, vendaEntidade);
		
	}
	
	@Test
	void updateWhenFoundObj() {
		
		VendaEntity vendaEntidade = createValidVenda();
		Optional<VendaEntity> optional = Optional.of(vendaEntidade);
		
		when (vendaRepository.findById(vendaEntidade.getId())).thenReturn(optional);
		when (vendaRepository.save(vendaEntidade)).thenReturn(vendaEntidade);
		VendaDTO vendaAlterado = vendaService.update(vendaEntidade.getId(), vendaEntidade);
		isVendaValid(vendaAlterado, vendaEntidade);
		
	}
	
	@Test
	void updateWhenNotFoundObj() {
		
		Optional<VendaEntity> optional = Optional.empty();
		
		VendaEntity vendaEntidade = createValidVenda();
		
		when(vendaRepository.findById(1)).thenReturn(optional);
		
		VendaDTO vendaAlterado = vendaService.update(1, vendaEntidade);
		
		isVendaValid(vendaAlterado, new VendaEntity());
		
	}
	
	@Test
	void deleteTest() {
		
		assertDoesNotThrow(() -> vendaService.delete(1));
		verify(vendaRepository,times(1)).deleteById(1);
	}


	private void isVendaValid(VendaDTO obj, VendaEntity vendaEntidade) {

		assertThat(obj.getValorTotal()).isEqualTo(vendaEntidade.getValorTotal());
		assertThat(obj.getId()).isEqualTo(vendaEntidade.getId());

	}

	private VendaEntity createValidVenda() {

		VendaEntity vendaValido = new VendaEntity();
		vendaValido.setValorTotal(550.45f);

		return vendaValido;
	}

	
	

}
