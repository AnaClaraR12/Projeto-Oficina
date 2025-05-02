/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oficina;

/**
 *
 * @author Ana Clara
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Representa um Cliente da oficina com dados pessoais, endereços detalhados,
 * veículos, ordens de serviço, status e contato de emergência.
 */
public class Cliente {
    // Dados pessoais
    private String nome;
    private String telefone;
    private String email;
    private String cpfPseudoAnonimizado;

    // Endereço detalhado
    private String rua;
    private String numero;
    private String cidade;
    private String estado;
    private String cep;

    // Contato de emergência opcional
    private String contatoEmergencia;

    // Lista de veículos do cliente
    private final List<Veiculo> listaVeiculos;

    // Histórico de ordens de serviço relacionadas ao cliente
    private final List<OrdemServico> ordensDeServico;

    // Status do cliente (ex: ativo, bloqueado, preferencial)
    public enum StatusCliente { ATIVO, BLOQUEADO, PREFERENCIAL }
    private StatusCliente status;

    /**
     * Construtor completo para Cliente.
     */
    public Cliente(String nome, String telefone, String email, String cpfPseudoAnonimizado,
                   String rua, String numero, String cidade, String estado, String cep) {
        setNome(nome);
        setTelefone(telefone);
        setEmail(email);
        setCpfPseudoAnonimizado(cpfPseudoAnonimizado);
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

    // Getters e setters com validação

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio.");
        }
        this.nome = nome.trim();
    }

    public String getTelefone() {
        return telefone;
    }

    /**
     * Valida formato simples para telefone (ex: somente dígitos e opcional hífen ou espaço).
     */
    public void setTelefone(String telefone) {
        if (telefone == null || !telefone.matches("[0-9 \\-()+]{8,15}")) {
            throw new IllegalArgumentException("Telefone inválido.");
        }
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    /**
     * Valida e-mail em formato básico usando expressão regular.
     */
    public void setEmail(String email) {
        if (email == null || !Pattern.compile("^[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,}$").matcher(email).matches()) {
            throw new IllegalArgumentException("Email inválido.");
        }
        this.email = email;
    }

    public String getCpfPseudoAnonimizado() {
        return cpfPseudoAnonimizado;
    }

    /**
     * Exige CPF pseudo-anonimizado em formato similar a '123.***.789-00'
     */
    public void setCpfPseudoAnonimizado(String cpfPseudoAnonimizado) {
        if (cpfPseudoAnonimizado == null || !cpfPseudoAnonimizado.matches("\\d{3}\\.\\*{3}\\.\\d{3}-\\d{2}")) {
            throw new IllegalArgumentException("CPF pseudo-anonimizado inválido. Ex: 123.***.789-00");
        }
        this.cpfPseudoAnonimizado = cpfPseudoAnonimizado;
    }

    // Novo método getCpf para retornar o cpfPseudoAnonimizado de forma simplificada
    public String getCpf() {
        return cpfPseudoAnonimizado;
    }

    // Endereço detalhado - campos simples, apenas trim para set

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        if (rua == null || rua.trim().isEmpty()) {
            throw new IllegalArgumentException("Rua não pode ser vazia.");
        }
        this.rua = rua.trim();
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        if (numero == null || numero.trim().isEmpty()) {
            throw new IllegalArgumentException("Número não pode ser vazio.");
        }
        this.numero = numero.trim();
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        if (cidade == null || cidade.trim().isEmpty()) {
            throw new IllegalArgumentException("Cidade não pode ser vazia.");
        }
        this.cidade = cidade.trim();
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        if (estado == null || estado.trim().isEmpty()) {
            throw new IllegalArgumentException("Estado não pode ser vazio.");
        }
        this.estado = estado.trim();
    }

    public String getCep() {
        return cep;
    }

    /**
     * Valida formato simples de CEP brasileiro (xxxxx-xxx ou xxxxxxxx)
     */
    public void setCep(String cep) {
        if (cep == null || !cep.matches("\\d{5}-?\\d{3}")) {
            throw new IllegalArgumentException("CEP inválido.");
        }
        this.cep = cep;
    }

    public String getContatoEmergencia() {
        return contatoEmergencia;
    }

    public void setContatoEmergencia(String contatoEmergencia) {
        this.contatoEmergencia = contatoEmergencia; // valor opcional, pode ser null ou vazio
    }

    public List<Veiculo> getListaVeiculos() {
        return new ArrayList<>(listaVeiculos); // retorno cópia para preservar encapsulamento
    }

    /**
     * Adiciona um veículo ao cliente.
     * @param veiculo Veículo a adicionar, não pode ser nulo.
     */
    public void adicionarVeiculo(Veiculo veiculo) {
        if (veiculo == null) {
            throw new IllegalArgumentException("Veículo não pode ser nulo.");
        }
        this.listaVeiculos.add(veiculo);
    }

    /**
     * Remove um veículo do cliente.
     * @param veiculo Veículo a remover.
     * @return true se removido, false se não encontrado.
     */
    public boolean removerVeiculo(Veiculo veiculo) {
        return this.listaVeiculos.remove(veiculo);
    }

    public List<OrdemServico> getOrdensDeServico() {
        return new ArrayList<>(ordensDeServico); // cópia para encapsulamento
    }

    /**
     * Adiciona uma ordem de serviço ao histórico do cliente.
     * @param os OrdemDeServico a adicionar.
     */
    public void adicionarOrdemDeServico(OrdemServico os) {
        if (os == null) {
            throw new IllegalArgumentException("Ordem de Serviço não pode ser nula.");
        }
        this.ordensDeServico.add(os);
    }

    /**
     * Calcula o total a pagar do cliente somando o valor de todas as ordens de serviço.
     * @return total em double.
     */
    public double calcularTotalConta() {
        double total = 0.0;
        for (OrdemServico os : ordensDeServico) {
            total += os.getValorTotal();
        }
        return total;
    }

    public StatusCliente getStatus() {
        return status;
    }

    public void setStatus(StatusCliente status) {
        if (status == null) {
            throw new IllegalArgumentException("Status não pode ser nulo.");
        }
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cliente{")
          .append("nome=").append(nome)
          .append(", telefone=").append(telefone)
          .append(", email=").append(email)
          .append(", cpfPseudoAnonimizado=").append(cpfPseudoAnonimizado)
          .append(", endereço=").append(rua).append(", ").append(numero)
          .append(", ").append(cidade).append(", ").append(estado)
          .append(", CEP=").append(cep)
          .append(", contatoEmergencia=").append(contatoEmergencia)
          .append(", status=").append(status)
          .append(", veículos=[");

        for (Veiculo v : listaVeiculos) {
            sb.append("\n  ").append(v.toString());
        }
        sb.append("\n], ordemServico=[");

        for (OrdemServico os : ordensDeServico) {
            sb.append("\n  ").append(os.toString());
        }
        sb.append("\n]}");

        return sb.toString();
    }
}
