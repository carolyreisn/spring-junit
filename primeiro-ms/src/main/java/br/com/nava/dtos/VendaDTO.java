package br.com.nava.dtos;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;

import br.com.nava.entities.ProdutoEntity;
import br.com.nava.entities.UsuarioEntity;
import br.com.nava.entities.VendaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendaDTO {
	
	private int id;
	@NotEmpty(message ="Preenchimento obrigatório")
	@NotNull(message ="Preenchimento obrigatório")
	private float valorTotal;
	private UsuarioEntity usuario;
	private List<ProdutoEntity> produtos;
	
	public VendaEntity toEntity() {
		
		ModelMapper mapper = new ModelMapper();
		
		return mapper.map(this, VendaEntity.class);
	}

}
