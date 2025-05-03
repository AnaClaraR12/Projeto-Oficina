package com.mycompany.oficina;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa um cliente da oficina, contendo seus dados pessoais e os veículos cadastrados.
 * Permite atualizar os dados e adicionar veículos à lista do cliente.
 * Também mantém o controle do total de veículos registrados por todos os clientes.
 * 
 * @author Pedro
 * @version 1.0
 */
public class Cliente {

    private String nome;
    private String telefone;
    private String endereco;
    private List<Veiculo> veiculos;

    /** Contador estático que registra o total de veículos adicionados por todos os clientes. */
    protected static int totalVeiculosCliente = 0;

    /**
     * Construtor da classe {@code Cliente}.
     * Inicializa um novo cliente com os dados fornecidos e uma lista vazia de veículos.
     *
     * @param nome o nome do cliente
     * @param telefone o número de telefone do cliente
     * @param endereco o endereço do cliente
     */
    public Cliente(String nome, String telefone, String endereco) {
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.veiculos = new ArrayList<>();
    }

    /**
     * Retorna o nome do cliente.
     *
     * @return o nome do cliente
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna o telefone do cliente.
     *
     * @return o telefone do cliente
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * Retorna o endereço do cliente.
     *
     * @return o endereço do cliente
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * Retorna a lista de veículos cadastrados pelo cliente.
     *
     * @return a lista de veículos do cliente
     */
    public List<Veiculo> getVeiculos() {
        return veiculos;
    }

    /**
     * Retorna o total de veículos cadastrados por todos os clientes.
     *
     * @return o número total de veículos cadastrados
     */
    public static int getTotalVeiculosCliente() {
        return totalVeiculosCliente;
    }

    /**
     * Atualiza o telefone e o endereço do cliente.
     *
     * @param telefone o novo número de telefone
     * @param endereco o novo endereço
     */
    public void atualizarDados(String telefone, String endereco) {
        this.telefone = telefone;
        this.endereco = endereco;
    }

    /**
     * Adiciona um veículo à lista do cliente e incrementa o contador global de veículos.
     *
     * @param v o veículo a ser adicionado
     */
    public void adicionarVeiculo(Veiculo v) {
        veiculos.add(v);
        totalVeiculosCliente++;
    }

    /**
     * Retorna uma representação em string do cliente, contendo nome, telefone e endereço.
     *
     * @return uma string com os dados do cliente
     */
    @Override
    public String toString() {
        return nome + " - " + telefone + " - " + endereco;
    }
}
