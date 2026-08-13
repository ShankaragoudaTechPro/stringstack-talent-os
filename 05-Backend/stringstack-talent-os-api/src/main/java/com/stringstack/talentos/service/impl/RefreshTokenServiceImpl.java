package com.stringstack.talentos.service.impl;

import com.stringstack.talentos.dto.auth.RefreshTokenResponse;
import com.stringstack.talentos.entity.RefreshToken;
import com.stringstack.talentos.entity.User;
import com.stringstack.talentos.exception.ResourceNotFoundException;
import com.stringstack.talentos.repository.RefreshTokenRepository;
import com.stringstack.talentos.security.jwt.JwtService;
import com.stringstack.talentos.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private static final long REFRESH_TOKEN_EXPIRY_DAYS = 7;

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtService jwtService;

    @Override
    public RefreshToken createRefreshToken(User user) {

        refreshTokenRepository.findByUser(user)
                .ifPresent(refreshTokenRepository::delete);

        RefreshToken refreshToken = RefreshToken.builder()
                .token(UUID.randomUUID().toString())
                .user(user)
                .expiryDate(LocalDateTime.now().plusDays(REFRESH_TOKEN_EXPIRY_DAYS))
                .revoked(false)
                .build();

        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public RefreshToken verifyRefreshToken(String token) {

        RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Refresh token not found."));

        if (refreshToken.isRevoked()) {
            throw new IllegalArgumentException("Refresh token has been revoked.");
        }

        if (refreshToken.getExpiryDate().isBefore(LocalDateTime.now())) {

            refreshTokenRepository.delete(refreshToken);

            throw new IllegalArgumentException("Refresh token has expired.");
        }

        return refreshToken;
    }

    @Override
    public RefreshTokenResponse refreshAccessToken(String refreshToken) {

        RefreshToken token = verifyRefreshToken(refreshToken);

        String accessToken = jwtService.generateToken(token.getUser().getEmail());

        return RefreshTokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(token.getToken())
                .tokenType("Bearer")
                .build();
    }

    @Override
    public void revokeRefreshToken(String token) {

        RefreshToken refreshToken = verifyRefreshToken(token);

        refreshToken.setRevoked(true);

        refreshTokenRepository.save(refreshToken);
    }

    @Override
    public void revokeAllUserTokens(User user) {

        refreshTokenRepository.findByUser(user)
                .ifPresent(refreshToken -> {

                    refreshToken.setRevoked(true);

                    refreshTokenRepository.save(refreshToken);
                });
    }
}