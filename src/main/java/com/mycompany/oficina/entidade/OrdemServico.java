package com.mycompany.oficina.entidade;

import com.mycompany.oficina.entidade.Veiculo;
import com.mycompany.oficina.entidade.Cliente;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Representa uma ordem de serviço da oficina, contendo informações sobre o cliente,
 * veículo, serviços realizados, peças utilizadas, responsável, datas, status e valor total.
 * Permite o controle do andamento e finalização da ordem, além de cálculos automáticos de valores.
 *
 * @author Ana Clara e Pedro
 * @version 1.0
 */
public class OrdemServico {
    private static int contadorOrdens = 1;
    private final int idOrdemServico; // ID da OS, único e imutável após a criação
    private final Cliente cliente; // Referência ao cliente da ordem de serviço
    private final Veiculo veiculo; // Referência ao veículo envolvido na OS
    private final Funcionario mecanico; // Funcionário responsável pela OS
    private final List<Servico> servicos; // Lista de serviços realizados na OS
    private final List<Peca> pecasUtilizadas; // Lista de peças utilizadas na OS
    private StatusOrdemServico status; // Alterado para enum
    private final LocalDateTime dataAbertura; // Data de início da OS
    private LocalDateTime dataConclusao; // Data de finalização da OS
    private String descricao; // Descrição da ordem de serviço
    private double valorTotal; // Valor total da OS (calcula a soma dos serviços e peças)

    /**
     * Enumeração que define o status de uma Ordem de Serviço.
     */
    public enum StatusOrdemServico {
        EM_ANDAMENTO("Em Andamento"),
        FINALIZADA("Finalizada"),
        CANCELADA("Cancelada"),
        AGUARDANDO_PECAS("Aguardando Peças"), // Adicionei um status comum
        AGUARDANDO_APROVACAO("Aguardando Aprovação"), // Adicionei um status comum
        RECEBIDO("Recebido"),
        ENTREGUE("Entregue");

        private final String descricao;

        StatusOrdemServico(String descricao) {
            this.descricao = descricao;
        }

        public String getDescricao() {
            return descricao;
        }
    }

    /**
     * Construtor para criar uma nova ordem de serviço.
     * 
     * FORMATO DO ID: Número inteiro sequencial (1, 2, 3, 4, 5...)
     * TIPO: int (números inteiros positivos)
     * EXEMPLOS: 1, 2, 3, 10, 25, 100
     * 
     * O ID é gerado automaticamente pelo contador estático.
     * Não é necessário especificar o ID manualmente.
     * Cada nova OS recebe um ID único e sequencial.
     *
     * @param cliente cliente associado à ordem (não pode ser nulo)
     * @param veiculo veículo associado à ordem (não pode ser nulo)
     * @param mecanico funcionário responsável pela execução (não pode ser nulo)
     * @throws IllegalArgumentException se cliente, veículo ou mecanico forem nulos
     */
    public OrdemServico(Cliente cliente, Veiculo veiculo, Funcionario mecanico) {
        if (cliente == null) throw new IllegalArgumentException("Cliente da Ordem de Serviço não pode ser nulo.");
        if (veiculo == null) throw new IllegalArgumentException("Veículo da Ordem de Serviço não pode ser nulo.");
        if (mecanico == null) throw new IllegalArgumentException("Funcionário responsável pela Ordem de Serviço não pode ser nulo.");

        this.idOrdemServico = contadorOrdens++;
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.mecanico = mecanico;
        this.servicos = new ArrayList<>();
        this.pecasUtilizadas = new ArrayList<>();
        this.status = StatusOrdemServico.RECEBIDO;
        this.dataAbertura = LocalDateTime.now();
        this.valorTotal = 0.0;
    }

