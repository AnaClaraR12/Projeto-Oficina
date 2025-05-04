/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oficina;


import java.util.ArrayList;
import java.util.List;

/**
 * Representa um veículo da oficina, com informações sobre o modelo, placa,
 * ano de fabricação e ordens de serviço associadas.
 * 
 * A classe oferece métodos para gerenciar as ordens de serviço e atualizar os
 * dados do veículo, com validações embutidas para garantir consistência.
 * 
 * @author Ana Clara e Pedro
 * @version 1.0
 */

public class Veiculo {

    private String modelo;
    private String placa;
    private int anoFabricacao;
    private List<OrdemServico> ordensServico;

    // Contador de instâncias de Veículo com acesso encapsulado
    private static int contadorVeiculos = 0;

    /**
     * Construtor da classe {@code Veiculo}.
     * Inicializa um novo veículo com modelo, placa e ano de fabricação.
     * Também inicializa a lista de ordens de serviço.
     *
     * @param modelo o modelo do veículo
     * @param placa a placa do veículo (formato esperado: ABC-1234)
     * @param anoFabricacao o ano de fabricação do veículo
     */
    public Veiculo(String modelo, String placa, int anoFabricacao) {
        setModelo(modelo);
        setPlaca(placa);
        setAnoFabricacao(anoFabricacao);
        this.ordensServico = new ArrayList<>();
        contadorVeiculos++;
    }

    /**
     * Retorna o modelo do veículo.
     *
     * @return o modelo do veículo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * Define o modelo do veículo, não podendo ser vazio.
     *
     * @param modelo o novo modelo do veículo
     * @throws IllegalArgumentException se o modelo for nulo ou vazio
     */
    public void setModelo(String modelo) {
        if (modelo == null || modelo.trim().isEmpty()) {
            throw new IllegalArgumentException("Modelo não pode ser vazio.");
        }
        this.modelo = modelo.trim();
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
     * Define a placa do veículo, exigindo formato válido (ex: ABC-1234).
     *
     * @param placa a nova placa do veículo
     * @throws IllegalArgumentException se o formato da placa for inválido
     */
    public void setPlaca(String placa) {
        if (placa == null || !placa.matches("[A-Z]{3}-\\d{4}")) {
            throw new IllegalArgumentException("Placa inválida. Ex: ABC-1234.");
        }
        this.placa = placa.toUpperCase();
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
     * @param anoFabricacao o novo ano de fabricação
     * @throws IllegalArgumentException se o ano for fora do intervalo aceitável
     */
    public void setAnoFabricacao(int anoFabricacao) {
        if (anoFabricacao < 1886 || anoFabricacao > 2025) {
            throw new IllegalArgumentException("Ano de fabricação inválido.");
        }
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
     * Adiciona uma ordem de serviço à lista associada ao veículo.
     *
     * @param os a ordem de serviço a ser adicionada
     * @throws IllegalArgumentException se a ordem de serviço for nula
     */
    public void adicionarOrdemServico(OrdemServico os) {
        if (os == null) {
            throw new IllegalArgumentException("Ordem de serviço não pode ser nula.");
        }
        ordensServico.add(os);
    }

    /**
     * Atualiza os dados do veículo.
     *
     * @param modelo o novo modelo do veículo
     * @param anoFabricacao o novo ano de fabricação
     */
    public void atualizarDados(String modelo, int anoFabricacao) {
        setModelo(modelo);
        setAnoFabricacao(anoFabricacao);
    }

    /**
     * Retorna o número total de veículos criados.
     *
     * @return o número de instâncias de veículo
     */
    public static int getContadorVeiculos() {
        return contadorVeiculos;
    }

    /**
     * Reseta o contador de veículos criados.
     */
    public static void resetContador() {
        contadorVeiculos = 0;
    }

    /**
     * Retorna uma representação textual do veículo.
     *
     * @return a string com modelo, placa e ano
     */
    @Override
    public String toString() {
        return String.format("%s - %s - %d", modelo, placa, anoFabricacao);
    }
}
