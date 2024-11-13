package co.istad.reporting.features.auth.dto;

public record LoginRequest(
        String username,
        String password
) {
}
