package com.mycompany.oficina;

/**
 * Representa uma peça da oficina com controle de preço e estoque.
 * Usada tanto para fins de venda quanto controle de inventário.
 * 
 * @author Pedro e Ana Clara
 * @version 2.0
 */
public class Peca {
    private int id;
    private String nome;
    private double preco;
    private int quantidade;

    public Peca(int id, String nome, double preco, int quantidade) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    // Atualiza a quantidade de estoque (adição)
    public void atualizarEstoque(int quantidade) {
        this.quantidade += quantidade;
    }

    // Atualiza o preço da peça
    public void atualizarPreco(double novoPreco) {
        this.preco = novoPreco;
    }

    @Override
    public String toString() {
        return "Peça: " + nome + " (ID: " + id + ", Quantidade: " + quantidade + ", Preço: R$" + String.format("%.2f", preco) + ")";
    }
}