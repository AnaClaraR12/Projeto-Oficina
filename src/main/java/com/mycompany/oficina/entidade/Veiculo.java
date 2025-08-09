/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oficina.entidade;

import com.mycompany.oficina.entidade.Cliente;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Representa um veículo da oficina, com informações sobre o modelo, placa,
 * ano de fabricação e ordens de serviço associadas.
 *
 * A classe oferece métodos para gerenciar as ordens de serviço e atualizar os
 * dados do veículo, com validações embutidas para garantir consistência.
 *
 * @author Ana Clara e Pedro
 * @version 1.0
 */
public class Veiculo {

    private String modelo;
    private String placa;
    private int anoFabricacao;
    private transient Cliente cliente; // Adicionado: Referência ao cliente proprietário (transient para evitar ciclo na serialização)
    private final List<OrdemServico> ordensServico; // Alterado para final

    // Contador de instâncias de Veículo com acesso encapsulado
    private static int contadorVeiculos = 0;

    /**
     * Construtor da classe {@code Veiculo}.
     * Inicializa um novo veículo com modelo, placa, ano de fabricação e cliente.
     * Também inicializa a lista de ordens de serviço.
     *
     * @param modelo o modelo do veículo
     * @param placa a placa do veículo (formato esperado: ABC-1234)
     * @param anoFabricacao o ano de fabricação do veículo
     * @param cliente o cliente proprietário do veículo
     */
    public Veiculo(String modelo, String placa, int anoFabricacao, Cliente cliente) {
        setModelo(modelo);
        setPlaca(placa);
        setAnoFabricacao(anoFabricacao);
        setCliente(cliente); // Define o cliente proprietário
        this.ordensServico = new ArrayList<>();
        contadorVeiculos++;
    }

    /**
     * Retorna o modelo do veículo.
     *
     * @return o modelo do veículo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * Define o modelo do veículo, não podendo ser vazio.
     *
     * @param modelo o novo modelo do veículo
     * @throws IllegalArgumentException se o modelo for nulo ou vazio
     */
    public void setModelo(String modelo) {
        if (modelo == null || modelo.trim().isEmpty()) {
            throw new IllegalArgumentException("Modelo não pode ser vazio.");
        }
        this.modelo = modelo.trim();
    }

    /**
     * Retorna a placa do veículo.
     *
     * @return a placa do veículo
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * Define a placa do veículo, exigindo formato válido (ex: ABC-1234).
     *
     * @param placa a nova placa do veículo
     * @throws IllegalArgumentException se o formato da placa for inválido
     */
    public void setPlaca(String placa) {
        // Considera o formato novo padrão (ABC1D23) e o antigo (ABC-1234)
        if (placa == null || (!placa.matches("[A-Z]{3}[0-9][A-Z][0-9]{2}") && !placa.matches("[A-Z]{3}-\\d{4}"))) {
            throw new IllegalArgumentException("Placa inválida. Ex: ABC-1234 ou ABC1D23.");
        }
        this.placa = placa.toUpperCase();
    }

    /**
     * Retorna o ano de fabricação do veículo.
     *
     * @return o ano de fabricação do veículo
     */
    public int getAnoFabricacao() {
        return anoFabricacao;
    }

    /**
     * Define o ano de fabricação do veículo.
     *
     * @param anoFabricacao o novo ano de fabricação
     * @throws IllegalArgumentException se o ano for fora do intervalo aceitável
     */
    public void setAnoFabricacao(int anoFabricacao) {
        // Ano atual ou data dinâmica seria melhor com java.time
        if (anoFabricacao < 1886 || anoFabricacao > 2025) {
            throw new IllegalArgumentException("Ano de fabricação inválido.");
        }
        this.anoFabricacao = anoFabricacao;
    }

    /**
     * Retorna o cliente proprietário do veículo.
     *
     * @return o objeto Cliente proprietário
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Define o cliente proprietário do veículo.
     * Garante a associação bidirecional: adiciona o veículo à lista do cliente.
     *
     * @param cliente o novo cliente proprietário
     */
    public void setCliente(Cliente cliente) {
        if (cliente == null) {
            // Se o veículo está sendo desassociado de um cliente
            if (this.cliente != null && this.cliente.getListaVeiculos().contains(this)) {
                // Remove o veículo da lista do cliente antigo, se ele ainda estiver lá
                this.cliente.removerVeiculo(this); // Chama removerVeiculo para que ele cuide da bidirecionalidade
            }
            this.cliente = null;
        } else {
            // Se o veículo já tem um cliente diferente, remove do antigo primeiro
            if (this.cliente != null && this.cliente != cliente) {
                this.cliente.removerVeiculo(this);
            }
            this.cliente = cliente;
            // Adiciona o veículo à lista do novo cliente, se ainda não estiver lá
            if (!cliente.getListaVeiculos().contains(this)) {
                cliente.adicionarVeiculo(this);
            }
        }
    }

    /**
     * Retorna uma visão imutável da lista de ordens de serviço associadas ao veículo.
     *
     * @return a lista de ordens de serviço
     */
    public List<OrdemServico> getOrdensServico() {
        // Retorna uma cópia da lista para manter o encapsulamento
        return Collections.unmodifiableList(ordensServico);
    }

    /**
     * Adiciona uma ordem de serviço à lista associada ao veículo.
     *
     * @param os a ordem de serviço a ser adicionada
     * @throws IllegalArgumentException se a ordem de serviço for nula
     */
    public void adicionarOrdemServico(OrdemServico os) {
        if (os == null) {
            throw new IllegalArgumentException("Ordem de serviço não pode ser nula.");
        }
        if (!ordensServico.contains(os)) { // Evita duplicatas
            ordensServico.add(os);
        }
    }

    /**
     * Atualiza os dados do veículo.
     *
     * @param modelo o novo modelo do veículo
     * @param anoFabricacao o novo ano de fabricação
     */
    public void atualizarDados(String modelo, int anoFabricacao) {
        setModelo(modelo);
        setAnoFabricacao(anoFabricacao);
    }

    /**
     * Retorna o número total de veículos criados.
     *
     * @return o número de instâncias de veículo
     */
    public static int getContadorVeiculos() {
        return contadorVeiculos;
    }

    /**
     * Reseta o contador de veículos criados.
     */
    public static void resetContador() {
        contadorVeiculos = 0;
    }

    /**
     * Retorna uma representação textual do veículo.
     * Inclui o nome do cliente se disponível.
     *
     * @return a string com modelo, placa, ano e cliente
     */
    @Override
    public String toString() {
        String clienteInfo = (cliente != null) ? " (Proprietário: " + cliente.getNome() + ")" : "";
        return String.format("%s - %s - %d%s", modelo, placa, anoFabricacao, clienteInfo);
    }
}