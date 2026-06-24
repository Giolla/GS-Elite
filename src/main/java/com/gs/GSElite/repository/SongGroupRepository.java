package com.gs.GSElite.repository;

import com.google.cloud.firestore.Firestore;
import com.gs.GSElite.model.SongGroup;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Repository
public class SongGroupRepository {

    private final Firestore firestore;

    public SongGroupRepository(Firestore firestore) {
        this.firestore = firestore;
    }

    public List<SongGroup> findAll() throws ExecutionException, InterruptedException {
        return firestore.collection("songGroups").get().get().getDocuments().stream()
                .map(doc -> doc.toObject(SongGroup.class))
                .sorted(Comparator.comparingInt(SongGroup::getOrder))
                .collect(Collectors.toList());
    }
}
