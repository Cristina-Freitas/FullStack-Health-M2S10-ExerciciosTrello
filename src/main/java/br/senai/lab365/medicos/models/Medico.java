package br.senai.lab365.medicos.models;

import br.senai.lab365.medicos.enums.EspecialidadeEnum;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "medicos")
public class Medico{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_medico")
    private Long id;
    @Column(nullable = false)

    private String nome;
    @Column(nullable = false, unique = true)

    private String crm;
    @Column(nullable = false)
    private LocalDate dataNascimento;

    @Column(nullable = false)
    private String telefone;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private EspecialidadeEnum especialidade;

    public Medico() {
    }

    public Medico(String nome, String crm, LocalDate dataNascimento, String telefone, EspecialidadeEnum especialidade) {
        this.nome = nome;
        this.crm = crm;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.especialidade = especialidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public EspecialidadeEnum getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(EspecialidadeEnum especialidade) {
        this.especialidade = especialidade;
    }
}
