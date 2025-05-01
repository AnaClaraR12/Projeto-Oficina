package com.mycompany.oficina;

import java.util.Date;

/**
 * Classe que representa um agendamento de manutenção ou serviço para um cliente na oficina.
 * Cada agendamento possui um identificador único, data e hora, e um status de confirmação.
 * 
 * @author Ana Clara
 */
public class Agendamento {
    
    /** Identificador único do agendamento. */
    private int id;

    /** Data e hora do agendamento. */
    private Date dataHora;

    /** Status de confirmação do agendamento (true se confirmado, false se pendente). */
    private boolean confirmado;

    /**
     * Construtor para a classe {@code Agendamento}.
     *
     * @param id o identificador único do agendamento
     * @param dataHora a data e hora marcadas para o agendamento
     */
    public Agendamento(int id, Date dataHora) {
        this.id = id;
        this.dataHora = dataHora;
        this.confirmado = false; // Inicialmente o agendamento não está confirmado
    }

    /**
     * Retorna o identificador do agendamento.
     *
     * @return o ID do agendamento
     */
    public int getId() {
        return id;
    }

    /**
     * Define o identificador do agendamento.
     *
     * @param id o novo ID do agendamento
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retorna a data e hora do agendamento.
     *
     * @return a data e hora do agendamento
     */
    public Date getDataHora() {
        return dataHora;
    }

    /**
     * Define a data e hora do agendamento.
     *
     * @param dataHora a nova data e hora a serem atribuídas
     */
    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    /**
     * Verifica se o agendamento está confirmado.
     *
     * @return {@code true} se confirmado, {@code false} caso contrário
     */
    public boolean isConfirmado() {
        return confirmado;
    }

    /**
     * Confirma o agendamento, alterando seu status para confirmado.
     * Também exibe uma mensagem indicando a confirmação.
     */
    public void confirmarAgendamento() {
        this.confirmado = true;
        System.out.println("Agendamento confirmado para " + dataHora);
    }

    /**
     * Retorna uma representação em string do agendamento,
     * incluindo ID, data/hora e status de confirmação.
     *
     * @return uma string com os dados do agendamento
     */
    @Override
    public String toString() {
        return "Agendamento [ID: " + id + ", Data/Hora: " + dataHora + ", Confirmado: " + confirmado + "]";
    }
}
