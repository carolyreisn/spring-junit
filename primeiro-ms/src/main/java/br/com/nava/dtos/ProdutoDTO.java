package br.com.nava.dtos;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.modelmapper.ModelMapper;

import br.com.nava.entities.ProdutoEntity;
import br.com.nava.entities.VendaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO {
	
	private int id;
	@NotEmpty(message ="Preenchimento obrigatório")
	@NotNull(message ="Preenchimento obrigatório")
	@Length(min=3,max =80, message = "O número de caracteres deve ser entre 3 e 80")
	@Pattern( regexp = "^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ]*$", message = "É valido apenas letras")
	private String nome;
	@NotEmpty(message ="Preenchimento obrigatório")
	@NotNull(message ="Preenchimento obrigatório")
	@Length(min=3,max =300, message = "O número de caracteres deve ser entre 3 e 300")
	@Pattern( regexp = "^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ]*$", message = "É valido apenas letras")
	private String descricao;
	private float preco;
	
	private List<VendaEntity> vendas;
	
	public ProdutoEntity toEntity() {
		
		ModelMapper mapper = new ModelMapper();
		
		return mapper.map(this, ProdutoEntity.class);
	}

}
