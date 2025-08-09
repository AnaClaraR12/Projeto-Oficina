package com.mycompany.oficina.entidade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.UUID;

/**
 * Representa um cliente da oficina, com dados pessoais, endereço,
 * veículos associados, ordens de serviço e contato de emergência.
 *
 * A classe também possui controle de status do cliente.
 *
 * @author Ana Clara e Pedro
 * @version 1.1
 */
public class Cliente {

    // Identificador único
    private final String id;

    // Dados pessoais
    private String nome;
    private String cpf;
    private String cnh;
    private String telefone;
    private String email;

    // Endereço detalhado
    private String rua;
    private String numero;
    private String cidade;
    private String estado;
    private String cep;

    // Contato de emergência
    private String contatoEmergencia;

    // Veículos e ordens de serviço
    private final List<Veiculo> listaVeiculos;
    private final List<OrdemServico> ordensDeServico;
    private final List<Venda> vendas; // Inicializado no construtor

    /**
     * Enumeração que define o status de um cliente.
     */
    public enum StatusCliente { ATIVO, BLOQUEADO, PREFERENCIAL }

    private StatusCliente status;

    /**
     * Construtor completo da classe {@code Cliente} que gera automaticamente um ID único.
     *
     * @param nome o nome do cliente
     * @param cpf o CPF do cliente
     * @param cnh a CNH do cliente
     * @param telefone o telefone do cliente
     * @param email o email do cliente
     * @param rua a rua do endereço do cliente
     * @param numero o número da residência do cliente
     * @param cidade a cidade do cliente
     * @param estado o estado do cliente
     * @param cep o CEP do cliente
     */
    public Cliente(String nome, String cpf, String cnh, String telefone, String email,
                   String rua, String numero, String cidade, String estado, String cep) {
        this.id = UUID.randomUUID().toString();
        setNome(nome);
        setCpf(cpf);
        setCnh(cnh);
        setTelefone(telefone);
        setEmail(email);
        setRua(rua);
        setNumero(numero);
        setCidade(cidade);
        setEstado(estado);
        setCep(cep);

        this.contatoEmergencia = null;
        this.listaVeiculos = new ArrayList<>();
        this.ordensDeServico = new ArrayList<>();
        this.vendas = new ArrayList<>(); // Inicializa a lista de vendas
        this.status = StatusCliente.ATIVO;
    }

