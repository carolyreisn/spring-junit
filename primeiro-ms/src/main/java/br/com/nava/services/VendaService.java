package br.com.nava.services;

import java.util.ArrayList;
//import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nava.dtos.VendaDTO;
import br.com.nava.entities.ProdutoEntity;
import br.com.nava.entities.VendaEntity;
import br.com.nava.repositories.ProdutoRepository;
import br.com.nava.repositories.VendaRepository;

@Service
public class VendaService {
		
		@Autowired
		private VendaRepository vendaRepository;
		
		@Autowired
		private ProdutoRepository produtoRepository;
		
		public List<VendaDTO> getAll() {
			List<VendaEntity> lista = vendaRepository.findAll();
			
			List<VendaDTO> listaDTO = new ArrayList<>();
			
			for (VendaEntity vendaEntity : lista) {
				
				listaDTO.add(vendaEntity.toDTO());
			}
			
			return listaDTO;
		}
		
		public VendaDTO getOne(int id) {
			
			Optional<VendaEntity> optionalvenda = vendaRepository.findById(id);
			VendaEntity venda = optionalvenda.orElse(new VendaEntity());
			return venda.toDTO();

		}
		public VendaDTO save(VendaEntity venda) {
			//primeiro teremos que salvar a venda (para preencher a lista de vendas para cada produto)
			
			VendaEntity vendaSalva = vendaRepository.save(venda);
			
			// depois teremos que alterar a lista de vendas para cada produtos	
			// para cada produto da venda do body, temos que atualizar a venda salva no banco
			
			//todos os produtos da venda
			
			List<ProdutoEntity> listaProdutos = venda.getProdutos();
//			List<VendaEntity> listaVendas = new ArrayList<VendaEntity>();
//			listaVendas.add(vendaSalva);
			
			// atualizando as vendas para cada produto acima
			
			for(int i = 0; i < listaProdutos.size(); i++) {
				// Arrays.asList(): converte um conjunto de objetos em uma lista
				listaProdutos.get(i).setVendas( Arrays.asList(vendaSalva)  );
//				listaProdutos.get(i).setVendas(listaVendas);
			}
			
			//salvando as atualizações no banco de dados
			produtoRepository.saveAll(listaProdutos);
			
			return vendaSalva.toDTO();
		
		}
		
			public VendaDTO update(int id, VendaEntity venda) {
				Optional<VendaEntity> optionalvenda = vendaRepository.findById(id);
				if(optionalvenda.isPresent() ==true) {
					VendaEntity vendaBD = optionalvenda.get();
					vendaBD.setValorTotal(venda.getValorTotal());
					
					return vendaRepository.save(vendaBD).toDTO();
				}
				else {
					return new VendaDTO();
				}
				
			}
			
			public void delete(int id) {
				
				vendaRepository.deleteById(id);
				
			}

	}
