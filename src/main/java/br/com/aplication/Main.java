package br.com.aplication;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.dto.TemperatureDto;
import io.github.cdimascio.dotenv.Dotenv;

public class Main {
    // public final static String API_URL = "http://api.temperatureDtostack.com/current?access_key=" + API_KEY + "&query=";

    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        Dotenv dotenv = Dotenv.configure().directory("C:\\Users\\luizl\\OneDrive\\Documentos\\GitHub\\professorOnline2-0\\professor-online2.0\\AppWeatherForecast\\AppWeatherForecast").load();

        String API_K = dotenv.get("API_KEY");
        String API_U = dotenv.get("API_URL");
        System.out.println(API_U);
        System.out.println(API_K);

        System.out.print("Digite a cidade que deseja consultar: "); String city = sc.next();

        String URL_FINAL = API_U + city;
        System.out.println(URL_FINAL);
        try {
            // 1. Faz a requisição HTTP
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_FINAL))
                .GET()
                .build();

            HttpResponse<String> response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());

            // 2. Verifica se a resposta foi bem-sucedida (código 200)
            if (response.statusCode() != 200) {
                System.err.println("Erro na API: " + response.statusCode());
                return;
            }

            // 3. Extrai apenas os campos necessários do JSON
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.body());

            TemperatureDto temperatureDto = new TemperatureDto();
            temperatureDto.setCity(root.path("location").path("name").asText());
            temperatureDto.setRegion(root.path("location").path("region").asText());
            temperatureDto.setCountry(root.path("location").path("country").asText());
            temperatureDto.setTemperature(root.path("current").path("temperature").asInt());
            temperatureDto.setFeelsLike(root.path("current").path("feelslike").asInt());
            temperatureDto.setTimeZone(root.path("location").path("timezone_id").asText());

            // 4. Imprime os dados formatados
            System.out.println(temperatureDto);

        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
