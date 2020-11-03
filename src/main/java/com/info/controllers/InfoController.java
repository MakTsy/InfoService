package com.info.controllers;

import com.google.gson.Gson;
import com.info.services.InfoService;
import com.info.services.UpdaterData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("info")
public class InfoController {
    private final InfoService infoService;
    private final UpdaterData updaterData;

    @Autowired
    public InfoController(InfoService infoService, UpdaterData updaterData) {
        this.infoService = infoService;
        this.updaterData = updaterData;
    }

    @GetMapping(value = "/allFlights", produces = "application/json")
    public ResponseEntity<String> getFlights() {
        Gson json = new Gson();
        this.updaterData.updateDB();
        String ans = json.toJson(this.infoService.getInformationAboutFlights());
        return new ResponseEntity<String>(ans, HttpStatus.OK);
    }

}
