package com.mycompany.oficina.seguranca;

import com.mycompany.oficina.entidade.Administrador;
import com.mycompany.oficina.entidade.Funcionario;
import com.mycompany.oficina.entidade.JsonUtil;
import com.mycompany.oficina.entidade.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Classe responsável por gerenciar colaboradores (funcionários e administradores) da oficina.
 * Permite cadastrar, listar, alterar e remover colaboradores, além de autenticação e permissões.
 *
 * @author Ana Clara e Pedro
 * @version 1.0
 */
public class GerenciarColaboradores {
    private static GerenciarColaboradores instancia;
    
    private List<Usuario> colaboradores;

    /**
     * Construtor privado para implementar o padrão Singleton.
     * Inicializa a lista de colaboradores e carrega dados do JSON.
     */
    private GerenciarColaboradores() {
        System.out.println("Inicializando GerenciarColaboradores...");
        colaboradores = new ArrayList<>();
        carregarColaboradoresDoJson();
        System.out.println("GerenciarColaboradores inicializado com " + colaboradores.size() + " colaboradores");
    }

    /**
     * Retorna a instância única da classe GerenciarColaboradores (Singleton).
     * Cria a instância na primeira chamada e a reutiliza nas chamadas subsequentes.
     *
     * @return A instância única de GerenciarColaboradores.
     */
    public static synchronized GerenciarColaboradores getInstance() {
        System.out.println("GerenciarColaboradores.getInstance() chamado");
        if (instancia == null) {
            System.out.println("Criando nova instância de GerenciarColaboradores");
            instancia = new GerenciarColaboradores();
        } else {
            System.out.println("Reutilizando instância existente de GerenciarColaboradores");
        }
        return instancia;
    }

