package com.mycompany.oficina;
/**
 * Classe responsável por controlar e manipular listas de ordens de serviço.
 * Fornece funcionalidades para filtragem por cliente, período e status, 
 * além de exibição e atualização de ordens de serviço.
 * 
 * Esta classe atua como uma camada de lógica entre a interface de usuário e os dados.
 * 
 * @author Ana Clara
 * @version 1.0
 */
import java.util.List;
import java.util.ArrayList;

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
            if (os.getCliente().equals(cliente)) {
                resultado.add(os);
            }
        }
        return resultado;
    }

    /**
     * Filtra as ordens de serviço por período de data de início.
     *
     * @param ordens Lista de ordens disponíveis.
     * @param dataInicioFiltro Data inicial (formato: yyyy-MM-dd).
     * @param dataFimFiltro Data final (formato: yyyy-MM-dd).
     * @return Lista de ordens dentro do período.
     */
    public static List<OrdemServico> filtrarPorPeriodo(List<OrdemServico> ordens, String dataInicioFiltro, String dataFimFiltro) {
        List<OrdemServico> resultado = new ArrayList<>();
        for (OrdemServico os : ordens) {
            String data = os.getDataInicio();
            if (data.compareTo(dataInicioFiltro) >= 0 && data.compareTo(dataFimFiltro) <= 0) {
                resultado.add(os);
            }
        }
        return resultado;
    }

    /**
     * Filtra as ordens de serviço pelo status.
     *
     * @param ordens Lista de ordens disponíveis.
     * @param statusDesejado Status a ser filtrado (ex: "Finalizada").
     * @return Lista de ordens com o status especificado.
     */
    public static List<OrdemServico> filtrarPorStatus(List<OrdemServico> ordens, String statusDesejado) {
        List<OrdemServico> resultado = new ArrayList<>();
        for (OrdemServico os : ordens) {
            if (os.getStatus().equalsIgnoreCase(statusDesejado)) {
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
            if (!clientesExibidos.contains(cliente)) {
                System.out.println("Cliente: " + cliente.getNome() + " | CPF: " + cliente.getCpf());
                List<OrdemServico> ordensDoCliente = filtrarPorCliente(ordens, cliente);
                exibirOrdens(ordensDoCliente);
                System.out.println("-----------------------------------------------------");
                clientesExibidos.add(cliente);
            }
        }
    }
}
