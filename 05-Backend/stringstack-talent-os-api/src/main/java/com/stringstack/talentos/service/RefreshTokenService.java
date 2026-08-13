package com.stringstack.talentos.service;

import com.stringstack.talentos.dto.auth.RefreshTokenResponse;
import com.stringstack.talentos.entity.RefreshToken;
import com.stringstack.talentos.entity.User;

public interface RefreshTokenService {

    RefreshToken createRefreshToken(User user);

    RefreshToken verifyRefreshToken(String token);

    RefreshTokenResponse refreshAccessToken(String refreshToken);

    void revokeRefreshToken(String token);

    void revokeAllUserTokens(User user);

}