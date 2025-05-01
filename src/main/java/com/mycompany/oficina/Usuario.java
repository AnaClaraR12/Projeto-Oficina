package com.mycompany.oficina;

/**
 * Classe que representa um usuário genérico do sistema da oficina, contendo informações básicas
 * como nome e email. Esta classe pode ser estendida por usuários mais específicos, como clientes ou funcionários.
 * 
 * @author Ana Clara
 */
public class Usuario {
    
    /** Nome do usuário. */
    private String nome;
    
    /** Email do usuário. */
    private String email;

    /**
     * Construtor para a classe {@code Usuario}.
     *
     * @param nome o nome do usuário
     * @param email o email do usuário
     */
    public Usuario(String nome, String email) {
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

    /**
     * Define o nome do usuário.
     *
     * @param nome o novo nome a ser atribuído
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna o email do usuário.
     *
     * @return o email do usuário
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define o email do usuário.
     *
     * @param email o novo email a ser atribuído
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Atualiza os dados do usuário com um novo nome e email.
     *
     * @param nome o novo nome do usuário
     * @param email o novo email do usuário
     */
    public void atualizarDados(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    /**
     * Retorna uma representação em string do usuário, incluindo nome e email.
     *
     * @return uma string com os dados básicos do usuário
     */
    @Override
    public String toString() {
        return "Nome: " + nome + ", Email: " + email;
    }
}
