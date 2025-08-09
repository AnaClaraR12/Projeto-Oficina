package com.mycompany.oficina.entidade;

import com.mycompany.oficina.seguranca.CriptografiaSenha;

/**
 * Classe que representa um administrador no sistema da oficina,
 * responsável por gerenciar permissões e realizar funções administrativas avançadas.
 * Um administrador é um tipo especial de usuário que possui um nível de acesso específico
 * e seu próprio mecanismo de autenticação por senha.
 *
 * @author Ana Clara e Pedro
 * @version 1.1 // 
 */
public class Administrador extends Usuario {

    /** Nível de acesso do administrador no sistema. */
    private int nivelAcesso;
    /** Senha criptografada do administrador. */
    private String senhaCriptografada;

    /**
     * Construtor para a classe {@code Administrador}.
     * A senha fornecida é automaticamente criptografada e armazenada.
     *
     * @param nome o nome do administrador.
     * @param email o email do administrador (usado para login).
     * @param senha a senha do administrador (em texto simples, será criptografada).
     * @param nivelAcesso o nível de acesso atribuído ao administrador.
     */
    public Administrador(String nome, String email, String senha, int nivelAcesso) {
        super(nome, email); // Chama o construtor da superclasse Usuario
        if (senha == null || senha.trim().isEmpty()) {
            throw new IllegalArgumentException("A senha não pode ser nula ou vazia.");
        }
        this.senhaCriptografada = CriptografiaSenha.criptografarSenha(senha);
        this.nivelAcesso = nivelAcesso;
    }

    /**
     * Retorna o nível de acesso do administrador.
     *
     * @return o nível de acesso do administrador.
     */
    public int getNivelAcesso() {
        return nivelAcesso;
    }

    /**
     * Define o nível de acesso do administrador.
     *
     * @param nivelAcesso o novo nível de acesso a ser atribuído.
     */
    public void setNivelAcesso(int nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }

    /**
     * Verifica se a senha informada (em texto simples) corresponde à senha armazenada (criptografada).
     *
     * @param senhaDigitada a senha fornecida para verificação.
     * @return {@code true} se a senha informada for correta, caso contrário, {@code false}.
     */
    public boolean autenticar(String senhaDigitada) {
        if (senhaDigitada == null || this.senhaCriptografada == null) {
            return false;
        }
        return this.senhaCriptografada.equals(CriptografiaSenha.criptografarSenha(senhaDigitada));
    }

    /**
     * Altera a senha do administrador se a senha antiga fornecida for correta.
     *
     * @param senhaAntigaPlana A senha atual do administrador (em texto simples).
     * @param novaSenhaPlana A nova senha desejada (em texto simples, será criptografada).
     * @return {@code true} se a senha foi alterada com sucesso, {@code false} caso contrário
     * (ex: senha antiga incorreta, nova senha inválida).
     */
    public boolean alterarSenha(String senhaAntigaPlana, String novaSenhaPlana) {
        if (novaSenhaPlana == null || novaSenhaPlana.trim().isEmpty()) {
            System.out.println("Nova senha não pode ser vazia.");
            return false;
        }
        if (autenticar(senhaAntigaPlana)) {
            this.senhaCriptografada = CriptografiaSenha.criptografarSenha(novaSenhaPlana);
            System.out.println("Senha do administrador " + getNome() + " alterada com sucesso.");
            return true;
        } else {
            System.out.println("Não foi possível alterar a senha. Senha antiga incorreta.");
            return false;
        }
    }

    /**
     * Define uma nova senha para o administrador diretamente.
     *
     * @param novaSenhaPlana A nova senha desejada (em texto simples, será criptografada).
     */
    public void definirNovaSenha(String novaSenhaPlana) {
        if (novaSenhaPlana == null || novaSenhaPlana.trim().isEmpty()) {
            System.out.println("Nova senha não pode ser vazia.");
            return;
        }
        this.senhaCriptografada = CriptografiaSenha.criptografarSenha(novaSenhaPlana);
        System.out.println("Nova senha definida para o administrador " + getNome() + ".");
    }


    /**
     * Retorna uma representação em string do administrador,
     * incluindo os dados básicos do usuário e o nível de acesso.
     * Não inclui a senha.
     *
     * @return uma string com os dados do administrador.
     */
    @Override
    public String toString() {
        return super.toString() + ", Nível de Acesso: " + nivelAcesso + " (Administrador)";
    }
}