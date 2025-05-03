package com.mycompany.oficina;

import java.util.ArrayList;
import java.util.List;

/**
 * Responsável por gerenciar o estoque de peças da oficina.
 * Permite adicionar peças, listar o estoque e atualizar quantidades.
 * 
 * Cada peça no estoque possui um identificador único.
 * 
 * @author Pedro
 * @version 1.0
 */
public class GerenciarEstoque {
    
    private List<Peca> pecas;

    /**
     * Construtor da classe {@code GerenciarEstoque}.
     * Inicializa a lista de peças como vazia.
     */
    public GerenciarEstoque() {
        this.pecas = new ArrayList<>();
    }

    /**
     * Adiciona uma peça ao estoque e exibe uma confirmação no console.
     *
     * @param peca a peça a ser adicionada
     */
    public void adicionarPeca(Peca peca) {
        pecas.add(peca);
        System.out.println("Peça adicionada: " + peca.toString());
    }

    /**
     * Lista todas as peças disponíveis no estoque.
     * Exibe uma mensagem se o estoque estiver vazio.
     */
    public void listarEstoque() {
        if (pecas.isEmpty()) {
            System.out.println("Estoque vazio.");
        } else {
            System.out.println("Estoque:");
            for (Peca p : pecas) {
                System.out.println("- " + p.toString());
            }
        }
    }

    /**
     * Atualiza a quantidade de uma peça no estoque com base no ID informado.
     *
     * @param id o identificador da peça
     * @param novaQuantidade a nova quantidade a ser definida
     * @return {@code true} se a peça for encontrada e atualizada; {@code false} caso contrário
     */
    public boolean atualizarQuantidade(int id, int novaQuantidade) {
        for (Peca p : pecas) {
            if (p.getId() == id) {
                p.atualizarEstoque(novaQuantidade);
                System.out.println("Quantidade atualizada: " + p.toString());
                return true;
            }
        }
        System.out.println("Peça com ID " + id + " não encontrada.");
        return false;
    }

    /**
     * Retorna a lista completa de peças no estoque.
     *
     * @return uma lista de objetos do tipo {@code Peca}
     */
    public List<Peca> getPecas() {
        return pecas;
    }
}