    /**
     * Construtor que permite especificar um ID (útil para carregar clientes existentes).
     *
     * @param id o ID único do cliente
     * @param nome o nome do cliente
     * @param cpf o CPF do cliente
     * @param cnh a CNH do cliente
     * @param telefone o telefone do cliente
     * @param email o email do cliente
     * @param rua a rua do endereço do cliente
     * @param numero o número da residência do cliente
     * @param cidade a cidade do cliente
     * @param estado o estado do cliente
     * @param cep o CEP do cliente
     */
    public Cliente(String id, String nome, String cpf, String cnh, String telefone, String email,
                   String rua, String numero, String cidade, String estado, String cep) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID inválido.");
        }
        this.id = id;
        setNome(nome);
        setCpf(cpf);
        setCnh(cnh);
        setTelefone(telefone);
        setEmail(email);
        setRua(rua);
        setNumero(numero);
        setCidade(cidade);
        setEstado(estado);
        setCep(cep);

        this.contatoEmergencia = null;
        this.listaVeiculos = new ArrayList<>();
        this.ordensDeServico = new ArrayList<>();
        this.vendas = new ArrayList<>(); // Inicializa a lista de vendas
        this.status = StatusCliente.ATIVO;
    }

    /** @return o ID único do cliente */
    public String getId() {
        return id;
    }

    /** @return o nome do cliente */
    public String getNome() {
        return nome;
    }

    /** @param nome define o nome do cliente */
    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) throw new IllegalArgumentException("Nome inválido.");
        this.nome = nome.trim();
    }

    /** @return o CPF do cliente */
    public String getCpf() {
        return cpf;
    }

    /** @param cpf define o CPF do cliente */
    public void setCpf(String cpf) {
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new IllegalArgumentException("CPF não pode ser vazio.");
        }
        this.cpf = cpf.trim();
    }

    /** @return a CNH do cliente */
    public String getCnh() {
        return cnh;
    }

    /** @param cnh define a CNH do cliente */
    public void setCnh(String cnh) {
        if (cnh == null || cnh.trim().isEmpty()) {
            throw new IllegalArgumentException("CNH não pode ser vazia.");
        }
        this.cnh = cnh.trim();
    }

    /** @return o telefone do cliente */
    public String getTelefone() {
        return telefone;
    }

    /** @param telefone define o telefone do cliente */
    public void setTelefone(String telefone) {
        if (telefone == null || telefone.trim().isEmpty()) {
            throw new IllegalArgumentException("Telefone não pode ser vazio.");
        }
        this.telefone = telefone.trim();
    }

    /** @return o email do cliente */
    public String getEmail() {
        return email;
    }

    /** @param email define o email do cliente */
    public void setEmail(String email) {
        if (email == null || !Pattern.compile("^[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,}$").matcher(email).matches()) {
            throw new IllegalArgumentException("Email inválido.");
        }
        this.email = email;
    }

    /** @return a rua do endereço do cliente */
    public String getRua() {
        return rua;
    }

    /** @param rua define a rua do cliente */
    public void setRua(String rua) {
        if (rua == null || rua.trim().isEmpty()) throw new IllegalArgumentException("Rua inválida.");
        this.rua = rua.trim();
    }

    /** @return o número da residência */
    public String getNumero() {
        return numero;
    }

    /** @param numero define o número da residência */
    public void setNumero(String numero) {
        if (numero == null || numero.trim().isEmpty()) throw new IllegalArgumentException("Número inválido.");
        this.numero = numero.trim();
    }

    /** @return a cidade do cliente */
    public String getCidade() {
        return cidade;
    }

    /** @param cidade define a cidade do cliente */
    public void setCidade(String cidade) {
        if (cidade == null || cidade.trim().isEmpty()) throw new IllegalArgumentException("Cidade inválida.");
        this.cidade = cidade.trim();
    }

    /** @return o estado do cliente */
    public String getEstado() {
        return estado;
    }

    /** @param estado define o estado do cliente */
    public void setEstado(String estado) {
        if (estado == null || estado.trim().isEmpty()) throw new IllegalArgumentException("Estado inválido.");
        this.estado = estado.trim();
    }

    /** @return o CEP do cliente */
    public String getCep() {
        return cep;
    }

    /** @param cep define o CEP do cliente */
    public void setCep(String cep) {
        if (cep == null || cep.trim().isEmpty()) {
            throw new IllegalArgumentException("CEP não pode ser vazio.");
        }
        this.cep = cep.trim();
    }

    /** @return o contato de emergência do cliente */
    public String getContatoEmergencia() {
        return contatoEmergencia;
    }

    /** @param contatoEmergencia define o contato de emergência */
    public void setContatoEmergencia(String contatoEmergencia) {
        this.contatoEmergencia = contatoEmergencia;
    }

    /** @return o status atual do cliente */
    public StatusCliente getStatus() {
        return status;
    }

    /** @param status define o status do cliente */
    public void setStatus(StatusCliente status) {
        if (status == null) throw new IllegalArgumentException("Status inválido.");
        this.status = status;
    }

    /**
     * Retorna uma visão imutável da lista de veículos associados ao cliente.
     *
     * @return lista de veículos associados ao cliente
     */
    public List<Veiculo> getListaVeiculos() {
        return Collections.unmodifiableList(listaVeiculos);
    }

    /**
     * Adiciona um novo veículo à lista de veículos do cliente.
     *
     * @param veiculo o veículo a ser adicionado
     */
    public void adicionarVeiculo(Veiculo veiculo) {
        if (veiculo == null) {
            throw new IllegalArgumentException("Veículo inválido.");
        }
        if (!listaVeiculos.contains(veiculo)) {
            listaVeiculos.add(veiculo);
            if (veiculo.getCliente() != this) {
                veiculo.setCliente(this);
            }
        }
    }

    /**
     * Remove um veículo da lista do cliente.
     *
     * @param veiculo o veículo a ser removido
     * @return true se o veículo foi removido, false caso contrário
     */
    public boolean removerVeiculo(Veiculo veiculo) {
        boolean removido = listaVeiculos.remove(veiculo);
        if (removido && veiculo.getCliente() == this) {
            veiculo.setCliente(null); // Remove a associação bidirecional
        }
        return removido;
    }

    /**
     * Retorna uma visão imutável da lista de ordens de serviço do cliente.
     *
     * @return lista de ordens de serviço do cliente
     */
    public List<OrdemServico> getOrdensDeServico() {
        // Retorna uma cópia da lista para manter o encapsulamento
        return Collections.unmodifiableList(ordensDeServico);
    }

    /**
     * Adiciona uma ordem de serviço à lista do cliente.
     *
     * @param os a ordem de serviço a ser adicionada
     */
    public void adicionarOrdemDeServico(OrdemServico os) {
        if (os == null) throw new IllegalArgumentException("Ordem de serviço inválida.");
        if (!ordensDeServico.contains(os)) {
            ordensDeServico.add(os);
        }
    }

    /**
     * Calcula o valor total acumulado de todas as ordens de serviço do cliente.
     *
     * @return o valor total a ser pago
     */
    public double calcularTotalConta() {
        double total = 0.0;
        for (OrdemServico os : ordensDeServico) {
            total += os.getValorTotal();
        }
        return total;
    }

    /**
     * Adiciona uma venda à lista de vendas do cliente.
     *
     * @param venda a venda a ser adicionada
     */
    public void adicionarVenda(Venda venda) {
        if (venda != null && !vendas.contains(venda)) {
            vendas.add(venda);
        }
    }

    /**
     * Retorna uma visão imutável da lista de vendas do cliente.
     *
     * @return lista de vendas do cliente
     */
    public List<Venda> getVendas() {
        return Collections.unmodifiableList(vendas);
    }

    /**
     * Retorna o CPF pseudoanonimizado (apenas os 3 últimos dígitos visíveis).
     * Exemplo: *******123
     * @return CPF parcialmente oculto
     */
    public String getCpfPseudoanonimizado() {
        if (cpf == null || cpf.length() < 3) return "CPF inválido";
        if (cpf.length() <= 3) return "***" + cpf;
        return "*******" + cpf.substring(cpf.length() - 3);
    }

    /**
     * Retorna uma representação textual completa do cliente.
     *
     * @return string com todos os dados do cliente
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(id)
          .append("\nCliente: ").append(nome)
          .append("\nCPF: ").append(getCpfPseudoanonimizado())
          .append("\nCNH: ").append(cnh)
          .append("\nTelefone: ").append(telefone)
          .append("\nEmail: ").append(email)
          .append("\nEndereço: ").append(rua).append(", ").append(numero)
          .append(" - ").append(cidade).append(" / ").append(estado)
          .append(" - CEP: ").append(cep)
          .append("\nContato de Emergência: ").append(contatoEmergencia)
          .append("\nStatus: ").append(status);

        sb.append("\nVeículos: ");
        if (listaVeiculos.isEmpty()) {
            sb.append("Nenhum veículo cadastrado.");
        } else {
            for (Veiculo v : listaVeiculos) {
                sb.append("\n  - ").append(v.toString());
            }
        }

        sb.append("\nOrdens de Serviço:");
        if (ordensDeServico.isEmpty()) {
            sb.append(" Nenhuma OS associada.");
        } else {
            for (OrdemServico os : ordensDeServico) {
                sb.append("\n  - ").append(os.toString());
            }
        }
        
        sb.append("\nVendas Realizadas:");
        if (vendas.isEmpty()) {
            sb.append(" Nenhuma venda associada.");
        } else {
            for (Venda v : vendas) {
                sb.append("\n  - ").append(v.toString());
            }
        }

        return sb.toString();
    }
}