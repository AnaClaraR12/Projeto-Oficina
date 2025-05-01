/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oficina.seguranca;

/**
 * Classe que centraliza a lógica de controle de permissões no sistema.
 * Contém métodos que verificam se o usuário logado tem permissão para realizar ações específicas.
 * 
 * @author Ana Clara
 */
public class ControlePermissao {

    /**
     * Verifica se o usuário logado tem permissão para alterar o cargo de outro funcionário.
     * 
     * @param permissao O tipo de permissão do usuário logado
     * @return true se o usuário tiver permissão de acesso total (Administrador), false caso contrário
     */
    public static boolean podeAlterarCargo(TipoPermissao permissao) {
        return permissao == TipoPermissao.ACESSO_TOTAL;
    }

    /**
     * Verifica se o usuário logado tem permissão para visualizar relatórios.
     * 
     * @param permissao O tipo de permissão do usuário logado
     * @return true se o usuário tiver permissão de acesso total ou limitado, false caso contrário
     */
    public static boolean podeVisualizarRelatorios(TipoPermissao permissao) {
        return permissao == TipoPermissao.ACESSO_TOTAL || permissao == TipoPermissao.ACESSO_LIMITADO;
    }

    // Outros métodos de permissões podem ser adicionados conforme necessário
}

