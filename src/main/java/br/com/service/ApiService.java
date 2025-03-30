package br.com.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jdi.InternalException;

import br.com.dto.TemperatureDto;

public class ApiService {
    private static final String API_KEY = "b48067568a94320d092dbb6ad8596026";
    private static final String URL_BASE = "http://api.weatherstack.com/current";

    String city = "blumenau";

    TemperatureDto temperatureDto = new TemperatureDto();

    public TemperatureDto tempo() throws IOException, InternalException {
        try {
             // CRIA A REQUISIÇÃO HTTP
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(URL_BASE + "?access_key=" + API_KEY + "&query=" + city)).build();
             // ENVIA A REQUISIÇÃO
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            ObjectMapper mapper = new ObjectMapper();

            temperatureDto = mapper.readValue(response.body(), TemperatureDto.class );
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return temperatureDto;
    }
}
