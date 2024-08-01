package br.senai.lab365.medicos.controllers;

import br.senai.lab365.medicos.dto.MedicoDTO;
import br.senai.lab365.medicos.models.Medico;
import br.senai.lab365.medicos.services.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @PostMapping
    public Medico cadastrar(@RequestBody MedicoDTO medicoDTO) {
        return medicoService.cadastrar(medicoDTO);
    }
}
