package com.mycompany.oficina.seguranca;

import com.mycompany.oficina.entidade.Administrador;
import com.mycompany.oficina.entidade.Funcionario;

import java.util.Optional;

/**
 * Classe responsável pelo sistema de autenticação da oficina.
 * Gerencia login, logout e controle de sessão de usuários e administradores.
 * <p>
 * Usa {@link TipoPermissao} para distinguir níveis de acesso.
 * É implementada como um Singleton para garantir uma única instância de autenticação no sistema.
 *
 * @author Ana Clara e Pedro
 * @version 1.2 (Alterações para focar em Administrador e Funcionário, sem usuário genérico)
 */
public class Autenticacao {
    private static Autenticacao instancia;

    private Funcionario usuarioLogadoFuncionario;
    
    private Administrador usuarioLogadoAdministrador;

    private TipoPermissao permissaoUsuarioLogado;

    /** Referência para o serviço de gerenciamento de colaboradores, usado para buscar funcionários e administradores. */
    private GerenciarColaboradores colaboradorService;

    
    private Autenticacao() {
        this.colaboradorService = GerenciarColaboradores.getInstance();
    }

    
    public static synchronized Autenticacao getInstance() {
        if (instancia == null) {
            instancia = new Autenticacao();
        }
        return instancia;
    }

    /**
     * Tenta autenticar um administrador com base no email e senha fornecidos.
     * Se bem-sucedido, define o administrador logado e suas permissões (ACESSO_TOTAL).
     * Limpa qualquer outro tipo de usuário que possa estar logado.
     *
     * @param email O email do administrador tentando o login.
     * @param senha A senha fornecida pelo administrador.
     * @return {@code true} se o login for bem-sucedido, {@code false} caso contrário.
     */
    public boolean loginAdministrador(String email, String senha) {
        System.out.println("Tentando login de administrador para: " + email);
        Optional<Administrador> optAdmin = colaboradorService.buscarAdministradorPorEmail(email);

        if (optAdmin.isPresent()) {
            Administrador admin = optAdmin.get();
            System.out.println("Administrador encontrado: " + admin.getNome());
           
            if (admin.autenticar(senha)) {
                this.usuarioLogadoAdministrador = admin;
                this.usuarioLogadoFuncionario = null; 
                this.permissaoUsuarioLogado = TipoPermissao.ACESSO_TOTAL;
                System.out.println("Login de Administrador realizado com sucesso para: " + admin.getNome());
                return true;
            } else {
                System.out.println("Senha incorreta para administrador: " + email);
            }
        } else {
            System.out.println("Administrador não encontrado para email: " + email);
        }
        return false;
    }

    /**
     * Tenta autenticar um funcionário com base no email e senha fornecidos.
     * Se bem-sucedido, define o funcionário logado e suas permissões (ACESSO_LIMITADO).
     * Limpa qualquer outro tipo de usuário que possa estar logado.
     *
     * @param email O email do funcionário tentando o login.
     * @param senha A senha fornecida pelo funcionário.
     * @return {@code true} se o login for bem-sucedido, {@code false} caso contrário.
     */
    public boolean loginFuncionario(String email, String senha) {
        System.out.println("Tentando login de funcionário para: " + email);
        Optional<Funcionario> optFuncionario = colaboradorService.buscarFuncionarioPorEmail(email);

        if (optFuncionario.isPresent()) {
            Funcionario funcionario = optFuncionario.get();
            System.out.println("Funcionário encontrado: " + funcionario.getNome());
            
            if (funcionario.autenticar(senha)) {
                this.usuarioLogadoFuncionario = funcionario;
                this.usuarioLogadoAdministrador = null; 
                this.permissaoUsuarioLogado = TipoPermissao.ACESSO_LIMITADO;
                System.out.println("Login de funcionário realizado com sucesso para: " + funcionario.getNome());
                return true;
            } else {
                System.out.println("Senha incorreta para funcionário: " + email);
            }
        } else {
            System.out.println("Funcionário não encontrado para email: " + email);
        }
        return false;
    }

    /**
     * Realiza o logout do usuário atualmente logado, limpando os dados de sessão.
     */
    public void fazerLogout() {
        this.usuarioLogadoFuncionario = null;
        this.usuarioLogadoAdministrador = null;
        this.permissaoUsuarioLogado = null;
        System.out.println("Logout realizado.");
    }

    /**
     * Retorna o objeto do funcionário atualmente logado.
     *
     * @return O objeto {@link Funcionario} logado, ou {@code null} se nenhum funcionário estiver logado.
     */
    public Funcionario getUsuarioLogadoFuncionario() {
        return usuarioLogadoFuncionario;
    }

    /**
     * Retorna o objeto do administrador atualmente logado.
     *
     * @return O objeto {@link Administrador} logado, ou {@code null} se nenhum administrador estiver logado.
     */
    public Administrador getUsuarioLogadoAdministrador() {
        return usuarioLogadoAdministrador;
    }

    /**
     * Verifica se há um funcionário atualmente logado no sistema.
     *
     * @return {@code true} se um funcionário estiver logado, {@code false} caso contrário.
     */
    public boolean isFuncionarioLogado() {
        return this.usuarioLogadoFuncionario != null;
    }

    /**
     * Verifica se há um administrador atualmente logado no sistema.
     *
     * @return {@code true} se um administrador estiver logado, {@code false} caso contrário.
     */
    public boolean isAdministradorLogado() { 
        return this.usuarioLogadoAdministrador != null;
    }

    /**
     * Retorna o tipo de permissão do usuário atualmente logado.
     *
     * @return O {@link TipoPermissao} do usuário logado, ou {@code null} se ninguém estiver logado.
     */
    public TipoPermissao getPermissaoUsuarioLogado() {
        return permissaoUsuarioLogado;
    }

    /**
     * Verifica se o usuário atualmente logado tem privilégios de administrador (ACESSO_TOTAL).
     * Isso ocorre **apenas** se um {@link Administrador} estiver logado.
     *
     * @return {@code true} se um administrador estiver logado, {@code false} caso contrário.
     */
    public boolean temAcessoTotal() {
        return this.permissaoUsuarioLogado == TipoPermissao.ACESSO_TOTAL;
    }

    /**
     * Retorna uma representação em string do estado atual do serviço de Autenticação,
     * indicando o usuário logado (se houver).
     *
     * @return Uma string descrevendo o estado da autenticação.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Estado da Autenticação:\n");
        if (isAdministradorLogado()) {
            Administrador admin = getUsuarioLogadoAdministrador();
            sb.append("  - Usuário Logado: Administrador - ").append(admin != null ? admin.getNome() : "N/A");
        } else if (isFuncionarioLogado()) {
            Funcionario f = getUsuarioLogadoFuncionario();
            sb.append("  - Usuário Logado: Funcionário - ").append(f != null ? f.getNome() : "N/A");
        } else {
            sb.append("  - Status: Nenhum usuário logado no momento.");
        }

        if (permissaoUsuarioLogado != null && (isAdministradorLogado() || isFuncionarioLogado())) {
            sb.append(" (Permissão: ").append(getPermissaoUsuarioLogado()).append(")\n");
        } else { 
            sb.append("\n");
        }
        return sb.toString();
    }
}