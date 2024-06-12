package com.martinm.baconator.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;
import java.util.*;

@Service
public class BaconService {

  private final HttpClient httpClient;
  private final ObjectMapper objectMapper;

  public BaconService() {
    this.httpClient = HttpClient.newHttpClient();
    this.objectMapper = new ObjectMapper();
  }

  public Map<String, Object> getBaconData(int howmuch) {
    Map<String, Object> response = new HashMap<>();

    List<Map<String, Object>> items = new ArrayList<>();
    String runId = UUID.randomUUID().toString();
    long globalStart = Instant.now().toEpochMilli();

    response.put("runId", runId);
    response.put("start", globalStart);

    for (int i = 0; i < howmuch; i++) {
      long start = Instant.now().toEpochMilli();
      List<String> data = fetchBaconData();
      long end = Instant.now().toEpochMilli();

      Map<String, Object> item = new HashMap<>();
      item.put("start", start);
      item.put("end", end);
      item.put("duration", formatDuration(end - start));
      item.put("data", data);

      items.add(item);
    }

    long globalEnd = Instant.now().toEpochMilli();
    response.put("end", globalEnd);
    response.put("items", items);

    return response;
  }

  private List<String> fetchBaconData() {
    try {
      HttpRequest request = HttpRequest.newBuilder()
          .uri(new URI("https://baconipsum.com/api/?type=all-meat&paras=1"))
          .GET()
          .build();

      HttpResponse<String> response = httpClient.send(request,
          HttpResponse.BodyHandlers.ofString());

      return objectMapper.readValue(response.body(), List.class);

    } catch (Exception e) {
      e.printStackTrace();
      return Collections.emptyList();
    }
  }

  private String formatDuration(long durationMs) {
    long hours = (durationMs / 3600000) % 24;
    long minutes = (durationMs / 60000) % 60;
    long seconds = (durationMs / 1000) % 60;
    long milliseconds = durationMs % 1000;

    return String.format("%dh %dm %ds %dms", hours, minutes, seconds, milliseconds);
  }
}