package com.mycompany.oficina;

import com.mycompany.oficina.entidade.*;
import com.mycompany.oficina.seguranca.Autenticacao;
import com.mycompany.oficina.seguranca.GerenciarColaboradores;
import com.mycompany.oficina.controller.GerenciarCliente;
import com.mycompany.oficina.controller.GerenciarEstoque;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.util.HashMap;
import java.io.FileWriter;

import com.google.gson.GsonBuilder;

/**
 * Classe principal que gerencia o sistema da oficina.
 * Controla funcionários, agendamentos, peças, controle financeiro e todas as operações da oficina.
 *
 * @author Ana Clara e Pedro
 * @version 1.0
 */
public class Oficina {
    private final Autenticacao autenticacao;
    private final GerenciarColaboradores colaboradores;
    private final GerenciarCliente clientes;
    private final GerenciarEstoque estoque;
    
    private final List<OrdemServico> ordensDeServico;
    private final List<Agendamento> agendamentos;
    private final List<Despesa> despesasOficina;
    
    /**
     * Cria uma nova instância do sistema da oficina.
     * 
     * Inicializa todos os módulos e cria um admin padrão
     * se não existir nenhum.
     */
    public Oficina() {
        System.out.println("Criando instância da Oficina...");
        System.out.println("Inicializando sistema da Oficina...");
        
        System.out.println("Criando instância de Autenticacao...");
        this.autenticacao = Autenticacao.getInstance();
        
        System.out.println("Criando instância de GerenciarColaboradores...");
        this.colaboradores = GerenciarColaboradores.getInstance();
        
        System.out.println("Criando instância de GerenciarCliente...");
        this.clientes = new GerenciarCliente();
        
        System.out.println("Criando instância de GerenciarEstoque...");
        this.estoque = new GerenciarEstoque();
        
        // Inicializar elevadores
        Elevador.inicializarElevadores();
        System.out.println("Elevadores inicializados: 3 elevadores disponíveis");
        
        // Carregar dados existentes usando variáveis temporárias
        List<OrdemServico> ordensTemp = JsonUtil.lerLista("json/ordens_servico.json", OrdemServico.class);
        this.ordensDeServico = (ordensTemp != null) ? ordensTemp : new ArrayList<>();
        
        List<Agendamento> agendTemp = JsonUtil.lerLista("json/agendamentos.json", Agendamento.class);
        this.agendamentos = (agendTemp != null) ? agendTemp : new ArrayList<>();
        
        List<Despesa> despesasTemp = JsonUtil.lerLista("json/despesas.json", Despesa.class);
        this.despesasOficina = (despesasTemp != null) ? despesasTemp : new ArrayList<>();
        
        criarAdministradorInicial();
        System.out.println("Sistema da Oficina inicializado!");
    }
    
    /**
     * Cria um administrador inicial se não existir nenhum no sistema.
     * 
     * Este método é chamado automaticamente no construtor para garantir
     * que sempre exista pelo menos um administrador com acesso total.
     */
    private void criarAdministradorInicial() {
        // Verifica se já existe algum administrador
        if (colaboradores.listarTodosOsAdministradores().isEmpty()) {
            // Cria um administrador inicial com acesso total
            colaboradores.cadastrarAdministrador(
                "Administrador",  // nome
                "admin@oficina.com",  // email
                "admin123",  // senha
                1  // nível de acesso (1 = acesso total)
            );
            System.out.println("\nAdministrador inicial criado!");
            System.out.println("Email: admin@oficina.com");
            System.out.println("Senha: admin123");
        } else {
            System.out.println("\nAdministrador já existe no sistema.");
            System.out.println("Email: admin@oficina.com");
            System.out.println("Senha: admin123");
        }
    }
    
    /**
     * Inicia o sistema da oficina.
     * 
     * Este método controla o fluxo principal do sistema, exibindo os menus
     * apropriados baseado no status de autenticação do usuário.
     */
    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        boolean executando = true;
        
        while (executando) {
            if (!autenticacao.isFuncionarioLogado() && !autenticacao.isAdministradorLogado()) {
                exibirMenuLogin(scanner);
            } else {
                exibirMenuPrincipal(scanner);
            }
        }
        
