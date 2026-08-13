package com.stringstack.talentos.service;

public interface EmailService {

    void sendPasswordResetEmail(String to, String token);

}