    /**
     * Construtor para deserialização do JSON com objetos completos.
     *
     * @param idOrdemServico ID da ordem de serviço
     * @param cliente objeto Cliente
     * @param veiculo objeto Veiculo
     * @param mecanico objeto Funcionario
     * @param servicos lista de Servico
     * @param pecasUtilizadas lista de Peca
     * @param status status da ordem como string
     * @param dataAbertura data de abertura como string
     * @param valorTotal valor total da ordem
     */
    public OrdemServico(int idOrdemServico, Cliente cliente, Veiculo veiculo, Funcionario mecanico,
                       List<Servico> servicos, List<Peca> pecasUtilizadas, String status,
                       String dataAbertura, double valorTotal) {
        this.idOrdemServico = idOrdemServico;
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.mecanico = mecanico;
        this.servicos = (servicos != null) ? servicos : new ArrayList<>();
        this.pecasUtilizadas = (pecasUtilizadas != null) ? pecasUtilizadas : new ArrayList<>();
        this.status = StatusOrdemServico.valueOf(status);
        this.dataAbertura = LocalDateTime.parse(dataAbertura);
        this.valorTotal = valorTotal;
    }

    /**
     * Adiciona um serviço à ordem de serviço e atualiza o valor total.
     *
     * @param servico serviço a ser adicionado (não pode ser nulo)
     * @throws IllegalArgumentException se o serviço for nulo
     */
    public void adicionarServico(Servico servico) {
        if (servico == null) throw new IllegalArgumentException("Serviço a ser adicionado não pode ser nulo.");
        
        // Verificar se o serviço já foi adicionado
        for (Servico s : servicos) {
            if (s.getId() == servico.getId()) {
                System.out.println("AVISO: Serviço '" + servico.getDescricao() + "' já foi adicionado à OS.");
                return; // Não adiciona duplicatas
            }
        }
        
        // Adicionar serviço
        servicos.add(servico);
        valorTotal += servico.getValor(); // Recalcula o valor total da OS
        System.out.println("Serviço adicionado: " + servico.getDescricao() + " - R$" + String.format("%.2f", servico.getValor()));
    }

    /**
     * Adiciona uma peça à ordem de serviço e atualiza o valor total.
     * ATENÇÃO: Este método NÃO atualiza o estoque automaticamente.
     * Use apenas para registrar peças utilizadas na ordem.
     *
     * @param peca peça a ser adicionada (não pode ser nula)
     * @param quantidade quantidade da peça a ser adicionada
     * @throws IllegalArgumentException se a peça for nula ou quantidade inválida
     */
    public void adicionarPeca(Peca peca, int quantidade) {
        if (peca == null) throw new IllegalArgumentException("Peça a ser adicionada não pode ser nula.");
        if (quantidade <= 0) throw new IllegalArgumentException("Quantidade inválida para adicionar peça.");

        // Adiciona à ordem de serviço (sem atualizar estoque)
        Peca pecaUtilizada = new Peca(peca.getId(), peca.getNome(), peca.getPreco(), quantidade);
        pecasUtilizadas.add(pecaUtilizada);
        valorTotal += peca.getPreco() * quantidade; // Recalcula o valor total da OS
        
        System.out.println("Peça adicionada à OS: " + quantidade + "x " + peca.getNome() + " (R$" + String.format("%.2f", peca.getPreco()) + " cada)");
    }

    /**
     * Finaliza a ordem de serviço, alterando o status, registrando a data de término
     * e atualizando o estoque automaticamente.
     *
     * @param dataConclusao data de finalização da ordem (não pode ser nula ou vazia)
     * @throws IllegalArgumentException se dataConclusao for nula ou vazia
     * @throws IllegalStateException se houver problema ao atualizar o estoque
     */
    public void finalizarOrdem(LocalDateTime dataConclusao) {
        if (dataConclusao == null) throw new IllegalArgumentException("Data de finalização não pode ser nula ou vazia.");
        
        // Atualizar status
        this.status = StatusOrdemServico.ENTREGUE;
        this.dataConclusao = dataConclusao;
        
        // Atualizar estoque automaticamente
        try {
            atualizarEstoque();
        } catch (Exception e) {
            System.err.println("AVISO: Ordem finalizada, mas houve problema ao atualizar estoque: " + e.getMessage());
            // Não falha a finalização por problemas de estoque
        }
    }

