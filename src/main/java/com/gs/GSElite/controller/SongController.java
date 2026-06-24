package com.gs.GSElite.controller;

import com.gs.GSElite.model.SongGroup;
import com.gs.GSElite.service.SongService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/songs")
public class SongController {

    private final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping
    public List<SongGroup> getAllSongGroups() throws ExecutionException, InterruptedException {
        return songService.getAllSongGroups();
    }
}
