package com.mycompany.oficina;

/**
 * Representa um relatório financeiro da oficina, contendo informações sobre
 * receitas, despesas e cálculo de lucro.
 * 
 * A classe permite calcular o lucro com base nos valores informados.
 * 
 * @author Ana Clara
 * @version 1.0
 */
public class RelatorioFinanceiro {
    private double receitas;
    private double despesas;

    /**
     * Construtor da classe {@code RelatorioFinanceiro}.
     * Inicializa um novo relatório com os valores de receitas e despesas.
     *
     * @param receitas o valor total das receitas
     * @param despesas o valor total das despesas
     */
    public RelatorioFinanceiro(double receitas, double despesas) {
        this.receitas = receitas;
        this.despesas = despesas;
    }

    /**
     * Calcula o lucro com base nas receitas e despesas registradas.
     *
     * @return o valor do lucro (receitas - despesas)
     */
    public double calcularLucro() {
        return receitas - despesas;
    }
}
