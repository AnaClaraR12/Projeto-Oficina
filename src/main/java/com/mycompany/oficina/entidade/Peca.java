package com.mycompany.oficina.entidade;

/**
 * Representa uma peça da oficina com controle de preço e estoque.
 * Usada tanto para fins de venda quanto controle de inventário.
 *
 * @author Pedro e Ana Clara
 * @version 2.0
 */
public class Peca {
    private static int contadorPecas = 1; // Contador para gerar IDs automaticamente
    private final int id; // Alterado para final
    private String nome;
    private double preco;
    private int quantidade;

    /**
     * Construtor da classe Peca com geração automática de ID.
     * 
     * FORMATO DO ID: Número inteiro sequencial (1, 2, 3, 4, 5...)
     * TIPO: int (números inteiros positivos)
     * EXEMPLOS: 1, 2, 3, 10, 25, 100
     * 
     * O ID é gerado automaticamente pelo contador estático.
     * Não é necessário especificar o ID manualmente.
     *
     * @param nome nome da peça
     * @param preco preço unitário da peça
     * @param quantidade quantidade inicial em estoque
     * @throws IllegalArgumentException se os parâmetros forem inválidos
     */
    public Peca(String nome, double preco, int quantidade) {
        this.id = contadorPecas++;
        setNome(nome); // Usa setter para validação
        setPreco(preco); // Usa setter para validação
        setQuantidade(quantidade); // Usa setter para validação
    }

    /**
     * Construtor da classe Peca com ID específico.
     * 
     * FORMATO DO ID: Número inteiro positivo (1, 2, 3, 4, 5...)
     * TIPO: int (números inteiros maiores que zero)
     * EXEMPLOS: 1, 2, 3, 10, 25, 100
     * 
     * Use este construtor apenas quando precisar especificar um ID específico,
     * como ao carregar dados de um banco de dados ou arquivo.
     *
     * @param id identificador único da peça (deve ser > 0)
     * @param nome nome da peça
     * @param preco preço unitário da peça
     * @param quantidade quantidade inicial em estoque
     * @throws IllegalArgumentException se os parâmetros forem inválidos
     */
    public Peca(int id, String nome, double preco, int quantidade) {
        if (id <= 0) throw new IllegalArgumentException("ID da peça inválido. Deve ser um número inteiro maior que zero.");
        this.id = id;
        // Atualiza o contador se o ID fornecido for maior que o atual
        if (id >= contadorPecas) {
            contadorPecas = id + 1;
        }
        setNome(nome); 
        setPreco(preco); 
        setQuantidade(quantidade); 
    }

    /**
     * Retorna o próximo ID que será usado para uma nova peça.
     * @return próximo ID disponível
     */
    public static int getProximoId() {
        return contadorPecas;
    }

    /**
     * Retorna o contador total de peças criadas.
     * @return total de peças criadas
     */
    public static int getContadorPecas() {
        return contadorPecas - 1;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    /**
     * Define o nome da peça.
     * @param nome novo nome da peça
     * @throws IllegalArgumentException se o nome for nulo ou vazio
     */
    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome da peça não pode ser nulo ou vazio.");
        }
        this.nome = nome.trim();
    }

    public double getPreco() {
        return preco;
    }

    /**
     * Define o preço da peça.
     * @param preco novo preço da peça
     * @throws IllegalArgumentException se o preço for negativo
     */
    public void setPreco(double preco) {
        if (preco < 0) {
            throw new IllegalArgumentException("Preço da peça não pode ser negativo.");
        }
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    /**
     * Define a quantidade de peças em estoque.
     * @param quantidade nova quantidade
     * @throws IllegalArgumentException se a quantidade for negativa
     */
    public void setQuantidade(int quantidade) {
        if (quantidade < 0) {
            throw new IllegalArgumentException("Quantidade em estoque não pode ser negativa.");
        }
        this.quantidade = quantidade;
    }

    /**
     * Adiciona uma quantidade específica ao estoque da peça.
     * @param quantidadeAdicionada quantidade a ser adicionada
     * @throws IllegalArgumentException se a quantidade a ser adicionada for negativa
     */
    public void adicionarEstoque(int quantidadeAdicionada) {
        if (quantidadeAdicionada < 0) {
            throw new IllegalArgumentException("Não é possível adicionar quantidade negativa. Use removerEstoque para subtrair.");
        }
        this.quantidade += quantidadeAdicionada;
    }

    /**
     * Remove uma quantidade específica do estoque da peça.
     * @param quantidadeRemovida quantidade a ser removida
     * @return true se a remoção foi bem-sucedida, false caso contrário (estoque insuficiente)
     * @throws IllegalArgumentException se a quantidade a ser removida for negativa
     */
    public boolean removerEstoque(int quantidadeRemovida) {
        if (quantidadeRemovida < 0) {
            throw new IllegalArgumentException("Não é possível remover quantidade negativa.");
        }
        if (this.quantidade >= quantidadeRemovida) {
            this.quantidade -= quantidadeRemovida;
            return true;
        } else {
            System.out.println("Estoque insuficiente para remover " + quantidadeRemovida + " unidades de " + nome);
            return false;
        }
    }

    /**
     * Atualiza o preço da peça.
     *
     * @param novoPreco o novo preço da peça
     */
    public void atualizarPreco(double novoPreco) {
        setPreco(novoPreco); // Usa o setter para validação
    }

    @Override
    public String toString() {
        return "Peça: " + nome + " (ID: " + id + ", Quantidade: " + quantidade + ", Preço: R$" + String.format("%.2f", preco) + ")";
    }
}