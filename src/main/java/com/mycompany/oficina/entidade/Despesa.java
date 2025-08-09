package com.mycompany.oficina.entidade;

import java.time.LocalDate;

/**
 * Representa uma despesa da oficina, contendo informações sobre descrição,
 * valor, data, categoria e responsável pela despesa.
 * 
 * Esta classe é utilizada para controle financeiro da oficina,
 * permitindo o registro e acompanhamento de todas as despesas realizadas.
 * 
 * @author Ana Clara e Pedro
 * @version 1.0
 */
public class Despesa {
    private String descricao;
    private double valor;
    private LocalDate data;
    private String categoria;
    private String responsavel;

    /**
     * Construtor da classe Despesa.
     * 
     * @param descricao descrição da despesa
     * @param valor valor da despesa
     * @param data data da despesa
     * @param categoria categoria da despesa (ex: Manutenção, Aluguel, Salários)
     * @param responsavel pessoa responsável pela despesa
     */
    public Despesa(String descricao, double valor, LocalDate data, String categoria, String responsavel) {
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
        this.categoria = categoria;
        this.responsavel = responsavel;
    }

    /** @return a descrição da despesa */
    public String getDescricao() { return descricao; }
    
    /** @param descricao define a descrição da despesa */
    public void setDescricao(String descricao) { this.descricao = descricao; }
    
    /** @return o valor da despesa */
    public double getValor() { return valor; }
    
    /** @param valor define o valor da despesa */
    public void setValor(double valor) { this.valor = valor; }
    
    /** @return a data da despesa */
    public LocalDate getData() { return data; }
    
    /** @param data define a data da despesa */
    public void setData(LocalDate data) { this.data = data; }
    
    /** @return a categoria da despesa */
    public String getCategoria() { return categoria; }
    
    /** @param categoria define a categoria da despesa */
    public void setCategoria(String categoria) { this.categoria = categoria; }
    
    /** @return o responsável pela despesa */
    public String getResponsavel() { return responsavel; }
    
    /** @param responsavel define o responsável pela despesa */
    public void setResponsavel(String responsavel) { this.responsavel = responsavel; }

    @Override
    public String toString() {
        return String.format("[%s] %s - R$%.2f (%s) - Resp: %s", data, descricao, valor, categoria, responsavel);
    }
} 