package etf.unibl.org.ip.ipbackend.services;

import etf.unibl.org.ip.ipbackend.models.requests.RegistrationRequest;

public interface UserService {
    public void register(RegistrationRequest request);
}
