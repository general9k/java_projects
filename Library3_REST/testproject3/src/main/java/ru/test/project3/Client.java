package ru.test.project3;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Client {
    public static void main(String[] args) {
        final String sensorName = "TEST";
        registrationSensor(sensorName);

        Random random = new Random();

        double maxTemp = 40.0;

        for (int i = 0; i < 500; i++) {
            System.out.println(i);
            registrationMeasure(random.nextDouble() * maxTemp,
                    random.nextBoolean(), sensorName);
        }
    }


    private static void registrationSensor(String sensorName) {
        final String url = "http://localhost:8080/sensors/registration";

        Map<String, Object> jsonData = new HashMap<>();

        jsonData.put("name", sensorName);

        makePostRequest(url, jsonData);
    }

    private static void registrationMeasure(double value, boolean raining, String sensorName) {
        final String url = "http://localhost:8080/measurements/add";

        Map<String, Object> jsonData = new HashMap<>();

        jsonData.put("value", value);
        jsonData.put("raining", raining);
        jsonData.put("sensor", Map.of("name", sensorName));

        makePostRequest(url, jsonData);
    }

    private static void makePostRequest(String url, Map<String, Object> jsonData) {
        final RestTemplate restTemplate = new RestTemplate();

        final HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> request = new HttpEntity<>(jsonData, headers);

        try {
            restTemplate.postForObject(url, request, String.class);
            System.out.println("Запрос отправлен");
        } catch (HttpClientErrorException e) {
            System.out.println("Ошибка запроса");
            System.out.println(e.getMessage());
        }
    }
}
