package com.mycompany.oficina;

import java.util.Date;

/**
 * Classe que representa um agendamento de manutenção ou serviço para um cliente na oficina.
 * Cada agendamento possui um identificador único, data e hora, cliente, veículo e funcionário associado.
 * O status pode ser "Agendado", "Cancelado" ou "Concluído".
 * 
 * @author Ana Clara e Pedro
 * @version 1.0
 */
public class Agendamento {

    /** Identificador único do agendamento. */
    private int id;

    /** Data e hora do agendamento. */
    private Date dataHora;

    /** Status textual do agendamento (ex: "Agendado", "Cancelado", "Concluído"). */
    private String status;

    /** Indica se o agendamento está confirmado. */
    private boolean confirmado;

    /** Cliente associado ao agendamento. */
    private Cliente cliente;

    /** Veículo que será atendido no agendamento. */
    private Veiculo veiculo;

    /** Funcionário (ou mecânico) responsável pelo agendamento. */
    private Funcionario funcionario;

    /**
     * Construtor da classe {@code Agendamento}.
     *
     * @param id          o identificador único do agendamento
     * @param dataHora    a data e hora marcadas para o agendamento
     * @param cliente     o cliente que solicitou o agendamento
     * @param veiculo     o veículo que será atendido
     * @param funcionario o funcionário responsável
     */
    public Agendamento(int id, Date dataHora, Cliente cliente, Veiculo veiculo, Funcionario funcionario) {
        this.id = id;
        this.dataHora = dataHora;
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.funcionario = funcionario;
        this.confirmado = false;
        this.status = "Agendado";
    }

    /** @return o ID do agendamento */
    public int getId() {
        return id;
    }

    /**
     * Define o ID do agendamento.
     * @param id novo identificador
     */
    public void setId(int id) {
        this.id = id;
    }

    /** @return data e hora do agendamento */
    public Date getDataHora() {
        return dataHora;
    }

    /**
     * Define a data e hora do agendamento.
     * @param dataHora nova data e hora
     */
    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    /** @return o status textual do agendamento */
    public String getStatus() {
        return status;
    }

    /**
     * Define o status textual do agendamento.
     * @param status novo status (ex: "Cancelado", "Concluído")
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /** @return true se confirmado, false caso contrário */
    public boolean isConfirmado() {
        return confirmado;
    }

    /** @return o cliente associado ao agendamento */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Define o cliente do agendamento.
     * @param cliente novo cliente
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /** @return o veículo associado ao agendamento */
    public Veiculo getVeiculo() {
        return veiculo;
    }

    /**
     * Define o veículo do agendamento.
     * @param veiculo novo veículo
     */
    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    /** @return o funcionário responsável pelo agendamento */
    public Funcionario getFuncionario() {
        return funcionario;
    }

    /**
     * Define o funcionário responsável.
     * @param funcionario novo funcionário
     */
    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    /**
     * Confirma o agendamento, alterando o status para "Confirmado".
     */
    public void confirmarAgendamento() {
        this.confirmado = true;
        this.status = "Confirmado";
        System.out.println("Agendamento confirmado para " + dataHora);
    }

    /**
     * Cancela o agendamento, alterando o status para "Cancelado".
     */
    public void cancelarAgendamento() {
        this.confirmado = false;
        this.status = "Cancelado";
        System.out.println("Agendamento cancelado para " + dataHora);
    }

    /**
     * Retorna uma representação em string do agendamento,
     * incluindo ID, data/hora, cliente, veículo, funcionário e status.
     *
     * @return representação textual do agendamento
     */
    @Override
    public String toString() {
        return "Agendamento [ID: " + id +
               ", Data/Hora: " + dataHora +
               ", Cliente: " + cliente.getNome() +
               ", Veículo: " + veiculo.getModelo() +
               ", Funcionário: " + funcionario.getNome() +
               ", Status: " + status +
               ", Confirmado: " + confirmado + "]";
    }
}

