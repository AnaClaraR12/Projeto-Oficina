package com.mycompany.oficina;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa uma ordem de serviço vinculada a um veículo, contendo informações sobre
 * descrição, status, peças utilizadas e identificador único.
 * 
 * Permite adicionar peças, alterar o status e consultar os detalhes da ordem.
 * 
 * @author Pedro
 * @version 1.0
 */
public class OrdemServico {
    private int id;
    private String descricao;
    private String status;
    private Veiculo veiculo;
    private List<Peca> pecas;

    /**
     * Construtor da classe {@code OrdemServico}.
     * Inicializa uma nova ordem com status "Aberto" e lista vazia de peças.
     *
     * @param id o identificador único da ordem de serviço
     * @param descricao a descrição do serviço a ser realizado
     * @param veiculo o veículo relacionado à ordem de serviço
     */
    public OrdemServico(int id, String descricao, Veiculo veiculo) {
        this.id = id;
        this.descricao = descricao;
        this.status = "Aberto";
        this.veiculo = veiculo;
        this.pecas = new ArrayList<>();
    }

    /**
     * Adiciona uma peça à ordem de serviço.
     *
     * @param peca a peça a ser incluída na ordem
     */
    public void adicionarPeca(Peca peca) {
        pecas.add(peca);
    }

    /**
     * Finaliza a ordem de serviço, alterando o status para "Finalizada".
     */
    public void finalizarOrdem() {
        this.status = "Finalizada";
    }

    /**
     * Retorna o identificador da ordem de serviço.
     *
     * @return o ID da ordem
     */
    public int getId() {
        return id;
    }

    /**
     * Retorna a descrição da ordem de serviço.
     *
     * @return a descrição do serviço
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Retorna o status atual da ordem de serviço.
     *
     * @return o status da ordem
     */
    public String getStatus() {
        return status;
    }

    /**
     * Retorna o veículo associado à ordem de serviço.
     *
     * @return o veículo da ordem
     */
    public Veiculo getVeiculo() {
        return veiculo;
    }

    /**
     * Retorna a lista de peças utilizadas na ordem de serviço.
     *
     * @return a lista de peças
     */
    public List<Peca> getPecas() {
        return pecas;
    }

    /**
     * Atualiza a descrição da ordem de serviço.
     *
     * @param descricao a nova descrição
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Atualiza o status da ordem de serviço.
     *
     * @param status o novo status (ex.: "Aberto", "Finalizada")
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Retorna uma representação textual da ordem de serviço.
     *
     * @return os dados formatados da ordem
     */
    @Override
    public String toString() {
        return "OS #" + id + " - " + descricao + " - Status: " + status + " - Veículo: " + veiculo.getPlaca();
    }
}
