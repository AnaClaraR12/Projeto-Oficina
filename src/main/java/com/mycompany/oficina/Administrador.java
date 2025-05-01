package com.mycompany.oficina;

/**
 * Classe que representa um administrador no sistema da oficina, 
 * responsável por gerenciar permissões e realizar funções administrativas avançadas.
 * Um administrador é um tipo especial de usuário que possui um nível de acesso específico.
 * 
 * @author Ana Clara
 */
public class Administrador extends Usuario {
    
    /** Nível de acesso do administrador no sistema. */
    private int nivelAcesso;

    /**
     * Construtor para a classe {@code Administrador}.
     *
     * @param nome o nome do administrador
     * @param email o email do administrador
     * @param nivelAcesso o nível de acesso atribuído ao administrador
     */
    public Administrador(String nome, String email, int nivelAcesso) {
        super(nome, email); // Chama o construtor da superclasse Usuario
        this.nivelAcesso = nivelAcesso;
    }

    /**
     * Retorna o nível de acesso do administrador.
     *
     * @return o nível de acesso do administrador
     */
    public int getNivelAcesso() {
        return nivelAcesso;
    }

    /**
     * Define o nível de acesso do administrador.
     *
     * @param nivelAcesso o novo nível de acesso a ser atribuído
     */
    public void setNivelAcesso(int nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }

    /**
     * Retorna uma representação em string do administrador, 
     * incluindo os dados básicos do usuário e o nível de acesso.
     *
     * @return uma string com os dados do administrador
     */
    @Override
    public String toString() {
        return super.toString() + ", Nível de Acesso: " + nivelAcesso;
    }

    // Métodos adicionais de gerenciamento (como cadastro de usuários e peças) 
    // poderão ser implementados futuramente após a conclusão da classe Oficina.
}
