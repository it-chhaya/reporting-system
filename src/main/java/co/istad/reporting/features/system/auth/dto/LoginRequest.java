package co.istad.reporting.features.system.auth.dto;

public record LoginRequest(
        String username,
        String password
) {
}
