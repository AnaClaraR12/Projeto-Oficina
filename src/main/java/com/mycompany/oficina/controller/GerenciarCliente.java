package com.mycompany.oficina.controller;

import com.mycompany.oficina.entidade.OrdemServico;
import com.mycompany.oficina.entidade.Veiculo;
import com.mycompany.oficina.entidade.Cliente;
import com.mycompany.oficina.entidade.JsonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por gerenciar operações relacionadas aos clientes da oficina.
 * Permite cadastrar, listar, buscar, alterar, remover clientes, bem como gerenciar seus veículos e ordens de serviço.
 * 
 * @author Ana Clara e Pedro
 * @version 1.0
 */
public class GerenciarCliente {
    private final List<Cliente> clientes;

    /**
     * Construtor da classe {@code GerenciarCliente}.
     * Inicializa a lista de clientes.
     */
    public GerenciarCliente() {
        this.clientes = new ArrayList<>();
        
        // Carregar clientes existentes do JSON
        carregarClientesDoJson();
    }
    
    /**
     * Carrega os clientes do arquivo JSON.
     * Se o arquivo não existir ou estiver vazio, a lista permanece vazia.
     */
    private void carregarClientesDoJson() {
        try {
            System.out.println("Tentando carregar clientes do JSON...");
            List<Cliente> clientesCarregados = JsonUtil.lerLista("json/clientes.json", Cliente.class);
            if (clientesCarregados != null) {
                this.clientes.addAll(clientesCarregados);
                System.out.println("Clientes carregados do JSON: " + clientesCarregados.size());
                for (Cliente cliente : clientesCarregados) {
                    System.out.println("  - Cliente carregado: " + cliente.getNome() + " (ID: " + cliente.getId() + ")");
                }
            } else {
                System.out.println("Nenhum cliente carregado do JSON (lista nula)");
            }
        } catch (Exception e) {
            System.out.println("Erro ao carregar clientes do JSON: " + e.getMessage());
            e.printStackTrace();
        }
    }



    /**
     * Realiza o cadastro de um novo cliente com os dados informados.
     */
    public void cadastrarCliente(String nome, String cpf, String cnh, String telefone, String email, 
                                String rua, String numero, String cidade, String estado, String cep, String contatoEmergencia) {
        try {
            System.out.println("\n--- Cadastro de Cliente ---");
            
            Cliente cliente = new Cliente(nome, cpf, cnh, telefone, email, rua, numero, cidade, estado, cep);
            if (contatoEmergencia != null && !contatoEmergencia.isEmpty()) {
                cliente.setContatoEmergencia(contatoEmergencia);
            }

            clientes.add(cliente);
            System.out.println("✓ Cliente cadastrado com sucesso!");
            JsonUtil.salvarLista("json/clientes.json", clientes);
        } catch (Exception e) {
            System.out.println("❌ Erro: " + e.getMessage());
        }
    }

