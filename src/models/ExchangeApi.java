package models;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ExchangeApi {
    String apiKey = "ba71d132b8f89c183dcfaf1f";
    String link = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/USD";

    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(link))
            .build();

    Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .setFieldNamingPolicy(com.google.gson.FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create();

    public Conversor callApi() {
        try {
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            return new Conversor(response.body());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}