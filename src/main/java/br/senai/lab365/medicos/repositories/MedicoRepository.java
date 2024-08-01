package br.senai.lab365.medicos.repositories;

import br.senai.lab365.medicos.enums.EspecialidadeEnum;
import br.senai.lab365.medicos.models.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long>{

    Page<Medico> findByNomeContainingIgnoreCase(
            String nome, Pageable pageable
    );

    Page<Medico> findByNomeContainingIgnoreCaseAndEspecialidade(
            String nome, EspecialidadeEnum especialidade, Pageable pageable
    );

    Page<Medico> findByNomeContainingIgnoreCaseAndDataNascimento(
            String nome, LocalDate dataNascimento, Pageable pageable
    );

    Page<Medico> findByNomeContainingIgnoreCaseAndEspecialidadeAndDataNascimento(
            String nome, EspecialidadeEnum especialidade, LocalDate dataNascimento, Pageable pageable
    );
}
