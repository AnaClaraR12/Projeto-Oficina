/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oficina;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa um veículo que pode ter ordens de serviço associadas.
 * 
 * @author Ana Clara
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
     * Construtor para a classe {@code Veiculo}.
     * 
     * @param modelo o modelo do veículo
     * @param placa a placa do veículo
     * @param anoFabricacao o ano de fabricação do veículo
     */
    public Veiculo(String modelo, String placa, int anoFabricacao) {
        setModelo(modelo); // Usando setter com validação
        setPlaca(placa); // Usando setter com validação
        setAnoFabricacao(anoFabricacao); // Usando setter com validação
        this.ordensServico = new ArrayList<>();
        contadorVeiculos++;
    }

    // Getters e Setters com validação

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        if (modelo == null || modelo.trim().isEmpty()) {
            throw new IllegalArgumentException("Modelo não pode ser vazio.");
        }
        this.modelo = modelo.trim();
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        if (placa == null || !placa.matches("[A-Z]{3}-\\d{4}")) {
            throw new IllegalArgumentException("Placa inválida. Ex: ABC-1234.");
        }
        this.placa = placa.toUpperCase(); // Convertendo para maiúsculas para padronização
    }

    public int getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(int anoFabricacao) {
        if (anoFabricacao < 1886 || anoFabricacao > 2025) { // Ano de fabricação razoável
            throw new IllegalArgumentException("Ano de fabricação inválido.");
        }
        this.anoFabricacao = anoFabricacao;
    }

    public List<OrdemServico> getOrdensServico() {
        return ordensServico;
    }

    public void adicionarOrdemServico(OrdemServico os) {
        if (os == null) {
            throw new IllegalArgumentException("Ordem de serviço não pode ser nula.");
        }
        ordensServico.add(os);
    }

    // Método para atualizar dados
    public void atualizarDados(String modelo, int anoFabricacao) {
        setModelo(modelo);
        setAnoFabricacao(anoFabricacao);
    }

    // Métodos do contador
    public static int getContadorVeiculos() {
        return contadorVeiculos;
    }

    public static void resetContador() {
        contadorVeiculos = 0;
    }

    /**
     * Representação em string do veículo, incluindo modelo, placa e ano de fabricação.
     */
    @Override
    public String toString() {
        return String.format("%s - %s - %d", modelo, placa, anoFabricacao);
    }
}
