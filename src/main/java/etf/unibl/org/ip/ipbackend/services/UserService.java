package etf.unibl.org.ip.ipbackend.services;

import etf.unibl.org.ip.ipbackend.models.dtos.Profile;
import etf.unibl.org.ip.ipbackend.models.requests.ProfileUpdateRequest;
import etf.unibl.org.ip.ipbackend.models.requests.RegistrationRequest;

import java.io.IOException;

public interface UserService {
    public void register(RegistrationRequest request) throws IOException;

    public void updateProfile(ProfileUpdateRequest request);

    public Profile getProfile(Integer id);
}
