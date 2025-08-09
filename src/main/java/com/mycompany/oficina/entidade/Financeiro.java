package com.mycompany.oficina.entidade;

import java.util.ArrayList;
import java.util.List;

/**
 * Controle financeiro mensal da oficina.
 * 
 * Acompanha receitas, despesas e saldo de cada mês,
 * permitindo controle do fluxo de caixa da oficina.
 * 
 * @author Ana Clara e Pedro
 * @version 2.0
 */
public class Financeiro {
    private int mes;
    private int ano;
    private double receitas;
    private double despesas;
    private double saldo;
    
    // Associações simples
    private transient List<Despesa> despesasList;
    private transient List<Venda> vendasList;

    /**
     * Cria um registro financeiro para um período específico.
     * 
     * @param mes o mês do período financeiro (1-12)
     * @param ano o ano do período financeiro
     * @param receitas o total de receitas do período
     * @param despesas o total de despesas do período
     * @param saldo o saldo final do período
     */
    public Financeiro(int mes, int ano, double receitas, double despesas, double saldo) {
        this.mes = mes;
        this.ano = ano;
        this.receitas = receitas;
        this.despesas = despesas;
        this.saldo = saldo;
        this.despesasList = new ArrayList<>();
        this.vendasList = new ArrayList<>();
    }

    /** @return o mês do período financeiro */
    public int getMes() { return mes; }
    
    /** @param mes define o mês do período financeiro */
    public void setMes(int mes) { this.mes = mes; }
    
    /** @return o ano do período financeiro */
    public int getAno() { return ano; }
    
    /** @param ano define o ano do período financeiro */
    public void setAno(int ano) { this.ano = ano; }
    
    /** @return o total de receitas do período */
    public double getReceitas() { return receitas; }
    
    /** @param receitas define o total de receitas do período */
    public void setReceitas(double receitas) { this.receitas = receitas; }
    
    /** @return o total de despesas do período */
    public double getDespesas() { return despesas; }
    
    /** @param despesas define o total de despesas do período */
    public void setDespesas(double despesas) { this.despesas = despesas; }
    
    /** @return o saldo final do período */
    public double getSaldo() { return saldo; }
    
    /** @param saldo define o saldo final do período */
    public void setSaldo(double saldo) { this.saldo = saldo; }

    // Métodos simples para as associações
    public void adicionarDespesa(Despesa despesa) {
        if (despesa != null) {
            despesasList.add(despesa);
        }
    }

    public void adicionarVenda(Venda venda) {
        if (venda != null) {
            vendasList.add(venda);
        }
    }

    public List<Despesa> getListaDespesas() {
        return despesasList;
    }

    public List<Venda> getListaVendas() {
        return vendasList;
    }

    /**
     * Retorna uma representação em string do objeto financeiro.
     * 
     * @return string formatada com os dados financeiros
     */
    @Override
    public String toString() {
        return String.format("Financeiro [mes=%d, ano=%d, receitas=%.2f, despesas=%.2f, saldo=%.2f]", mes, ano, receitas, despesas, saldo);
    }
} 