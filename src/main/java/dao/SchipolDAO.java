package dao;

import Packate.Data;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import Packate.Flight;

public class SchipolDAO {

    public static Data getData() {

        try {

            String BASE_URL = "https://api.schiphol.nl/public-flights/flights";
            String id = "3d254642";
            String key = "ebb319a6ced526a4a48c630370b50c0e";
            String version = "v4";

            java.net.http.HttpResponse<String> response;
            ObjectMapper mapper = new ObjectMapper();

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(BASE_URL))
                    .header("app_id", id)
                    .header("app_key", key)
                    .header("ResourceVersion", version)
                    .GET().build();
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());

            JsonNode node = mapper.readValue(response.body(), JsonNode.class);

            Data data = mapper.readValue(response.body(), Data.class);
            return data;

        } catch (URISyntaxException | InterruptedException | IOException e) {
            e.printStackTrace();
            System.out.println("HERE");
            return null;
        }
    }

    public static void main(String[] args) {

        Data data = SchipolDAO.getData();

        System.out.println(data.getFlights().getFirst().aircraftType().iataMain());


            ObjectMapper mapper = new ObjectMapper();

        try {
            String objAsJSON = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(data);
            System.out.println(objAsJSON);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

