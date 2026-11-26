
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.lang.reflect.Type;
import java.util.List;

class LocalDateAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {

    @Override
    public JsonElement serialize(LocalDate data, Type tipo, JsonSerializationContext contexto) {
        return new JsonPrimitive(data.toString());
    }

    @Override
    public LocalDate deserialize(JsonElement json, Type tipo, JsonDeserializationContext contexto) {
        return LocalDate.parse(json.getAsString());
    }
}


public class GerenciarArquivos {
    private static final JsonDeserializer<Transacao> ADAPTADOR_TRANSACAO =
            (json, tipo, contexto) -> {

                JsonObject objeto = json.getAsJsonObject();
                JsonElement campoTipo = objeto.get("tipo");

                if (campoTipo == null || campoTipo.isJsonNull()) {
                    throw new JsonParseException("Transação sem o campo 'tipo'.");
                }

                switch (campoTipo.getAsString()) {

                    case "Receita":
                        return contexto.deserialize(json, Receita.class);

                    case "Despesa":
                        return contexto.deserialize(json, Despesa.class);

                    case "Transferencia":
                        return contexto.deserialize(json, Transferencia.class);

                    default:
                        throw new JsonParseException(
                                "Tipo de transação desconhecido: " + campoTipo.getAsString());
                }
            };

    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .registerTypeAdapter(Transacao.class, ADAPTADOR_TRANSACAO)
            .create();

    public void salvarDados(List<Conta> listaContas) {

        try (FileWriter escritor = new FileWriter("Arquivo.json")) {

            gson.toJson(listaContas, escritor);

        } catch (IOException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        }
    }

    public List<Conta> puxarDados() {

        try (FileReader leitor = new FileReader("Arquivo.json")) {

            Type tipo = new TypeToken<List<Conta>>() {}.getType();

            List<Conta> contas = gson.fromJson(leitor, tipo);

            if (contas == null) {
                throw new ExcecoesFinanceiras.ArquivoDadosException(
                        "ArquivoDadosException: o arquivo não existe, está estragado ou tem formato errado.");
            }
            return contas;

        } catch (FileNotFoundException e) {
            throw new ExcecoesFinanceiras.ArquivoDadosException(
                    "ArquivoDadosException: o arquivo não existe, está estragado ou tem formato errado.", e);
        } catch (IOException | JsonParseException | DateTimeParseException e) {
            throw new ExcecoesFinanceiras.ArquivoDadosException(
                    "ArquivoDadosException: o arquivo não existe, está estragado ou tem formato errado.", e);
        }
    }
}
