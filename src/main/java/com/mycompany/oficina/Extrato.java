package com.mycompany.oficina;
/**
 * Classe responsável por gerar e salvar o extrato de serviços e peças de um cliente.
 * Contém os dados do cliente, ordens de serviço, itens e valores.
 * 
 * @author Ana
 * @version 1.0
 */

import com.mycompany.oficina.Cliente;
import com.mycompany.oficina.OrdemServico;
import com.mycompany.oficina.Peca;
import com.mycompany.oficina.Servico;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Extrato {

    /**
     * Gera e imprime o extrato com os dados do cliente e ordens de serviço.
     * Também salva o extrato em um arquivo de texto.
     * 
     * @param cliente Cliente para o qual o extrato será gerado.
     */
    public static void gerarExtrato(Cliente cliente) {
        StringBuilder sb = new StringBuilder();
        sb.append("==== Extrato do Cliente ====\n");
        sb.append("Nome: ").append(cliente.getNome()).append("\n");
        sb.append("Email: ").append(cliente.getEmail()).append("\n");
        sb.append("Telefone: ").append(cliente.getTelefone()).append("\n");
        sb.append("Status: ").append(cliente.getStatus()).append("\n\n");

        double totalGeral = 0;

        // Corrigido para usar o método correto getOrdensDeServico()
        List<OrdemServico> ordens = cliente.getOrdensDeServico();
        for (OrdemServico os : ordens) {
            sb.append("Ordem de Serviço #").append(os.getIdOrdemServico()).append("\n");
            sb.append("Descrição: ").append(os.getDescricao()).append("\n");

            sb.append("Serviços:\n");
            for (Servico servico : os.getServicos()) {
                sb.append("- ").append(servico.getTipo())  // Usando getTipo() conforme solicitado
                   .append(": R$ ").append(servico.getPreco()).append("\n");
                totalGeral += servico.getPreco();
            }

            sb.append("--------------------------\n");
        }

        sb.append("TOTAL GERAL: R$ ").append(totalGeral).append("\n");

        // Imprimir no console
        System.out.println(sb.toString());

        // Salvar em arquivo
        try (FileWriter writer = new FileWriter("extrato_" + cliente.getNome().replaceAll(" ", "_") + ".txt")) {
            writer.write(sb.toString());
        } catch (IOException e) {
            System.err.println("Erro ao salvar extrato: " + e.getMessage());
        }
    }
}
