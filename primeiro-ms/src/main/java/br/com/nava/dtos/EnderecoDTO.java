package br.com.nava.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.modelmapper.ModelMapper;

import br.com.nava.entities.EnderecoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDTO {
	
	private int id;
	@NotEmpty(message ="Preenchimento obrigatório")
	@NotNull(message ="Preenchimento obrigatório")
	private String rua;
	@NotEmpty(message ="Preenchimento obrigatório")
	@NotNull(message ="Preenchimento obrigatório")
	private int numero;
	@NotEmpty(message ="Preenchimento obrigatório")
	@NotNull(message ="Preenchimento obrigatório")
	@Pattern( regexp = "^[0-9]{5}-[0-9]{3}$", message = "Digite um CEP valido")
	private String cep;
	private String cidade;
	private String estado;
	
	public EnderecoEntity toEntity() {
		
		ModelMapper mapper = new ModelMapper();
		
		return mapper.map(this, EnderecoEntity.class);
	}

}
