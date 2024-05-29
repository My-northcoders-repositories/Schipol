package dao;

import Packate.Data;
import com.fasterxml.jackson.core.JsonProcessingException;
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

    public static Object getData() {

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

            Data data = mapper.readValue(response.body(), Data.class);
            return data;
//            var obj = mapper.readValue(response.body(), Object.class);
//            return obj;
        } catch (URISyntaxException | InterruptedException | IOException e) {
            e.printStackTrace();
            System.out.println("HERE");
            return null;
        }
    }

    public static void main(String[] args) {
        //List<Flight> data = SchipolDAO.getData().flights();
        var obj = SchipolDAO.getData();
        //System.out.println(data.get(0));
        ObjectMapper mapper = new ObjectMapper();

        try {
//            String objAsJSON = mapper.writeValueAsString(obj);
            String objAsJSON = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
            System.out.println(objAsJSON);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

