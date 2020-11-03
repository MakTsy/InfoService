package com.info.services;

import com.google.gson.*;
import com.info.entities.Flight;
import com.info.entities.dto.FlightDTO;
import com.info.services.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class UpdaterData {
    private final InfoService infoService;
    private static final String URL = "http://localhost:8082";
    private static final RestTemplate restTemplate = new RestTemplate();
    private static final HttpHeaders headers = new HttpHeaders();
    private static final HttpEntity<Object> headersEntity = new HttpEntity<>(headers);
    Gson gson = new GsonBuilder().disableHtmlEscaping().create();

    @Autowired
    public UpdaterData(InfoService infoService) {
        this.infoService = infoService;
    }

    @Transactional
    public void updateDB() {
        ResponseEntity<String> response = restTemplate.getForEntity(URL + "/flights/getFlights", String.class);
        JsonArray flightDTOs = gson.fromJson(response.getBody(), JsonArray.class);
        for (JsonElement fl : flightDTOs) {
            Flight flightDTO = gson.fromJson(fl, Flight.class);
            infoService.addFlight(flightDTO);
        }
    }


}
