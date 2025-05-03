package com.mycompany.oficina;

import java.util.Date;

/**
 * Representa um agendamento de serviço na oficina, contendo informações como
 * o identificador e a data/hora marcada.
 * Esta classe permite confirmar o agendamento registrado.
 * 
 * @author Ana Clara
 * @version 1.0
 */
public class Agendamento {

    private int id;
    private Date dataHora;

    /**
     * Construtor da classe {@code Agendamento}.
     * Inicializa um agendamento com o identificador e a data/hora definida.
     *
     * @param id o identificador único do agendamento
     * @param dataHora a data e hora marcadas para o agendamento
     */
    public Agendamento(int id, Date dataHora) {
        this.id = id;
        this.dataHora = dataHora;
    }

    /**
     * Confirma o agendamento e exibe a data e hora no console.
     */
    public void confirmarAgendamento() {
        System.out.println("Agendamento confirmado para " + dataHora);
    }
}
