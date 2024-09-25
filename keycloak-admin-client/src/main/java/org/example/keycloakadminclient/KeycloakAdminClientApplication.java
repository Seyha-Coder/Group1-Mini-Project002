package org.example.keycloakadminclient;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@OpenAPIDefinition(
        info = @Info(title = "Keycloak-Admin-Client",
                version = "1.0",
                description = "Documentation Keycloak-Admin-Client v1.0"),
        security = {
                @SecurityRequirement(name = "oauth")
        },
        servers = @Server(url = "/")
)
@SecurityScheme(
        name = "oauth",
        type = SecuritySchemeType.OAUTH2,
        flows = @OAuthFlows(
                clientCredentials = @OAuthFlow(
                        tokenUrl ="http://localhost:8080/realms/Mini-Project002/protocol/openid-connect/token"
                ),
                password = @OAuthFlow(
                        tokenUrl ="http://localhost:8080/realms/Mini-Project002/protocol/openid-connect/token"
                )
        ),
        in = SecuritySchemeIn.HEADER
)

public class KeycloakAdminClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(KeycloakAdminClientApplication.class, args);
    }

}
