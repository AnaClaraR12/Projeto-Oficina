package com.mycompany.oficina;

/**
 * Classe Peca: representa uma peça que pode ser vendida na oficina.
 * Cada peça tem um nome e um preço. Essa classe é usada para gerenciar o estoque de peças da oficina,
 * e pode ser utilizada para mostrar informações sobre as peças disponíveis para venda.
 * 
 * @author Ana Clara
 * @version 1.0
 */
public class Peca {
    private String nome;  // Nome da peça, por exemplo, 'Pneu', 'Óleo', 'Filtro de Ar'
    private double preco; // Preço da peça, utilizado para calcular o valor total da venda

    /**
     * Construtor para criar uma nova peça.
     * 
     * @param nome Nome da peça (ex: "Pneu", "Óleo")
     * @param preco Preço da peça (ex: 150.0, 50.0)
     */
    public Peca(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    /**
     * Retorna o nome da peça.
     * 
     * @return nome da peça
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome da peça.
     * 
     * @param nome nome da peça a ser atribuído
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna o preço da peça.
     * 
     * @return preço da peça
     */
    public double getPreco() {
        return preco;
    }

    /**
     * Define o preço da peça.
     * 
     * @param preco preço a ser atribuído à peça
     */
    public void setPreco(double preco) {
        this.preco = preco;
    }

    /**
     * Retorna uma representação em string da peça, incluindo seu nome e preço.
     * 
     * @return uma string representando a peça, com nome e preço
     */
    @Override
    public String toString() {
        return "Peça: " + nome + ", Preço: R$" + String.format("%.2f", preco); // Exibe preço formatado com 2 casas decimais
    }
}
