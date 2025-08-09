package com.mycompany.oficina.entidade;

import com.mycompany.oficina.entidade.Peca;
import com.mycompany.oficina.entidade.Cliente;
import com.mycompany.oficina.entidade.JsonUtil;
import java.util.ArrayList;
import java.util.Collections; 
import java.util.List;
import java.time.LocalDateTime;

/**
 * Representa uma venda realizada na oficina.
 * Contém lista de peças vendidas, valor total, cliente e data/hora da venda.
 *
 * @author Pedro e Ana Clara
 * @version 1.0
 */
public class Venda {
    private final List<Peca> pecasVendidas; // Alterado para final
    private double total;
    private Cliente cliente;
    private LocalDateTime dataHoraVenda; // Adicionado: data e hora da venda

    /**
     * Construtor padrão da classe Venda.
     * Inicializa a lista de peças vendidas e o total.
     */
    public Venda() {
        this.pecasVendidas = new ArrayList<>();
        this.total = 0.0;
        this.dataHoraVenda = LocalDateTime.now(); // Data/hora atual da venda
    }

    /**
     * Construtor da classe Venda com cliente.
     *
     * @param cliente o cliente associado à venda (pode ser nulo para vendas no balcão)
     */
    public Venda(Cliente cliente) {
        this(); // Chama o construtor padrão para inicializar pecasVendidas, total e dataHoraVenda
        this.setCliente(cliente); // Usa o setter para validação
    }

    /**
     * Retorna o cliente associado à venda.
     * @return o cliente da venda
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Define o cliente associado à venda.
     * @param cliente o cliente a ser associado
     */
    public void setCliente(Cliente cliente) {
        // Permitindo cliente nulo para vendas no balcão sem cadastro
        this.cliente = cliente;
    }

    /**
     * Retorna a data e hora em que a venda foi registrada.
     * @return a data e hora da venda
     */
    public LocalDateTime getDataHoraVenda() {
        return dataHoraVenda;
    }

    /**
     * Adiciona uma peça à venda, decrementando a quantidade do estoque da peça original.
     *
     * @param peca a peça a ser vendida (do estoque)
     * @param quantidadeVendida a quantidade de unidades da peça a ser vendida
     * @throws IllegalArgumentException se a peça for nula ou a quantidade vendida for inválida
     */
    public void adicionarPeca(Peca peca, int quantidadeVendida) {
        if (peca == null) throw new IllegalArgumentException("Peça não pode ser nula.");
        if (quantidadeVendida <= 0) throw new IllegalArgumentException("Quantidade vendida deve ser maior que zero.");

        // Ler estoque atualizado
        List<Peca> pecasEstoque = JsonUtil.lerLista("json/pecas.json", Peca.class);
        if (pecasEstoque == null) {
            throw new IllegalStateException("Não foi possível carregar o estoque.");
        }
        
        // Encontrar a peça no estoque
        Peca pecaEstoque = null;
        for (Peca p : pecasEstoque) {
            if (p.getId() == peca.getId()) {
                pecaEstoque = p;
                break;
            }
        }
        
        if (pecaEstoque == null) {
            throw new IllegalStateException("Peça não encontrada no estoque: " + peca.getNome());
        }
        
        if (pecaEstoque.getQuantidade() < quantidadeVendida) {
            throw new IllegalStateException("Quantidade insuficiente no estoque para a peça: " + peca.getNome());
        }
        
        // Atualizar quantidade no estoque
        pecaEstoque.setQuantidade(pecaEstoque.getQuantidade() - quantidadeVendida);
        
        // Salvar estoque atualizado
        JsonUtil.salvarLista("json/pecas.json", pecasEstoque);
        
        // Adicionar à venda
        double valorItem = quantidadeVendida * peca.getPreco();
        total += valorItem;
        
        Peca pecaVendidaRegistro = new Peca(peca.getId(), peca.getNome(), peca.getPreco(), quantidadeVendida);
        pecasVendidas.add(pecaVendidaRegistro);
        
        System.out.println("Venda registrada: " + quantidadeVendida + "x " + peca.getNome() + " por R$" + String.format("%.2f", valorItem));
    }

    /**
     * Retorna o valor total da venda.
     * @return o total da venda
     */
    public double getTotal() {
        return total;
    }

    /**
     * Retorna uma visão imutável da lista de peças vendidas nesta venda.
     * @return a lista de peças vendidas
     */
    public List<Peca> getPecasVendidas() {
        return Collections.unmodifiableList(pecasVendidas);
    }

    /**
     * Lista todas as peças que fazem parte desta venda e o valor total.
     */
    public void listarVenda() {
        System.out.println("\n--- Resumo da Venda ---");
        System.out.println("Data/Hora: " + dataHoraVenda);
        System.out.println("Cliente: " + (cliente != null ? cliente.getNome() : "Venda Balcão"));
        if (pecasVendidas.isEmpty()) {
            System.out.println("Nenhum item vendido nesta transação.");
        } else {
            System.out.println("Itens vendidos:");
            for (Peca p : pecasVendidas) {
                // p.getQuantidade() aqui se refere à quantidade VENDIDA no contexto desta transação
                System.out.println("- " + p.getQuantidade() + "x " + p.getNome() + " (R$" + String.format("%.2f", p.getPreco()) + " cada)");
            }
        }
        System.out.println("---------------------");
        System.out.println("Total da Venda: R$" + String.format("%.2f", total));
    }

    /**
     * Retorna uma representação em string da venda.
     *
     * @return uma string formatada com detalhes da venda
     */
    @Override
    public String toString() {
        String clienteInfo = (cliente != null) ? cliente.getNome() : "Venda Balcão";
        return String.format("Venda em %s - Cliente: %s - Total: R$%.2f", dataHoraVenda, clienteInfo, total);
    }
}