package dev.kearls.autocomplete;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MyRestController {
    private final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();
    private static final String API_KEY = System.getenv("GOOGLE_MAPS_API_KEY");
    ObjectMapper objectMapper = new ObjectMapper();

    /**
     * NOTE: I *think* we should be able to get this from the Google Maps Places API
     * (https://developers.google.com/maps/documentation/places/web-service/autocomplete)
     * but so far I haven't been able to get that to work.
     *
     * @param partial
     * @return
     */
    @GetMapping("/{partial}")
    public List<String> auto(@PathVariable String partial) {
        // FIXME build a cache!
        var theURI = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("maps.googleapis.com")
                .pathSegment("maps", "api", "place", "autocomplete", "json")
                .queryParam("input", partial)
                .queryParam("types", "geocode")
                .queryParam("key", API_KEY)
                .build()
                .toUri();

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(theURI)
                .setHeader("User-Agent", "Java 11 HttpClient Bot")  // FIXME do we need this?
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode());  // FIXME check this and log an error if not 2xx

            JsonNode arrayNode = objectMapper.readTree(response.body());
            JsonNode predictions = arrayNode.get("predictions");

            var placeNames = new ArrayList<String>();
            for (JsonNode prediction : predictions) {
                placeNames.add(prediction.get("description").textValue());
            }
            return placeNames;
        } catch (Exception e) {
            // FIXME log an error, don't throw an exception
            throw new RuntimeException(e);
        }
    }
}
