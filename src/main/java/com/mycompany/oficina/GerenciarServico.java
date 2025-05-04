package com.mycompany.oficina;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Responsável por gerenciar as ordens de serviço de uma oficina.
 * Permite adicionar, editar e exibir ordens de serviço.
 * Implementa a interface {@link Serializable} para possibilitar a serialização de seus dados.
 * 
 * @author Pedro e Ana Clara
 * @version 1.0
 */
public class GerenciarServico implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<OrdemServico> ordens;

    /**
     * Construtor da classe {@code GerenciarServico}.
     * Inicializa a lista de ordens de serviço.
     */
    public GerenciarServico() {
        this.ordens = new ArrayList<>();
    }

    /**
     * Adiciona uma nova ordem de serviço à lista.
     *
     * @param ordem a ordem de serviço a ser adicionada
     */
    public void adicionarOrdem(OrdemServico ordem) {
        ordens.add(ordem);
        System.out.println("Ordem adicionada: " + ordem.toString());
    }

    /**
     * Edita uma ordem de serviço existente, substituindo por uma nova com dados atualizados.
     * Mantém as peças da ordem anterior.
     *
     * @param id o ID da ordem a ser editada
     * @param novaDescricao a nova descrição da ordem
     * @param novoStatus o novo status da ordem (ex.: "Finalizada")
     * @return {@code true} se a ordem foi encontrada e editada com sucesso; {@code false} caso contrário
     */
       public boolean editarOrdem(int id, String novaDescricao, String novoStatus) {
    for (OrdemServico os : ordens) {
        // Usar o método correto para obter o ID
        if (os.getIdOrdemServico() == id) {
            // Atualiza a descrição se novaDescricao não for nula
            if (novaDescricao != null) {
                os.setDescricao(novaDescricao);
            }

            // Atualiza o status se novoStatus for "Finalizada"
            if (novoStatus != null && novoStatus.equalsIgnoreCase("Finalizada")) {
                os.finalizarOrdem("dataFim"); // Substitua "dataFim" pela data atual ou desejada
            }

            System.out.println("Ordem atualizada: " + os.toString());
            return true;
        }
    }
    System.out.println("Ordem com ID " + id + " não encontrada.");
    return false;
}   

    /**
     * Exibe todas as ordens de serviço cadastradas no sistema.
     * Caso não existam ordens, uma mensagem indicativa será exibida.
     */
    public void exibirOrdens() {
        if (ordens.isEmpty()) {
            System.out.println("Nenhuma ordem de serviço registrada.");
        } else {
            for (OrdemServico os : ordens) {
                System.out.println(os.toString());
            }
        }
    }

    /**
     * Retorna a lista de ordens de serviço cadastradas.
     *
     * @return uma lista contendo todas as ordens de serviço
     */
    public List<OrdemServico> getOrdens() {
        return ordens;
    }
}