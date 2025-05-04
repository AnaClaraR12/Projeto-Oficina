/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oficina;

/**
 * Classe responsável por representar um relatório financeiro da oficina,
 * contendo informações de receitas e despesas.
 * 
 * Permite o cálculo do lucro com base nesses dados.
 * 
 * @author Ana Clara e Pedro
 * @version 1.0
 */
public class RelatorioFinanceiro {
    private double receitas;
    private double despesas;

    /**
     * Construtor da classe RelatorioFinanceiro.
     * 
     * @param receitas o valor total das receitas
     * @param despesas o valor total das despesas
     */
    public RelatorioFinanceiro(double receitas, double despesas) {
        this.receitas = receitas;
        this.despesas = despesas;
    }

    /**
     * Calcula o lucro da oficina com base nas receitas e despesas.
     * 
     * @return o valor do lucro (receitas - despesas)
     */
    public double calcularLucro() {
        return receitas - despesas;
    }
}

