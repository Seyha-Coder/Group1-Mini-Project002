package org.example.taskservice.config;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    @Bean
    public RequestInterceptor requestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate requestTemplate) {
                // Get the access token from the Keycloak service
             // Pass the realm name as needed

                String  token = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJ4TWg5X25TeUNKMUE0dnRWNFU3bU1vczhjR1JCcXhxcDhZaHc5UkRUcnhRIn0.eyJleHAiOjE3MjcyNTE3ODUsImlhdCI6MTcyNzI1MTQ4NSwianRpIjoiNDk5MzkyMWUtMjQxMS00MDIzLWJjM2EtNmE2ODIyOWQxYTE5IiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwL3JlYWxtcy9NaW5pLVByb2plY3QwMDIiLCJhdWQiOlsicmVhbG0tbWFuYWdlbWVudCIsImFjY291bnQiXSwic3ViIjoiNDYzYmQyZmEtYTliYS00NzM5LWE4MTMtNGVlNTYzMTA3YTNlIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoibWluaS1wcm9qZWN0IiwiYWNyIjoiMSIsImFsbG93ZWQtb3JpZ2lucyI6WyIqIl0sInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJvZmZsaW5lX2FjY2VzcyIsImRlZmF1bHQtcm9sZXMtbWluaS1wcm9qZWN0MDAyIiwidW1hX2F1dGhvcml6YXRpb24iXX0sInJlc291cmNlX2FjY2VzcyI6eyJyZWFsbS1tYW5hZ2VtZW50Ijp7InJvbGVzIjpbInZpZXctaWRlbnRpdHktcHJvdmlkZXJzIiwidmlldy1yZWFsbSIsIm1hbmFnZS1pZGVudGl0eS1wcm92aWRlcnMiLCJpbXBlcnNvbmF0aW9uIiwicmVhbG0tYWRtaW4iLCJjcmVhdGUtY2xpZW50IiwibWFuYWdlLXVzZXJzIiwicXVlcnktcmVhbG1zIiwidmlldy1hdXRob3JpemF0aW9uIiwicXVlcnktY2xpZW50cyIsInF1ZXJ5LXVzZXJzIiwibWFuYWdlLWV2ZW50cyIsIm1hbmFnZS1yZWFsbSIsInZpZXctZXZlbnRzIiwidmlldy11c2VycyIsInZpZXctY2xpZW50cyIsIm1hbmFnZS1hdXRob3JpemF0aW9uIiwibWFuYWdlLWNsaWVudHMiLCJxdWVyeS1ncm91cHMiXX0sIm1pbmktcHJvamVjdCI6eyJyb2xlcyI6WyJ1bWFfcHJvdGVjdGlvbiJdfSwiYWNjb3VudCI6eyJyb2xlcyI6WyJtYW5hZ2UtYWNjb3VudCIsIm1hbmFnZS1hY2NvdW50LWxpbmtzIiwidmlldy1wcm9maWxlIl19fSwic2NvcGUiOiJlbWFpbCBwcm9maWxlIiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJjbGllbnRIb3N0IjoiMTcyLjE5LjAuMSIsInByZWZlcnJlZF91c2VybmFtZSI6InNlcnZpY2UtYWNjb3VudC1taW5pLXByb2plY3QiLCJjbGllbnRBZGRyZXNzIjoiMTcyLjE5LjAuMSIsImNsaWVudF9pZCI6Im1pbmktcHJvamVjdCJ9.eSaWyCj4rSb4xpiawNkVWzAgVB-O_gk8hFa5R_uFxjRDdLoZnkqBEJbG6MMhh0Qj6Sh_lUnv0UCKvciCovzTAHj4QGybN1LjNP7eikC1OzOBGf6r6fo7s_ec8VTw9AgYX0osYRfYH2khRVp_VZjIGNQPikjc3qBl0c7g9lcdxhMOyYzSi-Fr3lNttgidP3Ov6jf4T75l99l9Sr6O45Sl3KvAlQ8mQPeEilQ0Bo3Mgv8XThd8sj76ptso8pNII-UUTmkTPCMU6myFVi8J91Uay1POTOFH4so4VDkGDESaqP-QiUVL4LiqbRM884CHl_Ch_3YwBVYioD5lrTXnxup2FA"
                ;
                requestTemplate.header("Authorization", "Bearer " + token);
            }
        };
    }
}

