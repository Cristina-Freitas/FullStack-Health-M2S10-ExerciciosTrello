package br.senai.lab365.medicos.services;

import br.senai.lab365.medicos.dto.MedicoListRequest;
import br.senai.lab365.medicos.dto.MedicoListResponse;
import br.senai.lab365.medicos.dto.MedicoRequest;
import br.senai.lab365.medicos.dto.MedicoResponse;
import br.senai.lab365.medicos.enums.EspecialidadeEnum;
import br.senai.lab365.medicos.models.Medico;
import br.senai.lab365.medicos.repositories.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Optional;
import static br.senai.lab365.medicos.mappers.MedicoMapper.map;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    public Medico cadastrar(MedicoRequest medicoRequest) {
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
            medico.setCrm(medicoRequest.getCrm());
            medico.setDataNascimento(medicoRequest.getDataNascimento());
            medico.setTelefone(medicoRequest.getTelefone());
            medico.setEspecialidade(medicoRequest.getEspecialidade());

            // Salva o médico atualizado
            medico = medicoRepository.save(medico);

            // Cria e retorne o DTO de resposta
            MedicoResponse response = new MedicoResponse();
            response.setId(medico.getId());
            response.setNome(medico.getNome());
            response.setCrm(medico.getCrm());
            response.setDataNascimento(medico.getDataNascimento());
            response.setTelefone(medico.getTelefone());
            response.setEspecialidade(medico.getEspecialidade());

            return response;
        } else {
            return null;
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
}
