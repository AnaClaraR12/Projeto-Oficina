<div id="top"></div>

<!--Banner do Trabalho-->
<h2 align="center" >
  <img src="./BannerPOO.png" alt="POO logo" title="POO" />
</h2>

<!--Descrição do Trabalho-->
<h4 align="center">Projeto Prático da Matéria de Programação Orientada a Objetos</h4>


<!--Sobre o projeto-->
## :computer: Sobre o projeto
Bem-vindos ao repositório do projeto de uma Oficina Mecanica implementada em Java.

Projeto Documentado com Diagrama de Classe, Casos de Uso e Diagramas de Sequencia.

Parte Escrita do Projeto em LaTeX:
[LaTeX](https://www.overleaf.com/read/jhymtfvbywmb#6e50a7)

Foco do Projeto:

Conceitos e uso de POO, Arquivos JSON e Padrões de projetos.

Universidade Federal dos Vales do Jequitinhonha e Mucuri - (UFVJM) 2025/1

>Autores: Pedro Augusto Hackner Bittencourt e Ana Clara Azevedo.

## Contato: pedro.hackner@ufvjm.edu.br, clara.azevedo@ufvjm.edu.br.

<p align="right">(<a href="#top">back to top</a>)</p>

## :rocket: Como executar o projeto da Oficina
Antes de começar, você vai precisar ter instalado em sua máquina as seguintes ferramentas: [Git](https://git-scm.com/) , [NetBeans](https://netbeans.apache.org/front/main/index.html) e [JDK](https://www.oracle.com/java/technologies/downloads/?er=221886).

<!--Running session-->
### :package: Instalando
```bash
# Clone este repositório
$ git clone https://github.com/PedroAHB/Projeto-Oficina-PedroV1
```

### ⚡ Rodando a aplicação
```bash
# Execute o arquivo principal via Netbeans
$ Acesse o menu interativo via Console
```

<!--Menu do Programa-->
## 📰: Descrição do Menu
<h2 align="center" >


## 📋 RESUMO GERAL

| Entidade | Tipo | Formato | Exemplos | Geração |
|----------|------|---------|----------|---------|
| **Cliente** | String | UUID | `550e8400-e29b-41d4-a716-446655440000` | Automática |
| **Peca** | int | Sequencial | `1, 2, 3, 10, 25` | Automática/Manual |
| **Servico** | int | Sequencial | `1, 2, 3, 10, 25` | Manual |
| **OrdemServico** | int | Sequencial | `1, 2, 3, 10, 25` | Automática |
| **Agendamento** | int | Sequencial | `1, 2, 3, 10, 25` | Automática |
| **Elevador** | int | Sequencial | `1, 2, 3, 10, 25` | Manual |
| **Extrato** | String | Alfanumérico | `EXT001, EXT002` | Manual |

---

## 🔍 DETALHAMENTO POR ENTIDADE

### 1. **CLIENTE**
- **Tipo**: `String`
- **Formato**: UUID (Universally Unique Identifier)
- **Exemplos**: 
  - `550e8400-e29b-41d4-a716-446655440000`
  - `6ba7b810-9dad-11d1-80b4-00c04fd430c8`
- **Geração**: Automática (UUID.randomUUID())
- **Validação**: Não pode ser nulo ou vazio
- **Uso**: Identificação única do cliente no sistema

### 2. **PEÇA**
- **Tipo**: `int`
- **Formato**: Número inteiro sequencial
- **Exemplos**: `1, 2, 3, 10, 25, 100`
- **Geração**: 
  - **Automática**: `new Peca("Nome", preco, quantidade)`
  - **Manual**: `new Peca(id, "Nome", preco, quantidade)`
- **Validação**: Deve ser > 0
- **Contador**: `contadorPecas` (estático)
- **Métodos úteis**:
  - `Peca.getProximoId()` - próximo ID disponível
  - `Peca.getContadorPecas()` - total de peças criadas

### 3. **SERVIÇO**
- **Tipo**: `int`
- **Formato**: Número inteiro sequencial
- **Exemplos**: `1, 2, 3, 10, 25, 100`
- **Geração**: Manual (deve ser especificado)
- **Validação**: Deve ser > 0
- **Uso**: Identificação única do serviço

### 4. **ORDEM DE SERVIÇO**
- **Tipo**: `int`
- **Formato**: Número inteiro sequencial
- **Exemplos**: `1, 2, 3, 10, 25, 100`
- **Geração**: Automática (`contadorOrdens++`)
- **Validação**: Gerado automaticamente
- **Uso**: Identificação única da OS

### 5. **AGENDAMENTO**
- **Tipo**: `int`
- **Formato**: Número inteiro sequencial
- **Exemplos**: `1, 2, 3, 10, 25, 100`
- **Geração**: Automática
- **Validação**: Gerado automaticamente
- **Uso**: Identificação única do agendamento

### 6. **ELEVADOR**
- **Tipo**: `int`
- **Formato**: Número inteiro sequencial
- **Exemplos**: `1, 2, 3, 10, 25, 100`
- **Geração**: Manual
- **Validação**: Deve ser > 0
- **Uso**: Identificação única do elevador

### 7. **EXTRATO**
- **Tipo**: `String`
- **Formato**: Alfanumérico com prefixo
- **Exemplos**: `EXT001, EXT002, EXT010`
- **Geração**: Manual
- **Validação**: Não pode ser nulo ou vazio
- **Uso**: Identificação única do extrato

---

## 🛠️ COMO USAR

### **Para Peças (Recomendado - Automático)**
```java
// ✅ RECOMENDADO - ID gerado automaticamente
Peca peca1 = new Peca("Óleo de Motor", 25.00, 50);
Peca peca2 = new Peca("Filtro de Óleo", 15.00, 30);
// IDs: 1, 2 (gerados automaticamente)
```

### **Para Peças (Manual - Apenas quando necessário)**
```java
// ⚠️ APENAS quando precisar especificar ID
Peca peca1 = new Peca(100, "Óleo de Motor", 25.00, 50);
Peca peca2 = new Peca(101, "Filtro de Óleo", 15.00, 30);
```

### **Para Serviços**
```java
// ✅ ID deve ser especificado manualmente
Servico servico1 = new Servico(1, "Troca de Óleo", 50.00, "Manutenção");
Servico servico2 = new Servico(2, "Troca de Filtro", 30.00, "Manutenção");
```

### **Para Ordem de Serviço**
```java
// ✅ ID gerado automaticamente
OrdemServico os = new OrdemServico(cliente, veiculo, mecanico);
// ID: gerado automaticamente
```

### **Para Cliente**
```java
// ✅ ID gerado automaticamente (UUID)
Cliente cliente = new Cliente("João", "12345678901", "12345678901", 
                             "99999-0000", "joao@email.com", 
                             "Rua A", "10", "Cidade", "Estado", "12345-678");
// ID: UUID gerado automaticamente
```

---

## ⚠️ REGRAS IMPORTANTES

1. **IDs Sequenciais**: Para peças, serviços, OS e agendamentos, use números sequenciais (1, 2, 3...)
2. **IDs Únicos**: Cada ID deve ser único dentro de sua categoria
3. **Validação**: IDs devem ser sempre > 0 para tipos int
4. **Geração Automática**: Use sempre que possível para evitar conflitos
5. **UUID para Clientes**: Garante unicidade global

---

## 🔧 MÉTODOS ÚTEIS

### **Para Peças**
```java
// Verificar próximo ID
int proximoId = Peca.getProximoId();

// Verificar total de peças
int totalPecas = Peca.getContadorPecas();
```

### **Para Ordem de Serviço**
```java
// Obter ID da OS
int idOS = ordemServico.getIdOrdemServico();
```

---

## 📝 EXEMPLOS PRÁTICOS

### **Cadastro de Peças no Estoque**
```java
List<Peca> estoque = new ArrayList<>();
estoque.add(new Peca("Óleo de Motor", 25.00, 50));        // ID: 1
estoque.add(new Peca("Filtro de Óleo", 15.00, 30));       // ID: 2
estoque.add(new Peca("Pastilha de Freio", 45.00, 40));    // ID: 3
```

### **Cadastro de Serviços**
```java
List<Servico> servicos = new ArrayList<>();
servicos.add(new Servico(1, "Troca de Óleo", 50.00, "Manutenção"));
servicos.add(new Servico(2, "Troca de Filtro", 30.00, "Manutenção"));
servicos.add(new Servico(3, "Alinhamento", 60.00, "Suspensão"));
```

### **Criação de Ordem de Serviço**
```java
OrdemServico os = new OrdemServico(cliente, veiculo, mecanico);
// ID gerado automaticamente: 1, 2, 3, etc.
```

---

**Última atualização**: Junho 2025
**Versão**: 1.0 
</h2>
<p align="right">(<a href="#top">back to top</a>)</p>
