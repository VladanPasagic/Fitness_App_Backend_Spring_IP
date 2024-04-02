package etf.unibl.org.ip.ipbackend.configurations;

import etf.unibl.org.ip.ipbackend.configurations.services.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JWTAuthFilterConfiguration {
    private final JWTService jwtService;
}
