package com.mycompany.oficina.entidade;

import com.mycompany.oficina.entidade.Peca;
import com.mycompany.oficina.entidade.Servico;
import com.mycompany.oficina.entidade.OrdemServico;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * Representa um extrato individual gerado para uma Ordem de Serviço específica.
 * Armazena um snapshot dos detalhes da ordem de serviço no momento da emissão,
 * incluindo cliente, serviços, peças e valores, garantindo precisão histórica.
 *
 * @author Ana Clara e Pedro
 * @version 1.1
 */
public class Extrato {

    /**
     * Identificador único universal para o extrato.
     */
    private String idExtrato;

    /**
     * Data em que o extrato foi emitido.
     */
    private LocalDate dataEmissao;

    /**
     * Identificador da Ordem de Serviço à qual este extrato está vinculado.
     */
    private String idOrdemServico;

    /**
     * Identificador do Cliente associado a este extrato (obtido da Ordem de Serviço).
     */
    private String idCliente;

    /**
     * Nome do Cliente no momento da emissão do extrato (copiado para fins históricos).
     */
    private String nomeCliente;

    /**
     * Descrição do problema conforme registrado na Ordem de Serviço no momento da emissão.
     */
    private String descricaoProblemaOS;

    /**
     * Lista dos serviços realizados, copiados da Ordem de Serviço.
     * Contém os detalhes e preços dos serviços como estavam no momento da emissão.
     */
    private List<Servico> servicosRealizados;

    /**
     * Lista das peças utilizadas, copiadas da Ordem de Serviço.
     * Contém os detalhes e preços das peças como estavam no momento da emissão.
     */
    private List<Peca> pecasUtilizadas;

    /**
     * Valor total referente apenas aos serviços listados neste extrato.
     */
    private double valorTotalServicos;

    /**
     * Valor total referente apenas às peças listadas neste extrato.
     */
    private double valorTotalPecas;

    /**
     * Valor total geral do extrato (soma do valor total de serviços e peças).
     */
    private double valorTotalExtrato;

    /**
     * Construtor privado.
     */
    private Extrato() {}

    /**
     * Constrói uma nova instância de Extrato com base em uma Ordem de Serviço.
     * <p>
     * Copia os dados relevantes da Ordem de Serviço e do Cliente associado
     * para garantir que o extrato seja um registro histórico preciso.
     * Gera automaticamente um ID único para o extrato e define a data de emissão
     * para o momento atual.
     * </p>
     *
     * @param os A {@link OrdemServico} a partir da qual o extrato será gerado.
     * Não pode ser {@code null}. O cliente associado à OS também não pode ser {@code null}.
     * @throws IllegalArgumentException Se a Ordem de Serviço ou o Cliente associado à OS forem nulos.
     */
    public Extrato(OrdemServico os) {
        if (os == null) {
            throw new IllegalArgumentException("Ordem de Serviço não pode ser nula para gerar extrato.");
        }
        if (os.getCliente() == null) {
            throw new IllegalArgumentException("Cliente não pode ser nulo na Ordem de Serviço.");
        }

        this.idExtrato = UUID.randomUUID().toString();
        this.dataEmissao = LocalDate.now(); // Data atual da emissão
        this.idOrdemServico = String.valueOf(os.getIdOrdemServico());
        this.idCliente = os.getCliente().getId();
        this.nomeCliente = os.getCliente().getNome();
        this.descricaoProblemaOS = os.getDescricao(); 

        
        this.servicosRealizados = List.copyOf(os.getServicos()); // Garante imutabilidade e cópia da lista
        this.pecasUtilizadas = List.copyOf(os.getPecasUsadas());       

        this.valorTotalServicos = calcularTotalServicos(this.servicosRealizados);
        this.valorTotalPecas = calcularTotalPecas(this.pecasUtilizadas);
        this.valorTotalExtrato = this.valorTotalServicos + this.valorTotalPecas;
    }

    /**
     * Calcula o valor total dos serviços fornecidos.
     *
     * @param servicos A lista de {@link Servico} realizados.
     * @return O somatório dos preços de todos os serviços na lista.
     */
    private double calcularTotalServicos(List<Servico> servicos) {
        return servicos.stream().mapToDouble(Servico::getPreco).sum();
    }

