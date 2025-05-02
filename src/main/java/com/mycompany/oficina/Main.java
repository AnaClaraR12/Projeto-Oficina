package com.mycompany.oficina;

import java.util.Date;
import com.mycompany.oficina.seguranca.Autenticacao;
import com.mycompany.oficina.Extrato;


/**
 * Classe Main: executa testes de todas as funcionalidades do sistema da oficina.
 * Realiza operações como cadastro de usuários, login, adição de funcionários, 
 * agendamentos, peças, listagem de dados e gerenciamento de clientes e veículos.
 * 
 * @author Ana Clara
 * @version 1.0
 */
import java.util.Date;

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
        System.out.println("Testando login de 'ana@email.com': " + auth.loginEmail("ana@email.com", "1234")); // sucesso
        System.out.println("Testando login de 'joao@email.com': " + auth.loginEmail("joao@email.com", "erro")); // falha

        // Criar funcionários
        Funcionario f1 = new Funcionario("Carlos Silva", "carlos@email.com", "Mecânico", "senha123");
        Funcionario f2 = new Funcionario("Mariana Souza", "mariana@email.com", "Recepcionista", "senha456");

        // Adicionar funcionários na oficina
        oficina.adicionarFuncionario(f1);
        oficina.adicionarFuncionario(f2);

        // Listar funcionários
        System.out.println("Funcionários na oficina:");
        oficina.listarFuncionarios();

        // Atualizar cargo
        f1.atualizarCargo("Mecânico Especialista");
        System.out.println("Cargo atualizado: " + f1);

        // Gerenciamento de Clientes
        System.out.println("\n--- Gerenciamento de Clientes ---");

        // Criar cliente com endereço detalhado
        Cliente cliente1 = new Cliente("João Silva", "99999-9999", "joao@email.com", "123.***.789-00",
                "Rua A", "123", "Cidade A", "Estado A", "12345-678");

        // Criar veículos
        Veiculo veiculo1 = new Veiculo("Fiesta", "ABC-1234", 2015);
        Veiculo veiculo2 = new Veiculo("Honda", "XYZ-5678", 2018);

        // Adicionar veículos ao cliente
        cliente1.adicionarVeiculo(veiculo1);
        cliente1.adicionarVeiculo(veiculo2);

        System.out.println("Cliente 1 com veículos: ");
        System.out.println(cliente1);

        // Alterar dados do cliente
        cliente1.setTelefone("98888-8888");
        cliente1.setEmail("joao.silva@email.com");

        System.out.println("\nCliente 1 após alteração de telefone e email:");
        System.out.println(cliente1);

        // Remover um veículo
        cliente1.removerVeiculo(veiculo1);

        System.out.println("\nCliente 1 após remoção de um veículo:");
        System.out.println(cliente1);

        // Criar e adicionar peças
        Peca p1 = new Peca("Filtro de Óleo", 50.0);
        Peca p2 = new Peca("Pastilha de Freio", 120.0);
        oficina.adicionarPeca(p1);
        oficina.adicionarPeca(p2);

        // Listar peças
        System.out.println("Peças disponíveis na oficina:");
        oficina.listarPecas();

        // Criar e adicionar agendamento
        Agendamento ag1 = new Agendamento(1, new Date(), cliente1, veiculo2, f1);
        oficina.adicionarAgendamento(ag1);

        // Confirmar agendamento
        ag1.confirmarAgendamento();
        System.out.println("Agendamento confirmado: " + ag1);

        // Listar agendamentos
        System.out.println("Agendamentos:");
        oficina.listarAgendamentos();

        // Criar e adicionar uma ordem de serviço
        OrdemServico os1 = new OrdemServico(1, cliente1, veiculo2, f1, "2025-05-01");
        os1.adicionarPeca(p1);
        os1.adicionarPeca(p2);
        cliente1.adicionarOrdemDeServico(os1);

        // Alterar status do cliente
        cliente1.setStatus(Cliente.StatusCliente.PREFERENCIAL);

        System.out.println("\nCliente 1 após adicionar ordem de serviço e alterar status:");
        System.out.println(cliente1);

        // Exibir total da conta do cliente
        System.out.println("Total da conta do cliente 1: R$ " + cliente1.calcularTotalConta());
        
        // Gerar e salvar extrato do cliente1
        Extrato.gerarExtrato(cliente1);



        // Criar outro cliente sem veículos
        Cliente cliente2 = new Cliente("Maria Oliveira", "97777-7777", "maria@email.com", "987.***.654-00",
                "Rua B", "789", "Cidade B", "Estado B", "98765-432");

        System.out.println("\nCliente 2 sem veículos:");
        System.out.println(cliente2);

        // Criar administrador
        Administrador admin = new Administrador("Ana Clara", "admin@oficina.com", 1);

        // Exibir informações do administrador
        System.out.println("Administrador cadastrado: " + admin);
    }
}
