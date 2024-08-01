package br.senai.lab365.medicos.controllers;

import br.senai.lab365.medicos.services.MedicoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
    private MedicoService medicoService;
}
