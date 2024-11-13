package co.istad.reporting.features.auth;

import co.istad.reporting.features.auth.dto.AuthResponse;

public interface AuthService {

    AuthResponse login(String username, String password);

}
