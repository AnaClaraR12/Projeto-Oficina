package com.mycompany.oficina;

import java.util.Date;
import com.mycompany.oficina.seguranca.Autenticacao;


/**
 * Classe Main: executa testes de todas as funcionalidades do sistema da oficina.
 * Realiza operações como cadastro de usuários, login, adição de funcionários, 
 * agendamentos, peças, listagem de dados e gerenciamento de clientes e veículos.
 * 
 * @author Ana Clara
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {
        // Criar Oficina
        Oficina oficina = new Oficina();

        // Criar sistema de Autenticação
        Autenticacao auth = new Autenticacao();

        // Cadastrar usuários
        auth.cadastrarUsuario("ana@email.com", "1234");
        auth.cadastrarUsuario("joao@email.com", "abcd");

        // Testar login
        System.out.println("Login de 'ana@email.com': " + auth.loginEmail("ana@email.com", "1234"));
        System.out.println("Login de 'joao@email.com': " + auth.loginEmail("joao@email.com", "erro"));

        // Criar funcionários
        Funcionario f1 = new Funcionario("Carlos Silva", "carlos@email.com", "Mecânico", "senha123");
        Funcionario f2 = new Funcionario("Mariana Souza", "mariana@email.com", "Recepcionista", "senha456");

        oficina.adicionarFuncionario(f1);
        oficina.adicionarFuncionario(f2);

        System.out.println("\nFuncionários na oficina:");
        oficina.listarFuncionarios();

        // Atualizar cargo
        f1.atualizarCargo("Mecânico Especialista");
        System.out.println("Cargo atualizado: " + f1);

        // --- Gerenciamento de Clientes ---
        System.out.println("\n--- Gerenciamento de Clientes ---");

        Cliente cliente1 = new Cliente("João Silva", "99999-9999", "joao@email.com", "123.***.789-00",
                "Rua A", "123", "Cidade A", "Estado A", "12345-678");

        Veiculo veiculo1 = new Veiculo("Fiesta", "ABC-1234", 2015);
        Veiculo veiculo2 = new Veiculo("Honda", "XYZ-5678", 2018);

        cliente1.adicionarVeiculo(veiculo1);
        cliente1.adicionarVeiculo(veiculo2);

        System.out.println("\nCliente 1 com veículos: ");
        System.out.println(cliente1);

        cliente1.setTelefone("98888-8888");
        cliente1.setEmail("joao.silva@email.com");

        System.out.println("\nCliente 1 após alteração de telefone e email:");
        System.out.println(cliente1);

        cliente1.removerVeiculo(veiculo1);

        System.out.println("\nCliente 1 após remoção de um veículo:");
        System.out.println(cliente1);

        // --- Peças ---
        Peca p1 = new Peca("Filtro de Óleo", 50.0);
        Peca p2 = new Peca("Pastilha de Freio", 120.0);
        oficina.adicionarPeca(p1);
        oficina.adicionarPeca(p2);

        System.out.println("\nPeças disponíveis na oficina:");
        oficina.listarPecas();

        // --- Agendamento ---
        Agendamento ag1 = new Agendamento(1, new Date(), cliente1, veiculo2, f1);
        oficina.adicionarAgendamento(ag1);
        ag1.confirmarAgendamento();
        System.out.println("\nAgendamento confirmado: " + ag1);

        System.out.println("\nAgendamentos:");
        oficina.listarAgendamentos();

        // --- Ordem de Serviço ---
        OrdemServico os1 = new OrdemServico(1, cliente1, veiculo2, f1, "2025-05-01");
        os1.adicionarPeca(p1);
        os1.adicionarPeca(p2);
        cliente1.adicionarOrdemDeServico(os1);

        cliente1.setStatus(Cliente.StatusCliente.PREFERENCIAL);

        System.out.println("\nCliente 1 após adicionar ordem de serviço e alterar status:");
        System.out.println(cliente1);

        System.out.println("Total da conta do cliente 1: R$ " + cliente1.calcularTotalConta());

        // Extrato
        Extrato.gerarExtrato(cliente1);

        // --- Segundo cliente ---
        Cliente cliente2 = new Cliente("Maria Oliveira", "97777-7777", "maria@email.com", "987.***.654-00",
                "Rua B", "789", "Cidade B", "Estado B", "98765-432");

        System.out.println("\nCliente 2 sem veículos:");
        System.out.println(cliente2);

        // --- Administrador ---
        Administrador admin = new Administrador("Ana Clara", "admin@oficina.com", 1);
        System.out.println("\nAdministrador cadastrado: " + admin);
    }
}