    /**
     * Cancela a ordem de serviço.
     */
    public void cancelarOrdem() {
        this.status = StatusOrdemServico.CANCELADA;
        this.dataConclusao = LocalDateTime.now(); // Opcional: registrar data de cancelamento
    }

    // ===== Getters =====

    /**
     * @return o ID da ordem de serviço
     */
    public int getIdOrdemServico() {
        return idOrdemServico;
    }

    /**
     * @return o cliente associado à ordem
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @return o veículo associado à ordem
     */
    public Veiculo getVeiculo() {
        return veiculo;
    }

    /**
     * @return o funcionário responsável pela ordem
     */
    public Funcionario getMecanico() {
        return mecanico;
    }

    /**
     * @return o status atual da ordem
     */
    public StatusOrdemServico getStatus() {
        return status;
    }

    /**
     * Define o status da ordem de serviço.
     * @param status novo status
     * @throws IllegalArgumentException se o status for nulo
     */
    public void setStatus(StatusOrdemServico status) {
        if (status == null) throw new IllegalArgumentException("Status da Ordem de Serviço não pode ser nulo.");
        this.status = status;
        if (status == StatusOrdemServico.ENTREGUE) {
            this.dataConclusao = LocalDateTime.now();
        }
    }

    /**
     * @return a data de início da ordem
     */
    public LocalDateTime getDataAbertura() {
        return dataAbertura;
    }

    /**
     * @return a data de finalização da ordem
     */
    public LocalDateTime getDataConclusao() {
        return dataConclusao;
    }

    /**
     * @return a descrição da ordem de serviço
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Define a descrição da ordem de serviço.
     *
     * @param descricao nova descrição
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return o valor total da ordem de serviço
     */
    public double getValorTotal() {
        return valorTotal;
    }

    /**
     * Define o valor total da ordem de serviço manualmente.
     * @param valor novo valor total
     */
    public void setValorTotal(double valor) {
        this.valorTotal = valor;
    }

    /**
     * Retorna uma visão imutável da lista de serviços realizados.
     * @return lista de serviços realizados
     */
    public List<Servico> getServicos() {
        return Collections.unmodifiableList(servicos);
    }

    /**
     * Retorna uma visão imutável da lista de peças utilizadas.
     * @return lista de peças utilizadas
     */
    public List<Peca> getPecasUtilizadas() {
        return Collections.unmodifiableList(pecasUtilizadas);
    }

    /**
     * Retorna uma representação textual resumida da ordem de serviço.
     *
     * @return string contendo ID, status e placa do veículo
     */
    @Override
    public String toString() {
        String clienteNome = (cliente != null) ? cliente.getNome() : "N/A";
        String veiculoModelo = (veiculo != null) ? veiculo.getModelo() : "N/A";
        String mecanicoNome = (mecanico != null) ? mecanico.getNome() : "N/A";

        StringBuilder sb = new StringBuilder();
        sb.append("OS #").append(idOrdemServico)
          .append(" - Status: ").append(status.getDescricao())
          .append(" - Cliente: ").append(clienteNome)
          .append(" - Veículo: ").append(veiculoModelo)
          .append(" - Responsável: ").append(mecanicoNome)
          .append(" - Início: ").append(dataAbertura);
        if (dataConclusao != null) {
            sb.append(" - Fim: ").append(dataConclusao);
        }
        sb.append(" - Valor Total: R$").append(String.format("%.2f", valorTotal));
        
        if (!servicos.isEmpty()) {
            sb.append("\n  Serviços:");
            for (Servico s : servicos) {
                sb.append("\n    - ").append(s.toString());
            }
        } else {
            sb.append("\n  Serviços: Nenhum serviço adicionado.");
        }

        if (!pecasUtilizadas.isEmpty()) {
            sb.append("\n  Peças Utilizadas:");
            for (Peca p : pecasUtilizadas) {
                sb.append("\n    - ").append(p.toString());
            }
        } else {
            sb.append("\n  Peças: Nenhuma peça utilizada.");
        }

        return sb.toString();
    }

    /**
     * Salva a ordem de serviço no formato JSON.
     * (Implementação pendente)
     */
    public void salvarOrdemServico() {
        // Implementação para salvar a ordem de serviço no arquivo JSON
    }
    