    /**
     * Carrega os colaboradores do arquivo JSON.
     * Se o arquivo não existir ou estiver vazio, a lista permanece vazia.
     */
    private void carregarColaboradoresDoJson() {
        try {
            System.out.println("Tentando carregar colaboradores do JSON...");
            
            // Carregar todos os usuários do JSON
            List<Usuario> colaboradoresCarregados = JsonUtil.lerLista("json/colaboradores.json", Usuario.class);
            if (colaboradoresCarregados != null) {
                System.out.println("Colaboradores carregados do JSON: " + colaboradoresCarregados.size());
                
                // Filtrar e adicionar apenas os que são realmente Administrador ou Funcionario
                for (Usuario usuario : colaboradoresCarregados) {
                    if (usuario instanceof Administrador || usuario instanceof Funcionario) {
                        this.colaboradores.add(usuario);
                        System.out.println("  - " + usuario.getClass().getSimpleName() + ": " + usuario.getEmail());
                    } else {
                        System.out.println("  - Usuário ignorado (tipo não reconhecido): " + usuario.getEmail());
                    }
                }
            } else {
                System.out.println("Nenhum colaborador carregado do JSON (lista nula)");
            }
        } catch (Exception e) {
            System.out.println("Erro ao carregar colaboradores do JSON: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Cadastra um novo administrador no sistema, incluindo sua senha.
     * Verifica se o email já está em uso antes de cadastrar.
     *
     * @param nome o nome do administrador.
     * @param email o email do administrador (deve ser único).
     * @param senha a senha do administrador (será criptografada pela classe Administrador).
     * @param nivelAcesso o nível de acesso do administrador.
     */
    public void cadastrarAdministrador(String nome, String email, String senha, int nivelAcesso) {
        if (buscarUsuarioPorEmail(email).isPresent()) {
            System.out.println("Erro ao cadastrar administrador: O email '" + email + "' já está em uso.");
            return;
        }
        try {
            Administrador admin = new Administrador(nome, email, senha, nivelAcesso);
            this.colaboradores.add(admin);
            System.out.println("Administrador '" + nome + "' cadastrado com sucesso!");
            JsonUtil.salvarLista("json/colaboradores.json", colaboradores);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao cadastrar administrador: " + e.getMessage());
        }
    }

    /**
     * Cadastra um novo funcionário no sistema.
     * A senha é criptografada pela própria classe Funcionario.
     * Verifica se o email já está em uso antes de cadastrar.
     *
     * @param nome o nome do funcionário.
     * @param email o email do funcionário (deve ser único).
     * @param cargo o cargo do funcionário.
     * @param senha a senha do funcionário (será criptografada pela classe Funcionario).
     */
    public void cadastrarFuncionario(String nome, String email, String cargo, String senha) {
        if (buscarUsuarioPorEmail(email).isPresent()) {
            System.out.println("Erro ao cadastrar funcionário: O email '" + email + "' já está em uso.");
            return;
        }
        try {
            Funcionario func = new Funcionario(nome, email, cargo, senha);
            this.colaboradores.add(func);
            System.out.println("Funcionário '" + nome + "' cadastrado com sucesso!");
            JsonUtil.salvarLista("json/colaboradores.json", colaboradores);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao cadastrar funcionário: " + e.getMessage());
        }
    }

    /**
     * Permite que um administrador altere sua própria senha, verificando a senha antiga.
     *
     * @param emailAdmin O email do administrador cuja senha será alterada.
     * @param senhaAntiga A senha antiga do administrador para verificação.
     * @param novaSenha A nova senha desejada (será criptografada pela classe Administrador).
     * @return {@code true} se a senha foi alterada com sucesso, {@code false} caso contrário (ex: admin não encontrado, senha antiga incorreta).
     */
    public boolean mudarSenhaAdministrador(String emailAdmin, String senhaAntiga, String novaSenha) {
        Optional<Administrador> optAdmin = buscarAdministradorPorEmail(emailAdmin);
        if (optAdmin.isPresent()) {
            Administrador admin = optAdmin.get();
            return admin.alterarSenha(senhaAntiga, novaSenha);
        } else {
            System.out.println("Administrador com email '" + emailAdmin + "' não encontrado.");
            return false;
        }
    }

    /**
     * Permite que a senha de um administrador seja redefinida diretamente (sem a senha antiga).
     *
     * @param emailAdminAlvo O email do administrador cuja senha será redefinida.
     * @param novaSenha A nova senha a ser definida (será criptografada pela classe Administrador).
     * @return {@code true} se a senha foi redefinida com sucesso, {@code false} se o administrador não for encontrado.
     */
    public boolean redefinirSenhaAdministrador(String emailAdminAlvo, String novaSenha) {
        Optional<Administrador> optAdmin = buscarAdministradorPorEmail(emailAdminAlvo);
        if (optAdmin.isPresent()) {
            Administrador admin = optAdmin.get();
            admin.definirNovaSenha(novaSenha); // Método em Administrador que não pede senha antiga
            return true;
        } else {
            System.out.println("Administrador com email '" + emailAdminAlvo + "' não encontrado para redefinição de senha.");
            return false;
        }
    }

    /**
     * Busca um usuário (seja {@link Administrador} ou {@link Funcionario}) na lista de colaboradores
     * pelo email fornecido, ignorando diferenças de maiúsculas/minúsculas.
     *
     * @param email O email do usuário a ser buscado.
     * @return Um {@link Optional} contendo o {@link Usuario} se encontrado,
     * ou um {@link Optional#empty()} caso contrário ou se o email for nulo/vazio.
     */
    public Optional<Usuario> buscarUsuarioPorEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return Optional.empty();
        }
        for (Usuario usuario : this.colaboradores) {
            if (usuario.getEmail().equalsIgnoreCase(email)) {
                return Optional.of(usuario);
            }
        }
        return Optional.empty();
    }

    /**
     * Busca um {@link Administrador} específico na lista de colaboradores
     * pelo email fornecido, ignorando diferenças de maiúsculas/minúsculas.
     *
     * @param email O email do Administrador a ser buscado.
     * @return Um {@link Optional} contendo o {@link Administrador} se encontrado e for do tipo correto,
     * ou um {@link Optional#empty()} caso contrário ou se o email for nulo/vazio.
     */
    public Optional<Administrador> buscarAdministradorPorEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return Optional.empty();
        }
        for (Usuario usuario : this.colaboradores) {
            if (usuario instanceof Administrador && usuario.getEmail().equalsIgnoreCase(email)) {
                return Optional.of((Administrador) usuario);
            }
        }
        return Optional.empty();
    }

    /**
     * Busca um {@link Funcionario} específico na lista de colaboradores
     * pelo email fornecido, ignorando diferenças de maiúsculas/minúsculas.
     *
     * @param email O email do Funcionario a ser buscado.
     * @return Um {@link Optional} contendo o {@link Funcionario} se encontrado e for do tipo correto,
     * ou um {@link Optional#empty()} caso contrário ou se o email for nulo/vazio.
     */
    public Optional<Funcionario> buscarFuncionarioPorEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return Optional.empty();
        }
        for (Usuario usuario : this.colaboradores) {
            if (usuario instanceof Funcionario && usuario.getEmail().equalsIgnoreCase(email)) {
                return Optional.of((Funcionario) usuario);
            }
        }
        return Optional.empty();
    }

    /**
     * Busca um {@link Funcionario} pelo nome (ignorando maiúsculas/minúsculas).
     * @param nome O nome do funcionário a ser buscado.
     * @return Um {@link Optional} contendo o {@link Funcionario} se encontrado, ou {@link Optional#empty()} caso contrário.
     */
    public Optional<Funcionario> buscarFuncionarioPorNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            return Optional.empty();
        }
        for (Usuario usuario : this.colaboradores) {
            if (usuario instanceof Funcionario && usuario.getNome().equalsIgnoreCase(nome)) {
                return Optional.of((Funcionario) usuario);
            }
        }
        return Optional.empty();
    }

    /**
     * Retorna uma nova lista contendo todos os colaboradores do tipo {@link Funcionario}.
     *
     * @return Uma {@link List} de {@link Funcionario} cadastrados. A lista estará vazia se não houver nenhum.
     */
    public List<Funcionario> listarTodosOsFuncionarios() {
        List<Funcionario> listaFuncionarios = new ArrayList<>();
        for (Usuario usuario : this.colaboradores) {
            if (usuario instanceof Funcionario) {
                listaFuncionarios.add((Funcionario) usuario);
            }
        }
        return listaFuncionarios;
    }

    /**
     * Retorna uma nova lista contendo todos os colaboradores do tipo {@link Administrador}.
     *
     * @return Uma {@link List} de {@link Administrador} cadastrados. A lista estará vazia se não houver nenhum.
     */
    public List<Administrador> listarTodosOsAdministradores() {
        List<Administrador> listaAdmins = new ArrayList<>();
        for (Usuario usuario : this.colaboradores) {
            if (usuario instanceof Administrador) {
                listaAdmins.add((Administrador) usuario);
            }
        }
        return listaAdmins;
    }

    /**
     * Retorna uma nova lista contendo todos os colaboradores ({@link Usuario}) cadastrados no sistema.
     * A lista retornada é uma cópia da lista interna para proteger a lista original de modificações externas diretas.
     *
     * @return Uma {@link List} de {@link Usuario} (incluindo Administradores e Funcionários).
     */
    public List<Usuario> listarTodosOsColaboradores() {
        return new ArrayList<>(this.colaboradores);
    }

    /**
     * Retorna uma representação em string do estado do GerenciarColaboradores,
     * incluindo o número total de colaboradores e a contagem por tipo (Funcionário, Administrador).
     *
     * @return Uma string descrevendo o resumo dos colaboradores gerenciados.
     */
    @Override
    public String toString() {
        int numFuncionarios = 0;
        int numAdmins = 0;
        if (this.colaboradores != null) {
            for (Usuario u : this.colaboradores) {
                if (u instanceof Funcionario) {
                    numFuncionarios++;
                } else if (u instanceof Administrador) {
                    numAdmins++;
                }
            }
        }

        return "Resumo do GerenciarColaboradores:\n" +
               "  - Total de Colaboradores Registrados: " + (this.colaboradores != null ? this.colaboradores.size() : 0) + "\n" +
               "  - Número de Funcionários: " + numFuncionarios + "\n" +
               "  - Número de Administradores: " + numAdmins;
    }

    // Método para remover colaborador pelo email
    public boolean removerColaboradorPorEmail(String email) {
        Optional<Usuario> usuario = buscarUsuarioPorEmail(email);
        if (usuario.isPresent()) {
            colaboradores.remove(usuario.get());
            JsonUtil.salvarLista("json/colaboradores.json", colaboradores);
            System.out.println("Colaborador removido com sucesso.");
            return true;
        } else {
            System.out.println("Colaborador não encontrado.");
            return false;
        }
    }
}