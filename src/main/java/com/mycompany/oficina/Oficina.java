/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.oficina;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe Oficina: gerencia funcionários, agendamentos, peças e controle financeiro da oficina.
 * Armazena os dados em listas e possibilita a adição, remoção e listagem dessas entidades.
 * As informações podem ser persistidas em formato JSON para armazenamento permanente.
 */
public class Oficina {
    private List<Funcionario> funcionarios;   // Lista de funcionários da oficina
    private List<Agendamento> agendamentos;   // Lista de agendamentos de serviços na oficina
    private List<Peca> pecas;                 // Lista de peças cadastradas na oficina

    public Oficina() {
        funcionarios = new ArrayList<>();    // Inicializa a lista de funcionários
        agendamentos = new ArrayList<>();    // Inicializa a lista de agendamentos
        pecas = new ArrayList<>();           // Inicializa a lista de peças
    }

    /**
     * Método para adicionar um funcionário à lista de funcionários.
     * 
     * @param funcionario O funcionário a ser adicionado
     */
    public void adicionarFuncionario(Funcionario funcionario) {
        funcionarios.add(funcionario);  // Adiciona o funcionário à lista
        System.out.println("Funcionário adicionado: " + funcionario);
    }

    /**
     * Método para remover um funcionário da lista de funcionários.
     * 
     * @param funcionario O funcionário a ser removido
     */
    public void removerFuncionario(Funcionario funcionario) {
        funcionarios.remove(funcionario);  // Remove o funcionário da lista
        System.out.println("Funcionário removido: " + funcionario);
    }

    /**
     * Método para listar todos os funcionários cadastrados na oficina.
     * Exibe os dados dos funcionários no console.
     */
    public void listarFuncionarios() {
        System.out.println("Funcionários cadastrados:");
        for (Funcionario f : funcionarios) {
            System.out.println(f);  // Exibe cada funcionário
        }
    }

    /**
     * Método para adicionar um agendamento à lista de agendamentos.
     * 
     * @param agendamento O agendamento a ser adicionado
     */
    public void adicionarAgendamento(Agendamento agendamento) {
        agendamentos.add(agendamento);  // Adiciona o agendamento à lista
        System.out.println("Agendamento adicionado: " + agendamento);
    }

    /**
     * Método para listar todos os agendamentos realizados na oficina.
     * Exibe os dados dos agendamentos no console.
     */
    public void listarAgendamentos() {
        System.out.println("Agendamentos:");
        for (Agendamento a : agendamentos) {
            System.out.println(a);  // Exibe cada agendamento
        }
    }

    /**
     * Método para adicionar uma peça à lista de peças.
     * 
     * @param peca A peça a ser adicionada
     */
    public void adicionarPeca(Peca peca) {
        pecas.add(peca);  // Adiciona a peça à lista
        System.out.println("Peça adicionada: " + peca);
    }

    /**
     * Método para listar todas as peças cadastradas na oficina.
     * Exibe os dados das peças no console.
     */
    public void listarPecas() {
        System.out.println("Peças cadastradas:");
        for (Peca p : pecas) {
            System.out.println(p);  // Exibe cada peça
        }
    }
}
