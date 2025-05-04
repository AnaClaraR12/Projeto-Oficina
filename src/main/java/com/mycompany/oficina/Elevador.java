package com.mycompany.oficina;

/**
 * Representa um elevador da oficina mecânica, utilizado para diferentes tipos de serviço em veículos.
 * Cada elevador possui um identificador único, um tipo de serviço associado e um estado de ocupação.
 * A classe também mantém um vetor fixo de três elevadores disponíveis na oficina.
 * 
 * @author Ana Clara e Pedro
 * @version 1.0
 */
public class Elevador {

    private int idElevador;
    private String tipoServico;
    private boolean ocupado;
    private static Elevador[] elevadores = new Elevador[3];

    /**
     * Construtor da classe {@code Elevador}.
     * Inicializa um elevador com o ID e o tipo de serviço especificado.
     * O elevador é criado como desocupado por padrão.
     *
     * @param idElevador o identificador do elevador (1, 2 ou 3)
     * @param tipoServico o tipo de serviço associado ao elevador (ex.: "Simples", "Intermediário", "Complexo")
     */
    public Elevador(int idElevador, String tipoServico) {
        this.idElevador = idElevador;
        this.tipoServico = tipoServico;
        this.ocupado = false;
    }

    /**
     * Retorna o identificador do elevador.
     *
     * @return o ID do elevador
     */
    public int getIdElevador() {
        return idElevador;
    }

    /**
     * Define o identificador do elevador.
     *
     * @param idElevador o novo ID a ser atribuído
     */
    public void setIdElevador(int idElevador) {
        this.idElevador = idElevador;
    }

    /**
     * Retorna o tipo de serviço associado ao elevador.
     *
     * @return o tipo de serviço
     */
    public String getTipoServico() {
        return tipoServico;
    }

    /**
     * Define o tipo de serviço associado ao elevador.
     *
     * @param tipoServico o novo tipo de serviço a ser atribuído
     */
    public void setTipoServico(String tipoServico) {
        this.tipoServico = tipoServico;
    }

    /**
     * Verifica se o elevador está ocupado.
     *
     * @return {@code true} se o elevador estiver em uso, {@code false} caso contrário
     */
    public boolean isOcupado() {
        return ocupado;
    }

    /**
     * Define o estado de ocupação do elevador.
     *
     * @param ocupado {@code true} para marcar como ocupado, {@code false} para livre
     */
    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    /**
     * Retorna o vetor contendo os três elevadores disponíveis na oficina.
     *
     * @return o vetor de elevadores
     */
    public static Elevador[] getElevadores() {
        return elevadores;
    }

    /**
     * Inicializa os três elevadores da oficina, cada um com um tipo de serviço específico.
     */
    public static void inicializarElevadores() {
        elevadores[0] = new Elevador(1, "Simples");
        elevadores[1] = new Elevador(2, "Intermediário");
        elevadores[2] = new Elevador(3, "Complexo");
    }

    /**
     * Ocupa o elevador, se ele estiver livre.
     * Exibe uma mensagem indicando o status da operação.
     */
    public void ocuparElevador() {
        if (!ocupado) {
            ocupado = true;
            System.out.println("Elevador " + idElevador + " (" + tipoServico + ") agora está ocupado.");
        } else {
            System.out.println("Elevador " + idElevador + " já está ocupado.");
        }
    }

    /**
     * Libera o elevador, se ele estiver ocupado.
     * Exibe uma mensagem indicando o status da operação.
     */
    public void liberarElevador() {
        if (ocupado) {
            ocupado = false;
            System.out.println("Elevador " + idElevador + " foi liberado.");
        } else {
            System.out.println("Elevador " + idElevador + " já está livre.");
        }
    }

    /**
     * Retorna uma representação em string do elevador, incluindo ID, tipo de serviço e estado de ocupação.
     *
     * @return uma string com os dados do elevador
     */
    @Override
    public String toString() {
        return "Elevador " + idElevador + " [" + tipoServico + "] - " + (ocupado ? "Ocupado" : "Livre");
    }
}
