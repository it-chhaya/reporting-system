package co.istad.reporting.features.system.auth;

import co.istad.reporting.features.system.auth.dto.AuthResponse;
import co.istad.reporting.features.system.auth.dto.RefreshTokenRequest;

public interface AuthService {

    AuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

    AuthResponse login(String username, String password);
}
