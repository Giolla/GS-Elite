package com.gs.GSElite.service;

import com.gs.GSElite.model.Ship;
import com.gs.GSElite.repository.ShipRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ShipServiceTest {

    @Mock
    private ShipRepository shipRepository;

    @InjectMocks
    private ShipService shipService;

    @Test
    void getAllShips_delegatesToRepository() throws Exception {
        Ship ship = new Ship();
        ship.setId("anaconda");
        when(shipRepository.findAll()).thenReturn(List.of(ship));

        List<Ship> result = shipService.getAllShips();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getId()).isEqualTo("anaconda");
        verify(shipRepository).findAll();
    }

    @Test
    void getShipById_returnsShip_whenFound() throws Exception {
        Ship ship = new Ship();
        ship.setId("anaconda");
        when(shipRepository.findById("anaconda")).thenReturn(Optional.of(ship));

        Optional<Ship> result = shipService.getShipById("anaconda");

        assertThat(result).isPresent();
        assertThat(result.get().getId()).isEqualTo("anaconda");
        verify(shipRepository).findById("anaconda");
    }

    @Test
    void getShipById_returnsEmpty_whenNotFound() throws Exception {
        when(shipRepository.findById("unknown")).thenReturn(Optional.empty());

        Optional<Ship> result = shipService.getShipById("unknown");

        assertThat(result).isEmpty();
        verify(shipRepository).findById("unknown");
    }
}
