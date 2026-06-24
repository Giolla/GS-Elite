package com.gs.GSElite.service;

import com.gs.GSElite.model.SongGroup;
import com.gs.GSElite.repository.SongGroupRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SongServiceTest {

    @Mock
    private SongGroupRepository songGroupRepository;

    @InjectMocks
    private SongService songService;

    @Test
    void getAllSongGroups_delegatesToRepository() throws Exception {
        SongGroup group = new SongGroup();
        group.setId("woob");
        when(songGroupRepository.findAll()).thenReturn(List.of(group));

        List<SongGroup> result = songService.getAllSongGroups();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getId()).isEqualTo("woob");
        verify(songGroupRepository).findAll();
    }
}
