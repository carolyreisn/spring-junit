package br.com.nava.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.nava.entities.PetEntity;

@Repository
public interface PetRepository extends JpaRepository<PetEntity, Integer> {

}
