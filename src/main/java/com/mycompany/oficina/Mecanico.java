package com.mycompany.oficina;

/**
 * Representa um mecânico da oficina, com informações sobre sua especialidade.
 * Herda propriedades da classe {@link Funcionario} e pode realizar serviços em veículos.
 * 
 * @author Ana Clara
 * @version 1.0
 */
public class Mecanico extends Funcionario {
    private String especialidade;

    /**
     * Construtor da classe {@code Mecanico}.
     * Inicializa o mecânico com nome, email, cargo e especialidade.
     *
     * @param nome o nome do mecânico
     * @param email o email do mecânico
     * @param cargo o cargo do mecânico (ex.: "Mecânico")
     * @param especialidade a área de especialização do mecânico (ex.: "Motor", "Suspensão")
     */
    public Mecanico(String nome, String email, String cargo, String especialidade) {
        super(nome, email, cargo);
        this.especialidade = especialidade;
    }

    /**
     * Realiza um serviço em um veículo.
     * Exibe no console o serviço realizado e o veículo correspondente.
     *
     * @param servico o serviço a ser realizado
     * @param veiculo o veículo no qual o serviço será executado
     */
    public void realizarServico(Servico servico, Veiculo veiculo) {
        System.out.println("Serviço " + servico.toString() + " realizado no veículo: " + veiculo.toString());
    }
}
