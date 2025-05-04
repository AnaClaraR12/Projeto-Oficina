package com.mycompany.oficina;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa uma venda realizada na oficina.
 * Contém lista de peças vendidas, valor total e método de finalização.
 * 
 * @author Pedro e Ana Clara
 * @version 1.0
 */
public class Venda {
    private List<Peca> pecasVendidas;
    private double total;
    private Cliente cliente;


    public Venda() {
        this.pecasVendidas = new ArrayList<>();
        this.total = 0.0;
    }
    
    public Venda(Cliente cliente) {
        this();
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


    public void adicionarPeca(Peca peca, int quantidadeVendida) {
        if (peca.getQuantidade() >= quantidadeVendida) {
            peca.atualizarEstoque(-quantidadeVendida);
            double valorVenda = quantidadeVendida * peca.getPreco();
            total += valorVenda;

            // Criar nova instância para registrar venda separadamente
            Peca pecaVendida = new Peca(peca.getId(), peca.getNome(), peca.getPreco(), quantidadeVendida);
            pecasVendidas.add(pecaVendida);

            System.out.println("Venda registrada: " + quantidadeVendida + "x " + peca.getNome() + " por R$" + String.format("%.2f", valorVenda));
        } else {
            System.out.println("Estoque insuficiente para " + peca.getNome());
        }
    }

    public double getTotal() {
        return total;
    }

    public void listarVenda() {
        System.out.println("Resumo da venda:");
        for (Peca p : pecasVendidas) {
            System.out.println("- " + p.getQuantidade() + "x " + p.getNome() + " (R$" + String.format("%.2f", p.getPreco()) + ")");
        }
        System.out.println("Total: R$" + String.format("%.2f", total));
    }
}