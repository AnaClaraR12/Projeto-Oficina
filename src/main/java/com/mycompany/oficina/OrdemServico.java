/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oficina;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa uma ordem de serviço da oficina, contendo informações sobre o cliente,
 * veículo, serviços realizados, peças utilizadas, responsável, datas, status e valor total.
 * Permite o controle do andamento e finalização da ordem, além de cálculos automáticos de valores.
 * 
 * Métodos para persistência em JSON ainda precisam ser implementados.
 * 
 * @author Ana Clara
 */
public class OrdemServico {
    private int idOrdemServico; // ID da OS, único e imutável após a criação
    private Cliente cliente; // Referência ao cliente da ordem de serviço
    private Veiculo veiculo; // Referência ao veículo envolvido na OS
    private List<Servico> servicos; // Lista de serviços realizados na OS
    private List<Peca> pecasUsadas; // Lista de peças utilizadas na OS
    private Funcionario responsavel; // Funcionário responsável pela OS
    private String dataInicio; // Data de início da OS
    private String dataFim; // Data de finalização da OS
    private double valorTotal; // Valor total da OS (calcula a soma dos serviços e peças)
    private String status; // Status da OS (Em andamento, Finalizada, Cancelada)
    private String descricao; // Descrição da ordem de serviço

    /**
     * Construtor para criar uma nova ordem de serviço.
     *
     * @param idOrdemServico identificador único da ordem
     * @param cliente cliente associado à ordem
     * @param veiculo veículo associado à ordem
     * @param responsavel funcionário responsável pela execução
     * @param dataInicio data de início da ordem
     */
    public OrdemServico(int idOrdemServico, Cliente cliente, Veiculo veiculo, Funcionario responsavel, String dataInicio) {
        this.idOrdemServico = idOrdemServico;
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.responsavel = responsavel;
        this.dataInicio = dataInicio;
        this.servicos = new ArrayList<>();
        this.pecasUsadas = new ArrayList<>();
        this.valorTotal = 0.0;
        this.status = "Em andamento"; // Status inicial
    }

    /**
     * Adiciona um serviço à ordem de serviço e atualiza o valor total.
     *
     * @param servico serviço a ser adicionado
     */
    public void adicionarServico(Servico servico) {
        servicos.add(servico);
        calcularValorTotal(); // Recalcula o valor total da OS
    }

    /**
     * Adiciona uma peça à ordem de serviço e atualiza o valor total.
     *
     * @param peca peça a ser adicionada
     */
    public void adicionarPeca(Peca peca) {
        pecasUsadas.add(peca);
        calcularValorTotal(); // Recalcula o valor total da OS
    }

    /**
     * Calcula o valor total da ordem de serviço com base nos serviços e peças.
     */
    private void calcularValorTotal() {
        valorTotal = 0.0;
        for (Servico servico : servicos) {
            valorTotal += servico.getPreco(); // Adiciona o valor dos serviços
        }
        for (Peca peca : pecasUsadas) {
            valorTotal += peca.getPreco(); // Adiciona o valor das peças
        }
    }

    /**
     * Finaliza a ordem de serviço, alterando o status e registrando a data de término.
     *
     * @param dataFim data de finalização da ordem
     */
    public void finalizarOrdem(String dataFim) {
        this.status = "Finalizada"; // Atualiza o status para finalizada
        this.dataFim = dataFim; // Registra a data de finalização
    }

    // ===== Getters =====

    /**
     * @return o ID da ordem de serviço
     */
    public int getIdOrdemServico() {
        return idOrdemServico;
    }

    /**
     * @return o cliente associado à ordem
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @return o veículo associado à ordem
     */
    public Veiculo getVeiculo() {
        return veiculo;
    }

    /**
     * @return lista de serviços realizados
     */
    public List<Servico> getServicos() {
        return servicos;
    }

    /**
     * @return lista de peças utilizadas
     */
    public List<Peca> getPecasUsadas() {
        return pecasUsadas;
    }

    /**
     * @return o funcionário responsável pela ordem
     */
    public Funcionario getResponsavel() {
        return responsavel;
    }

    /**
     * @return a data de início da ordem
     */
    public String getDataInicio() {
        return dataInicio;
    }

    /**
     * @return a data de finalização da ordem
     */
    public String getDataFim() {
        return dataFim;
    }

    /**
     * @return o valor total da ordem de serviço
     */
    public double getValorTotal() {
        return valorTotal;
    }

    /**
     * @return o status atual da ordem
     */
    public String getStatus() {
        return status;
    }

    /**
     * @return a descrição da ordem de serviço
     */
    public String getDescricao() {
        return descricao;
    }

    // ===== Setters =====

    /**
     * Define a descrição da ordem de serviço.
     *
     * @param descricao nova descrição
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Retorna uma representação textual resumida da ordem de serviço.
     *
     * @return string contendo ID, status e placa do veículo
     */
    @Override
    public String toString() {
        return "OS #" + idOrdemServico + " - Status: " + status + " - Veículo: " + veiculo.getPlaca();
    }

    /**
     * Salva a ordem de serviço no formato JSON.
     * (Implementação pendente)
     */
    public void salvarOrdemServico() {
        // Implementação para salvar a ordem de serviço no arquivo JSON
    }

    /**
     * Carrega ordens de serviço do arquivo JSON.
     * 
     * @return lista de ordens de serviço carregadas
     */
    public static List<OrdemServico> carregarOrdemServico() {
        // Implementação para carregar as ordens de serviço do arquivo JSON
        return new ArrayList<>();
    }
}
