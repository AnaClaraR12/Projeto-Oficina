package com.mycompany.oficina.controller;

import com.mycompany.oficina.entidade.OrdemServico;
import com.mycompany.oficina.entidade.Cliente;
import java.util.List;
import java.util.ArrayList;

/**
 * Classe responsável por controlar e manipular listas de ordens de serviço.
 * Fornece funcionalidades para filtragem por cliente, período e status,
 * além de exibição e atualização de ordens de serviço.
 *
 * @author Ana Clara e Pedro
 * @version 1.0
 */
public class OrdemServicoController {

    /**
     * Filtra as ordens de serviço pelo cliente.
     *
     * @param ordens Lista de ordens disponíveis.
     * @param cliente Cliente a ser filtrado.
     * @return Lista de ordens pertencentes ao cliente.
     */
    public static List<OrdemServico> filtrarPorCliente(List<OrdemServico> ordens, Cliente cliente) {
        List<OrdemServico> resultado = new ArrayList<>();
        for (OrdemServico os : ordens) {
            if (os.getCliente() != null && os.getCliente().equals(cliente)) {
                resultado.add(os);
            }
        }
        return resultado;
    }

    /**
     * Filtra as ordens de serviço por período de data de início.
     *
     * @param ordens Lista de ordens disponíveis.
     * @param dataInicioFiltro Data inicial (formato: YYYY-MM-DD).
     * @param dataFimFiltro Data final (formato: YYYY-MM-DD).
     * @return Lista de ordens dentro do período.
     */
    public static List<OrdemServico> filtrarPorPeriodo(List<OrdemServico> ordens, String dataInicioFiltro, String dataFimFiltro) {
        List<OrdemServico> resultado = new ArrayList<>();
        for (OrdemServico os : ordens) {
            String data = os.getDataInicio();
            if (data != null && data.compareTo(dataInicioFiltro) >= 0 && data.compareTo(dataFimFiltro) <= 0) {
                resultado.add(os);
            }
        }
        return resultado;
    }

    /**
     * Filtra as ordens de serviço pelo status.
     *
     * @param ordens Lista de ordens disponíveis.
     * @param statusDesejado Status a ser filtrado (ex: OrdemServico.StatusOrdemServico.FINALIZADA).
     * @return Lista de ordens com o status especificado.
     */
    public static List<OrdemServico> filtrarPorStatus(List<OrdemServico> ordens, OrdemServico.StatusOrdemServico statusDesejado) {
        List<OrdemServico> resultado = new ArrayList<>();
        for (OrdemServico os : ordens) {
            if (os.getStatus() == statusDesejado) {
                resultado.add(os);
            }
        }
        return resultado;
    }

    /**
     * Exibe a lista de ordens de serviço formatadas.
     *
     * @param ordens Lista de ordens a serem exibidas.
     */
    public static void exibirOrdens(List<OrdemServico> ordens) {
        if (ordens.isEmpty()) {
            System.out.println("Nenhuma ordem de serviço encontrada.");
            return;
        }
        for (OrdemServico os : ordens) {
            System.out.println(os); // usa o toString() da OrdemServico
        }
    }

    /**
     * Atualiza uma ordem de serviço existente.
     *
     * @param ordens Lista de ordens de serviço.
     * @param ordem A ordem de serviço a ser atualizada.
     * @return true se a ordem foi atualizada, false se não foi encontrada.
     */
    public static boolean atualizarOrdemServico(List<OrdemServico> ordens, OrdemServico ordem) {
        if (ordem == null) return false;
        boolean ordemEncontrada = false;
        for (int i = 0; i < ordens.size(); i++) {
            if (ordens.get(i).getIdOrdemServico() == ordem.getIdOrdemServico()) {
                ordens.set(i, ordem);
                ordemEncontrada = true;
                break;
            }
        }
        return ordemEncontrada;
    }

    /**
     * Exibe as ordens de serviço agrupadas por cliente.
     *
     * @param ordens Lista de todas as ordens de serviço.
     */
    public static void exibirOrdensPorCliente(List<OrdemServico> ordens) {
        List<Cliente> clientesExibidos = new ArrayList<>();
        for (OrdemServico os : ordens) {
            Cliente cliente = os.getCliente();
            // Verifica se o cliente já foi processado para evitar repetição
            if (cliente != null && !clientesExibidos.contains(cliente)) {
                System.out.println("Cliente: " + cliente.getNome() + " | CPF: " + cliente.getCpfPseudoanonimizado()); // Usa CPF anonimizado
                List<OrdemServico> ordensDoCliente = filtrarPorCliente(ordens, cliente);
                exibirOrdens(ordensDoCliente);
                System.out.println("-----------------------------------------------------");
                clientesExibidos.add(cliente);
            }
        }
        if (clientesExibidos.isEmpty() && !ordens.isEmpty()) {
             System.out.println("Não foi possível agrupar ordens por cliente. Verifique se os clientes estão associados corretamente.");
        } else if (ordens.isEmpty()) {
            System.out.println("Nenhuma ordem de serviço para exibir.");
        }
    }
}
