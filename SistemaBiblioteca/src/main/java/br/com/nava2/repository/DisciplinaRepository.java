package br.com.nava2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.nava2.entity.Disciplina;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Integer> {

}
