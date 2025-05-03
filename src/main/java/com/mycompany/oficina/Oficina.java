package com.mycompany.oficina;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa uma oficina mecânica com listas de clientes, veículos, funcionários e serviços.
 * Permite realizar operações básicas de adição, remoção e execução de serviços.
 * 
 * Esta classe centraliza a gestão dos elementos principais da oficina.
 * 
 * @author Ana Clara
 * @version 1.0
 */
public class Oficina {
    private List<Cliente> clientes = new ArrayList<>();
    private List<Veiculo> veiculos = new ArrayList<>();
    private List<Funcionario> funcionarios = new ArrayList<>();
    private List<Servico> servicos = new ArrayList<>();

    /**
     * Adiciona um cliente à lista da oficina.
     *
     * @param cliente o cliente a ser adicionado
     */
    public void adicionarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    /**
     * Remove um cliente da lista com base no nome.
     *
     * @param nome o nome do cliente a ser removido
     */
    public void removerCliente(String nome) {
        clientes.removeIf(c -> c.getNome().equalsIgnoreCase(nome));
    }

    /**
     * Adiciona um veículo à lista da oficina.
     *
     * @param veiculo o veículo a ser adicionado
     */
    public void adicionarVeiculo(Veiculo veiculo) {
        veiculos.add(veiculo);
    }

    /**
     * Remove um veículo da lista com base na placa.
     *
     * @param placa a placa do veículo a ser removido
     */
    public void removerVeiculo(String placa) {
        veiculos.removeIf(v -> v.getPlaca().equalsIgnoreCase(placa));
    }

    /**
     * Adiciona um funcionário à equipe da oficina.
     *
     * @param funcionario o funcionário a ser adicionado
     */
    public void adicionarFuncionario(Funcionario funcionario) {
        funcionarios.add(funcionario);
    }

    /**
     * Remove um funcionário da equipe com base no nome.
     *
     * @param nome o nome do funcionário a ser removido
     */
    public void removerFuncionario(String nome) {
        funcionarios.removeIf(f -> f.getNome().equalsIgnoreCase(nome));
    }

    /**
     * Adiciona um serviço à lista de serviços disponíveis na oficina.
     *
     * @param servico o serviço a ser adicionado
     */
    public void adicionarServico(Servico servico) {
        servicos.add(servico);
    }

    /**
     * Simula a execução de um serviço em um veículo com base na placa e no tipo de serviço.
     * Exibe no console qual serviço está sendo executado e em qual veículo.
     *
     * @param placa a placa do veículo
     * @param tipoServico o tipo de serviço a ser executado
     */
    public void executarServico(String placa, String tipoServico) {
        System.out.println("Executando serviço " + tipoServico + " no veículo com placa " + placa);
        // Aqui poderia ser implementada a lógica de buscar veículo e aplicar o serviço
    }
}
