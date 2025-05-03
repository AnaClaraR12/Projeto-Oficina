package com.mycompany.oficina;

/**
 * Representa um serviço oferecido pela oficina, com informações sobre seu tipo e preço.
 * 
 * A classe permite calcular o custo de um serviço e exibi-lo em formato textual.
 * 
 * @author Ana Clara
 * @version 1.0
 */
public class Servico {
    private String tipo;
    private double preco;

    /**
     * Construtor da classe {@code Servico}.
     * Inicializa um novo serviço com tipo e preço especificados.
     *
     * @param tipo o tipo do serviço (ex.: "Troca de óleo", "Alinhamento")
     * @param preco o valor do serviço em reais
     */
    public Servico(String tipo, double preco) {
        this.tipo = tipo;
        this.preco = preco;
    }

    /**
     * Retorna o custo do serviço.
     *
     * @return o valor do serviço
     */
    public double calcularCusto() {
        return preco;
    }

    /**
     * Retorna uma representação textual do serviço.
     *
     * @return o tipo e o valor formatado do serviço
     */
    @Override
    public String toString() {
        return tipo + ": R$" + preco;
    }
}
