//package com.elsewedyt.toolingapp.services;
//
//import com.elsewedyt.toolingapp.Logging.logging;
//
//import java.net.URI;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
//import java.net.http.HttpRequest.BodyPublishers;
//
//public class ApiCaller {
//    public static String callApi(String endpointUrl, String method, String jsonInput) {
//        try {
//            HttpClient client = HttpClient.newHttpClient();
//            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
//                    .uri(new URI(endpointUrl))
//                    .header("Content-Type", "application/json");
//
//            switch (method.toUpperCase()) {
//                case "POST":
//                    requestBuilder.POST(BodyPublishers.ofString(jsonInput));
//                    break;
//                case "PUT":
//                    requestBuilder.PUT(BodyPublishers.ofString(jsonInput));
//                    break;
//                case "GET":
//                    requestBuilder.GET();
//                    break;
//                case "DELETE":
//                    requestBuilder.DELETE();
//                    break;
//                default:
//                    throw new IllegalArgumentException("Unsupported method: " + method);
//            }
//
//            HttpRequest request = requestBuilder.build();
//            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//
//            return response.body();
//        } catch (Exception ex) {
//            logging.logException("ERROR", ApiCaller.class.getName(), "callApi", ex);
//            return "Error calling API: " + ex.getMessage();
//        }
//    }
//}
//