        scanner.close();
    }
    
    private void exibirMenuLogin(Scanner scanner) {
        System.out.println("\n==== Sistema Oficina Milho Verde ====");
        System.out.println("1. Login");
        System.out.println("2. Sair");
        System.out.print("Escolha uma opção: ");
        
        String opcao = scanner.nextLine();
        switch (opcao) {
            case "1":
                realizarLogin(scanner);
                break;
            case "2":
                System.out.println("Saindo do sistema. Até logo!");
                System.exit(0);
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }
    
    private void realizarLogin(Scanner scanner) {
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        
        // Debug: verificar se o administrador existe
        System.out.println("Tentando login com email: " + email);
        System.out.println("Total de administradores: " + colaboradores.listarTodosOsAdministradores().size());
        for (Administrador admin : colaboradores.listarTodosOsAdministradores()) {
            System.out.println("Admin encontrado: " + admin.getEmail());
        }
        
        if (autenticacao.loginFuncionario(email, senha) || autenticacao.loginAdministrador(email, senha)) {
            System.out.println("Login realizado com sucesso!");
        } else {
            System.out.println("Falha no login. Verifique suas credenciais.");
        }
    }
    
    private void exibirMenuPrincipal(Scanner scanner) {
        boolean sair = false;
        while (!sair) {
            System.out.println("\n==== Menu Principal ====");
            System.out.println("1. Gerenciar Clientes");
            System.out.println("2. Gerenciar Agendamentos");
            System.out.println("3. Gerenciar Ordens de Serviço");
            System.out.println("4. Gerenciar Estoque");
            if (autenticacao.temAcessoTotal()) {
                System.out.println("5. Gerenciar Colaboradores");
            }
            System.out.println("6. Registrar Ponto");
            System.out.println("7. Relatórios");
            System.out.println("8. Logout");
            if (autenticacao.temAcessoTotal()) {
                System.out.println("9. Financeiro");
            }
            System.out.println("10. Registrar Venda de Peças");
            System.out.println("11. Gerenciar Elevadores");
            System.out.print("Escolha uma opção: ");
            
            String opcao = scanner.nextLine();
            switch (opcao) {
                case "1":
                    menuClientes(scanner);
                    break;
                case "2":
                    menuAgendamentos(scanner);
                    break;
                case "3":
                    menuOrdensServico(scanner);
                    break;
                case "4":
                    menuEstoque(scanner);
                    break;
                case "5":
                    if (autenticacao.temAcessoTotal()) {
                        menuColaboradores(scanner);
                    } else {
                        System.out.println("Acesso restrito. Apenas administradores podem gerenciar colaboradores.");
                    }
                    break;
                case "6":
                    menuPonto(scanner);
                    break;
                case "7":
                    menuRelatorios(scanner);
                    break;
                case "8":
                    autenticacao.fazerLogout();
                    System.out.println("Logout realizado com sucesso!");
                    sair = true;
                    break;
                case "9":
                    if (autenticacao.temAcessoTotal()) {
                        menuFinanceiro(scanner);
                    } else {
                        System.out.println("Acesso restrito ao setor financeiro. Apenas administradores podem acessar.");
                    }
                    break;
                case "10":
                    registrarVendaPeca(scanner);
                    break;
                case "11":
                    menuElevadores(scanner);
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
    
    private void menuClientes(Scanner scanner) {
        boolean sair = false;
        while (!sair) {
            System.out.println("\n==== Menu Clientes ====");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Listar Clientes");
            System.out.println("3. Buscar Cliente");
            System.out.println("4. Alterar Dados do Cliente");
            System.out.println("5. Remover Cliente");
            System.out.println("6. Adicionar Veículo ao Cliente");
            System.out.println("7. Emitir Extrato do Cliente");
            System.out.println("8. Voltar");
            System.out.print("Escolha uma opção: ");

            String opcao = scanner.nextLine();
            switch (opcao) {
                case "1":
                    clientes.cadastrarCliente();
                    break;
                case "2":
                    clientes.listarClientes();
                    break;
                case "3":
                    clientes.buscarCliente();
                    break;
                case "4":
                    clientes.alterarCliente();
                    break;
                case "5":
                    clientes.removerCliente();
                    break;
                case "6":
                    adicionarVeiculoCliente(scanner);
                    break;
                case "7":
                    emitirContaCliente(scanner);
                    break;
                case "8":
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
    
    private void menuAgendamentos(Scanner scanner) {
        boolean sair = false;
        while (!sair) {
            System.out.println("\n==== Menu Agendamentos ====");
            System.out.println("1. Criar Agendamento");
            System.out.println("2. Listar Agendamentos");
            System.out.println("3. Buscar Agendamento");
            System.out.println("4. Cancelar Agendamento");
            System.out.println("5. Voltar");
            System.out.print("Escolha uma opção: ");

            String opcao = scanner.nextLine();
            switch (opcao) {
                case "1":
                    criarAgendamento(scanner);
                    break;
                case "2":
                    listarAgendamentos();
                    break;
                case "3":
                    buscarAgendamento(scanner);
                    break;
                case "4":
                    cancelarAgendamento(scanner);
                    break;
                case "5":
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
    
    private void menuOrdensServico(Scanner scanner) {
        boolean sair = false;
        while (!sair) {
            System.out.println("\n==== Menu Ordens de Serviço ====");
            System.out.println("1. Criar Ordem de Serviço");
            System.out.println("2. Listar Ordens de Serviço");
            System.out.println("3. Buscar Ordem de Serviço");
            System.out.println("4. Atualizar Status");
            System.out.println("5. Voltar");
            System.out.print("Escolha uma opção: ");

            String opcao = scanner.nextLine();
            switch (opcao) {
                case "1":
                    criarOrdemServico(scanner);
                    break;
                case "2":
                    listarOrdensServico();
                    break;
                case "3":
                    buscarOrdemServico(scanner);
                    break;
                case "4":
                    atualizarStatusOrdemServico(scanner);
                    break;
                case "5":
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
    
    private void menuEstoque(Scanner scanner) {
        boolean sair = false;
        while (!sair) {
            System.out.println("\n==== Menu Estoque ====");
            System.out.println("1. Cadastrar Produto");
            System.out.println("2. Listar Produtos");
            System.out.println("3. Buscar Produto");
            System.out.println("4. Atualizar Estoque");
            System.out.println("5. Voltar");
            System.out.print("Escolha uma opção: ");

            String opcao = scanner.nextLine();
            switch (opcao) {
                case "1":
                    estoque.cadastrarProduto();
                    break;
                case "2":
                    estoque.listarProdutos();
                    break;
                case "3":
                    estoque.buscarProduto();
                    break;
                case "4":
                    estoque.atualizarEstoque();
                    break;
                case "5":
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
    
    private void menuColaboradores(Scanner scanner) {
        // Verifica se o usuário tem permissão de administrador
        if (!autenticacao.temAcessoTotal()) {
            System.out.println("Acesso restrito. Apenas administradores podem gerenciar colaboradores.");
            return;
        }

        boolean sair = false;
        while (!sair) {
            System.out.println("\n==== Menu Colaboradores ====");
            System.out.println("1. Cadastrar Funcionário");
            System.out.println("2. Cadastrar Administrador");
            System.out.println("3. Alterar Dados do Colaborador");
            System.out.println("4. Remover Colaborador");
            System.out.println("5. Voltar");
            System.out.print("Escolha uma opção: ");

            String opcao = scanner.nextLine();
            switch (opcao) {
                case "1":
                    cadastrarFuncionario(scanner);
                    break;
                case "2":
                    cadastrarAdministrador(scanner);
                    break;
                case "3":
                    alterarColaborador(scanner);
                    break;
                case "4":
                    removerColaborador(scanner);
                    break;
                case "5":
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
    
    private void menuRelatorios(Scanner scanner) {
        boolean sair = false;
        while (!sair) {
            System.out.println("\n==== Menu Relatórios ====");
            System.out.println("1. Relatório de Serviços");
            System.out.println("2. Relatório de Vendas");
            System.out.println("3. Relatório de Estoque");
            if (autenticacao.temAcessoTotal()) {
                System.out.println("4. Relatório de Colaboradores");
            }
            System.out.println("5. Voltar");
            System.out.print("Escolha uma opção: ");

            String opcao = scanner.nextLine();
            switch (opcao) {
                case "1":
                    gerarRelatorioServicos();
                    break;
                case "2":
                    gerarRelatorioVendas();
                    break;
                case "3":
                    gerarRelatorioEstoque();
                    break;
                case "4":
                    if (autenticacao.temAcessoTotal()) {
                        gerarRelatorioColaboradores();
                    } else {
                        System.out.println("Acesso restrito. Apenas administradores podem visualizar o relatório de colaboradores.");
                    }
                    break;
                case "5":
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
    
    // Métodos de implementação dos menus
    private void criarAgendamento(Scanner scanner) {
        System.out.println("\n--- Criar Agendamento ---");
        System.out.print("CPF do cliente: ");
        String cpf = scanner.nextLine();
        Cliente cliente = clientes.buscarClientePorCpf(cpf);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }
        System.out.print("Modelo do veículo: ");
        String modelo = scanner.nextLine();
        Veiculo veiculo = null;
        for (Veiculo v : cliente.getListaVeiculos()) {
            if (v.getModelo().equalsIgnoreCase(modelo)) {
                veiculo = v;
                break;
            }
        }
        if (veiculo == null) {
            System.out.println("Veículo não encontrado para este cliente.");
            return;
        }
        System.out.print("Data e hora (dd/MM/yyyy HH:mm, ex: 02/07/2025 14:00): ");
        String dataHora = scanner.nextLine();
        java.time.LocalDateTime dataHoraAgendamento;
        try {
            java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            dataHoraAgendamento = java.time.LocalDateTime.parse(dataHora, formatter);
        } catch (Exception e) {
            System.out.println("Formato de data/hora inválido! Use o formato dd/MM/yyyy HH:mm, por exemplo: 02/07/2025 14:00");
            return;
        }
        System.out.print("Funcionário responsável (email): ");
        String emailFunc = scanner.nextLine();
        Optional<Usuario> usuario = colaboradores.buscarUsuarioPorEmail(emailFunc);
        if (usuario.isEmpty() || !(usuario.get() instanceof Funcionario)) {
            System.out.println("Funcionário não encontrado.");
            return;
        }
        Funcionario funcionario = (Funcionario) usuario.get();
        System.out.print("Valor do serviço: ");
        double valor = Double.parseDouble(scanner.nextLine());
        int novoId = agendamentos.isEmpty() ? 1 : agendamentos.stream().mapToInt(Agendamento::getId).max().orElse(0) + 1;
        Agendamento agendamento = new Agendamento(novoId, dataHoraAgendamento, valor, cliente, veiculo, funcionario);
        agendamentos.add(agendamento);
        JsonUtil.salvarLista("json/agendamentos.json", agendamentos);
        System.out.println("Agendamento criado com sucesso!");
    }
    
    private void listarAgendamentos() {
        System.out.println("\n--- Lista de Agendamentos ---");
        if (agendamentos.isEmpty()) {
            System.out.println("Nenhum agendamento cadastrado.");
            return;
        }
        for (Agendamento a : agendamentos) {
            System.out.println(a);
            System.out.println("------------------------");
        }
    }
    
    private void cancelarAgendamento(Scanner scanner) {
        System.out.print("Digite o CPF do cliente do agendamento: ");
        String cpf = scanner.nextLine();
        System.out.print("Digite a data e hora do agendamento (dd/MM/yyyy HH:mm, ex: 02/07/2025 14:00): ");
        String dataHora = scanner.nextLine();
        java.time.LocalDateTime dataHoraAgendamento;
        try {
            java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            dataHoraAgendamento = java.time.LocalDateTime.parse(dataHora, formatter);
        } catch (Exception e) {
            System.out.println("Formato de data/hora inválido! Use o formato dd/MM/yyyy HH:mm, por exemplo: 02/07/2025 14:00");
            return;
        }
        // DEBUG: Mostrar todos os agendamentos carregados
        System.out.println("DEBUG: Agendamentos carregados:");
        for (Agendamento a : agendamentos) {
            System.out.println("ID: " + a.getId() + " | CPF: '" + a.getCliente().getCpf() + "' | DataHora: " + a.getDataHora());
        }
        Agendamento agendamentoRemover = null;
        for (Agendamento a : agendamentos) {
            if (a.getCliente().getCpf().equals(cpf) &&
                a.getDataHora().truncatedTo(java.time.temporal.ChronoUnit.MINUTES)
                    .equals(dataHoraAgendamento.truncatedTo(java.time.temporal.ChronoUnit.MINUTES))) {
                agendamentoRemover = a;
                break;
            }
        }
        if (agendamentoRemover != null) {
            agendamentos.remove(agendamentoRemover);
            JsonUtil.salvarLista("json/agendamentos.json", agendamentos);
            System.out.println("Agendamento cancelado com sucesso!");
        } else {
            System.out.println("Agendamento não encontrado.");
        }
    }
    
    private void criarOrdemServico(Scanner scanner) {
        System.out.println("\n--- Criar Ordem de Serviço ---");
        System.out.print("CPF do cliente: ");
        String cpf = scanner.nextLine();
        System.out.println("DEBUG: CPF digitado: '" + cpf + "'");
        
        Cliente cliente = clientes.buscarClientePorCpf(cpf);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            System.out.println("DEBUG: Verifique se o CPF está correto e se o cliente foi cadastrado.");
            return;
        }
        
        System.out.println("DEBUG: Cliente encontrado: " + cliente.getNome() + " (CPF: " + cliente.getCpf() + ")");
        
        // Mostrar veículos disponíveis do cliente
        System.out.println("\nVeículos do cliente " + cliente.getNome() + ":");
        if (cliente.getListaVeiculos().isEmpty()) {
            System.out.println("Este cliente não possui veículos cadastrados.");
            System.out.println("Para adicionar um veículo, vá em: Menu Principal > Gerenciar Clientes > Adicionar veículo ao cliente");
            return;
        }
        
        for (int i = 0; i < cliente.getListaVeiculos().size(); i++) {
            Veiculo v = cliente.getListaVeiculos().get(i);
            System.out.println((i + 1) + ". " + v.getModelo() + " (" + v.getPlaca() + ") - " + v.getAnoFabricacao());
        }
        
        System.out.print("Digite o número do veículo ou o modelo: ");
        String modelo = scanner.nextLine();
        Veiculo veiculo = null;
        
        // Tentar encontrar por número
        try {
            int numero = Integer.parseInt(modelo);
            if (numero > 0 && numero <= cliente.getListaVeiculos().size()) {
                veiculo = cliente.getListaVeiculos().get(numero - 1);
            }
        } catch (NumberFormatException e) {
            // Se não for número, procurar por modelo
            for (Veiculo v : cliente.getListaVeiculos()) {
                if (v.getModelo().equalsIgnoreCase(modelo)) {
                    veiculo = v;
                    break;
                }
            }
        }
        
        if (veiculo == null) {
            System.out.println("Veículo não encontrado para este cliente.");
            return;
        }
        
        System.out.print("Funcionário responsável (nome): ");
        String nomeFunc = scanner.nextLine();
        Optional<Funcionario> funcionarioOpt = colaboradores.buscarFuncionarioPorNome(nomeFunc);
        if (funcionarioOpt.isEmpty()) {
            System.out.println("Funcionário não encontrado.");
            return;
        }
        Funcionario funcionario = funcionarioOpt.get();
        System.out.print("Valor da ordem de serviço: ");
        double valor = Double.parseDouble(scanner.nextLine());
        System.out.print("Descreva o problema do veículo: ");
        String descricao = scanner.nextLine();
        OrdemServico os = new OrdemServico(cliente, veiculo, funcionario);
        os.setValorTotal(valor);
        os.setDescricao(descricao);
        // Adicionar peças utilizadas (opcional)
        System.out.print("Deseja adicionar peças utilizadas nesta ordem de serviço? (s/n): ");
        String respPecas = scanner.nextLine();
        if (respPecas.equalsIgnoreCase("s")) {
            List<Peca> pecasEstoque = JsonUtil.lerLista("json/pecas.json", Peca.class);
            if (pecasEstoque == null || pecasEstoque.isEmpty()) {
                System.out.println("Nenhuma peça disponível no estoque.");
            } else {
                boolean adicionarMais = true;
                while (adicionarMais) {
                    System.out.println("Peças disponíveis:");
                    for (Peca p : pecasEstoque) {
                        System.out.println("ID: " + p.getId() + " | " + p.getNome() + " | Quantidade: " + p.getQuantidade());
                    }
                    System.out.print("Digite o ID da peça: ");
                    int idPeca = Integer.parseInt(scanner.nextLine());
                    Peca pecaSelecionada = null;
                    for (Peca p : pecasEstoque) {
                        if (p.getId() == idPeca) {
                            pecaSelecionada = p;
                            break;
                        }
                    }
                    if (pecaSelecionada == null) {
                        System.out.println("Peça inválida.");
                        continue;
                    }
                    System.out.print("Quantidade a adicionar: ");
                    int qtd = Integer.parseInt(scanner.nextLine());
                    // Adicionar à OS (estoque será atualizado pelo método)
                    try {
                        os.adicionarPeca(pecaSelecionada, qtd);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        continue;
                    }
                    System.out.print("Adicionar mais peças? (s/n): ");
                    String resp = scanner.nextLine();
                    adicionarMais = resp.equalsIgnoreCase("s");
                }
            }
        }
        ordensDeServico.add(os);
        JsonUtil.salvarLista("json/ordens_servico.json", ordensDeServico);
        System.out.println("Ordem de serviço criada com sucesso! ID: " + os.getIdOrdemServico());
    }
    
    private void listarOrdensServico() {
        System.out.println("\n--- Lista de Ordens de Serviço ---");
        if (ordensDeServico.isEmpty()) {
            System.out.println("Nenhuma ordem de serviço cadastrada.");
            return;
        }
        for (OrdemServico os : ordensDeServico) {
            System.out.println(os);
            System.out.println("------------------------");
        }
    }
    
    private void atualizarStatusOrdemServico(Scanner scanner) {
        System.out.print("Digite o CPF do cliente: ");
        String cpf = scanner.nextLine();
        List<OrdemServico> ordensDoCliente = new ArrayList<>();
        for (OrdemServico os : ordensDeServico) {
            if (os.getCliente().getCpf().equals(cpf)) {
                ordensDoCliente.add(os);
            }
        }
        if (ordensDoCliente.isEmpty()) {
            System.out.println("Nenhuma ordem de serviço encontrada para este cliente.");
            return;
        }
        System.out.println("Ordens de serviço encontradas:");
        for (OrdemServico os : ordensDoCliente) {
            System.out.println("ID: " + os.getIdOrdemServico() + " | Veículo: " + os.getVeiculo().getModelo() + " | Status: " + os.getStatus() + " | Data: " + os.getDataAbertura());
        }
        System.out.print("Digite o ID da ordem de serviço que deseja atualizar: ");
        int id = Integer.parseInt(scanner.nextLine());
        for (OrdemServico os : ordensDoCliente) {
            if (os.getIdOrdemServico() == id) {
                System.out.println("Status atual: " + os.getStatus());
                System.out.print("Novo status (EM_ANDAMENTO, FINALIZADA, CANCELADA, AGUARDANDO_PECAS, AGUARDANDO_APROVACAO, RECEBIDO, ENTREGUE): ");
                String novoStatus = scanner.nextLine();
                try {
                    OrdemServico.StatusOrdemServico statusAnterior = os.getStatus();
                    os.setStatus(OrdemServico.StatusOrdemServico.valueOf(novoStatus));
                    
                    // Se a OS foi finalizada ou entregue, registrar no financeiro
                    if ((novoStatus.equals("FINALIZADA") || novoStatus.equals("ENTREGUE")) && 
                        (statusAnterior != OrdemServico.StatusOrdemServico.FINALIZADA && 
                         statusAnterior != OrdemServico.StatusOrdemServico.ENTREGUE)) {
                        registrarOrdemServicoNoFinanceiro(os);
                        System.out.println("Status atualizado e ordem de serviço registrada no financeiro!");
                    } else {
                        System.out.println("Status atualizado com sucesso!");
                    }
                    
                    JsonUtil.salvarLista("json/ordens_servico.json", ordensDeServico);
                } catch (Exception e) {
                    System.out.println("Status inválido.");
                }
                return;
            }
        }
        System.out.println("Ordem de serviço não encontrada para o ID informado.");
    }
    
    private void buscarAgendamento(Scanner scanner) {
        System.out.print("Digite o CPF do cliente: ");
        String cpf = scanner.nextLine();
        boolean encontrado = false;
        for (Agendamento a : agendamentos) {
            if (a.getCliente().getCpf().equals(cpf)) {
                System.out.println(a);
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("Nenhum agendamento encontrado para este cliente.");
        }
    }
    
    private void cadastrarFuncionario(Scanner scanner) {
        System.out.println("\n--- Cadastro de Funcionário ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Cargo: ");
        String cargo = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        colaboradores.cadastrarFuncionario(nome, email, cargo, senha);
    }
    
    private void cadastrarAdministrador(Scanner scanner) {
        System.out.println("\n--- Cadastro de Administrador ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        System.out.print("Nível de Acesso (1-3): ");
        int nivelAcesso = Integer.parseInt(scanner.nextLine());

        colaboradores.cadastrarAdministrador(nome, email, senha, nivelAcesso);
    }
    
    private void alterarColaborador(Scanner scanner) {
        System.out.print("Digite o email do colaborador: ");
        String email = scanner.nextLine();
        
        Optional<Usuario> usuario = colaboradores.buscarUsuarioPorEmail(email);
        if (usuario.isPresent()) {
            System.out.println("Digite os novos dados:");
            System.out.print("Novo nome: ");
            String novoNome = scanner.nextLine();
            System.out.print("Novo email: ");
            String novoEmail = scanner.nextLine();
            
            usuario.get().atualizarDados(novoNome, novoEmail);
            
            // Salvar as alterações no JSON
            List<Usuario> todosColaboradores = colaboradores.listarTodosOsColaboradores();
            JsonUtil.salvarLista("json/colaboradores.json", todosColaboradores);
            
            System.out.println("Dados atualizados com sucesso!");
        } else {
            System.out.println("Colaborador não encontrado.");
        }
    }
    
    private void removerColaborador(Scanner scanner) {
        System.out.print("Digite o email do colaborador a ser removido: ");
        String email = scanner.nextLine();
        colaboradores.removerColaboradorPorEmail(email);
    }
    
    private void gerarRelatorioServicos() {
        System.out.println("\n--- Relatório de Ordens de Serviço ---");
        List<OrdemServico> ordens = JsonUtil.lerLista("json/ordens_servico.json", OrdemServico.class);
        if (ordens == null || ordens.isEmpty()) {
            System.out.println("Nenhuma ordem de serviço encontrada no arquivo.");
            return;
        }
        for (OrdemServico os : ordens) {
            try {
                System.out.println(os);
                System.out.println("------------------------");
            } catch (Exception e) {
                System.out.println("Erro ao exibir ordem de serviço: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
    
    private void gerarRelatorioVendas() {
        System.out.println("\n--- Relatório de Vendas ---");
        List<Venda> vendas = JsonUtil.lerLista("json/vendas.json", Venda.class);
        if (vendas == null || vendas.isEmpty()) {
            System.out.println("Nenhuma venda encontrada no arquivo.");
            return;
        }
        
        System.out.println("Total de vendas: " + vendas.size());
        System.out.println("========================================");
        
        for (int i = 0; i < vendas.size(); i++) {
            Venda v = vendas.get(i);
            System.out.println("\nVenda #" + (i + 1) + ":");
            System.out.println("Data/Hora: " + v.getDataHoraVenda());
            System.out.println("Cliente: " + (v.getCliente() != null ? v.getCliente().getNome() : "Venda Balcão"));
            System.out.println("Total: R$" + String.format("%.2f", v.getTotal()));
            
            if (!v.getPecasVendidas().isEmpty()) {
                System.out.println("Peças vendidas:");
                for (Peca p : v.getPecasVendidas()) {
                    System.out.println("  - " + p.getQuantidade() + "x " + p.getNome() + " (R$" + String.format("%.2f", p.getPreco()) + " cada)");
                }
            }
            System.out.println("----------------------------------------");
        }
    }
    
    private void gerarRelatorioEstoque() {
        System.out.println("\n--- Relatório de Estoque ---");
        List<Peca> pecas = JsonUtil.lerLista("json/pecas.json", Peca.class);
        if (pecas == null || pecas.isEmpty()) {
            System.out.println("Nenhuma peça encontrada no arquivo.");
            return;
        }
        
        System.out.println("Total de peças no estoque: " + pecas.size());
        System.out.println("========================================");
        
        double valorTotalEstoque = 0.0;
        for (Peca p : pecas) {
            System.out.printf("ID: %d | %s | Qtd: %d | Preço: R$%.2f | Valor Total: R$%.2f%n", 
                p.getId(), p.getNome(), p.getQuantidade(), p.getPreco(), 
                (p.getPreco() * p.getQuantidade()));
            valorTotalEstoque += (p.getPreco() * p.getQuantidade());
        }
        
        System.out.println("========================================");
        System.out.printf("Valor total do estoque: R$%.2f%n", valorTotalEstoque);
    }
    
    private void gerarRelatorioColaboradores() {
        if (!autenticacao.temAcessoTotal()) {
            System.out.println("Acesso restrito. Apenas administradores podem visualizar o relatório de colaboradores.");
            return;
        }
        System.out.println("\n--- Relatório de Colaboradores ---");
        List<Usuario> colaboradores = JsonUtil.lerLista("json/colaboradores.json", Usuario.class);
        if (colaboradores == null || colaboradores.isEmpty()) {
            System.out.println("Nenhum colaborador encontrado no arquivo.");
            return;
        }
        for (Usuario u : colaboradores) {
            System.out.println(u);
        }
    }
    
    private void listarDespesas() {
        System.out.println("\n--- Lista de Despesas da Oficina ---");
        List<Despesa> despesas = JsonUtil.lerLista("json/despesas.json", Despesa.class);
        if (despesas == null || despesas.isEmpty()) {
            System.out.println("Nenhuma despesa registrada no arquivo.");
            return;
        }
        for (Despesa d : despesas) {
            System.out.println(d);
        }
    }
    
    private void gerarBalancoMensal(Scanner scanner) {
        System.out.println("\n--- Balanço Mensal ---");
        System.out.print("Digite o mês (1-12): ");
        int mes = Integer.parseInt(scanner.nextLine());
        System.out.print("Digite o ano (ex: 2025): ");
        int ano = Integer.parseInt(scanner.nextLine());
        List<Despesa> despesas = JsonUtil.lerLista("json/despesas.json", Despesa.class);
        List<Venda> vendas = JsonUtil.lerLista("json/vendas.json", Venda.class);
        double totalDespesas = 0.0;
        if (despesas != null) {
            for (Despesa d : despesas) {
                if (d.getData().getMonthValue() == mes && d.getData().getYear() == ano) {
                    totalDespesas += d.getValor();
                }
            }
        }
        double totalReceitas = 0.0;
        
        // Receitas de vendas de peças
        if (vendas != null) {
            for (Venda v : vendas) {
                if (v.getDataHoraVenda().getMonthValue() == mes && v.getDataHoraVenda().getYear() == ano) {
                    totalReceitas += v.getTotal();
                }
            }
        }
        
        // Receitas de ordens de serviço finalizadas/entregues
        List<OrdemServico> ordensServico = JsonUtil.lerLista("json/ordens_servico.json", OrdemServico.class);
        if (ordensServico != null) {
            for (OrdemServico os : ordensServico) {
                if (os.getDataAbertura().getMonthValue() == mes && 
                    os.getDataAbertura().getYear() == ano &&
                    (os.getStatus() == OrdemServico.StatusOrdemServico.FINALIZADA || 
                     os.getStatus() == OrdemServico.StatusOrdemServico.ENTREGUE)) {
                    totalReceitas += os.getValorTotal();
                }
            }
        }
        double saldo = totalReceitas - totalDespesas;
        
        // Calcular receitas por categoria
        double receitasVendas = 0.0;
        double receitasServicos = 0.0;
        
        if (vendas != null) {
            for (Venda v : vendas) {
                if (v.getDataHoraVenda().getMonthValue() == mes && v.getDataHoraVenda().getYear() == ano) {
                    receitasVendas += v.getTotal();
                }
            }
        }
        
        if (ordensServico != null) {
            for (OrdemServico os : ordensServico) {
                if (os.getDataAbertura().getMonthValue() == mes && 
                    os.getDataAbertura().getYear() == ano &&
                    (os.getStatus() == OrdemServico.StatusOrdemServico.FINALIZADA || 
                     os.getStatus() == OrdemServico.StatusOrdemServico.ENTREGUE)) {
                    receitasServicos += os.getValorTotal();
                }
            }
        }
        
        System.out.println("\n--- DETALHAMENTO DAS RECEITAS ---");
        System.out.printf("Receitas de vendas (peças): R$ %.2f\n", receitasVendas);
        System.out.printf("Receitas de serviços: R$ %.2f\n", receitasServicos);
        System.out.printf("Total de receitas: R$ %.2f\n", totalReceitas);
        System.out.println("--------------------------------");
        System.out.printf("Despesas do mês: R$ %.2f\n", totalDespesas);
        System.out.printf("Saldo do mês: R$ %.2f\n", saldo);
    }
    
    private void menuPonto(Scanner scanner) {
        System.out.println("\n==== Registro de Ponto ====");
        System.out.println("1. Registrar Entrada");
        System.out.println("2. Registrar Saída");
        System.out.println("3. Voltar");
        System.out.print("Escolha uma opção: ");
        
        String opcao = scanner.nextLine();
        switch (opcao) {
            case "1":
                registrarEntrada(scanner);
                break;
            case "2":
                registrarSaida(scanner);
                break;
            case "3":
                return;
            default:
                System.out.println("Opção inválida!");
        }
    }
    
    private void registrarEntrada(Scanner scanner) {
        System.out.print("Email do funcionário: ");
        String email = scanner.nextLine();
        Optional<Funcionario> funcionarioOpt = colaboradores.buscarFuncionarioPorEmail(email);
        if (funcionarioOpt.isEmpty()) {
            System.out.println("Funcionário não encontrado.");
            return;
        }
        Funcionario funcionario = funcionarioOpt.get();
        java.time.LocalDateTime entrada = java.time.LocalDateTime.now();
        PontoFuncionario ponto = new PontoFuncionario(funcionario, entrada, null);
        salvarPontoFuncionarioCorreto(ponto);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        System.out.println("Entrada registrada para " + funcionario.getNome() + " às " + entrada.format(formatter));
    }
    
    private void registrarSaida(Scanner scanner) {
        System.out.print("Email do funcionário: ");
        String email = scanner.nextLine();
        Optional<Funcionario> funcionarioOpt = colaboradores.buscarFuncionarioPorEmail(email);
        if (funcionarioOpt.isEmpty()) {
            System.out.println("Funcionário não encontrado.");
            return;
        }
        Funcionario funcionario = funcionarioOpt.get();
        List<PontoFuncionario> pontos = JsonUtil.lerLista("json/pontos_funcionario.json", PontoFuncionario.class);
        if (pontos == null) pontos = new ArrayList<>();
        
        boolean encontrou = false;
        for (PontoFuncionario ponto : pontos) {
            // Comparar pelo nome do funcionário, já que o funcionário do JSON é temporário
            if (ponto.getFuncionario().getNome().equalsIgnoreCase(funcionario.getNome()) && ponto.getSaida() == null) {
                ponto.setSaida(java.time.LocalDateTime.now());
                // Salvar todos os pontos no formato correto
                salvarTodosPontosCorretos(pontos);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                System.out.println("Saída registrada para " + funcionario.getNome() + " às " + ponto.getSaida().format(formatter));
                encontrou = true;
                break;
            }
        }
        
        if (!encontrou) {
            System.out.println("Nenhuma entrada aberta encontrada para este funcionário.");
        }
    }
    
    private void buscarOrdemServico(Scanner scanner) {
        System.out.print("Digite o CPF do cliente: ");
        String cpf = scanner.nextLine();
        List<OrdemServico> ordensDoCliente = new ArrayList<>();
        for (OrdemServico os : ordensDeServico) {
            if (os.getCliente().getCpf().equals(cpf)) {
                ordensDoCliente.add(os);
            }
        }
        if (ordensDoCliente.isEmpty()) {
            System.out.println("Nenhuma ordem de serviço encontrada para este cliente.");
            return;
        }
        System.out.println("Ordens de serviço encontradas:");
        for (OrdemServico os : ordensDoCliente) {
            System.out.println("ID: " + os.getIdOrdemServico() + " | Veículo: " + os.getVeiculo().getModelo() + " | Status: " + os.getStatus() + " | Data: " + os.getDataAbertura());
        }
        System.out.print("Digite o ID da ordem de serviço que deseja visualizar: ");
        int id = Integer.parseInt(scanner.nextLine());
        for (OrdemServico os : ordensDoCliente) {
            if (os.getIdOrdemServico() == id) {
                System.out.println(os);
                return;
            }
        }
        System.out.println("Ordem de serviço não encontrada para o ID informado.");
    }
    
    private void menuFinanceiro(Scanner scanner) {
        if (!autenticacao.temAcessoTotal()) {
            System.out.println("Acesso restrito ao setor financeiro. Apenas administradores podem acessar.");
            return;
        }
        boolean sair = false;
        while (!sair) {
            System.out.println("\n==== Menu Financeiro ====");
            System.out.println("1. Lançar despesa");
            System.out.println("2. Listar despesas");
            System.out.println("3. Emitir conta de cliente");
            System.out.println("4. Gerar balanço mensal");
            System.out.println("5. Visualizar receitas de serviços");
            System.out.println("6. Voltar");
            System.out.print("Escolha uma opção: ");
            String opcao = scanner.nextLine();
            switch (opcao) {
                case "1":
                    lancarDespesa(scanner);
                    break;
                case "2":
                    listarDespesas();
                    break;
                case "3":
                    emitirContaCliente(scanner);
                    break;
                case "4":
                    gerarBalancoMensal(scanner);
                    break;
                case "5":
                    visualizarReceitasServicos(scanner);
                    break;
                case "6":
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
    
    // --- Métodos do menu financeiro ---
    private void lancarDespesa(Scanner scanner) {
        System.out.println("\n--- Lançar Despesa ---");
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        System.out.print("Valor: ");
        String valorStr = scanner.nextLine().replace(",", ".");
        double valor = Double.parseDouble(valorStr);
        System.out.print("Data (AAAA-MM-DD): ");
        LocalDate data = LocalDate.parse(scanner.nextLine());
        System.out.print("Categoria: ");
        String categoria = scanner.nextLine();
        String responsavel = autenticacao.getUsuarioLogadoAdministrador() != null ?
            autenticacao.getUsuarioLogadoAdministrador().getNome() : "Administrador";
        despesasOficina.add(new Despesa(descricao, valor, data, categoria, responsavel));
        System.out.println("Despesa lançada com sucesso!");
        JsonUtil.salvarLista("json/despesas.json", despesasOficina);
    }

    private void emitirContaCliente(Scanner scanner) {
        System.out.println("\n--- Emitir Extrato de Cliente ---");
        System.out.print("Digite o CPF do cliente: ");
        String cpf = scanner.nextLine();
        Cliente cliente = clientes.buscarClientePorCpf(cpf);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }
        
        List<OrdemServico> ordensDoCliente = cliente.getOrdensDeServico();
        if (ordensDoCliente.isEmpty()) {
            System.out.println("Cliente não possui ordens de serviço.");
            return;
        }
        
        System.out.println("\n=== EXTRATOS DO CLIENTE ===");
        System.out.println("Cliente: " + cliente.getNome() + " (CPF: " + cliente.getCpfPseudoanonimizado() + ")");
        System.out.println("=====================================");
        
        double totalGeral = 0.0;
        int contador = 1;
        
        for (OrdemServico os : ordensDoCliente) {
            System.out.println("\n--- Extrato #" + contador + " ---");
            Extrato extrato = new Extrato(os);
            System.out.println(extrato);
            
            // Verificar se o cálculo está correto
            extrato.verificarCalculoCorreto(os);
            
            totalGeral += extrato.getValorTotalExtrato();
            contador++;
        }
        
        System.out.println("=====================================");
        System.out.printf("TOTAL GERAL A PAGAR: R$ %.2f\n", totalGeral);
        
        // Perguntar se deseja salvar os extratos em arquivo
        System.out.print("\nDeseja salvar os extratos em arquivo? (s/n): ");
        String resposta = scanner.nextLine().toLowerCase();
        if (resposta.equals("s") || resposta.equals("sim")) {
            for (OrdemServico os : ordensDoCliente) {
                Extrato extrato = new Extrato(os);
                extrato.salvarExtratoEmArquivo();
            }
            System.out.println("Extratos salvos com sucesso!");
        }
    }

    // Método para registrar ordem de serviço no financeiro
    private void registrarOrdemServicoNoFinanceiro(OrdemServico os) {
        if (os.getValorTotal() > 0) {
            // Criar uma entrada no financeiro para a OS
            int mes = os.getDataAbertura().getMonthValue();
            int ano = os.getDataAbertura().getYear();
            
            // Carregar financeiro existente ou criar novo
            List<Financeiro> financeiros = JsonUtil.lerLista("json/financeiro.json", Financeiro.class);
            if (financeiros == null) financeiros = new ArrayList<>();
            
            // Buscar ou criar registro financeiro para o mês/ano
            Financeiro financeiroMes = null;
            for (Financeiro f : financeiros) {
                if (f.getMes() == mes && f.getAno() == ano) {
                    financeiroMes = f;
                    break;
                }
            }
            
            if (financeiroMes == null) {
                // Criar novo registro financeiro para o mês
                financeiroMes = new Financeiro(mes, ano, 0.0, 0.0, 0.0);
                financeiros.add(financeiroMes);
            }
            
            // Adicionar valor da OS às receitas
            financeiroMes.setReceitas(financeiroMes.getReceitas() + os.getValorTotal());
            financeiroMes.setSaldo(financeiroMes.getReceitas() - financeiroMes.getDespesas());
            
            // Salvar financeiro atualizado
            JsonUtil.salvarLista("json/financeiro.json", financeiros);
            
            System.out.println("Ordem de serviço registrada no financeiro: R$ " + String.format("%.2f", os.getValorTotal()));
        }
    }

    // Exemplo de método para registrar ponto de funcionário
    private void registrarPontoFuncionario(PontoFuncionario ponto) {
        List<PontoFuncionario> pontos = JsonUtil.lerLista("json/pontos_funcionario.json", PontoFuncionario.class);
        if (pontos == null) pontos = new ArrayList<>();
        pontos.add(ponto);
        JsonUtil.salvarLista("json/pontos_funcionario.json", pontos);
    }

    // Método para visualizar receitas de serviços
    private void visualizarReceitasServicos(Scanner scanner) {
        System.out.println("\n--- Receitas de Serviços ---");
        System.out.print("Digite o mês (1-12): ");
        int mes = Integer.parseInt(scanner.nextLine());
        System.out.print("Digite o ano (ex: 2025): ");
        int ano = Integer.parseInt(scanner.nextLine());
        
        List<OrdemServico> ordensServico = JsonUtil.lerLista("json/ordens_servico.json", OrdemServico.class);
        if (ordensServico == null || ordensServico.isEmpty()) {
            System.out.println("Nenhuma ordem de serviço encontrada.");
            return;
        }
        
        System.out.println("\nOrdens de serviço finalizadas/entregues no período:");
        System.out.println("ID | Cliente | Veículo | Valor | Data");
        System.out.println("----------------------------------------");
        
        double totalReceitas = 0.0;
        int contador = 0;
        
        for (OrdemServico os : ordensServico) {
            if (os.getDataAbertura().getMonthValue() == mes && 
                os.getDataAbertura().getYear() == ano &&
                (os.getStatus() == OrdemServico.StatusOrdemServico.FINALIZADA || 
                 os.getStatus() == OrdemServico.StatusOrdemServico.ENTREGUE)) {
                
                System.out.printf("%d | %s | %s | R$ %.2f | %s\n",
                    os.getIdOrdemServico(),
                    os.getCliente().getNome(),
                    os.getVeiculo().getModelo(),
                    os.getValorTotal(),
                    os.getDataAbertura().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                );
                totalReceitas += os.getValorTotal();
                contador++;
            }
        }
        
        if (contador == 0) {
            System.out.println("Nenhuma ordem de serviço finalizada/entregue neste período.");
        } else {
            System.out.println("----------------------------------------");
            System.out.printf("Total de receitas de serviços: R$ %.2f\n", totalReceitas);
            System.out.printf("Quantidade de serviços: %d\n", contador);
        }
    }

    // Método para salvar PontoFuncionario no formato correto (apenas nome do funcionário)
    private void salvarPontoFuncionarioCorreto(PontoFuncionario ponto) {
        List<Map<String, Object>> pontosData = new ArrayList<>();
        
        // Carregar pontos existentes
        List<PontoFuncionario> pontos = JsonUtil.lerLista("json/pontos_funcionario.json", PontoFuncionario.class);
        if (pontos != null) {
            for (PontoFuncionario p : pontos) {
                Map<String, Object> pontoData = new HashMap<>();
                pontoData.put("funcionario", p.getFuncionario().getNome());
                pontoData.put("entrada", p.getEntrada().toString());
                if (p.getSaida() != null) {
                    pontoData.put("saida", p.getSaida().toString());
                }
                pontosData.add(pontoData);
            }
        }
        
        // Adicionar novo ponto
        Map<String, Object> novoPonto = new HashMap<>();
        novoPonto.put("funcionario", ponto.getFuncionario().getNome());
        novoPonto.put("entrada", ponto.getEntrada().toString());
        if (ponto.getSaida() != null) {
            novoPonto.put("saida", ponto.getSaida().toString());
        }
        pontosData.add(novoPonto);
        
        // Salvar usando Gson diretamente
        try {
            String caminhoCompleto = System.getProperty("user.dir") + "/src/main/java/com/mycompany/oficina/json/pontos_funcionario.json";
            
            try (FileWriter writer = new FileWriter(caminhoCompleto)) {
                new GsonBuilder().setPrettyPrinting().create().toJson(pontosData, writer);
            }
        } catch (Exception e) {
            System.err.println("Erro ao salvar pontos de funcionário: " + e.getMessage());
        }
    }

    // Exemplo de método para registrar venda
    private void registrarVenda(Venda venda) {
        List<Venda> vendas = JsonUtil.lerLista("json/vendas.json", Venda.class);
        if (vendas == null) vendas = new ArrayList<>();
        vendas.add(venda);
        JsonUtil.salvarLista("json/vendas.json", vendas);
    }

    // Exemplo de método para registrar extrato
    private void registrarExtrato(Extrato extrato) {
        List<Extrato> extratos = JsonUtil.lerLista("json/extratos.json", Extrato.class);
        if (extratos == null) extratos = new ArrayList<>();
        extratos.add(extrato);
        JsonUtil.salvarLista("json/extratos.json", extratos);
    }

    // Exemplo de método para registrar financeiro
    private void registrarFinanceiro(Financeiro financeiro) {
        List<Financeiro> financeiros = JsonUtil.lerLista("json/financeiro.json", Financeiro.class);
        if (financeiros == null) financeiros = new ArrayList<>();
        financeiros.add(financeiro);
        JsonUtil.salvarLista("json/financeiro.json", financeiros);
    }

    // Exemplo de método para registrar colaborador
    private void registrarColaborador(Usuario colaborador) {
        List<Usuario> colaboradores = JsonUtil.lerLista("json/colaboradores.json", Usuario.class);
        if (colaboradores == null) colaboradores = new ArrayList<>();
        colaboradores.add(colaborador);
        JsonUtil.salvarLista("json/colaboradores.json", colaboradores);
    }

    // Método para salvar todos os pontos no formato correto
    private void salvarTodosPontosCorretos(List<PontoFuncionario> pontos) {
        List<Map<String, Object>> pontosData = new ArrayList<>();
        
        for (PontoFuncionario ponto : pontos) {
            Map<String, Object> pontoData = new HashMap<>();
            pontoData.put("funcionario", ponto.getFuncionario().getNome());
            pontoData.put("entrada", ponto.getEntrada().toString());
            if (ponto.getSaida() != null) {
                pontoData.put("saida", ponto.getSaida().toString());
            }
            pontosData.add(pontoData);
        }
        
        try {
            String caminhoCompleto = System.getProperty("user.dir") + "/src/main/java/com/mycompany/oficina/json/pontos_funcionario.json";
            
            try (FileWriter writer = new FileWriter(caminhoCompleto)) {
                new GsonBuilder().setPrettyPrinting().create().toJson(pontosData, writer);
            }
        } catch (Exception e) {
            System.err.println("Erro ao salvar pontos de funcionário: " + e.getMessage());
        }
    }

    private void adicionarVeiculoCliente(Scanner scanner) {
        System.out.println("\n--- Adicionar Veículo ao Cliente ---");
        System.out.print("CPF do cliente: ");
        String cpf = scanner.nextLine();
        System.out.print("Placa do veículo: ");
        String placa = scanner.nextLine();
        System.out.print("Modelo do veículo: ");
        String modelo = scanner.nextLine();
        System.out.print("Ano: ");
        int ano = Integer.parseInt(scanner.nextLine());

        clientes.adicionarVeiculoPublico(cpf, placa, modelo, ano);
    }

    private void registrarVendaPeca(Scanner scanner) {
        System.out.println("\n--- Registrar Venda de Peças ---");
        System.out.print("CPF do cliente: ");
        String cpf = scanner.nextLine();
        Cliente cliente = clientes.buscarClientePorCpf(cpf);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }
        List<Peca> pecasEstoque = JsonUtil.lerLista("json/pecas.json", Peca.class);
        if (pecasEstoque == null || pecasEstoque.isEmpty()) {
            System.out.println("Nenhuma peça disponível no estoque.");
            return;
        }
        List<Peca> pecasVendidas = new ArrayList<>();
        boolean adicionarMais = true;
        while (adicionarMais) {
            System.out.println("Peças disponíveis:");
            for (int i = 0; i < pecasEstoque.size(); i++) {
                Peca p = pecasEstoque.get(i);
                System.out.println((i + 1) + ". " + p.getNome() + " | Quantidade: " + p.getQuantidade());
            }
            System.out.print("Digite o número da peça: ");
            int numPeca = Integer.parseInt(scanner.nextLine());
            if (numPeca < 1 || numPeca > pecasEstoque.size()) {
                System.out.println("Peça inválida.");
                continue;
            }
            Peca pecaSelecionada = pecasEstoque.get(numPeca - 1);
            System.out.print("Quantidade a vender: ");
            int qtd = Integer.parseInt(scanner.nextLine());
            if (qtd > pecaSelecionada.getQuantidade()) {
                System.out.println("Quantidade insuficiente no estoque.");
                continue;
            }
            // Registrar a peça vendida (sem atualizar estoque aqui)
            Peca pecaVendida = new Peca(pecaSelecionada.getId(), pecaSelecionada.getNome(), pecaSelecionada.getPreco(), qtd);
            pecasVendidas.add(pecaVendida);
            System.out.print("Adicionar mais peças? (s/n): ");
            String resp = scanner.nextLine();
            adicionarMais = resp.equalsIgnoreCase("s");
        }
        // Registrar venda (que já atualiza o estoque automaticamente)
        Venda venda = new Venda(cliente);
        for (Peca p : pecasVendidas) {
            venda.adicionarPeca(p, p.getQuantidade());
        }
        registrarVenda(venda);
        System.out.println("Venda registrada com sucesso!");
    }

    private void menuElevadores(Scanner scanner) {
        boolean sair = false;
        while (!sair) {
            System.out.println("\n==== Menu Elevadores ====");
            System.out.println("1. Ver Status dos Elevadores");
            System.out.println("2. Ocupar Elevador");
            System.out.println("3. Liberar Elevador");
            System.out.println("4. Voltar");
            System.out.print("Escolha uma opção: ");

            String opcao = scanner.nextLine();
            switch (opcao) {
                case "1":
                    verStatusElevadores();
                    break;
                case "2":
                    ocuparElevador(scanner);
                    break;
                case "3":
                    liberarElevador(scanner);
                    break;
                case "4":
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void verStatusElevadores() {
        System.out.println("\n--- Status dos Elevadores ---");
        Elevador[] elevadores = Elevador.getElevadores();
        for (Elevador elevador : elevadores) {
            if (elevador != null) {
                System.out.println(elevador);
            }
        }
        System.out.println("----------------------------");
    }

    private void ocuparElevador(Scanner scanner) {
        System.out.println("\n--- Ocupar Elevador ---");
        verStatusElevadores();
        
        System.out.print("Digite o ID do elevador (1, 2 ou 3): ");
        try {
            int idElevador = Integer.parseInt(scanner.nextLine());
            
            if (idElevador < 1 || idElevador > 3) {
                System.out.println("ID inválido. Use 1, 2 ou 3.");
                return;
            }
            
            Elevador[] elevadores = Elevador.getElevadores();
            Elevador elevador = elevadores[idElevador - 1];
            
            if (elevador != null) {
                if (!elevador.isOcupado()) {
                    elevador.ocuparElevador();
                    System.out.println("Elevador " + idElevador + " ocupado com sucesso!");
                } else {
                    System.out.println("Elevador " + idElevador + " já está ocupado!");
                }
            } else {
                System.out.println("Elevador não encontrado.");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID inválido. Digite um número.");
        }
    }

    private void liberarElevador(Scanner scanner) {
        System.out.println("\n--- Liberar Elevador ---");
        verStatusElevadores();
        
        System.out.print("Digite o ID do elevador (1, 2 ou 3): ");
        try {
            int idElevador = Integer.parseInt(scanner.nextLine());
            
            if (idElevador < 1 || idElevador > 3) {
                System.out.println("ID inválido. Use 1, 2 ou 3.");
                return;
            }
            
            Elevador[] elevadores = Elevador.getElevadores();
            Elevador elevador = elevadores[idElevador - 1];
            
            if (elevador != null) {
                if (elevador.isOcupado()) {
                    elevador.liberarElevador();
                    System.out.println("Elevador " + idElevador + " liberado com sucesso!");
                } else {
                    System.out.println("Elevador " + idElevador + " já está livre!");
                }
            } else {
                System.out.println("Elevador não encontrado.");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID inválido. Digite um número.");
        }
    }

    private boolean verificarElevadorDisponivel() {
        Elevador[] elevadores = Elevador.getElevadores();
        for (Elevador elevador : elevadores) {
            if (elevador != null && !elevador.isOcupado()) {
                return true;
            }
        }
        return false;
    }

    private Elevador encontrarElevadorDisponivel() {
        Elevador[] elevadores = Elevador.getElevadores();
        for (Elevador elevador : elevadores) {
            if (elevador != null && !elevador.isOcupado()) {
                return elevador;
            }
        }
        return null;
    }
}
