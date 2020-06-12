package com.example.cartaofidelidade.model;

public class CartaoActivity {

    private int id;
    private PessoaActivity PessoaCpf;
    private int bonus;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PessoaActivity getPessoaCpf() {
        return this.PessoaCpf;
    }

    public void setPessoaCpf(PessoaActivity pessoaCpf) {
        PessoaCpf = pessoaCpf;
    }

    public int getBonus() {
        return this.bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }
}
