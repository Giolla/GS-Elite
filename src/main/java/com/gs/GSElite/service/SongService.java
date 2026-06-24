package com.gs.GSElite.service;

import com.gs.GSElite.model.SongGroup;
import com.gs.GSElite.repository.SongGroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class SongService {

    private final SongGroupRepository songGroupRepository;

    public SongService(SongGroupRepository songGroupRepository) {
        this.songGroupRepository = songGroupRepository;
    }

    public List<SongGroup> getAllSongGroups() throws ExecutionException, InterruptedException {
        return songGroupRepository.findAll();
    }
}
