package com.mycompany.oficina;

/**
 * Representa um administrador do sistema da oficina, que herda da classe {@link Usuario}.
 * O administrador possui um nível de acesso e pode cadastrar funcionários e peças no sistema.
 * 
 * @author Ana Clara
 * @version 1.0
 */
public class Administrador extends Usuario {

    private int nivelAcesso;

    /**
     * Construtor da classe {@code Administrador}.
     * Inicializa o nome, email e o nível de acesso do administrador.
     *
     * @param nome o nome do administrador
     * @param email o email do administrador
     * @param nivelAcesso o nível de acesso do administrador
     */
    public Administrador(String nome, String email, int nivelAcesso) {
        super(nome, email);
        this.nivelAcesso = nivelAcesso;
    }

    /**
     * Cadastra um novo funcionário no sistema.
     *
     * @param funcionario o funcionário a ser cadastrado
     */
    public void cadastrarFuncionario(Funcionario funcionario) {
        System.out.println("Funcionário cadastrado: " + funcionario.getNome());
    }

    /**
     * Cadastra uma nova peça no sistema.
     *
     * @param peca a peça a ser cadastrada
     */
    public void cadastrarPeca(Peca peca) {
        System.out.println("Peça cadastrada: " + peca.getNome());
    }
}

