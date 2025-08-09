/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oficina.entidade;

/**
 * Representa um serviço que pode ser realizado na oficina.
 * Cada serviço possui um identificador único, descrição, valor e categoria.
 * 
 * Esta classe é utilizada para registrar e manipular os serviços oferecidos pela oficina,
 * permitindo o controle de preços, categorias e identificação dos serviços prestados.
 *
 * @author Ana Clara e Pedro
 * @version 1.0
 */
public class Servico {
    private final int id;
    private final String descricao;
    private final double valor;
    private final String categoria;

    /**
     * Construtor da classe Servico.
     * 
     * FORMATO DO ID: Número inteiro sequencial (1, 2, 3, 4, 5...)
     * TIPO: int (números inteiros positivos)
     * EXEMPLOS: 1, 2, 3, 10, 25, 100
     * 
     * O ID deve ser único para cada serviço.
     * Recomenda-se usar números sequenciais para facilitar o controle.
     *
     * @param id o identificador do serviço (deve ser > 0)
     * @param descricao a descrição do serviço
     * @param valor o valor do serviço
     * @param categoria a categoria do serviço
     * @throws IllegalArgumentException se a descrição for nula/vazia, o valor for negativo ou a categoria for nula/vazia
     */
    public Servico(int id, String descricao, double valor, String categoria) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID do serviço deve ser um número inteiro maior que zero");
        }
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new IllegalArgumentException("Descrição do serviço não pode ser nula ou vazia");
        }
        if (valor < 0) {
            throw new IllegalArgumentException("Valor do serviço não pode ser negativo");
        }
        if (categoria == null || categoria.trim().isEmpty()) {
            throw new IllegalArgumentException("Categoria do serviço não pode ser nula ou vazia");
        }

        this.id = id;
        this.descricao = descricao.trim();
        this.valor = valor;
        this.categoria = categoria.trim();
    }

    /**
     * Retorna o identificador do serviço.
     *
     * @return identificador do serviço
     */
    public int getId() {
        return id;
    }

    /**
     * Retorna a descrição do serviço.
     *
     * @return descrição do serviço
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Retorna o valor do serviço.
     *
     * @return valor do serviço
     */
    public double getValor() {
        return valor;
    }

    /**
     * Retorna o preço do serviço.
     *
     * @return preço do serviço
     */
    public double getPreco() {
        return valor;
    }

    /**
     * Retorna a categoria do serviço.
     *
     * @return categoria do serviço
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Calcula o custo do serviço.
     * Neste caso, retorna o próprio valor.
     *
     * @return custo do serviço
     */
    public double calcularCusto() {
        return valor;
    }

    /**
     * Retorna uma representação em string do serviço.
     *
     * @return tipo e preço formatado do serviço
     */
    @Override
    public String toString() {
        return String.format("%s - %s (R$%.2f)", categoria, descricao, valor);
    }
}