package com.stringstack.talentos.repository;

import com.stringstack.talentos.entity.PasswordResetToken;
import com.stringstack.talentos.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    Optional<PasswordResetToken> findByToken(String token);

    Optional<PasswordResetToken> findByUser(User user);

    void deleteByToken(String token);

    void deleteByUser(User user);
}