package br.com.nava.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.modelmapper.ModelMapper;

import br.com.nava.entities.ProfessorEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorDTO {
	
	private int id;
	@NotEmpty(message ="Preenchimento obrigatório")
	@NotNull(message ="Preenchimento obrigatório")
	@Length(min=3,max =80, message = "O número de caracteres deve ser entre 3 e 80")
//	@Pattern( regexp = "^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]*$", message = "É valido apenas letras")
	@Pattern( regexp = "^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]*$", message = "É valido apenas letras")
	private String nome;
//	private String cpf;
	private String rua;
	private int numero;
	@Pattern( regexp = "^[0-9]{5}-[0-9]{3}$", message = "Digite um CEP valido")
	private String cep;
	
	public ProfessorEntity toEntity() {
		
		ModelMapper mapper = new ModelMapper();
		
		return mapper.map(this, ProfessorEntity.class);
	}

}