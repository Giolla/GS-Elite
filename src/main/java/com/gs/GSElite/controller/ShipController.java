package com.gs.GSElite.controller;

import com.gs.GSElite.model.Ship;
import com.gs.GSElite.service.ShipService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/ships")
public class ShipController {

    private final ShipService shipService;

    public ShipController(ShipService shipService) {
        this.shipService = shipService;
    }

    @GetMapping
    public List<Ship> getAllShips() throws ExecutionException, InterruptedException {
        return shipService.getAllShips();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ship> getShipById(@PathVariable String id)
            throws ExecutionException, InterruptedException {
        return shipService.getShipById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
