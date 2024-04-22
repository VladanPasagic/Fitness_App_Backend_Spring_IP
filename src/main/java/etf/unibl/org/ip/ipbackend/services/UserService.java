package etf.unibl.org.ip.ipbackend.services;

import etf.unibl.org.ip.ipbackend.models.dtos.User;
import etf.unibl.org.ip.ipbackend.models.requests.ProfileUpdateRequest;
import etf.unibl.org.ip.ipbackend.models.requests.RegistrationRequest;

import java.io.IOException;
import java.util.List;

public interface UserService {
    void register(RegistrationRequest request) throws IOException;

    void updateProfile(Integer id, ProfileUpdateRequest request) throws IOException;

    List<User> getAllUsers();
}