    /**
     * Calcula o valor total das peças utilizadas.
     *
     * @param pecas A lista de {@link Peca} utilizadas.
     * @return O somatório dos preços de todas as peças na lista.
     */
    private double calcularTotalPecas(List<Peca> pecas) {
        return pecas.stream().mapToDouble(peca -> peca.getPreco() * peca.getQuantidade()).sum();
    }

    // --- Getters ---

    /**
     * @return O ID único do extrato.
     */
    public String getIdExtrato() { return idExtrato; }

    /**
     * @return A data de emissão do extrato.
     */
    public LocalDate getDataEmissao() { return dataEmissao; }

    /**
     * @return O ID da Ordem de Serviço associada.
     */
    public String getIdOrdemServico() { return idOrdemServico; }

    /**
     * @return O ID do Cliente associado.
     */
    public String getIdCliente() { return idCliente; }

    /**
     * @return O nome do Cliente como registrado no momento da emissão do extrato.
     */
    public String getNomeCliente() { return nomeCliente; }

    /**
     * @return A descrição do problema da Ordem de Serviço.
     */
    public String getDescricaoProblemaOS() { return descricaoProblemaOS; }

    /**
     * @return Uma lista imutável dos serviços realizados neste extrato.
     */
    public List<Servico> getServicosRealizados() { return servicosRealizados; }

    /**
     * @return Uma lista imutável das peças utilizadas neste extrato.
     */
    public List<Peca> getPecasUtilizadas() { return pecasUtilizadas; }

    /**
     * @return O valor total dos serviços.
     */
    public double getValorTotalServicos() { return valorTotalServicos; }

    /**
     * @return O valor total das peças.
     */
    public double getValorTotalPecas() { return valorTotalPecas; }

    /**
     * @return O valor total geral do extrato.
     */
    public double getValorTotalExtrato() { return valorTotalExtrato; }

    // --- Setters ---

    /**
     * Define o ID do extrato.
     * @param idExtrato O novo ID do extrato.
     */
    public void setIdExtrato(String idExtrato) { this.idExtrato = idExtrato; }

    /**
     * Define a data de emissão do extrato.
     * @param dataEmissao A nova data de emissão.
     */
    public void setDataEmissao(LocalDate dataEmissao) { this.dataEmissao = dataEmissao; }

    /**
     * Define o ID da Ordem de Serviço.
     * @param idOrdemServico O novo ID da Ordem de Serviço.
     */
    public void setIdOrdemServico(String idOrdemServico) { this.idOrdemServico = idOrdemServico; }

    /**
     * Define o ID do Cliente.
     * @param idCliente O novo ID do Cliente.
     */
    public void setIdCliente(String idCliente) { this.idCliente = idCliente; }

    /**
     * Define o nome do Cliente.
     * @param nomeCliente O novo nome do Cliente.
     */
    public void setNomeCliente(String nomeCliente) { this.nomeCliente = nomeCliente; }

    /**
     * Define a descrição do problema da OS.
     * @param descricaoProblemaOS A nova descrição do problema.
     */
    public void setDescricaoProblemaOS(String descricaoProblemaOS) { this.descricaoProblemaOS = descricaoProblemaOS; }

    /**
     * Define a lista de serviços realizados.
     * @param servicosRealizados A nova lista de serviços.
     */
    public void setServicosRealizados(List<Servico> servicosRealizados) { this.servicosRealizados = servicosRealizados; }

    /**
     * Define a lista de peças utilizadas.
     * @param pecasUtilizadas A nova lista de peças.
     */
    public void setPecasUtilizadas(List<Peca> pecasUtilizadas) { this.pecasUtilizadas = pecasUtilizadas; }

    /**
     * Define o valor total dos serviços.
     * @param valorTotalServicos O novo valor total dos serviços.
     */
    public void setValorTotalServicos(double valorTotalServicos) { this.valorTotalServicos = valorTotalServicos; }

    /**
     * Define o valor total das peças.
     * @param valorTotalPecas O novo valor total das peças.
     */
    public void setValorTotalPecas(double valorTotalPecas) { this.valorTotalPecas = valorTotalPecas; }

    /**
     * Define o valor total do extrato.
     * @param valorTotalExtrato O novo valor total do extrato.
     */
    public void setValorTotalExtrato(double valorTotalExtrato) { this.valorTotalExtrato = valorTotalExtrato; }