    /**
     * Atualiza o estoque removendo as peças utilizadas nesta ordem de serviço.
     * Este método deve ser chamado APENAS quando a ordem for finalizada.
     * 
     * @throws IllegalStateException se houver problema ao atualizar o estoque
     */
    public void atualizarEstoque() {
        if (pecasUtilizadas.isEmpty()) {
            System.out.println("Nenhuma peça para atualizar no estoque.");
            return;
        }
        
        try {
            // Ler estoque atualizado
            List<Peca> pecasEstoque = JsonUtil.lerLista("json/pecas.json", Peca.class);
            if (pecasEstoque == null) {
                throw new IllegalStateException("Não foi possível carregar o estoque.");
            }
            
            // Atualizar cada peça utilizada
            for (Peca pecaUtilizada : pecasUtilizadas) {
                for (Peca pecaEstoque : pecasEstoque) {
                    if (pecaEstoque.getId() == pecaUtilizada.getId()) {
                        if (pecaEstoque.getQuantidade() < pecaUtilizada.getQuantidade()) {
                            throw new IllegalStateException("Quantidade insuficiente no estoque para " + pecaUtilizada.getNome());
                        }
                        pecaEstoque.setQuantidade(pecaEstoque.getQuantidade() - pecaUtilizada.getQuantidade());
                        System.out.println("Estoque atualizado: " + pecaUtilizada.getNome() + " - Qtd: " + pecaEstoque.getQuantidade());
                        break;
                    }
                }
            }
            
            // Salvar estoque atualizado
            JsonUtil.salvarLista("json/pecas.json", pecasEstoque);
            System.out.println("Estoque atualizado com sucesso para a OS #" + idOrdemServico);
            
        } catch (Exception e) {
            throw new IllegalStateException("Erro ao atualizar estoque: " + e.getMessage());
        }
    }

    /**
     * Carrega ordens de serviço do arquivo JSON.
     *
     * @return lista de ordens de serviço carregadas
     */
    public static List<OrdemServico> carregarOrdemServico() {
        // Implementação para carregar as ordens de serviço do arquivo JSON
        return new ArrayList<>();
    }

    public void gerarNotaFiscal() {
        System.out.println("\n=== Nota Fiscal de Serviço ===");
        System.out.println("Ordem de Serviço #" + idOrdemServico);
        System.out.println("Data: " + dataAbertura);
        System.out.println("\nCliente: " + cliente.getNome());
        System.out.println("CPF: " + cliente.getCpf());
        System.out.println("\nVeículo: " + veiculo.getModelo());
        System.out.println("Placa: " + veiculo.getPlaca());
        System.out.println("\nMecânico Responsável: " + mecanico.getNome());
        
        System.out.println("\nServiços Realizados:");
        for (Servico servico : servicos) {
            System.out.println("- " + servico.getDescricao() + ": R$" + String.format("%.2f", servico.getValor()));
        }
        
        System.out.println("\nPeças Utilizadas:");
        for (Peca peca : pecasUtilizadas) {
            System.out.println("- " + peca.getQuantidade() + "x " + peca.getNome() + 
                             " (R$" + String.format("%.2f", peca.getPreco()) + " cada)");
        }
        
        System.out.println("\nObservações: " + (descricao != null ? descricao : "Nenhuma"));
        System.out.println("\nValor Total: R$" + String.format("%.2f", valorTotal));
        System.out.println("===========================");
    }

    public String getDataInicio() {
        return dataAbertura.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    public void finalizarOrdem(String dataFim) {
        this.status = StatusOrdemServico.ENTREGUE;
        this.dataConclusao = LocalDateTime.parse(dataFim, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        
        // Atualizar estoque automaticamente
        try {
            atualizarEstoque();
        } catch (Exception e) {
            System.err.println("AVISO: Ordem finalizada, mas houve problema ao atualizar estoque: " + e.getMessage());
            // Não falha a finalização por problemas de estoque
        }
    }

    public List<Peca> getPecasUsadas() {
        return Collections.unmodifiableList(pecasUtilizadas);
    }
}