package etf.unibl.org.ip.ipbackend.services;

import etf.unibl.org.ip.ipbackend.models.dtos.Profile;
import etf.unibl.org.ip.ipbackend.models.requests.ProfileUpdateRequest;
import etf.unibl.org.ip.ipbackend.models.requests.RegistrationRequest;

public interface UserService {
    public void register(RegistrationRequest request);

    public void updateProfile(ProfileUpdateRequest request);

    public Profile getProfile(Integer id);
}
