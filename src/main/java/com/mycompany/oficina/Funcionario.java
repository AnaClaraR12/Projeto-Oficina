package com.mycompany.oficina;

import com.mycompany.oficina.seguranca.CriptografiaSenha; // Importa a classe responsável pela criptografia

/**
 * Classe que representa um funcionário da oficina.
 * Cada funcionário possui um cargo específico, podendo atuar em diferentes funções
 * dentro da oficina como mecânico, recepcionista, gerente, entre outros.
 * Esta classe herda de {@code Usuario}.
 * 
 * A senha do funcionário é armazenada de forma criptografada para aumentar a segurança.
 * 
 * @author Ana Clara
 */
public class Funcionario extends Usuario {
    
    /** Cargo ocupado pelo funcionário na oficina. */
    private String cargo;

    /** Senha criptografada do funcionário. */
    private String senhaCriptografada;

    /**
     * Construtor para a classe {@code Funcionario}.
     *
     * A senha fornecida é automaticamente criptografada e armazenada.
     *
     * @param nome o nome do funcionário
     * @param email o email do funcionário
     * @param cargo o cargo ocupado pelo funcionário
     * @param senha a senha do funcionário (em texto simples, será criptografada)
     */
    public Funcionario(String nome, String email, String cargo, String senha) {
        super(nome, email); // Chama o construtor da superclasse Usuario
        this.cargo = cargo;
        this.senhaCriptografada = CriptografiaSenha.criptografarSenha(senha); // Criptografa a senha
    }

    /**
     * Retorna o cargo do funcionário.
     *
     * @return o cargo do funcionário
     */
    public String getCargo() {
        return cargo;
    }

    /**
     * Define o cargo do funcionário.
     *
     * @param cargo o novo cargo a ser atribuído
     */
    public void setCargo(String cargo) {
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
     * Retorna uma representação em string do funcionário,
     * incluindo os dados básicos do usuário e o cargo.
     *
     * @return uma string com os dados do funcionário
     */
    @Override
    public String toString() {
        return super.toString() + ", Cargo: " + cargo;
    }

    /**
     * Verifica se a senha informada (em texto simples) corresponde à senha armazenada (criptografada).
     *
     * @param senhaDigitada a senha fornecida pelo usuário
     * @return {@code true} se a senha informada for correta, caso contrário, {@code false}
     */
    public boolean autenticar(String senhaDigitada) {
        return senhaCriptografada.equals(CriptografiaSenha.criptografarSenha(senhaDigitada));
    }
}

