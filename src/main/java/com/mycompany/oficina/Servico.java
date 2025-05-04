/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oficina;

/**
 * Representa um serviço oferecido pela oficina, como troca de óleo ou alinhamento.
 * Cada serviço possui um tipo e um preço associado.
 * 
 * @author Ana Clara e Pedro
 * @version 1.0
 */
public class Servico {
    private String tipo;
    private double preco;

    /**
     * Construtor da classe Servico.
     *
     * @param tipo  o tipo do serviço (ex: "Troca de óleo")
     * @param preco o preço do serviço
     */
    public Servico(String tipo, double preco) {
        this.tipo = tipo;
        this.preco = preco;
    }

    /**
     * Retorna o tipo do serviço.
     *
     * @return tipo do serviço
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Define o tipo do serviço.
     *
     * @param tipo novo tipo do serviço
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Retorna o preço do serviço.
     *
     * @return preço do serviço
     */
    public double getPreco() {
        return preco;
    }

    /**
     * Define o preço do serviço.
     *
     * @param preco novo preço do serviço
     */
    public void setPreco(double preco) {
        this.preco = preco;
    }

    /**
     * Calcula o custo do serviço.
     * Neste caso, retorna o próprio preço.
     *
     * @return custo do serviço
     */
    public double calcularCusto() {
        return preco;
    }

    /**
     * Retorna uma representação em string do serviço.
     *
     * @return tipo e preço formatado do serviço
     */
    @Override
    public String toString() {
        return tipo + ": R$" + String.format("%.2f", preco);
    }
}
