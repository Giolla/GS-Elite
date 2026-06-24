package com.gs.GSElite.controller;

import com.gs.GSElite.model.Ship;
import com.gs.GSElite.service.ShipService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ShipController.class)
class ShipControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ShipService shipService;

    @Test
    void getAllShips_returnsListOfShips() throws Exception {
        Ship ship = new Ship();
        ship.setId("anaconda");
        ship.setTitle("Anaconda");
        when(shipService.getAllShips()).thenReturn(List.of(ship));

        mockMvc.perform(get("/ships"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("anaconda"))
                .andExpect(jsonPath("$[0].title").value("Anaconda"));
    }

    @Test
    void getShipById_returnsShip_whenFound() throws Exception {
        Ship ship = new Ship();
        ship.setId("anaconda");
        ship.setTitle("Anaconda");
        when(shipService.getShipById("anaconda")).thenReturn(Optional.of(ship));

        mockMvc.perform(get("/ships/anaconda"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("anaconda"))
                .andExpect(jsonPath("$.title").value("Anaconda"));
    }

    @Test
    void getShipById_returns404_whenNotFound() throws Exception {
        when(shipService.getShipById("unknown")).thenReturn(Optional.empty());

        mockMvc.perform(get("/ships/unknown"))
                .andExpect(status().isNotFound());
    }
}
