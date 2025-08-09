package com.mycompany.oficina;


import com.mycompany.oficina.entidade.*;
import com.mycompany.oficina.seguranca.*;
import com.mycompany.oficina.comparator.*;
import java.util.*;
import java.util.Scanner;

/**
 * Classe principal do sistema da oficina.
 * Executa testes de todas as funcionalidades do sistema e permite acesso ao sistema interativo.
 *
 * 
 * @author Ana Clara e Pedro
 * @version 1.0
 */
public class Main {
    
    /**
     * Inicia o sistema da oficina.
     * 
     * Mostra um menu inicial para o usuário decidir se quer ver
     * as demonstrações ou usar o sistema completo.
     * 
     * @param args argumentos da linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        System.out.println("=== MAIN INICIADO ===");
        Scanner scanner = new Scanner(System.in);
        System.out.println("==== Menu Inicial ====");
        System.out.println("1. Executar demonstrações das questões");
        System.out.println("2. Acessar sistema interativo (login/menu)");
        System.out.print("Escolha uma opção: ");
        String opcao = scanner.nextLine();
        if (opcao.equals("2")) {
            System.out.println("Criando instância da Oficina...");
            Oficina oficina = new Oficina();
            System.out.println("Chamando oficina.iniciar()...");
            oficina.iniciar();
            return;
        }
        // Fluxo padrão: executar as questões
        // Questão 1: Implementação das classes e relações
        // Exemplo: instanciar Cliente, Veiculo, OrdemServico, associar, etc.
        Cliente cliente1 = new Cliente("Ana", "12345678901", "12345678901", "99999-0000", "ana@email.com", "Rua 1", "10", "CidadeA", "EstadoA", "12345-678");
        Veiculo veiculo1 = new Veiculo("Uno", "ABC-1234", 2015, cliente1);
        cliente1.adicionarVeiculo(veiculo1);
        Funcionario mecanico1 = new Funcionario("Pedro", "pedro@email.com", "Mecânico", "senha123");
        OrdemServico os1 = new OrdemServico(cliente1, veiculo1, mecanico1);
        cliente1.adicionarOrdemDeServico(os1);

        // Questão 2: Sistema utilizado por colaboradores e administrador
        Administrador admin = new Administrador("Admin", "admin@oficina.com", "admin123", 1);
        GerenciarColaboradores colaboradores = GerenciarColaboradores.getInstance();
        colaboradores.cadastrarFuncionario("Pedro", "pedro@email.com", "Mecânico", "senha123");
        colaboradores.cadastrarAdministrador("Admin", "admin@oficina.com", "admin123", 1);

        // Questão 3: Sobrescrever toString()
        System.out.println(cliente1);
        System.out.println(veiculo1);
        System.out.println(mecanico1);
        System.out.println(os1);

        // Questão 4: Uso do super nos construtores
        // (Já usado em Funcionario, Administrador, Veiculo, etc.)

        // Questão 5: Vetor fixo de elevadores
        Elevador[] elevadores = new Elevador[3];
        elevadores[0] = new Elevador(1, "Elevador A");
        elevadores[1] = new Elevador(2, "Elevador B");
        elevadores[2] = new Elevador(3, "Elevador C");
        for (Elevador e : elevadores) System.out.println(e);

        // Questão 6: Cadastrar, editar colaboradores
        Funcionario f = new Funcionario("João", "joao@email.com", "Auxiliar", "senha456");
        f.setCargo("Chefe de Oficina");
        System.out.println(f);

        // Questão 7: Cadastrar, alterar, excluir clientes
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(cliente1);
        Cliente cliente2 = new Cliente("Bruno", "23456789012", "23456789012", "98888-0000", "bruno@email.com", "Rua 2", "20", "CidadeB", "EstadoB", "23456-789");
        clientes.add(cliente2);
        cliente2.setNome("Bruno Silva");
        clientes.remove(cliente1);

        // Questão 8: Imprimir ordens de serviço de cada cliente
        for (Cliente c : clientes) {
            System.out.println("OS do cliente " + c.getNome() + ":");
            for (OrdemServico os : c.getOrdensDeServico()) {
                System.out.println(os);
            }
        }

        // Questão 9: Listas dinâmicas (já demonstrado acima com ArrayList)

        // Questão 10: Gerar extrato de serviço/venda para cliente
        Venda venda1 = new Venda(cliente2);
        cliente2.adicionarVenda(venda1);
        System.out.println("Extrato do cliente: " + cliente2.getNome());
        System.out.println(cliente2);

        // Questão 11: Controle de instâncias de Veículo
        // a) Encapsulamento (private static)
        System.out.println("Total de veículos (encapsulado): " + Veiculo.getContadorVeiculos());
        // b) protected static (exemplo fictício, pois protected é mais útil para herança)
        // c) Explicação: private + getter/setter é mais seguro, protected permite acesso em subclasses.

        // Questão 12: Método de classe para retornar total de veículos
        System.out.println("Total de veículos (método de classe): " + Veiculo.getContadorVeiculos());

        // Questão 13: Comparator para Agendamento e Cliente
        List<Agendamento> agendamentos = new ArrayList<>();
        // ... adicionar agendamentos
        agendamentos.sort(new CompararAgendamentoPorNomeCliente());
        // ... outro sort por data, etc.

        // Questão 14: Salvar/recuperar dados em JSON
        // Exemplo:
        // JsonUtil.salvarParaArquivo(clientes, "clientes.json");
        // List<Cliente> clientesLidos = JsonUtil.carregarDeArquivo("clientes.json", Cliente.class);

        // Questão 15: Iterator e foreach
        System.out.println("\n--- Questão 15: Iterator e foreach ---");
        List<Cliente> clientes15 = new ArrayList<>();
        clientes15.add(new Cliente("Ana", "12345678901", "12345678901", "99999-0000", "ana@email.com", "Rua 1", "10", "CidadeA", "EstadoA", "12345-678"));
        clientes15.add(new Cliente("Bruno", "23456789012", "23456789012", "98888-0000", "bruno@email.com", "Rua 2", "20", "CidadeB", "EstadoB", "23456-789"));
        clientes15.add(new Cliente("Carlos", "34567890123", "34567890123", "97777-0000", "carlos@email.com", "Rua 3", "30", "CidadeC", "EstadoC", "34567-890"));

        // Instanciar um iterator para a ArrayList de clientes
        Iterator<Cliente> iterator = clientes15.iterator();
        
        System.out.println("Percorrendo com Iterator (while):");
        // Fazer testes no main em percorrer o arraylist com chamadas usando o código:
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        
        // Explicaçao de como isso está acontecendo:
        System.out.println("\n--- Explicação do Iterator ---");
        System.out.println("O Iterator funciona da seguinte forma:");
        System.out.println("1. iterator.hasNext() - verifica se existe próximo elemento");
        System.out.println("2. iterator.next() - retorna o elemento atual e avança para o próximo");
        System.out.println("3. O loop continua até não haver mais elementos");
        System.out.println("4. O Iterator encapsula a estrutura interna da lista, permitindo acesso seguro");
        
        // Qual relação do código acima com o foreach em java?
        System.out.println("\n--- Relação com foreach ---");
        System.out.println("O foreach em Java é um 'syntactic sugar' que internamente usa Iterator.");
        System.out.println("O código foreach é convertido pelo compilador para usar Iterator automaticamente.");
        System.out.println("Ambos fazem a mesma coisa, mas o foreach é mais conciso e legível.");
        
        // Testar o foreach
        System.out.println("\nPercorrendo com foreach:");
        for (Cliente cliente : clientes15) {
            System.out.println(cliente);
        }
        
        System.out.println("\nComparação:");
        System.out.println("Iterator: while(iterator.hasNext()) { System.out.println(iterator.next()); }");
        System.out.println("Foreach: for (Cliente cliente : clientes15) { System.out.println(cliente); }");
        System.out.println("Resultado: Ambos produzem a mesma saída, mas o foreach é mais simples de escrever.");

        // Questão 16: Comparator + sort
        System.out.println("\n--- Questão 16: Comparator + sort ---");
        List<Cliente> clientes16 = new ArrayList<>();
        clientes16.add(new Cliente("Ana", "12345678901", "12345678901", "99999-0000", "ana@email.com", "Rua 1", "10", "CidadeA", "EstadoA", "12345-678"));
        clientes16.add(new Cliente("Bruno", "23456789012", "23456789012", "98888-0000", "bruno@email.com", "Rua 2", "20", "CidadeB", "EstadoB", "23456-789"));
        clientes16.add(new Cliente("Carlos", "34567890123", "34567890123", "97777-0000", "carlos@email.com", "Rua 3", "30", "CidadeC", "EstadoC", "34567-890"));
        
        System.out.println("Lista original:");
        clientes16.forEach(System.out::println);
        
        // Primeira execução: ordenar por nome usando Collections.sort()
        System.out.println("\n1ª execução - Ordenado por nome:");
        Collections.sort(clientes16, new CompararClientePorNome());
        clientes16.forEach(System.out::println);
        
        // Segunda execução: ordenar por CPF usando Collections.sort() com comparator implementado
        System.out.println("\n2ª execução - Ordenado por CPF:");
        Collections.sort(clientes16, new CompararClientePorCPF());
        clientes16.forEach(System.out::println);

        // Questão 17: find com Iterator/Comparator e binarySearch
        System.out.println("\n--- Questão 17: find e binarySearch ---");
        
        // Criar lista de clientes para teste
        List<Cliente> clientes17 = new ArrayList<>();
        clientes17.add(new Cliente("Ana", "12345678901", "12345678901", "99999-0000", "ana@email.com", "Rua 1", "10", "CidadeA", "EstadoA", "12345-678"));
        clientes17.add(new Cliente("Bruno", "23456789012", "23456789012", "98888-0000", "bruno@email.com", "Rua 2", "20", "CidadeB", "EstadoB", "23456-789"));
        clientes17.add(new Cliente("Carlos", "34567890123", "34567890123", "97777-0000", "carlos@email.com", "Rua 3", "30", "CidadeC", "EstadoC", "34567-890"));
        clientes17.add(new Cliente("Daniel", "45678901234", "45678901234", "96666-0000", "daniel@email.com", "Rua 4", "40", "CidadeD", "EstadoD", "45678-901"));
        
        // Método find implementado usando Iterator e Comparator (implementação inline)
        System.out.println("Testando método find implementado:");
        String nomeBusca = "Bruno";
        
        // Implementação do find usando Iterator e Comparator
        Cliente encontradoFind = null;
        Iterator<Cliente> iteratorFind = clientes17.iterator();
        Comparator<Cliente> comparatorNome = new CompararClientePorNome();
        Cliente clienteBusca = new Cliente(nomeBusca, "00000000000", "00000000000", "99999-0000", "busca@email.com", "Rua Busca", "1", "CidadeBusca", "EstadoBusca", "11111-111");
        
        while (iteratorFind.hasNext()) {
            Cliente cliente = iteratorFind.next();
            if (comparatorNome.compare(cliente, clienteBusca) == 0) {
                encontradoFind = cliente;
                break;
            }
        }
        
        System.out.println("Encontrado (find): " + encontradoFind);
        
        // Teste com nome que não existe
        String nomeInexistente = "João";
        Cliente naoEncontrado = null;
        Iterator<Cliente> iteratorNaoEncontrado = clientes17.iterator();
        Cliente clienteBuscaInexistente = new Cliente(nomeInexistente, "00000000001", "00000000001", "99999-0001", "inexistente@email.com", "Rua Inexistente", "2", "CidadeInexistente", "EstadoInexistente", "11111-112");
        
        while (iteratorNaoEncontrado.hasNext()) {
            Cliente cliente = iteratorNaoEncontrado.next();
            if (comparatorNome.compare(cliente, clienteBuscaInexistente) == 0) {
                naoEncontrado = cliente;
                break;
            }
        }
        
        System.out.println("Busca por '" + nomeInexistente + "' (find): " + (naoEncontrado != null ? naoEncontrado : "Não encontrado"));
        
        // Fazer chamadas ao binarySearch() da classe Collections e comparar com o find implementado
        System.out.println("\nComparando find implementado com binarySearch:");
        
        // Ordenar a lista para binarySearch funcionar corretamente
        Collections.sort(clientes17, new CompararClientePorNome());
        System.out.println("Lista ordenada por nome:");
        clientes17.forEach(System.out::println);
        
        // Usando binarySearch
        int indiceBinarySearch = Collections.binarySearch(clientes17, clienteBusca, new CompararClientePorNome());
        
        if (indiceBinarySearch >= 0) {
            Cliente encontradoBinarySearch = clientes17.get(indiceBinarySearch);
            System.out.println("Encontrado (binarySearch): " + encontradoBinarySearch);
            System.out.println("Índice encontrado: " + indiceBinarySearch);
        } else {
            System.out.println("Não encontrado (binarySearch) - índice: " + indiceBinarySearch);
        }
        
        // Comparação dos resultados
        System.out.println("\n--- Comparação dos métodos ---");
        System.out.println("Método find (Iterator): " + encontradoFind);
        System.out.println("Método binarySearch: " + (indiceBinarySearch >= 0 ? clientes17.get(indiceBinarySearch) : "Não encontrado"));
        System.out.println("Resultado: " + (encontradoFind != null && indiceBinarySearch >= 0 && encontradoFind.equals(clientes17.get(indiceBinarySearch)) ? "AMBOS ENCONTRARAM O MESMO CLIENTE" : "RESULTADOS DIFERENTES"));
        
        System.out.println("\n--- Vantagens e desvantagens ---");
        System.out.println("Find (Iterator): Funciona em listas não ordenadas, mas é O(n)");
        System.out.println("BinarySearch: Requer lista ordenada, mas é O(log n) - mais eficiente");

        // Questão 18: Fluxo completo de atendimento de 10 clientes
        System.out.println("\n--- Questão 18: Fluxo completo de 10 clientes ---");
        
        // Criar mecânico para os atendimentos
        Funcionario mecanicoAtendimento = new Funcionario("João Mecânico", "joao@oficina.com", "Mecânico", "senha123");
        
        // Criar estoque inicial com peças
        List<Peca> pecas = new ArrayList<>();
        pecas.add(new Peca("Óleo de Motor", 25.00, 50));
        pecas.add(new Peca("Filtro de Óleo", 15.00, 30));
        pecas.add(new Peca("Pastilha de Freio", 45.00, 40));
        pecas.add(new Peca("Bateria", 120.00, 10));
        pecas.add(new Peca("Pneu", 200.00, 20));
        
        // Demonstrar o sistema de IDs automáticos
        System.out.println("=== Sistema de IDs Automáticos para Peças ===");
        System.out.println("Total de peças criadas: " + Peca.getContadorPecas());
        System.out.println("Próximo ID disponível: " + Peca.getProximoId());
        System.out.println("Peças no estoque:");
        for (Peca peca : pecas) {
            System.out.println("  - ID " + peca.getId() + ": " + peca.getNome());
        }
        System.out.println();
        
        // Criar serviços disponíveis
        List<Servico> servicos = new ArrayList<>();
        servicos.add(new Servico(1, "Troca de Óleo", 50.00, "Manutenção"));
        servicos.add(new Servico(2, "Troca de Filtro", 30.00, "Manutenção"));
        servicos.add(new Servico(3, "Troca de Pastilha de Freio", 80.00, "Freios"));
        servicos.add(new Servico(4, "Troca de Bateria", 40.00, "Elétrica"));
        servicos.add(new Servico(5, "Alinhamento", 60.00, "Suspensão"));
        
        List<Cliente> clientes18 = new ArrayList<>();
        List<OrdemServico> ordensServico = new ArrayList<>();
        List<Venda> vendas = new ArrayList<>();
        
        // Fluxo completo para 10 clientes
        for (int i = 1; i <= 10; i++) {
            System.out.println("\n=== ATENDIMENTO " + i + " ===");
            
            // 1. CADASTRO DO CLIENTE
            Cliente cliente = new Cliente(
                "Cliente" + i, 
                String.format("%011d", i), 
                String.format("%011d", i), 
                "90000-0000", 
                "cli" + i + "@mail.com", 
                "Rua " + i, 
                String.valueOf(i), 
                "Cidade" + i, 
                "Estado" + i, 
                "11111-111"
            );
            clientes18.add(cliente);
            System.out.println("✓ Cliente cadastrado: " + cliente.getNome());
            
            // 2. CADASTRO DO VEÍCULO
            String[] modelos = {"Uno", "Gol", "Celta", "Palio", "Corsa", "Ka", "Sandero", "Onix", "HB20", "March"};
            String modelo = modelos[(i-1) % modelos.length];
            String placa = "ABC-" + String.format("%04d", i);
            int ano = 2015 + (i % 10);
            
            Veiculo veiculo = new Veiculo(modelo, placa, ano, cliente);
            cliente.adicionarVeiculo(veiculo);
            System.out.println("✓ Veículo cadastrado: " + modelo + " - " + placa + " (" + ano + ")");
            
            // 3. CRIAÇÃO DA ORDEM DE SERVIÇO
            OrdemServico os = new OrdemServico(cliente, veiculo, mecanicoAtendimento);
            cliente.adicionarOrdemDeServico(os);
            ordensServico.add(os);
            System.out.println("✓ Ordem de Serviço criada: OS#" + os.getIdOrdemServico());
            
            // 4. SELECIONAR SERVIÇOS E PEÇAS
            int numServicos = 1 + (i % 3); // 1 a 3 serviços por cliente
            double valorTotal = 0.0;
            
            for (int j = 0; j < numServicos; j++) {
                Servico servico = servicos.get((i + j) % servicos.size());
                os.adicionarServico(servico);
                valorTotal += servico.getValor();
                System.out.println("  ✓ Serviço adicionado: " + servico.getDescricao() + " - R$ " + servico.getValor());
                
                // 5. ADICIONAR PEÇAS À ORDEM DE SERVIÇO (estoque será atualizado automaticamente)
                Peca peca = pecas.get((i + j) % pecas.size());
                if (peca.getQuantidade() > 0) {
                    // Adicionar peça à OS (estoque será atualizado quando finalizar)
                    os.adicionarPeca(peca, 1);
                    valorTotal += peca.getPreco();
                    System.out.println("  ✓ Peça adicionada à OS: " + peca.getNome() + " - R$ " + peca.getPreco());
                } else {
                    System.out.println("  ⚠ Peça em falta: " + peca.getNome());
                }
            }
            
            // 6. FINALIZAR ORDEM DE SERVIÇO
            os.setStatus(OrdemServico.StatusOrdemServico.ENTREGUE);
            System.out.println("✓ Ordem de Serviço finalizada - Valor Total: R$ " + os.getValorTotal());
            
            // 7. CRIAR VENDA
            Venda venda = new Venda(cliente);
            // Adicionar peças à venda para calcular o total
            for (int j = 0; j < numServicos; j++) {
                Peca peca = pecas.get((i + j) % pecas.size());
                if (peca.getQuantidade() >= 0) { // Se ainda tem estoque
                    venda.adicionarPeca(peca, 1);
                }
            }
            vendas.add(venda);
            System.out.println("✓ Venda criada - Valor: R$ " + venda.getTotal());
            
            // 8. EMISSÃO DE NOTA FISCAL
            System.out.println("✓ Nota Fiscal emitida para " + cliente.getNome());
            System.out.println("  - Cliente: " + cliente.getNome() + " (CPF: " + cliente.getCpf() + ")");
            System.out.println("  - Veículo: " + veiculo.getModelo() + " - " + veiculo.getPlaca());
            System.out.println("  - Mecânico: " + mecanicoAtendimento.getNome());
            System.out.println("  - Serviços realizados: " + os.getServicos().size());
            System.out.println("  - Valor Total: R$ " + os.getValorTotal());
            System.out.println("  - Status: " + os.getStatus().getDescricao());
        }
        
        // RESUMO FINAL
        System.out.println("\n=== RESUMO DO ATENDIMENTO ===");
        System.out.println("Total de clientes atendidos: " + clientes18.size());
        System.out.println("Total de ordens de serviço: " + ordensServico.size());
        System.out.println("Total de vendas realizadas: " + vendas.size());
        
        double valorTotalVendas = vendas.stream().mapToDouble(Venda::getTotal).sum();
        System.out.println("Valor total das vendas: R$ " + valorTotalVendas);
        
        System.out.println("\nEstado final do estoque:");
        for (Peca peca : pecas) {
            System.out.println("  - " + peca.getNome() + ": " + peca.getQuantidade() + " unidades restantes");
        }
    }
}