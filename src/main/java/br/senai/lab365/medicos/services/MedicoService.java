package br.senai.lab365.medicos.services;

import br.senai.lab365.medicos.dto.MedicoRequest;
import br.senai.lab365.medicos.dto.MedicoResponse;
import br.senai.lab365.medicos.models.Medico;
import br.senai.lab365.medicos.repositories.MedicoRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

}
