package com.mycompany.oficina.entidade;

import com.mycompany.oficina.entidade.Veiculo;
import com.mycompany.oficina.entidade.Cliente;
import java.time.LocalDateTime;

/**
 * Representa um agendamento de manutenção ou serviço para um cliente na oficina.
 * Contém informações sobre data, cliente, veículo, serviço e status do agendamento.
 *
 * Esta classe é utilizada para organizar e controlar os agendamentos realizados na oficina.
 *
 * @author Ana Clara e Pedro
 * @version 1.0
 */
public class Agendamento {

    /** Identificador único do agendamento. */
    private final int id; 

    /** Data e hora do agendamento. */
    private LocalDateTime dataHora; 

    /** Status do agendamento usando enumeração. */
    private StatusAgendamento status; 

    /** Indica se o agendamento está confirmado. */
    private boolean confirmado;

    /** Valor total do serviço agendado. */
    private double valor;

    /** Valor retido em caso de cancelamento (20% do valor total). */
    private double valorRetido;

    /** Cliente associado ao agendamento. */
    private Cliente cliente;

    /** Veículo que será atendido no agendamento. */
    private Veiculo veiculo;

    /** Funcionário (ou mecânico) responsável pelo agendamento. */
    private Funcionario funcionario;

    /**
     * Enumeração que define o status de um agendamento.
     */
    public enum StatusAgendamento {
        AGENDADO("Agendado"),
        CONFIRMADO("Confirmado"), 
        CANCELADO("Cancelado"),
        CONCLUIDO("Concluído");

        private final String descricao;

        StatusAgendamento(String descricao) {
            this.descricao = descricao;
        }

        public String getDescricao() {
            return descricao;
        }
    }

    /**
     * Construtor da classe {@code Agendamento}.
     *
     * @param id o identificador único do agendamento
     * @param dataHora a data e hora marcadas para o agendamento
     * @param valor o valor total do serviço
     * @param cliente o cliente que solicitou o agendamento (não pode ser nulo)
     * @param veiculo o veículo que será atendido (não pode ser nulo)
     * @param funcionario o funcionário responsável (não pode ser nulo)
     * @throws IllegalArgumentException se cliente, veículo ou funcionário forem nulos
     */
    public Agendamento(int id, LocalDateTime dataHora, double valor, Cliente cliente, Veiculo veiculo, Funcionario funcionario) {
        if (cliente == null) throw new IllegalArgumentException("Cliente do agendamento não pode ser nulo.");
        if (veiculo == null) throw new IllegalArgumentException("Veículo do agendamento não pode ser nulo.");
        if (funcionario == null) throw new IllegalArgumentException("Funcionário do agendamento não pode ser nulo.");
        if (dataHora == null) throw new IllegalArgumentException("Data e hora do agendamento não podem ser nulas.");
        if (valor < 0) throw new IllegalArgumentException("Valor do agendamento não pode ser negativo.");

        this.id = id;
        this.dataHora = dataHora;
        this.valor = valor;
        this.valorRetido = 0.0;
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.funcionario = funcionario;
        this.confirmado = true;
        this.status = StatusAgendamento.AGENDADO; 
    }

    /** @return o ID do agendamento */
    public int getId() {
        return id;
    }

    /** @return data e hora do agendamento */
    public LocalDateTime getDataHora() {
        return dataHora;
    }

    /**
     * Define a data e hora do agendamento.
     * @param dataHora nova data e hora
     * @throws IllegalArgumentException se dataHora for nula
     */
    public void setDataHora(LocalDateTime dataHora) {
        if (dataHora == null) throw new IllegalArgumentException("Data e hora do agendamento não podem ser nulas.");
        this.dataHora = dataHora;
    }

    /** @return o status do agendamento */
    public StatusAgendamento getStatus() {
        return status;
    }

    /**
     * Define o status do agendamento.
     * @param status novo status
     * @throws IllegalArgumentException se status for nulo
     */
    public void setStatus(StatusAgendamento status) {
        if (status == null) throw new IllegalArgumentException("Status não pode ser nulo.");
        this.status = status;
    }

