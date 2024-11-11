package co.istad.reporting.security;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

@Configuration
public class JwtConfig {

    @Bean
    KeyPair accessTokenKeyPair() {
        try {
            KeyPairGenerator generator = KeyPairGenerator
                    .getInstance("RSA");
            generator.initialize(2048);
            return generator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    RSAKey accessTokenRSAKey(KeyPair accessTokenKeyPair) {
        return new RSAKey.Builder((RSAPublicKey) accessTokenKeyPair.getPublic())
                .privateKey(accessTokenKeyPair.getPrivate())
                .keyID(UUID.randomUUID().toString())
                .build();
    }

    @Bean
    JwtDecoder accessTokenJwtDecoder(RSAKey accessTokenRSAKey) {
        try {
            return NimbusJwtDecoder
                    .withPublicKey(accessTokenRSAKey.toRSAPublicKey())
                    .build();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }

}
