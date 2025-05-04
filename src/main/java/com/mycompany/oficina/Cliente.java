/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oficina;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Representa um cliente da oficina, com dados pessoais, endereço,
 * veículos associados, ordens de serviço e contato de emergência.
 * 
 * A classe também possui controle de status do cliente.
 * 
 * @author Ana Clara e Pedro
 * @version 1.0
 */
public class Cliente {

    // Dados pessoais
    private String nome;
    private String cpf;
    private String senha;
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
    private final List<Venda> vendas = new ArrayList<>();


    /**
     * Enumeração que define o status de um cliente.
     */
    public enum StatusCliente { ATIVO, BLOQUEADO, PREFERENCIAL }

    private StatusCliente status;

    /**
     * Construtor completo da classe {@code Cliente}.
     *
     * @param nome o nome do cliente
     * @param cpf o CPF do cliente
     * @param senha a senha de acesso do cliente
     * @param cnh a CNH do cliente
     * @param telefone o telefone do cliente
     * @param email o email do cliente
     * @param rua o nome da rua do endereço
     * @param numero o número da residência
     * @param cidade a cidade do cliente
     * @param estado o estado do cliente
     * @param cep o código postal (CEP)
     */
    public Cliente(String nome, String cpf, String senha, String cnh, String telefone, String email,
                   String rua, String numero, String cidade, String estado, String cep) {
        setNome(nome);
        setCpf(cpf);
        setSenha(senha);
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
        this.status = StatusCliente.ATIVO;
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

    /** @param cpf define o CPF do cliente (apenas números) */
    public void setCpf(String cpf) {
        if (cpf == null || !cpf.matches("\\d{11}")) throw new IllegalArgumentException("CPF inválido.");
        this.cpf = cpf;
    }

    /** @return a senha do cliente */
    public String getSenha() {
        return senha;
    }

    /** @param senha define a senha do cliente */
    public void setSenha(String senha) {
        if (senha == null || senha.length() < 4) throw new IllegalArgumentException("Senha inválida.");
        this.senha = senha;
    }

    /** @return a CNH do cliente */
    public String getCnh() {
        return cnh;
    }

    /** @param cnh define a CNH do cliente */
    public void setCnh(String cnh) {
        if (cnh == null || !cnh.matches("\\d{11}")) throw new IllegalArgumentException("CNH inválida.");
        this.cnh = cnh;
    }

    /** @return o telefone do cliente */
    public String getTelefone() {
        return telefone;
    }

    /** @param telefone define o telefone do cliente */
    public void setTelefone(String telefone) {
        if (telefone == null || !telefone.matches("[0-9 \\-()+]{8,15}")) {
            throw new IllegalArgumentException("Telefone inválido.");
        }
        this.telefone = telefone;
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
        if (cep == null || !cep.matches("\\d{5}-?\\d{3}")) {
            throw new IllegalArgumentException("CEP inválido.");
        }
        this.cep = cep;
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

    /** @return lista de veículos associados ao cliente */
    public List<Veiculo> getListaVeiculos() {
        return new ArrayList<>(listaVeiculos);
    }

    /**
     * Adiciona um novo veículo à lista de veículos do cliente.
     *
     * @param veiculo o veículo a ser adicionado
     */
    public void adicionarVeiculo(Veiculo veiculo) {
        if (veiculo == null) throw new IllegalArgumentException("Veículo inválido.");
        listaVeiculos.add(veiculo);
    }

    /**
     * Remove um veículo da lista do cliente.
     *
     * @param veiculo o veículo a ser removido
     * @return true se o veículo foi removido, false caso contrário
     */
    public boolean removerVeiculo(Veiculo veiculo) {
        return listaVeiculos.remove(veiculo);
    }

    /** @return lista de ordens de serviço do cliente */
    public List<OrdemServico> getOrdensDeServico() {
        return new ArrayList<>(ordensDeServico);
    }

    /**
     * Adiciona uma ordem de serviço à lista do cliente.
     *
     * @param os a ordem de serviço a ser adicionada
     */
    public void adicionarOrdemDeServico(OrdemServico os) {
        if (os == null) throw new IllegalArgumentException("Ordem de serviço inválida.");
        ordensDeServico.add(os);
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
    
    public void adicionarVenda(Venda venda) {
        if (venda != null) {
        vendas.add(venda);
        }
    }

    public List<Venda> getVendas() {
        return new ArrayList<>(vendas);
    }


    /**
     * Retorna uma representação textual completa do cliente.
     *
     * @return string com todos os dados do cliente
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cliente: ").append(nome)
          .append("\nCPF: ").append(cpf)
          .append("\nCNH: ").append(cnh)
          .append("\nTelefone: ").append(telefone)
          .append("\nEmail: ").append(email)
          .append("\nEndereço: ").append(rua).append(", ").append(numero)
          .append(" - ").append(cidade).append(" / ").append(estado)
          .append(" - CEP: ").append(cep)
          .append("\nContato de Emergência: ").append(contatoEmergencia)
          .append("\nStatus: ").append(status)
          .append("\nVeículos: ");
        for (Veiculo v : listaVeiculos) {
            sb.append("\n  ").append(v.toString());
        }
        sb.append("\nOrdens de Serviço:");
        for (OrdemServico os : ordensDeServico) {
            sb.append("\n  ").append(os.toString());
        }
        return sb.toString();
    }
}
