package com.mycompany.oficina.entidade;

import com.mycompany.oficina.entidade.Funcionario;
import java.time.LocalDateTime;

/**
 * Classe que representa um registro de ponto de um funcionário.
 * Cada registro contém a referência ao funcionário, o horário de entrada
 * e o horário de saída (quando registrado).
 *
 * Esta classe é utilizada para controle de presença e jornada dos funcionários da oficina.
 *
 * @author Ana Clara e Pedro
 * @version 1.0
 */
public class PontoFuncionario {

    /**
     * O objeto Funcionario ao qual este registro de ponto pertence.
     * Isso estabelece uma associação direta entre PontoFuncionario e Funcionario.
     */
    private Funcionario funcionario;

    /** Horário de entrada do funcionário. */
    private LocalDateTime entrada;

    /** Horário de saída do funcionário. Pode ser nulo se o funcionário ainda não saiu. */
    private LocalDateTime saida;

    /**
     * Construtor para a classe PontoFuncionario.
     *
     * @param funcionario O objeto Funcionario associado a este registro de ponto.
     * @param entrada O horário de entrada do funcionário.
     * @param saida O horário de saída do funcionário. Pode ser nulo para um registro de entrada.
     */
    public PontoFuncionario(Funcionario funcionario, LocalDateTime entrada, LocalDateTime saida) {
        this.funcionario = funcionario;
        this.entrada = entrada;
        this.saida = saida;
    }

    /**
     * Retorna o objeto Funcionario associado a este registro de ponto.
     *
     * @return o objeto Funcionario.
     */
    public Funcionario getFuncionario() {
        return funcionario;
    }

    /**
     * Define o funcionário associado a este registro de ponto.
     *
     * @param funcionario o objeto Funcionario a ser associado.
     */
    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    /**
     * Retorna o horário de entrada do funcionário.
     *
     * @return o horário de entrada.
     */
    public LocalDateTime getEntrada() {
        return entrada;
    }

    /**
     * Define o horário de entrada do funcionário.
     *
     * @param entrada o novo horário de entrada.
     */
    public void setEntrada(LocalDateTime entrada) {
        this.entrada = entrada;
    }

    /**
     * Retorna o horário de saída do funcionário.
     *
     * @return o horário de saída, ou null se ainda não registrado.
     */
    public LocalDateTime getSaida() {
        return saida;
    }

    /**
     * Define o horário de saída do funcionário.
     *
     * @param saida o novo horário de saída.
     */
    public void setSaida(LocalDateTime saida) {
        this.saida = saida;
    }

    /**
     * Retorna uma representação em string do registro de ponto,
     * incluindo o nome do funcionário, horário de entrada e saída.
     *
     * @return uma string formatada com os dados do ponto do funcionário.
     */
    @Override
    public String toString() {
        return "PontoFuncionario{" +
                "funcionario=" + (funcionario != null ? funcionario.getNome() : "N/A") +
                ", entrada=" + entrada +
                ", saida=" + (saida != null ? saida : "Aberto") +
                '}';
    }
}