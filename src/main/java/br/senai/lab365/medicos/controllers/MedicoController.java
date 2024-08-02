package br.senai.lab365.medicos.controllers;

import br.senai.lab365.medicos.dto.MedicoListRequest;
import br.senai.lab365.medicos.dto.MedicoListResponse;
import br.senai.lab365.medicos.dto.MedicoRequest;
import br.senai.lab365.medicos.dto.MedicoResponse;
import br.senai.lab365.medicos.models.Medico;
import br.senai.lab365.medicos.services.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @PostMapping
    public Medico cadastrar(@RequestBody MedicoRequest medicoRequest) {
        return medicoService.cadastrar(medicoRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<MedicoResponse> atualizar(@PathVariable Long id, @RequestBody MedicoRequest medicoRequest) {
        MedicoResponse medicoAtualizado = medicoService.atualizar(id, medicoRequest);
        if (medicoAtualizado != null) {
            return ResponseEntity.ok(medicoAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

   @GetMapping
   public ResponseEntity<Page<MedicoListResponse>> listarMedicos(
       MedicoListRequest filtros, Pageable pageable) {
        return ResponseEntity.ok(medicoService.listarMedicos(filtros, pageable));
   }

    @GetMapping("/{id}")
    public MedicoResponse buscarPorId(@PathVariable Long id) {

        return medicoService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        medicoService.deletar(id);
    }



}
