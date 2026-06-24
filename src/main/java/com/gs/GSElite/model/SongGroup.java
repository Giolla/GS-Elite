package com.gs.GSElite.model;

import com.google.cloud.firestore.annotation.DocumentId;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
public class SongGroup {
    @DocumentId
    private String id;
    private String name;
    private boolean disabled;
    private int order;
    private List<Song> songs;
}
