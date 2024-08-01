package br.senai.lab365.medicos.dto;

import br.senai.lab365.medicos.enums.EspecialidadeEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import java.time.LocalDate;

public class MedicoListResponse {

    private String nome;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    private EspecialidadeEnum especialidade;

    public MedicoListResponse() {
    }

    public MedicoListResponse(String nome, EspecialidadeEnum especialidade, LocalDate dataNascimento) {
        this.nome = nome;
        this.especialidade = especialidade;
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public EspecialidadeEnum getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(EspecialidadeEnum especialidade) {
        this.especialidade = especialidade;
    }

    @Override
    public String toString() {
        return "MedicoListResponse{" +
                "nome='" + nome + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", especialidade=" + especialidade +
                '}';
    }
}
