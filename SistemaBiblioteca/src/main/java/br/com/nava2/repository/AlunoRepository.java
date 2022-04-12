package br.com.nava2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.nava2.entity.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer> {

}
