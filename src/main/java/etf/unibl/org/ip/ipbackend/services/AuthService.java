package etf.unibl.org.ip.ipbackend.services;

import etf.unibl.org.ip.ipbackend.models.requests.LoginRequest;
import etf.unibl.org.ip.ipbackend.models.responses.LoginResponse;

public interface AuthService {
    public LoginResponse login(LoginRequest request);
}
