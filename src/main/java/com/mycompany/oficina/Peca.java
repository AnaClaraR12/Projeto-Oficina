package com.mycompany.oficina;

/**
 * Representa uma peça disponível no estoque da oficina, contendo informações sobre
 * seu identificador, nome e quantidade em estoque.
 * 
 * Oferece métodos para atualizar e consultar os dados da peça.
 * 
 * @author Pedro
 * @version 1.0
 */
public class Peca {
    private int id;
    private String nome;
    private int quantidade;

    /**
     * Construtor da classe {@code Peca}.
     * Inicializa uma nova peça com ID, nome e quantidade especificados.
     *
     * @param id o identificador único da peça
     * @param nome o nome da peça
     * @param quantidade a quantidade disponível no estoque
     */
    public Peca(int id, String nome, int quantidade) {
        this.id = id;
        this.nome = nome;
        this.quantidade = quantidade;
    }

    /**
     * Atualiza o estoque da peça, somando a quantidade informada.
     *
     * @param quantidade a quantidade a ser adicionada ao estoque
     */
    public void atualizarEstoque(int quantidade) {
        this.quantidade += quantidade;
    }

    /**
     * Retorna o identificador da peça.
     *
     * @return o ID da peça
     */
    public int getId() {
        return id;
    }

    /**
     * Retorna o nome da peça.
     *
     * @return o nome da peça
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna a quantidade disponível da peça no estoque.
     *
     * @return a quantidade da peça
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * Define diretamente a nova quantidade da peça no estoque.
     *
     * @param quantidade a nova quantidade a ser definida
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * Retorna uma representação textual da peça.
     *
     * @return os dados formatados da peça
     */
    @Override
    public String toString() {
        return nome + " (ID: " + id + ", x" + quantidade + ")";
    }
}
