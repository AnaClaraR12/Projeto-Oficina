package com.mycompany.oficina.entidade;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;

/**
 * Ferramentas utilitárias para salvar e carregar dados em arquivos JSON.
 * Usa a biblioteca Gson para converter objetos Java em JSON e vice-versa,
 * facilitando o armazenamento e recuperação de dados persistentes.
 *
 * @author Ana Clara e Pedro
 * @version 1.0
 */
public class JsonUtil {
    
    // Serializador e deserializador para LocalDateTime
    private static class LocalDateTimeAdapter implements JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {
        private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

        @Override
        public JsonElement serialize(LocalDateTime src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(formatter.format(src));
        }

        @Override
        public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            return LocalDateTime.parse(json.getAsString(), formatter);
        }
    }

    // Serializador e deserializador para LocalDate
    private static class LocalDateAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {
        private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

        @Override
        public JsonElement serialize(LocalDate src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(formatter.format(src));
        }

        @Override
        public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            return LocalDate.parse(json.getAsString(), formatter);
        }
    }

    // Adaptador personalizado para OrdemServico
    private static class OrdemServicoAdapter implements JsonDeserializer<OrdemServico> {
        @Override
        public OrdemServico deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            try {
                var jsonObject = json.getAsJsonObject();
                
                int idOrdemServico = jsonObject.get("idOrdemServico").getAsInt();
                Cliente cliente = context.deserialize(jsonObject.get("cliente"), Cliente.class);
                Veiculo veiculo = context.deserialize(jsonObject.get("veiculo"), Veiculo.class);
                Funcionario mecanico = context.deserialize(jsonObject.get("mecanico"), Funcionario.class);
                String status = jsonObject.get("status").getAsString();
                String dataAbertura = jsonObject.get("dataAbertura").getAsString();
                double valorTotal = jsonObject.get("valorTotal").getAsDouble();
                
                // Converter listas de serviços
                List<Servico> servicos = new ArrayList<>();
                if (jsonObject.has("servicos")) {
                    var servicosArray = jsonObject.getAsJsonArray("servicos");
                    for (var servicoElement : servicosArray) {
                        Servico servico = context.deserialize(servicoElement, Servico.class);
                        servicos.add(servico);
                    }
                }
                
                // Converter listas de peças utilizadas
                List<Peca> pecasUtilizadas = new ArrayList<>();
                if (jsonObject.has("pecasUtilizadas")) {
                    var pecasArray = jsonObject.getAsJsonArray("pecasUtilizadas");
                    for (var pecaElement : pecasArray) {
                        Peca peca = context.deserialize(pecaElement, Peca.class);
                        pecasUtilizadas.add(peca);
                    }
                }
                
                OrdemServico os = new OrdemServico(idOrdemServico, cliente, veiculo, mecanico, servicos, pecasUtilizadas, status, dataAbertura, valorTotal);
                return os;
            } catch (Exception e) {
                throw new JsonParseException("Erro ao deserializar OrdemServico: " + e.getMessage(), e);
            }
        }
    }

    // Adaptador personalizado para Venda
    private static class VendaAdapter implements JsonDeserializer<Venda> {
        @Override
        public Venda deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            try {
                var jsonObject = json.getAsJsonObject();
                
                // Deserializar cliente como objeto completo
                Cliente cliente = context.deserialize(jsonObject.get("cliente"), Cliente.class);
                
                double total = jsonObject.get("total").getAsDouble();
                String dataHoraVenda = jsonObject.get("dataHoraVenda").getAsString();
                
                // Criar venda usando construtor padrão
                Venda venda = new Venda(cliente);
                
                // Deserializar peças vendidas e adicionar à venda
                if (jsonObject.has("pecasVendidas")) {
                    var pecasArray = jsonObject.getAsJsonArray("pecasVendidas");
                    for (var pecaElement : pecasArray) {
                        var pecaObject = pecaElement.getAsJsonObject();
                        int id = pecaObject.get("id").getAsInt();
                        String nome = pecaObject.get("nome").getAsString();
                        double preco = pecaObject.get("preco").getAsDouble();
                        int quantidade = pecaObject.get("quantidade").getAsInt();
                        
                        // Criar peça temporária para adicionar à venda
                        Peca pecaTemp = new Peca(id, nome, preco, quantidade);
                        venda.adicionarPeca(pecaTemp, quantidade);
                    }
                }
                
                return venda;
            } catch (Exception e) {
                throw new JsonParseException("Erro ao deserializar Venda: " + e.getMessage(), e);
            }
        }
    }

    // Adaptador personalizado para PontoFuncionario
    private static class PontoFuncionarioAdapter implements JsonDeserializer<PontoFuncionario> {
        @Override
        public PontoFuncionario deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            try {
                var jsonObject = json.getAsJsonObject();
                
                // Lidar com funcionario sendo string ou objeto
                String funcionarioNome;
                JsonElement funcionarioElement = jsonObject.get("funcionario");
                if (funcionarioElement.isJsonPrimitive()) {
                    // Se for string (nome do funcionário)
                    funcionarioNome = funcionarioElement.getAsString();
                } else {
                    // Se for objeto JSON, extrair o nome
                    var funcionarioObj = funcionarioElement.getAsJsonObject();
                    funcionarioNome = funcionarioObj.get("nome").getAsString();
                }
                
                String entradaStr = jsonObject.get("entrada").getAsString();
                String saidaStr = jsonObject.has("saida") ? jsonObject.get("saida").getAsString() : null;
                
                // Criar funcionário temporário com o nome correto
                Funcionario funcionario = new Funcionario(funcionarioNome, "temp@temp.com", "Funcionário", "temp");
                
                // Converter strings para LocalDateTime
                LocalDateTime entrada = LocalDateTime.parse(entradaStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                LocalDateTime saida = saidaStr != null ? LocalDateTime.parse(saidaStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME) : null;
                
                return new PontoFuncionario(funcionario, entrada, saida);
            } catch (Exception e) {
                throw new JsonParseException("Erro ao deserializar PontoFuncionario: " + e.getMessage(), e);
            }
        }
    }

    // Adaptador personalizado para Usuario (pode ser Administrador ou Funcionario)
    private static class UsuarioAdapter implements JsonDeserializer<Usuario> {
        @Override
        public Usuario deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            try {
                var jsonObject = json.getAsJsonObject();
                
                String nome = jsonObject.get("nome").getAsString();
                String email = jsonObject.get("email").getAsString();
                String senhaCriptografada = jsonObject.get("senhaCriptografada").getAsString();
                
                // Verificar se é Administrador (tem campo nivelAcesso)
                if (jsonObject.has("nivelAcesso")) {
                    int nivelAcesso = jsonObject.get("nivelAcesso").getAsInt();
                    
                    // Criar administrador com senha temporária (será substituída pela criptografada)
                    Administrador admin = new Administrador(nome, email, "temp", nivelAcesso);
                    
                    // Definir a senha criptografada diretamente usando reflexão
                    try {
                        java.lang.reflect.Field senhaField = Administrador.class.getDeclaredField("senhaCriptografada");
                        senhaField.setAccessible(true);
                        senhaField.set(admin, senhaCriptografada);
                    } catch (Exception e) {
                        throw new JsonParseException("Erro ao definir senha criptografada: " + e.getMessage(), e);
                    }
                    
                    return admin;
                }
                // Caso contrário, é Funcionario (tem campo cargo)
                else if (jsonObject.has("cargo")) {
                    String cargo = jsonObject.get("cargo").getAsString();
                    
                    // Criar funcionário com senha temporária (será substituída pela criptografada)
                    Funcionario funcionario = new Funcionario(nome, email, cargo, "temp");
                    
                    // Definir a senha criptografada diretamente usando reflexão
                    try {
                        java.lang.reflect.Field senhaField = Funcionario.class.getDeclaredField("senhaCriptografada");
                        senhaField.setAccessible(true);
                        senhaField.set(funcionario, senhaCriptografada);
                    } catch (Exception e) {
                        throw new JsonParseException("Erro ao definir senha criptografada: " + e.getMessage(), e);
                    }
                    
                    return funcionario;
                }
                else {
                    throw new JsonParseException("Usuário deve ter campo 'nivelAcesso' (Administrador) ou 'cargo' (Funcionario)");
                }
            } catch (Exception e) {
                throw new JsonParseException("Erro ao deserializar Usuario: " + e.getMessage(), e);
            }
        }
    }

    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .registerTypeAdapter(OrdemServico.class, new OrdemServicoAdapter())
            .registerTypeAdapter(Venda.class, new VendaAdapter())
            .registerTypeAdapter(Usuario.class, new UsuarioAdapter())
            .registerTypeAdapter(PontoFuncionario.class, new PontoFuncionarioAdapter())
            .create();
    
    /**
     * Obtém o caminho base para os arquivos JSON.
     * Tenta diferentes estratégias para encontrar o diretório correto.
     * 
     * @return o caminho base para os arquivos JSON
     */
    private static String getJsonBasePath() {
        // Estratégia 1: Tentar usar o diretório de trabalho atual + src/main/java/com/mycompany/oficina/json
        String currentDir = System.getProperty("user.dir");
        String path1 = currentDir + File.separator + "src" + File.separator + "main" + 
                      File.separator + "java" + File.separator + "com" + File.separator + 
                      "mycompany" + File.separator + "oficina" + File.separator + "json";
        
        if (new File(path1).exists()) {
            return path1;
        }
        
        // Estratégia 2: Tentar usar o diretório do projeto (voltando alguns níveis)
        String path2 = currentDir + File.separator + "Oficina-Oficial" + File.separator + 
                      "src" + File.separator + "main" + File.separator + "java" + 
                      File.separator + "com" + File.separator + "mycompany" + 
                      File.separator + "oficina" + File.separator + "json";
        
        if (new File(path2).exists()) {
            return path2;
        }
        
        // Estratégia 3: Tentar usar ClassLoader para encontrar o recurso
        try {
            URL resourceUrl = JsonUtil.class.getClassLoader().getResource("com/mycompany/oficina/json");
            if (resourceUrl != null) {
                return new File(resourceUrl.toURI()).getAbsolutePath();
            }
        } catch (Exception e) {
            // Ignora exceção e continua para próxima estratégia
        }
        
        // Estratégia 4: Criar o diretório json no diretório atual se não existir
        String path4 = currentDir + File.separator + "json";
        File jsonDir = new File(path4);
        if (!jsonDir.exists()) {
            jsonDir.mkdirs();
        }
        return path4;
    }

    /**
     * Carrega uma lista de objetos de um arquivo JSON.
     * 
     * @param <T> o tipo dos objetos na lista
     * @param caminhoArquivo o caminho do arquivo JSON a ser lido (nome do arquivo apenas)
     * @param clazz a classe dos objetos na lista
     * @return a lista de objetos lida do arquivo, ou null se houver erro
     */
    public static <T> List<T> lerLista(String caminhoArquivo, Class<T> clazz) {
        String caminhoCompleto = getJsonBasePath() + File.separator + new File(caminhoArquivo).getName();
        
        try (FileReader reader = new FileReader(caminhoCompleto)) {
            Type tipoLista = TypeToken.getParameterized(List.class, clazz).getType();
            return gson.fromJson(reader, tipoLista);
        } catch (IOException e) {
            System.err.println("Erro ao ler arquivo JSON: " + caminhoCompleto + ": " + e.getMessage());
            return null;
        }
    }

    /**
     * Salva uma lista de objetos em um arquivo JSON.
     * 
     * @param <T> o tipo dos objetos na lista
     * @param caminhoArquivo o caminho do arquivo JSON onde salvar (nome do arquivo apenas)
     * @param lista a lista de objetos a ser salva
     */
    public static <T> void salvarLista(String caminhoArquivo, List<T> lista) {
        String caminhoCompleto = getJsonBasePath() + File.separator + new File(caminhoArquivo).getName();
        
        try (FileWriter writer = new FileWriter(caminhoCompleto)) {
            gson.toJson(lista, writer);
        } catch (IOException e) {
            System.err.println("Erro ao salvar arquivo JSON: " + caminhoCompleto + ": " + e.getMessage());
        }
    }
} 