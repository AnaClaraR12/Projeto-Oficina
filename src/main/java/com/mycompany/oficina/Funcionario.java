package com.mycompany.oficina;

/**
 * Representa um funcionário da oficina, que herda os atributos básicos de um usuário.
 * Cada funcionário possui um nome, email e cargo.
 * 
 * Esta classe permite a atualização do cargo do funcionário.
 * 
 * @author Ana Clara
 * @version 1.0
 */
public class Funcionario extends Usuario {

    protected String cargo;

    /**
     * Construtor da classe {@code Funcionario}.
     * Inicializa um funcionário com nome, email e cargo.
     *
     * @param nome o nome do funcionário
     * @param email o email do funcionário
     * @param cargo o cargo atual do funcionário
     */
    public Funcionario(String nome, String email, String cargo) {
        super(nome, email);
        this.cargo = cargo;
    }

    /**
     * Atualiza o cargo do funcionário.
     *
     * @param novoCargo o novo cargo a ser atribuído
     */
    public void atualizarCargo(String novoCargo) {
        this.cargo = novoCargo;
    }

    /**
     * Retorna uma representação em string do funcionário.
     * Inclui nome e cargo.
     *
     * @return uma string representando o funcionário
     */
    @Override
    public String toString() {
        return nome + " - " + cargo;
    }
}
