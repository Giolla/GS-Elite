package com.gs.GSElite.service;

import com.gs.GSElite.model.Ship;
import com.gs.GSElite.repository.ShipRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Service
public class ShipService {

    private final ShipRepository shipRepository;

    public ShipService(ShipRepository shipRepository) {
        this.shipRepository = shipRepository;
    }

    public List<Ship> getAllShips() throws ExecutionException, InterruptedException {
        return shipRepository.findAll();
    }

    public Optional<Ship> getShipById(String id) throws ExecutionException, InterruptedException {
        return shipRepository.findById(id);
    }
}