    /**
     * Retorna uma representação textual formatada do extrato.
     * Inclui todos os detalhes relevantes como ID, data, cliente, serviços, peças e totais.
     *
     * @return Uma {@code String} contendo os detalhes formatados do extrato.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("==== Extrato #").append(idExtrato).append(" ====\n");
        sb.append("Data de Emissão: ").append(dataEmissao).append("\n");
        sb.append("Cliente: ").append(nomeCliente).append(" (ID: ").append(idCliente).append(")\n");
        sb.append("Referente à Ordem de Serviço #").append(idOrdemServico).append("\n");
        sb.append("Problema Reportado: ").append(descricaoProblemaOS).append("\n\n");

        sb.append("Serviços Realizados:\n");
        if (servicosRealizados == null || servicosRealizados.isEmpty()) { // Adicionada verificação de nulidade
            sb.append("- Nenhum serviço registrado.\n");
        } else {
            for (Servico servico : servicosRealizados) {
                sb.append("- ").append(servico.getCategoria()).append(": R$ ").append(String.format("%.2f", servico.getPreco())).append("\n");
            }
        }
        sb.append("Total Serviços: R$ ").append(String.format("%.2f", valorTotalServicos)).append("\n\n");

        sb.append("Peças Utilizadas:\n");
        if (pecasUtilizadas == null || pecasUtilizadas.isEmpty()) { // Adicionada verificação de nulidade
            sb.append("- Nenhuma peça registrada.\n");
        } else {
            for (Peca peca : pecasUtilizadas) {
                double valorTotalPeca = peca.getPreco() * peca.getQuantidade();
                sb.append("- ").append(peca.getQuantidade()).append("x ").append(peca.getNome())
                  .append(" (R$ ").append(String.format("%.2f", peca.getPreco())).append(" cada)")
                  .append(": R$ ").append(String.format("%.2f", valorTotalPeca)).append("\n");
            }
        }
        sb.append("Total Peças: R$ ").append(String.format("%.2f", valorTotalPecas)).append("\n\n");

        sb.append("TOTAL GERAL DO EXTRATO: R$ ").append(String.format("%.2f", valorTotalExtrato)).append("\n");
        sb.append("========================\n");
        return sb.toString();
    }

    /**
     * Salva a representação textual deste extrato em um arquivo de texto.
     * O nome do arquivo será no formato "extrato_IDDOEXTRATO.txt".
     * Exibe uma mensagem no console sobre o sucesso ou falha da operação.
     */
    public void salvarExtratoEmArquivo() {
        String nomeArquivo = "extrato_" + this.idExtrato + ".txt";
        // Uso de try-with-resources para garantir que o FileWriter seja fechado automaticamente.
        try (FileWriter writer = new FileWriter(nomeArquivo)) {
            writer.write(this.toString());
            System.out.println("Extrato salvo em: " + nomeArquivo);
        } catch (IOException e) {
            System.err.println("Erro ao salvar extrato em arquivo: " + e.getMessage());
           
        }
    }
    
    /**
     * Método para verificar se o cálculo do extrato está correto.
     * Compara o valor total calculado com o valor total da ordem de serviço.
     * 
     * @param os A ordem de serviço para comparação
     * @return true se os valores coincidem, false caso contrário
     */
    public boolean verificarCalculoCorreto(OrdemServico os) {
        double valorOS = os.getValorTotal();
        double valorExtrato = this.valorTotalExtrato;
        double diferenca = Math.abs(valorOS - valorExtrato);
        
        // Tolerância de 0.01 para diferenças de arredondamento
        if (diferenca <= 0.01) {
            System.out.println("✅ Extrato correto! Valor OS: R$ " + String.format("%.2f", valorOS) + 
                             " | Valor Extrato: R$ " + String.format("%.2f", valorExtrato));
            return true;
        } else {
            System.out.println("❌ ERRO NO EXTRATO! Valor OS: R$ " + String.format("%.2f", valorOS) + 
                             " | Valor Extrato: R$ " + String.format("%.2f", valorExtrato) + 
                             " | Diferença: R$ " + String.format("%.2f", diferenca));
            return false;
        }
    }
}