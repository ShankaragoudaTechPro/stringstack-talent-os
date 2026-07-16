package com.stringstack.talentos.repository;

import com.stringstack.talentos.entity.Placement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlacementRepository extends JpaRepository<Placement, Long> {

    boolean existsByPlacementCode(String placementCode);

    Optional<Placement> findByPlacementCode(String placementCode);

}