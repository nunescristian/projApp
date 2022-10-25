package com.cristian.projetofinalaplicativos1;

import java.io.Serializable;

public class Cliente implements Serializable {

    private int id;
    private String nome;
    private String usuario;
    private String senha;
    private String categoria;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNome() {
        return nome;
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "\nID>: " + id + "\n" +
                "Nome do Cliente: " + nome + '\n' +
                "Usuário: " + usuario + "\n" +
                "Senha: " + senha + '\n' +
                "Categoria: " + categoria + '\n';
    }
}