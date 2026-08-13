package com.stringstack.talentos.service.impl;

import com.stringstack.talentos.dto.auth.ChangePasswordRequest;
import com.stringstack.talentos.dto.auth.ForgotPasswordRequest;
import com.stringstack.talentos.dto.auth.LoginRequest;
import com.stringstack.talentos.dto.auth.LoginResponse;
import com.stringstack.talentos.dto.auth.LogoutRequest;
import com.stringstack.talentos.dto.auth.RefreshTokenRequest;
import com.stringstack.talentos.dto.auth.RefreshTokenResponse;
import com.stringstack.talentos.dto.auth.ResetPasswordRequest;
import com.stringstack.talentos.entity.PasswordResetToken;

import com.stringstack.talentos.entity.RefreshToken;
import com.stringstack.talentos.entity.User;
import com.stringstack.talentos.exception.UnauthorizedException;
import com.stringstack.talentos.repository.PasswordResetTokenRepository;
import com.stringstack.talentos.repository.UserRepository;
import com.stringstack.talentos.security.jwt.JwtService;
import com.stringstack.talentos.service.AuthService;
import com.stringstack.talentos.service.EmailService;
import com.stringstack.talentos.service.RefreshTokenService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final RefreshTokenService refreshTokenService;

    @Override
    public LoginResponse login(LoginRequest request) {

        User user = userRepository
                .findByUsernameOrEmail(
                        request.getUsername(),
                        request.getUsername())
                .orElseThrow(() ->
                        new UnauthorizedException("Invalid Username or Email"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new UnauthorizedException("Invalid Password");
        }

        // Revoke previous refresh token (Single Device Login)
        refreshTokenService.revokeAllUserTokens(user);

        // Generate Access Token
        String accessToken = jwtService.generateToken(user.getEmail());

        // Generate Refresh Token
        RefreshToken refreshToken =
                refreshTokenService.createRefreshToken(user);

        return LoginResponse.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .accessToken(accessToken)
                .refreshToken(refreshToken.getToken())
                .tokenType("Bearer")
                .build();
    }

    @Override
    public void forgotPassword(ForgotPasswordRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new UnauthorizedException("User not found with this email."));

        passwordResetTokenRepository.findByUser(user)
                .ifPresent(passwordResetTokenRepository::delete);

        String token = UUID.randomUUID().toString();

        PasswordResetToken passwordResetToken = PasswordResetToken.builder()
                .token(token)
                .user(user)
                .expiryDate(LocalDateTime.now().plusMinutes(15))
                .build();

        passwordResetTokenRepository.save(passwordResetToken);

        emailService.sendPasswordResetEmail(
                user.getEmail(),
                token
        );
    }

    @Override
    public void resetPassword(ResetPasswordRequest request) {

        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            throw new IllegalArgumentException("New Password and Confirm Password do not match.");
        }

        PasswordResetToken resetToken = passwordResetTokenRepository
                .findByToken(request.getToken())
                .orElseThrow(() ->
                        new IllegalArgumentException("Invalid password reset token."));

        if (resetToken.getExpiryDate().isBefore(LocalDateTime.now())) {

            passwordResetTokenRepository.delete(resetToken);

            throw new IllegalArgumentException("Password reset token has expired.");
        }

        User user = resetToken.getUser();

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        userRepository.save(user);

        passwordResetTokenRepository.delete(resetToken);
    }

    @Override
    public void changePassword(String email, ChangePasswordRequest request) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UnauthorizedException("User not found."));

        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new UnauthorizedException("Old password is incorrect.");
        }

        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            throw new IllegalArgumentException("New Password and Confirm Password do not match.");
        }

        if (passwordEncoder.matches(request.getNewPassword(), user.getPassword())) {
            throw new IllegalArgumentException("New password cannot be same as old password.");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        userRepository.save(user);
    }

    @Override
    public RefreshTokenResponse refreshToken(RefreshTokenRequest request) {

        return refreshTokenService.refreshAccessToken(
                request.getRefreshToken()
        );
    }

    @Override
    public void logout(LogoutRequest request) {

        refreshTokenService.revokeRefreshToken(
                request.getRefreshToken()
        );
    }
}