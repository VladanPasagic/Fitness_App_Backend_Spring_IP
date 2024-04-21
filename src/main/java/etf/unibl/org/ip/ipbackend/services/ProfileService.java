package etf.unibl.org.ip.ipbackend.services;

import etf.unibl.org.ip.ipbackend.models.dtos.Profile;

public interface ProfileService {
    Profile getProfile(Integer id);
}
