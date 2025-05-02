/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oficina;

/**
 * Classe que representa um mecânico na oficina, que herda da classe {@code Funcionario}.
 * Além das características de um funcionário, um mecânico possui uma especialidade.
 * 
 * @author Ana Clara
 * @version 1.0
 */
public class Mecanico extends Funcionario {
    
    /** Especialidade do mecânico, como "Mecânico de Motor", "Mecânico de Freio", etc. */
    private String especialidade;

    /**
     * Construtor para a classe {@code Mecanico}.
     * Chama o construtor da superclasse {@code Funcionario} e define a especialidade do mecânico.
     *
     * @param nome o nome do mecânico
     * @param email o email do mecânico
     * @param cargo o cargo do mecânico
     * @param senha a senha do mecânico
     * @param especialidade a especialidade do mecânico
     */
    public Mecanico(String nome, String email, String cargo, String senha, String especialidade) {
        super(nome, email, cargo, senha); // Chama o construtor da superclasse Funcionario
        setEspecialidade(especialidade); // Usa o setter com validação
    }

    /**
     * Método para o mecânico realizar um serviço em um veículo.
     * 
     * @param servico o serviço a ser realizado
     * @param veiculo o veículo no qual o serviço será realizado
     */
    public void realizarServico(Servico servico, Veiculo veiculo) {
        System.out.println("Serviço " + servico.toString() + " realizado no veículo: " + veiculo.toString());
    }

    /**
     * Retorna a especialidade do mecânico.
     *
     * @return a especialidade do mecânico
     */
    public String getEspecialidade() {
        return especialidade;
    }

    /**
     * Define a especialidade do mecânico.
     *
     * @param especialidade a nova especialidade do mecânico
     * @throws IllegalArgumentException se a especialidade for nula ou vazia
     */
    public void setEspecialidade(String especialidade) {
        if (especialidade == null || especialidade.trim().isEmpty()) {
            throw new IllegalArgumentException("Especialidade não pode ser vazia.");
        }
        this.especialidade = especialidade.trim();
    }

    /**
     * Retorna uma representação em string do mecânico,
     * incluindo os dados do funcionário e a especialidade.
     *
     * @return uma string com os dados do mecânico
     */
    @Override
    public String toString() {
        return super.toString() + ", Especialidade: " + especialidade;
    }
}
