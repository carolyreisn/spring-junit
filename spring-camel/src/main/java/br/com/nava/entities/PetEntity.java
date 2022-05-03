package br.com.nava.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import br.com.nava.dtos.PetDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "PETS")
public class PetEntity {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private int id;
	private String nome;
	private String tipo;
	
	public PetDto toDTO() {
		ModelMapper mapper = new ModelMapper();
		
		PetDto dto = mapper.map(this, PetDto.class);
		
		return dto;
	}

}
