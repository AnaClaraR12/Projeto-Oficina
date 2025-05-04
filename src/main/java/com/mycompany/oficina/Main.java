package com.mycompany.oficina;

import java.util.Date;
import com.mycompany.oficina.seguranca.Autenticacao;
/**
 * Classe principal que inicializa e demonstra o funcionamento dos componentes do sistema de gestão de oficina.
 * 
 * <p>Esta classe executa a sequência de testes e simulações de uso do sistema,
 * incluindo funcionalidades como autenticação, cadastro de clientes, veículos,
 * funcionários, peças, agendamentos, ordens de serviço, vendas e operação de elevadores.</p>
 * 
 * @author Ana Clara e Pedro
 * @version 1.0
 */

public class Main {

    /**
     * Método principal que executa o fluxo de teste do sistema de oficina.
     * 
     * @param args argumentos da linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        // ===================== INICIALIZA ELEVADORES =====================
        Elevador.inicializarElevadores(); // Inicializa os elevadores da oficina

        System.out.println("\n=== Elevadores Iniciados ===");
        for (Elevador e : Elevador.getElevadores()) {
            System.out.println(e); // Exibe o estado de cada elevador
        }

        Elevador.getElevadores()[0].ocuparElevador(); // Ocupa o primeiro elevador

        // ===================== SISTEMA =====================
        Oficina oficina = new Oficina(); // Instancia o sistema principal da oficina
        Autenticacao auth = new Autenticacao(); // Sistema de autenticação

        // Cadastra usuários no sistema de autenticação
        auth.cadastrarUsuario("ana@email.com", "1234");
        auth.cadastrarUsuario("joao@email.com", "abcd");

        // Realiza tentativas de login e exibe os resultados
        System.out.println("Login de 'ana@email.com': " + auth.loginEmail("ana@email.com", "1234"));
        System.out.println("Login de 'joao@email.com': " + auth.loginEmail("joao@email.com", "erro"));

        // Cria e adiciona funcionários à oficina
        Funcionario f1 = new Funcionario("Carlos Silva", "carlos@email.com", "Mecânico", "senha123");
        Funcionario f2 = new Funcionario("Mariana Souza", "mariana@email.com", "Recepcionista", "senha456");
        oficina.adicionarFuncionario(f1);
        oficina.adicionarFuncionario(f2);

        System.out.println("\nFuncionários na oficina:");
        oficina.listarFuncionarios(); // Lista os funcionários cadastrados

        f1.atualizarCargo("Mecânico Especialista"); // Atualiza o cargo de um funcionário
        System.out.println("Cargo atualizado: " + f1);

        // Cria um cliente com dois veículos
        Cliente cliente1 = new Cliente("João Silva", "99999999999", "senha123", "12345678900",
                "98888-8888", "joao@email.com", "Rua A", "123", "Cidade A", "Estado A", "12345-678");
        Veiculo veiculo1 = new Veiculo("Fiesta", "ABC-1234", 2015);
        Veiculo veiculo2 = new Veiculo("Honda", "XYZ-5678", 2018);

        cliente1.adicionarVeiculo(veiculo1);
        cliente1.adicionarVeiculo(veiculo2);
        System.out.println("\nCliente 1 com veículos: ");
        System.out.println(cliente1);

        // Atualiza informações de contato do cliente
        cliente1.setTelefone("98888-8888");
        cliente1.setEmail("joao.silva@email.com");

        System.out.println("\nCliente 1 após alteração de telefone e email:");
        System.out.println(cliente1);

        // Remove um veículo do cliente
        cliente1.removerVeiculo(veiculo1);
        System.out.println("\nCliente 1 após remoção de um veículo:");
        System.out.println(cliente1);

        // Cadastra peças na oficina
        Peca p1 = new Peca(1, "Filtro de Óleo", 50.0, 10);
        Peca p2 = new Peca(2, "Pastilha de Freio", 120.0, 5);
        oficina.adicionarPeca(p1);
        oficina.adicionarPeca(p2);

        System.out.println("\nPeças disponíveis na oficina:");
        oficina.listarPecas();

        // Cria e confirma um agendamento
        Agendamento ag1 = new Agendamento(1, new Date(), cliente1, veiculo2, f1);
        oficina.adicionarAgendamento(ag1);
        ag1.confirmarAgendamento();
        System.out.println("\nAgendamento confirmado: " + ag1);

        System.out.println("\nAgendamentos:");
        oficina.listarAgendamentos();

        // Cria uma ordem de serviço e a associa ao cliente
        OrdemServico os1 = new OrdemServico(1, cliente1, veiculo2, f1, "2025-05-01");
        os1.adicionarPeca(p1);
        os1.adicionarPeca(p2);
        cliente1.adicionarOrdemDeServico(os1);

        cliente1.setStatus(Cliente.StatusCliente.PREFERENCIAL); // Atualiza o status do cliente

        System.out.println("\nCliente 1 após adicionar ordem de serviço e alterar status:");
        System.out.println(cliente1);
        System.out.println("Total da conta do cliente 1: R$ " + cliente1.calcularTotalConta());

        // Gera extrato do cliente
        Extrato.gerarExtrato(cliente1);

        // Cadastra um segundo cliente sem veículos
        Cliente cliente2 = new Cliente("Maria Oliveira", "77777777777", "senha456", "98765432100",
                "98888-8888", "maria@email.com", "Rua B", "789", "Cidade B", "Estado B", "98765-432");
        System.out.println("\nCliente 2 sem veículos:");
        System.out.println(cliente2);

        // Cria um administrador
        Administrador admin = new Administrador("Ana Clara", "admin@oficina.com", 1);
        System.out.println("\nAdministrador cadastrado: " + admin);

        // ===================== TESTES DA CLASSE VENDA =====================
        Venda venda = new Venda();
        venda.adicionarPeca(p1, 1); // Adiciona uma peça à venda
        venda.adicionarPeca(p2, 2); // Adiciona outra peça à venda
        System.out.println("\n" + venda);

        // ===================== LIBERAÇÃO DE ELEVADOR =====================
        Elevador.getElevadores()[0].liberarElevador(); // Libera o elevador ocupado
    }
}
