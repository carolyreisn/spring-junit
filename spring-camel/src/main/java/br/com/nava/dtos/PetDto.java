package br.com.nava.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;

import br.com.nava.entities.PetEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetDto {
	
	private int id;
	@NotEmpty(message ="Preenchimento obrigatório")
	@NotNull(message ="Preenchimento obrigatório")
	private String nome;
	private String tipo;
	
public PetEntity toEntity() {
		
		ModelMapper mapper = new ModelMapper();
		
		return mapper.map(this, PetEntity.class);
	}

}
