package com.mycompany.oficina.seguranca;

import com.mycompany.oficina.Funcionario;
 


import java.util.HashMap;
import java.util.Map;

/**
 * Classe responsável pelo sistema de autenticação da oficina.
 * Permite login via funcionário (com permissões) e via email/senha (usuário genérico).
 * Também gerencia o cadastro de usuários por email.
 * 
 * Usa TipoPermissao para distinguir níveis de acesso dos funcionários.
 * 
 * @author Ana Clara e Pedro
 * @version 1.0
 */

public class Autenticacao {
    private Funcionario usuarioLogado;
    private TipoPermissao permissao;
    private Map<String, String> usuarios;

    /**
     * Construtor que inicializa o mapa de usuários.
     */
    public Autenticacao() {
        usuarios = new HashMap<>();
    }

    /**
     * Login para funcionários registrados, com autenticação por senha e definição de permissão.
     * @param funcionario O funcionário tentando logar
     * @param senha A senha fornecida
     * @return true se login for bem-sucedido, false caso contrário
     */
    public boolean loginFuncionario(Funcionario funcionario, String senha) {
        if (funcionario.autenticar(senha)) {
            usuarioLogado = funcionario;
            permissao = funcionario.getCargo().equalsIgnoreCase("Administrador")
                    ? TipoPermissao.ACESSO_TOTAL
                    : TipoPermissao.ACESSO_LIMITADO;
            return true;
        }
        return false;
    }

    /**
     * Login de usuário genérico via email e senha.
     * @param email Email do usuário
     * @param senha Senha do usuário
     * @return true se login for bem-sucedido, false caso contrário
     */
    public boolean loginEmail(String email, String senha) {
        if (usuarios.containsKey(email) && usuarios.get(email).equals(senha)) {
            System.out.println("Login realizado com sucesso para " + email);
            return true;
        } else {
            System.out.println("Login falhou para " + email);
            return false;
        }
    }

    /**
     * Cadastra um novo usuário genérico (email/senha).
     * @param email Email do novo usuário
     * @param senha Senha do novo usuário
     */
    public void cadastrarUsuario(String email, String senha) {
        if (usuarios.containsKey(email)) {
            System.out.println("Usuário já cadastrado!");
        } else {
            usuarios.put(email, senha);
            System.out.println("Usuário cadastrado com sucesso!");
        }
    }

    /**
     * Verifica se o funcionário logado possui permissão total (Administrador).
     * @return true se for admin, false se não for ou se ninguém estiver logado
     */
    public boolean isAdminLogado() {
        return permissao == TipoPermissao.ACESSO_TOTAL;
    }

    /**
     * Realiza o logout do funcionário logado.
     */
    public void fazerLogout() {
        usuarioLogado = null;
        permissao = null;
    }

    public void alterarCargoFuncionario(Funcionario funcionario, String novoCargo) {
    if (ControlePermissao.podeAlterarCargo(permissao)) {
        funcionario.atualizarCargo(novoCargo);
        System.out.println("Cargo alterado com sucesso para: " + novoCargo);
    } else {
        System.out.println("Permissão negada. Apenas administradores podem alterar cargos.");
    }
}

    /**
     * Retorna o funcionário atualmente logado (se houver).
     * @return Funcionario logado ou null
     */
    public Funcionario getUsuarioLogado() {
        return usuarioLogado;
    }
}

