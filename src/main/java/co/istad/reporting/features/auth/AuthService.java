package co.istad.reporting.features.auth;

import co.istad.reporting.features.auth.dto.AuthResponse;
import co.istad.reporting.features.auth.dto.RefreshTokenRequest;

public interface AuthService {

    AuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

    AuthResponse login(String username, String password);
}
