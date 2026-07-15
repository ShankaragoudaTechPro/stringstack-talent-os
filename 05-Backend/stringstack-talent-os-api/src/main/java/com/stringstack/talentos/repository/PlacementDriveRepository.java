package com.stringstack.talentos.repository;

import com.stringstack.talentos.entity.PlacementDrive;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlacementDriveRepository
        extends JpaRepository<PlacementDrive, Long> {

    boolean existsByDriveCode(String driveCode);

    Optional<PlacementDrive> findByDriveCode(String driveCode);

}