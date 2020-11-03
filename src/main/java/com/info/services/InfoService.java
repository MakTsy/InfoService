package com.info.services;

import com.info.entities.Flight;
import com.info.repo.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class InfoService {
    private final FlightRepository flightRepository;

    @Autowired
    public InfoService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Transactional
    public List<Flight> getInformationAboutFlights() {
        List<Flight> flights = this.flightRepository.findAll();
        return flights;
    }

    @Transactional
    public void addFlight(Flight flight) {
        this.flightRepository.save(flight);
    }
}
