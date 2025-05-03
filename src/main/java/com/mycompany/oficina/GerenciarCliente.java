package com.mycompany.oficina;

import java.util.ArrayList;
import java.util.List;

/**
 * Responsável pelo gerenciamento de clientes da oficina.
 * Permite adicionar, listar e buscar clientes, além de acessar a lista completa.
 * 
 * Cada cliente pode possuir um ou mais veículos, e cada veículo pode estar vinculado a ordens de serviço.
 * 
 * @author Pedro
 * @version 1.0
 */
public class GerenciarCliente {
    
    private List<Cliente> clientes;

    /**
     * Construtor da classe {@code GerenciarCliente}.
     * Inicializa a lista de clientes como vazia.
     */
    public GerenciarCliente() {
        this.clientes = new ArrayList<>();
    }

    /**
     * Adiciona um cliente à lista e exibe uma confirmação no console.
     *
     * @param c o cliente a ser adicionado
     */
    public void adicionarCliente(Cliente c) {
        clientes.add(c);
        System.out.println("Cliente adicionado: " + c.getNome());
    }

    /**
     * Lista todos os clientes cadastrados, seus veículos e respectivas ordens de serviço.
     * Exibe uma mensagem se nenhum cliente estiver cadastrado.
     */
    public void listarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            for (Cliente c : clientes) {
                System.out.println("Cliente: " + c.toString());
                for (Veiculo v : c.getVeiculos()) {
                    System.out.println("  Veículo: " + v.toString());
                    for (OrdemServico os : v.getOrdensServico()) {
                        System.out.println("    " + os.toString());
                    }
                }
            }
        }
    }

    /**
     * Busca um cliente pelo nome (ignorando maiúsculas/minúsculas).
     *
     * @param nome o nome do cliente a ser buscado
     * @return o cliente correspondente, ou {@code null} se não encontrado
     */
    public Cliente buscarClientePorNome(String nome) {
        for (Cliente c : clientes) {
            if (c.getNome().equalsIgnoreCase(nome)) {
                return c;
            }
        }
        System.out.println("Cliente '" + nome + "' não encontrado.");
        return null;
    }

    /**
     * Retorna a lista de todos os clientes cadastrados.
     *
     * @return uma lista de clientes
     */
    public List<Cliente> getClientes() {
        return clientes;
    }
}
