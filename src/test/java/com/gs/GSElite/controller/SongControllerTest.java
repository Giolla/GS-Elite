package com.gs.GSElite.controller;

import com.gs.GSElite.model.Song;
import com.gs.GSElite.model.SongGroup;
import com.gs.GSElite.service.SongService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SongController.class)
class SongControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private SongService songService;

    @Test
    void getAllSongs_returnsListOfSongGroups() throws Exception {
        Song song = new Song();
        song.setName("Odonna");
        song.setUrl("https://storage.example.com/woob/odonna.mp3");

        SongGroup group = new SongGroup();
        group.setId("woob");
        group.setName("Woob");
        group.setSongs(List.of(song));

        when(songService.getAllSongGroups()).thenReturn(List.of(group));

        mockMvc.perform(get("/songs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("woob"))
                .andExpect(jsonPath("$[0].name").value("Woob"))
                .andExpect(jsonPath("$[0].songs[0].name").value("Odonna"));
    }
}
