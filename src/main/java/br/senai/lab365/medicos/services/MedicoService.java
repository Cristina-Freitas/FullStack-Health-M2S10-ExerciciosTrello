package br.senai.lab365.medicos.services;

import br.senai.lab365.medicos.dto.MedicoDTO;
import br.senai.lab365.medicos.models.Medico;
import br.senai.lab365.medicos.repositories.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    public Medico cadastrar(MedicoDTO medicoDTO) {
        Medico medico = new Medico(
                medicoDTO.getNome(),
                medicoDTO.getCrm(),
                medicoDTO.getDataNascimento(),
                medicoDTO.getTelefone(),
                medicoDTO.getEspecialidade()
        );

        return medicoRepository.save(medico);
    }

}
