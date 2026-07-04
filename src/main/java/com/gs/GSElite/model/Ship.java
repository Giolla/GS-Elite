package com.gs.GSElite.model;

import com.google.cloud.firestore.annotation.DocumentId;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
public class Ship {
    @DocumentId
    private String id;
    private String title;
    private String header;
    private String description;
    private String videoUrl;
    private String backgroundImage;
    private String schematic;
    private List<String> screenShots;
}
