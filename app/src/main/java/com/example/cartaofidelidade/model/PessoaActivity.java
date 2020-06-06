package com.example.cartaofidelidade.model;

import java.util.Date;

public class PessoaActivity {

    private int id;
    private String cpf;
    private String senha;
    private String nome;
    private String cep;
    private String email;
    private Date dtinclusao;
    private String complemento;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Date getDtInclusao() {
        return dtinclusao;
    }

    public void setDtinclusao(Date dtinclusao) {
        this.dtinclusao = dtinclusao;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
