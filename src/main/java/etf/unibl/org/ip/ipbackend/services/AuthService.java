package etf.unibl.org.ip.ipbackend.services;

import etf.unibl.org.ip.ipbackend.models.requests.LoginRequest;

public interface AuthService {
    public void login(LoginRequest request);
}
