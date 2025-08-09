package com.mycompany.oficina.controller;

import com.mycompany.oficina.entidade.JsonUtil;
import com.mycompany.oficina.entidade.OrdemServico;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por gerenciar as ordens de serviço da oficina.
 * Permite adicionar, editar, exibir e controlar ordens de serviço.
 * Implementa a interface Serializable para possibilitar a serialização de seus dados.
 *
 * @author Ana Clara e Pedro
 * @version 1.0
 */
public class GerenciarServico implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<OrdemServico> ordens;

    /**
     * Construtor da classe {@code GerenciarServico}.
     * Inicializa a lista de ordens de serviço e carrega dados existentes.
     */
    public GerenciarServico() {
        this.ordens = new ArrayList<>();
        carregarOrdensExistentes();
    }
    
    /**
     * Carrega ordens de serviço existentes do arquivo JSON.
     */
    private void carregarOrdensExistentes() {
        try {
            List<OrdemServico> ordensExistentes = JsonUtil.lerLista("json/ordens_servico.json", OrdemServico.class);
            if (ordensExistentes != null) {
                this.ordens.addAll(ordensExistentes);
                System.out.println("Carregadas " + ordensExistentes.size() + " ordens de serviço existentes.");
            }
        } catch (Exception e) {
            System.out.println("AVISO: Não foi possível carregar ordens de serviço existentes: " + e.getMessage());
        }
    }

    /**
     * Adiciona uma nova ordem de serviço à lista.
     *
     * @param ordem a ordem de serviço a ser adicionada
     */
    public void adicionarOrdem(OrdemServico ordem) {
        ordens.add(ordem);
        System.out.println("Ordem adicionada: " + ordem.toString());
        JsonUtil.salvarLista("json/ordens_servico.json", ordens);
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
        if (os.getIdOrdemServico() == id) {
            
            if (novaDescricao != null) {
                os.setDescricao(novaDescricao);
            }

            // Atualiza o status se novoStatus for "Finalizada"
            if (novoStatus != null && novoStatus.equalsIgnoreCase("Finalizada")) {
                os.finalizarOrdem("dataFim"); 
            }

            System.out.println("Ordem atualizada: " + os.toString());
            JsonUtil.salvarLista("json/ordens_servico.json", ordens);
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