    /** @return true se confirmado, false caso contrário */
    public boolean isConfirmado() {
        return confirmado;
    }

    /** @return o valor total do serviço */
    public double getValor() {
        return valor;
    }

    /**
     * Define o valor total do serviço.
     * @param valor novo valor
     * @throws IllegalArgumentException se valor for negativo
     */
    public void setValor(double valor) {
        if (valor < 0) throw new IllegalArgumentException("Valor do agendamento não pode ser negativo.");
        this.valor = valor;
    }

    /** @return o valor retido em caso de cancelamento */
    public double getValorRetido() {
        return valorRetido;
    }

    /** @return o cliente associado ao agendamento */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Define o cliente do agendamento.
     * @param cliente novo cliente
     * @throws IllegalArgumentException se cliente for nulo
     */
    public void setCliente(Cliente cliente) {
        if (cliente == null) throw new IllegalArgumentException("Cliente do agendamento não pode ser nulo.");
        this.cliente = cliente;
    }

    /** @return o veículo associado ao agendamento */
    public Veiculo getVeiculo() {
        return veiculo;
    }

    /**
     * Define o veículo do agendamento.
     * @param veiculo novo veículo
     * @throws IllegalArgumentException se veiculo for nulo
     */
    public void setVeiculo(Veiculo veiculo) {
        if (veiculo == null) throw new IllegalArgumentException("Veículo do agendamento não pode ser nulo.");
        this.veiculo = veiculo;
    }

    /** @return o funcionário responsável pelo agendamento */
    public Funcionario getFuncionario() {
        return funcionario;
    }

    /**
     * Define o funcionário responsável.
     * @param funcionario novo funcionário
     * @throws IllegalArgumentException se funcionario for nulo
     */
    public void setFuncionario(Funcionario funcionario) {
        if (funcionario == null) throw new IllegalArgumentException("Funcionário do agendamento não pode ser nulo.");
        this.funcionario = funcionario;
    }

    /**
     * Confirma o agendamento, alterando o status para "Confirmado".
     */
    public void confirmarAgendamento() {
        this.confirmado = true;
        this.status = StatusAgendamento.CONFIRMADO;
        this.valorRetido = 0.0; // Reseta qualquer valor retido anterior
        System.out.println("Agendamento confirmado para " + dataHora);
    }

    /**
     * Cancela o agendamento, alterando o status para "Cancelado" e retendo 20% do valor.
     */
    public void cancelarAgendamento() {
        this.confirmado = false;
        this.status = StatusAgendamento.CANCELADO;
        this.valorRetido = this.valor * 0.20; // Retém 20% do valor total
        System.out.println("Agendamento cancelado para " + dataHora);
        System.out.println("Valor retido (20%): R$" + String.format("%.2f", valorRetido));
    }

    /**
     * Retorna uma representação em string do agendamento,
     * incluindo ID, data/hora, cliente, veículo, funcionário e status.
     *
     * @return representação textual do agendamento
     */
    @Override
    public String toString() {
        String clienteNome = (cliente != null) ? cliente.getNome() : "N/A";
        String veiculoInfo = (veiculo != null) ? veiculo.getModelo() + " (" + veiculo.getPlaca() + ")" : "N/A";
        String funcionarioNome = (funcionario != null) ? funcionario.getNome() : "N/A";

        StringBuilder info = new StringBuilder();
        info.append("Agendamento [ID: ").append(id)
            .append(", Data/Hora: ").append(dataHora)
            .append(", Cliente: ").append(clienteNome)
            .append(", Veículo: ").append(veiculoInfo)
            .append(", Funcionário: ").append(funcionarioNome)
            .append(", Valor: R$").append(String.format("%.2f", valor))
            .append(", Status: ").append(status.getDescricao()) // Usa a descrição do enum
            .append(", Confirmado: ").append(confirmado);

        if (status == StatusAgendamento.CANCELADO) {
            info.append(", Valor Retido: R$").append(String.format("%.2f", valorRetido));
        }

        info.append("]");
        return info.toString();
    }
}