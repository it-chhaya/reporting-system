package co.istad.reporting.features.system.auth.dto;

public record AuthResponse(
        String accessToken,
        String refreshToken
) {
}
