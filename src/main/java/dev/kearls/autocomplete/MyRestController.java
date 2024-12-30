package dev.kearls.autocomplete;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MyRestController {
//    private final HttpClient httpClient = HttpClient.newBuilder()
//            .version(HttpClient.Version.HTTP_2)
//            .build();
//    private static final String API_KEY = System.getenv("GOOGLE_MAPS_API_KEY");

    @GetMapping("/{partial}")
    public List<String> auto(@PathVariable String partial) {

        var places = List.of("Ipswich, UK"
                , "Ipswich QLD, Australia"
                , "Ipswich, MA, USA"
                , "Ipswich Train Station, A137, Ipswich, UK"
                , "Ipswich, SD, USA");
        System.out.println(">>>>> Partial was " + partial);
        // "https://maps.googleapis.com/maps/api/place/autocomplete/json?input=Ipswich&types=geocode&key=$GOOGLE_MAPS_API_KEY"


//        var theURI = UriComponentsBuilder.newInstance()
//                .scheme("https")
//                .host("maps.googleapis.com")
//                .path("maps")
//                .path("api")
//                .path("place")
//                .path("autocomplete")
//                .path("json")
//                .queryParam("input", "Ipswich")  // this is where the input will go
//                .queryParam("types", "geocode")
//                .queryParam("key", "API_KEY goes here")
//                .build()
//                .toUri();
//
//        System.out.println("URI: "  + theURI.toString());

//        HttpRequest request = HttpRequest.newBuilder()
//                .GET()
//                .uri(theURI)
//                .setHeader("User-Agent", "Java 11 HttpClient Bot")
//                .build();
//
//        HttpResponse<String> response = null;
//        try {
//            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//
//        // print status code
//        System.out.println(response.statusCode());
//
//        // print response body
//        System.out.println(response.body());

        return places;
    }
}
