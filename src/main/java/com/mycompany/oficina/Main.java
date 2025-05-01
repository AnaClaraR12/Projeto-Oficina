/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oficina;

import java.util.Date;
import com.mycompany.oficina.seguranca.Autenticacao;


/**
 * Classe Main: executa testes de todas as funcionalidades do sistema da oficina.
 * Realiza operações como cadastro de usuários, login, adição de funcionários, 
 * agendamentos, peças e listagem de dados.
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

        // Criar e adicionar agendamento
        Agendamento ag1 = new Agendamento(1, new Date());
        oficina.adicionarAgendamento(ag1);

        // Confirmar agendamento
        ag1.confirmarAgendamento();
        System.out.println("Agendamento confirmado: " + ag1);

        // Listar agendamentos
        System.out.println("Agendamentos:");
        oficina.listarAgendamentos();

        // Criar e adicionar peças
        Peca p1 = new Peca("Filtro de Óleo", 50.0);
        Peca p2 = new Peca("Pastilha de Freio", 120.0);
        oficina.adicionarPeca(p1);
        oficina.adicionarPeca(p2);

        // Listar peças
        System.out.println("Peças disponíveis na oficina:");
        oficina.listarPecas();

        // Criar administrador
        Administrador admin = new Administrador("Ana Clara", "admin@oficina.com", 1);

        // Exibir informações do administrador
        System.out.println("Administrador cadastrado: " + admin);
    }
}
