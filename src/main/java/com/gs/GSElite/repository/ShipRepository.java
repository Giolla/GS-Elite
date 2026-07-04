package com.gs.GSElite.repository;

import com.google.cloud.firestore.Firestore;
import com.gs.GSElite.model.Ship;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Repository
public class ShipRepository {

    private final Firestore firestore;

    public ShipRepository(Firestore firestore) {
        this.firestore = firestore;
    }

    public List<Ship> findAll() throws ExecutionException, InterruptedException {
        return firestore.collection("ships").get().get().getDocuments().stream()
                .map(doc -> doc.toObject(Ship.class))
                .collect(Collectors.toList());
    }

    public Optional<Ship> findById(String id) throws ExecutionException, InterruptedException {
        var doc = firestore.collection("ships").document(id).get().get();
        if (!doc.exists()) {
            return Optional.empty();
        }
        return Optional.ofNullable(doc.toObject(Ship.class));
    }
}
