package com.mycompany.oficina;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Classe responsável por gerenciar operações relacionadas aos clientes da oficina.
 * Permite cadastrar, listar, buscar, alterar, remover clientes, bem como gerenciar seus veículos e ordens de serviço.
 * 
 * @author Ana Clara e Pedro
 * @version 1.0
 */
public class GerenciarCliente {
    private final List<Cliente> clientes;
    private final Scanner scanner;

    /**
     * Construtor da classe {@code GerenciarCliente}.
     * Inicializa a lista de clientes e o scanner para entrada de dados.
     */
    public GerenciarCliente() {
        this.clientes = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Exibe o menu de operações disponíveis para gerenciamento de clientes
     * e executa a ação correspondente à opção escolhida pelo usuário.
     */
    public void menu() {
        int opcao;
        do {
            System.out.println("\n--- MENU CLIENTE ---");
            System.out.println("1. Cadastrar cliente");
            System.out.println("2. Listar clientes");
            System.out.println("3. Buscar cliente por CPF");
            System.out.println("4. Alterar dados de cliente");
            System.out.println("5. Remover cliente");
            System.out.println("6. Adicionar veículo ao cliente");
            System.out.println("7. Ver ordens de serviço de cliente");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa buffer

            switch (opcao) {
                case 1 -> cadastrarCliente();
                case 2 -> listarClientes();
                case 3 -> buscarCliente();
                case 4 -> alterarCliente();
                case 5 -> removerCliente();
                case 6 -> adicionarVeiculo();
                case 7 -> verOrdensServico();
                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    /**
     * Realiza o cadastro de um novo cliente com os dados informados pelo usuário.
     */
    private void cadastrarCliente() {
        try {
            System.out.println("\n--- Cadastro de Cliente ---");
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("CPF (somente números): ");
            String cpf = scanner.nextLine();
            System.out.print("Senha: ");
            String senha = scanner.nextLine();
            System.out.print("CNH (somente números): ");
            String cnh = scanner.nextLine();
            System.out.print("Telefone: ");
            String telefone = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("Rua: ");
            String rua = scanner.nextLine();
            System.out.print("Número: ");
            String numero = scanner.nextLine();
            System.out.print("Cidade: ");
            String cidade = scanner.nextLine();
            System.out.print("Estado: ");
            String estado = scanner.nextLine();
            System.out.print("CEP (xxxxx-xxx): ");
            String cep = scanner.nextLine();

            Cliente cliente = new Cliente(nome, cpf, senha, cnh, telefone, email, rua, numero, cidade, estado, cep);
            System.out.print("Contato de emergência (opcional): ");
            String contato = scanner.nextLine();
            if (!contato.isEmpty()) {
                cliente.setContatoEmergencia(contato);
            }

            clientes.add(cliente);
            System.out.println(" Cliente cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println(" Erro: " + e.getMessage());
        }
    }

    /**
     * Lista todos os clientes cadastrados no sistema.
     */
    private void listarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }
        System.out.println("\n--- Lista de Clientes ---");
        for (Cliente c : clientes) {
            System.out.println(c);
            System.out.println("--------------------------------------------------");
        }
    }

    /**
     * Busca um cliente com base no CPF informado.
     *
     * @param cpf o CPF do cliente a ser buscado
     * @return o cliente correspondente ao CPF, ou {@code null} se não for encontrado
     */
    private Cliente encontrarClientePorCpf(String cpf) {
        for (Cliente c : clientes) {
            if (c.getCpf().equals(cpf)) {
                return c;
            }
        }
        return null;
    }

    /**
     * Solicita o CPF e exibe os dados do cliente correspondente, se encontrado.
     */
    private void buscarCliente() {
        System.out.print("Digite o CPF do cliente: ");
        String cpf = scanner.nextLine();
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
    private void alterarCliente() {
        System.out.print("Digite o CPF do cliente que deseja alterar: ");
        String cpf = scanner.nextLine();
        Cliente cliente = encontrarClientePorCpf(cpf);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        System.out.println("Alterando dados de: " + cliente.getNome());
        System.out.print("Novo telefone: ");
        cliente.setTelefone(scanner.nextLine());
        System.out.print("Novo email: ");
        cliente.setEmail(scanner.nextLine());
        System.out.print("Novo contato de emergência: ");
        cliente.setContatoEmergencia(scanner.nextLine());
        System.out.println("Dados atualizados.");
    }

    /**
     * Remove um cliente do sistema com base no CPF informado.
     */
    private void removerCliente() {
        System.out.print("Digite o CPF do cliente que deseja remover: ");
        String cpf = scanner.nextLine();
        Cliente cliente = encontrarClientePorCpf(cpf);
        if (cliente != null) {
            clientes.remove(cliente);
            System.out.println(" Cliente removido.");
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    /**
     * Adiciona um novo veículo ao cliente identificado pelo CPF.
     */
    private void adicionarVeiculo() {
        System.out.print("Digite o CPF do cliente: ");
        String cpf = scanner.nextLine();
        Cliente cliente = encontrarClientePorCpf(cpf);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        try {
            System.out.print("Placa do veículo: ");
            String placa = scanner.nextLine();
            System.out.print("Marca: ");
            String marca = scanner.nextLine();
            System.out.print("Modelo: ");
            String modelo = scanner.nextLine();
            System.out.print("Ano: ");
            int ano = scanner.nextInt();
            scanner.nextLine(); // limpa buffer

            Veiculo veiculo = new Veiculo(modelo, placa, ano);
            cliente.adicionarVeiculo(veiculo);
            System.out.println("Veículo adicionado.");
        } catch (Exception e) {
            System.out.println("Erro ao adicionar veículo: " + e.getMessage());
        }
    }

    /**
     * Exibe todas as ordens de serviço de um cliente, bem como o total a pagar.
     */
    private void verOrdensServico() {
        System.out.print("Digite o CPF do cliente: ");
        String cpf = scanner.nextLine();
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
}
