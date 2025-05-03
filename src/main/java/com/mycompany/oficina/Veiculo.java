package com.mycompany.oficina;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa um veículo da oficina, com informações sobre o modelo, placa,
 * ano de fabricação e ordens de serviço associadas.
 * 
 * A classe oferece métodos para gerenciar as ordens de serviço e atualizar os
 * dados do veículo.
 * 
 * @author Pedro
 * @version 1.0
 */
public class Veiculo {
    private String modelo;
    private String placa;
    private int anoFabricacao;
    private List<OrdemServico> ordensServico;

    // Contador de instâncias com acesso encapsulado
    private static int contadorVeiculos = 0;

    /**
     * Construtor da classe {@code Veiculo}.
     * Inicializa um novo veículo com modelo, placa e ano de fabricação.
     * Também inicializa a lista de ordens de serviço.
     *
     * @param modelo o modelo do veículo
     * @param placa a placa do veículo
     * @param anoFabricacao o ano de fabricação do veículo
     */
    public Veiculo(String modelo, String placa, int anoFabricacao) {
        this.modelo = modelo;
        this.placa = placa;
        this.anoFabricacao = anoFabricacao;
        this.ordensServico = new ArrayList<>();
        contadorVeiculos++;
    }

    // === Getters e Setters ===

    /**
     * Retorna o modelo do veículo.
     *
     * @return o modelo do veículo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * Define o modelo do veículo.
     *
     * @param modelo o novo modelo do veículo
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * Retorna a placa do veículo.
     *
     * @return a placa do veículo
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * Define a placa do veículo.
     *
     * @param placa a nova placa do veículo
     */
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    /**
     * Retorna o ano de fabricação do veículo.
     *
     * @return o ano de fabricação do veículo
     */
    public int getAnoFabricacao() {
        return anoFabricacao;
    }

    /**
     * Define o ano de fabricação do veículo.
     *
     * @param anoFabricacao o novo ano de fabricação do veículo
     */
    public void setAnoFabricacao(int anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    /**
     * Retorna a lista de ordens de serviço associadas ao veículo.
     *
     * @return a lista de ordens de serviço
     */
    public List<OrdemServico> getOrdensServico() {
        return ordensServico;
    }

    /**
     * Adiciona uma ordem de serviço à lista de ordens associadas ao veículo.
     *
     * @param os a ordem de serviço a ser adicionada
     */
    public void adicionarOrdemServico(OrdemServico os) {
        ordensServico.add(os);
    }

    /**
     * Atualiza os dados do veículo, como modelo e ano de fabricação.
     *
     * @param modelo o novo modelo do veículo
     * @param anoFabricacao o novo ano de fabricação do veículo
     */
    public void atualizarDados(String modelo, int anoFabricacao) {
        this.modelo = modelo;
        this.anoFabricacao = anoFabricacao;
    }

    // Métodos do contador

    /**
     * Retorna o contador de veículos criados.
     *
     * @return o número total de veículos criados
     */
    public static int getContadorVeiculos() {
        return contadorVeiculos;
    }

    /**
     * Reseta o contador de veículos.
     */
    public static void resetContador() {
        contadorVeiculos = 0;
    }

    /**
     * Retorna uma representação textual do veículo.
     *
     * @return a representação do veículo no formato "modelo - placa - ano"
     */
    @Override
    public String toString() {
        return modelo + " - " + placa + " - " + anoFabricacao;
    }
}
