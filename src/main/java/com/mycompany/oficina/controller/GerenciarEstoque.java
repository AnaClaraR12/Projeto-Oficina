package com.mycompany.oficina.controller;

import com.mycompany.oficina.entidade.JsonUtil;
import com.mycompany.oficina.entidade.Peca;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por gerenciar o estoque de peças da oficina.
 * Permite adicionar, listar, buscar e atualizar peças no estoque.
 *
 * @author Ana Clara e Pedro
 * @version 1.2 (Extremamente Simplificado)
 */
public class GerenciarEstoque {

    private final List<Peca> pecas;

    /**
     * Construtor da classe {@code GerenciarEstoque}.
     */
    public GerenciarEstoque() {
        this.pecas = new ArrayList<>();
        
        // Carregar peças do JSON na inicialização
        List<Peca> pecasCarregadas = JsonUtil.lerLista("json/pecas.json", Peca.class);
        if (pecasCarregadas != null) {
            this.pecas.addAll(pecasCarregadas);
            System.out.println("Estoque carregado: " + pecas.size() + " peças encontradas.");
        } else {
            System.out.println("Nenhuma peça encontrada no estoque. Estoque vazio.");
        }
    }



    /**
     * Cadastra uma nova peça.
     */
    public void cadastrarProduto(int id, String nome, double preco, int quantidade) {
        Peca novaPeca = new Peca(id, nome, preco, quantidade);
        pecas.add(novaPeca);
        System.out.println("✓ Peça cadastrada: " + novaPeca.toString());
        JsonUtil.salvarLista("json/pecas.json", pecas);
    }

    /**
     * Lista todas as peças no estoque.
     */
    public void listarProdutos() {
        // Sempre ler do JSON para garantir dados atualizados
        List<Peca> pecasAtualizadas = JsonUtil.lerLista("json/pecas.json", Peca.class);
        
        if (pecasAtualizadas == null || pecasAtualizadas.isEmpty()) {
            System.out.println("Estoque vazio.");
        } else {
            System.out.println("\n--- Estoque Atual ---");
            System.out.println("Total de peças: " + pecasAtualizadas.size());
            System.out.println("----------------------------------------");
            for (int i = 0; i < pecasAtualizadas.size(); i++) {
                Peca p = pecasAtualizadas.get(i);
                System.out.printf("%d. %s (ID: %d) | Qtd: %d | Preço: R$%.2f%n", 
                    (i + 1), p.getNome(), p.getId(), p.getQuantidade(), p.getPreco());
            }
            System.out.println("----------------------------------------");
            
            // Atualizar a lista local também
            this.pecas.clear();
            this.pecas.addAll(pecasAtualizadas);
        }
    }

    /**
     * Busca uma peça pelo ID.
     */
    public void buscarProduto(int id) {
        for (Peca p : pecas) {
            if (p.getId() == id) {
                System.out.println(p);
                return;
            }
        }
        System.out.println("❌ Peça não encontrada.");
    }

    /**
     * Atualiza a quantidade de uma peça existente.
     */
    public void atualizarEstoque(int id, int novaQuantidade) {
        Peca pecaEncontrada = null;
        for (Peca p : pecas) {
            if (p.getId() == id) {
                pecaEncontrada = p;
                break;
            }
        }

        if (pecaEncontrada != null) {
            pecaEncontrada.setQuantidade(novaQuantidade);
            System.out.println("✓ Quantidade atualizada: " + pecaEncontrada.toString());
            JsonUtil.salvarLista("json/pecas.json", pecas);
        } else {
            System.out.println("❌ Peça com ID " + id + " não encontrada.");
        }
    }

    /**
     * Retorna a lista de peças.
     */
    public List<Peca> getPecas() {
        return pecas;
    }
}