    /**
     * Lista todos os clientes cadastrados no sistema.
     */
    public void listarClientes() {
        try {
            if (clientes.isEmpty()) {
                System.out.println("Nenhum cliente cadastrado.");
                return;
            }
            System.out.println("\n--- Lista de Clientes ---");
            for (Cliente c : clientes) {
                try {
                    System.out.println(c);
                    System.out.println("--------------------------------------------------");
                } catch (Exception e) {
                    System.out.println("Erro ao exibir cliente: " + e.getMessage());
                    System.out.println("ID do cliente: " + (c != null ? c.getId() : "N/A"));
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar clientes: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Busca um cliente com base no CPF informado.
     *
     * @param cpf o CPF do cliente a ser buscado
     * @return o cliente correspondente ao CPF, ou {@code null} se não for encontrado
     */
    private Cliente encontrarClientePorCpf(String cpf) {
        System.out.println("DEBUG: Procurando cliente com CPF: '" + cpf + "'");
        System.out.println("DEBUG: Total de clientes carregados: " + clientes.size());
        
        // Normalizar o CPF buscado (remover espaços, pontos, traços)
        String cpfNormalizado = cpf.replaceAll("[\\s.-]", "");
        System.out.println("DEBUG: CPF normalizado: '" + cpfNormalizado + "'");
        
        for (Cliente c : clientes) {
            String cpfCliente = c.getCpf().replaceAll("[\\s.-]", "");
            System.out.println("DEBUG: Verificando cliente: " + c.getNome() + " com CPF: '" + c.getCpf() + "' (normalizado: '" + cpfCliente + "')");
            
            // Comparação exata
            if (cpfCliente.equals(cpfNormalizado)) {
                System.out.println("DEBUG: Cliente encontrado: " + c.getNome());
                return c;
            }
            
            // Comparação alternativa: verificar se o CPF digitado está contido no CPF do cliente
            if (cpfCliente.contains(cpfNormalizado) || cpfNormalizado.contains(cpfCliente)) {
                System.out.println("DEBUG: Cliente encontrado por comparação alternativa: " + c.getNome());
                return c;
            }
        }
        System.out.println("DEBUG: Cliente não encontrado para CPF: '" + cpf + "'");
        return null;
    }

    /**
     * Solicita o CPF e exibe os dados do cliente correspondente, se encontrado.
     */
    public void buscarCliente(String cpf) {
        Cliente cliente = encontrarClientePorCpf(cpf);
        if (cliente != null) {
            System.out.println(cliente);
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    /**
     * Permite alterar o telefone, email e contato de emergência de um cliente.
     */
    public void alterarCliente(String cpf, String novoTelefone, String novoEmail, String novoContatoEmergencia) {
        Cliente cliente = encontrarClientePorCpf(cpf);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        System.out.println("Alterando dados de: " + cliente.getNome());
        cliente.setTelefone(novoTelefone);
        cliente.setEmail(novoEmail);
        cliente.setContatoEmergencia(novoContatoEmergencia);
        System.out.println("✓ Dados atualizados com sucesso!");
        JsonUtil.salvarLista("json/clientes.json", clientes);
    }

    /**
     * Remove um cliente do sistema com base no CPF informado.
     */
    public void removerCliente(String cpf) {
        Cliente cliente = encontrarClientePorCpf(cpf);
        if (cliente != null) {
            clientes.remove(cliente);
            System.out.println("✓ Cliente removido com sucesso!");
            JsonUtil.salvarLista("json/clientes.json", clientes);
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    /**
     * Adiciona um novo veículo ao cliente identificado pelo CPF.
     */
    public void adicionarVeiculo(String cpf, String placa, String modelo, int ano) {
        Cliente cliente = encontrarClientePorCpf(cpf);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        try {
            Veiculo veiculo = new Veiculo(modelo, placa, ano, cliente);
            cliente.adicionarVeiculo(veiculo);
            System.out.println("✓ Veículo adicionado com sucesso!");
            
            // Salvar as alterações no JSON
            JsonUtil.salvarLista("json/clientes.json", clientes);
        } catch (Exception e) {
            System.out.println("❌ Erro ao adicionar veículo: " + e.getMessage());
        }
    }

    /**
     * Exibe todas as ordens de serviço de um cliente, bem como o total a pagar.
     */
    public void verOrdensServico(String cpf) {
        Cliente cliente = encontrarClientePorCpf(cpf);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        List<OrdemServico> ordens = cliente.getOrdensDeServico();
        if (ordens.isEmpty()) {
            System.out.println("Este cliente não possui ordens de serviço.");
        } else {
            System.out.println("\n--- Ordens de Serviço ---");
            for (OrdemServico os : ordens) {
                System.out.println(os);
            }
            System.out.printf("Total a pagar: R$ %.2f%n", cliente.calcularTotalConta());
           
        }
    }

    /**
     * Busca um cliente com base no email informado.
     * @param email o email do cliente a ser buscado
     * @return o cliente correspondente ao email, ou null se não for encontrado
     */
    public Cliente buscarClientePorEmail(String email) {
        for (Cliente c : clientes) {
            if (c.getEmail().equalsIgnoreCase(email)) {
                return c;
            }
        }
        return null;
    }

    /**
     * Busca um cliente pelo CPF informado (método público para uso externo).
     */
    public Cliente buscarClientePorCpf(String cpf) {
        return encontrarClientePorCpf(cpf);
    }


}

