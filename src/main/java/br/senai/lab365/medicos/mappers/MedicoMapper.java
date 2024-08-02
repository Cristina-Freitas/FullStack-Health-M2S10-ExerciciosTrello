package br.senai.lab365.medicos.mappers;

import br.senai.lab365.medicos.dto.MedicoListResponse;
import br.senai.lab365.medicos.dto.MedicoResponse;
import br.senai.lab365.medicos.models.Medico;
import org.springframework.data.domain.Page;

public class MedicoMapper {
    private MedicoMapper() {
    }

    public static MedicoListResponse map(Medico source){
        if(source == null) return null;
        MedicoListResponse target = new MedicoListResponse();
        target.setNome(source.getNome());
        target.setDataNascimento(source.getDataNascimento());
        target.setEspecialidade(source.getEspecialidade());
        return target;
    }

    public static MedicoResponse mapResponse(Medico source){
        if(source == null) return null;
        MedicoResponse target = new MedicoResponse();
        target.setId(source.getId());
        target.setNome(source.getNome());
        target.setCrm(source.getCrm());
        target.setDataNascimento(source.getDataNascimento());
        target.setTelefone(source.getTelefone());
        target.setEspecialidade(source.getEspecialidade());
        return target;
    }

    public static Page<MedicoListResponse> map(Page<Medico> source){
        return source.map(MedicoMapper::map);
    }

}
