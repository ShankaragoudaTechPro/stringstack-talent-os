package com.stringstack.talentos.repository;

import com.stringstack.talentos.entity.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrainerRepository extends JpaRepository<Trainer, Long> {

    boolean existsByTrainerCode(String trainerCode);

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

    Optional<Trainer> findByEmail(String email);

}