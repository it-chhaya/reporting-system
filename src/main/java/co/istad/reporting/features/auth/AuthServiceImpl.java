package co.istad.reporting.features.auth;

import co.istad.reporting.features.auth.dto.AuthResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final DaoAuthenticationProvider daoAuth;
    private final JwtEncoder accessTokenJwtEncoder;

    @Override
    public AuthResponse login(String username, String password) {

        Authentication auth =
                new UsernamePasswordAuthenticationToken(
                        username, password
                );
        auth = daoAuth.authenticate(auth);

        // Generate SCOPES
        // user:write user:read
        String scope = auth
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        log.info("Scope: {}", scope);

        Instant now = Instant.now();

        // Create JWT Token
        JwtClaimsSet accessTokenClaimsSet = JwtClaimsSet.builder()
                .id(auth.getName())
                .subject("Access APIs")
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.MINUTES))
                .claim("scope", scope)
                .build();

        JwtEncoderParameters accessTokenParameters =
                JwtEncoderParameters.from(accessTokenClaimsSet);

        String accessToken = accessTokenJwtEncoder
                .encode(accessTokenParameters)
                .getTokenValue();

        return new AuthResponse(accessToken);
    }

}
