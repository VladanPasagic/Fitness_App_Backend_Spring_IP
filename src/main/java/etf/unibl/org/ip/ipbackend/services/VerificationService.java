package etf.unibl.org.ip.ipbackend.services;

import etf.unibl.org.ip.ipbackend.models.entities.TraineeEntity;

public interface VerificationService {
    void verify(String token);

    void createToken(TraineeEntity trainee);
}
