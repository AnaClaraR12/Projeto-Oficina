package com.mycompany.oficina;

/**
 * Classe principal que executa o sistema de gerenciamento de uma oficina.
 * Realiza a inicialização de elevadores, cadastro de clientes, veículos,
 * peças em estoque, ordens de serviço e permite edições e listagens.
 * 
 * Esta classe serve como ponto de entrada da aplicação.
 * 
 * @author Pedro
 * @version 1.0
 */
public class Main {

    /**
     * Método principal que inicializa o sistema da oficina.
     * Executa operações de cadastro, listagem e edição de dados.
     *
     * @param args os argumentos da linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        // ===================== INICIALIZA ELEVADORES =====================
        Elevador.inicializarElevadores();

        System.out.println("\n=== Elevadores Iniciados ===");
        for (Elevador e : Elevador.getElevadores()) {
            System.out.println(e);
        }

        // Ocupar elevador 1 (serviço simples)
        Elevador.getElevadores()[0].ocuparElevador();

        // ===================== GERENCIADORES =====================
        GerenciarCliente gerenciarCliente = new GerenciarCliente();
        GerenciarEstoque gerenciarEstoque = new GerenciarEstoque();
        GerenciarServico gerenciarServico = new GerenciarServico();

        // ===================== CLIENTE E VEÍCULO =====================
        Cliente cliente1 = new Cliente("Carlos Silva", "38998888777", "Rua A, Milho Verde");
        Veiculo veiculo1 = new Veiculo("Strada", "JKL5678", 2021);
        cliente1.adicionarVeiculo(veiculo1);
        gerenciarCliente.adicionarCliente(cliente1);

        // Atualização dos dados do cliente
        cliente1.atualizarDados("38997777666", "Rua Nova, Milho Verde");

        // Atualização dos dados do veículo
        veiculo1.atualizarDados("Strada Working", 2022);

        // ===================== PEÇAS NO ESTOQUE =====================
        Peca p1 = new Peca(1, "Amortecedor dianteiro", 2);
        Peca p2 = new Peca(2, "Parafuso de fixação", 4);
        gerenciarEstoque.adicionarPeca(p1);
        gerenciarEstoque.adicionarPeca(p2);

        // ===================== ORDEM DE SERVIÇO =====================
        OrdemServico os1 = new OrdemServico(101, "Troca de amortecedor", veiculo1);
        os1.adicionarPeca(p1);
        os1.adicionarPeca(p2);
        veiculo1.adicionarOrdemServico(os1);
        gerenciarServico.adicionarOrdem(os1);

        // ===================== LISTAGENS =====================
        System.out.println("\n=== Clientes Cadastrados ===");
        gerenciarCliente.listarClientes();

        System.out.println("\n=== Estoque Atual ===");
        gerenciarEstoque.listarEstoque();

        System.out.println("\n=== Ordens de Serviço ===");
        gerenciarServico.exibirOrdens();

        // ===================== EDIÇÃO DE ORDEM DE SERVIÇO =====================
        System.out.println("\n=== Editando Ordem de Serviço ===");
        gerenciarServico.editarOrdem(101, "Troca completa de amortecedor e parafusos", "Finalizada");

        // ===================== CONTADORES =====================
        System.out.println("\n=== Contadores ===");
        System.out.println("Total de veículos criados (sistema): " + Veiculo.getContadorVeiculos());
        System.out.println("Total de veículos cadastrados por clientes: " + Cliente.getTotalVeiculosCliente());

        // Liberar elevador 1 após o serviço
        Elevador.getElevadores()[0].liberarElevador();
    }
}
