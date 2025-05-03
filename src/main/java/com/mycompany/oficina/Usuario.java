package com.mycompany.oficina;

/**
 * Representa um usuário da oficina, com informações sobre o nome e o email.
 * 
 * Esta classe fornece métodos para atualizar os dados do usuário e acessar
 * o nome.
 * 
 * @author Ana Clara
 * @version 1.0
 */
public class Usuario {
    protected String nome;
    protected String email;

    /**
     * Construtor da classe {@code Usuario}.
     * Inicializa um novo usuário com nome e email.
     *
     * @param nome o nome do usuário
     * @param email o email do usuário
     */
    public Usuario(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    /**
     * Atualiza os dados do usuário, como nome e email.
     *
     * @param nome o novo nome do usuário
     * @param email o novo email do usuário
     */
    public void atualizarDados(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    /**
     * Retorna o nome do usuário.
     *
     * @return o nome do usuário
     */
    public String getNome() {
        return nome;
    }
}
