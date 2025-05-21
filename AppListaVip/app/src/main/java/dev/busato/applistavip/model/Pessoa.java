package dev.busato.applistavip.model;

public class Pessoa {

    private String nome;
    private String sobrenome;

    private String nomeDoCurso;

    private String telefone;

    public Pessoa(String nome, String sobrenome, String nomeDoCurso, String telefone) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.nomeDoCurso = nomeDoCurso;
        this.telefone = telefone;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNomeDoCurso() {
        return nomeDoCurso;
    }

    public void setNomeDoCurso(String nomeDoCurso) {
        this.nomeDoCurso = nomeDoCurso;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String toString() {
        return String.format("Nome: %s, Sobrenome: %s, Curso desejado: %s, Telefone: %s", this.nome, this.sobrenome, this.nomeDoCurso, this.telefone);
    }
}
