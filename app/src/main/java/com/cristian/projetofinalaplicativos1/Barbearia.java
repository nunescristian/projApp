package com.cristian.projetofinalaplicativos1;

import java.io.Serializable;

public class Barbearia implements Serializable {
    private String nome;
    private float valor;
    private int quantidade;
    private float valorTotal = valorTotal();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public float valorTotal(){
        return this.quantidade * this.valor;
    }

    @Override
    public String toString() {
        return
                "Nome: " + nome + "\n" +
                "Valor: " + valor + "\n" +
                "Valor Total: " + valorTotal();
    }
}