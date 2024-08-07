package br.senai.lab365.medicos.services;

import br.senai.lab365.medicos.dto.MedicoListRequest;
import br.senai.lab365.medicos.dto.MedicoListResponse;
import br.senai.lab365.medicos.dto.MedicoRequest;
import br.senai.lab365.medicos.dto.MedicoResponse;
import br.senai.lab365.medicos.enums.EspecialidadeEnum;
import br.senai.lab365.medicos.models.Medico;
import br.senai.lab365.medicos.repositories.MedicoRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Optional;
import static br.senai.lab365.medicos.mappers.MedicoMapper.map;
import static br.senai.lab365.medicos.mappers.MedicoMapper.mapResponse;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    public Medico cadastrar(MedicoRequest medicoRequest) {
        if (medicoRepository.existsByCrm(medicoRequest.getCrm())) {
            throw new DuplicateKeyException("CRM já cadastrado");
        }
        Medico medico = new Medico(
                medicoRequest.getNome(),
                medicoRequest.getCrm(),
                medicoRequest.getDataNascimento(),
                medicoRequest.getTelefone(),
                medicoRequest.getEspecialidade()
        );

        return medicoRepository.save(medico);
    }

    public MedicoResponse atualizar(Long id, MedicoRequest medicoRequest) {
        Optional<Medico> medicoExistente = medicoRepository.findById(id);
        if (medicoExistente.isPresent()) {
            Medico medico = medicoExistente.get();
            // Atualiza os campos do médico existente com as informações do request
            medico.setNome(medicoRequest.getNome());
            //medico.setCrm(medicoRequest.getCrm());
            medico.setDataNascimento(medicoRequest.getDataNascimento());
            medico.setTelefone(medicoRequest.getTelefone());
            medico.setEspecialidade(medicoRequest.getEspecialidade());

            medico = medicoRepository.save(medico); // Salva o médico atualizado

            // Cria e retorne o DTO de resposta
            MedicoResponse response = new MedicoResponse();
            response.setId(medico.getId());
            response.setNome(medico.getNome());
            response.setCrm(medico.getCrm());
            response.setDataNascimento(medico.getDataNascimento());
            response.setTelefone(medico.getTelefone());
            response.setEspecialidade(medico.getEspecialidade());

            return mapResponse(medico);
        } else {
            throw new EntityNotFoundException("Médico não encontrado");
        }
    }

    public Page<MedicoListResponse> listarMedicos(MedicoListRequest filtros, Pageable pageable) {
        String nome = filtros.getNome() != null ? filtros.getNome() : "";
        LocalDate dataNascimento = filtros.getDataNascimento();
        EspecialidadeEnum especialidade = filtros.getEspecialidade();

        if (filtros.getDataNascimento() != null && filtros.getEspecialidade() != null && filtros.getNome() != null) {
            return map(medicoRepository.findByNomeContainingIgnoreCaseAndEspecialidadeAndDataNascimento(
                    nome, especialidade, dataNascimento, pageable));
        } else if (filtros.getEspecialidade() != null) {
            return map(medicoRepository.findByNomeContainingIgnoreCaseAndEspecialidade(
                    nome, especialidade, pageable));
        } else if (filtros.getDataNascimento() != null) {
            return map(medicoRepository.findByNomeContainingIgnoreCaseAndDataNascimento(
                    nome, dataNascimento, pageable));
        } else {
            return map(medicoRepository.findByNomeContainingIgnoreCase(
                    nome, pageable));
        }
    }

    public MedicoResponse buscarPorId(Long id) {
            Medico medico = medicoRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Médico não encontrado"));
                                           // .orElseThrow(EntityExistsException::new);
            return mapResponse(medico);
    }

    public void deletar(Long id) {
        if (medicoRepository.existsById(id)) {
            medicoRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Médico não encontrado");
        }
        medicoRepository.deleteById(id);
    }
}
